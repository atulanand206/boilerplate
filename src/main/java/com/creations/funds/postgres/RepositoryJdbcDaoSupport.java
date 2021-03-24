package com.creations.funds.postgres;

import com.creations.funds.jackson.Serializer;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class RepositoryJdbcDaoSupport extends JdbcDaoSupport {

    private final RepoConfig fRepoConfig;

    public RepositoryJdbcDaoSupport(DataSource dataSource,
                                    RepoConfig repoConfig) {
        setDataSource(dataSource);
        fRepoConfig = repoConfig;
    }

    public <T> void create(
            final T body,
            final Serializer<T> serializer) throws SQLException {
        StoredProcedureValidator.validateStoredProcedure(fRepoConfig.getCreateSproc());
        generateCallableStatementWithIdAndBody(UUID.randomUUID().toString(), body, fRepoConfig.getCreateSproc(), serializer, getConnection()).execute();
    }

    public <T> void createDeveloper(T body, Serializer<T> serializer) throws SQLException {
        StoredProcedureValidator.validateStoredProcedure(fRepoConfig.getCreateDeveloperSproc());
        generateCallableStatementWithIdAndBody(UUID.randomUUID().toString(), body, fRepoConfig.getCreateDeveloperSproc(), serializer, getConnection()).execute();
    }

    public <T> T get(
            final String name,
            final String sproc,
            final RowMapper<T> rowMapper) {
        StoredProcedureValidator.validateStoredProcedure(sproc);
        final PreparedStatementCreator getCallableStatement = (Connection connection) ->
                generateCallableStatementWithName(name, sproc, connection);
        final var items = getJdbcTemplate().query(getCallableStatement, rowMapper);
        final var item = items.stream().findFirst();
        if (item.isPresent())
            return item.get();
        throw new NoSuchElementException();
    }

    public <T> List<T> getAll(
            final String id,
            final String sproc,
            final RowMapper<T> rowMapper) {
        StoredProcedureValidator.validateStoredProcedure(sproc);
        final PreparedStatementCreator getCallableStatement = (Connection connection) ->
                generateCallableStatementWithId(id, sproc, connection);
        final var items = getJdbcTemplate().query(getCallableStatement, rowMapper);
        if (items.isEmpty())
            throw new NoSuchElementException();
        return items;
    }

    private <T> CallableStatement generateCallableStatementWithBody(
            final T body,
            final String sproc,
            final Serializer<T> serializer,
            final Connection connection) throws SQLException {
        final var pgObject = buildPgObject(serializer.serialize(body));
        final var sql = String.format("{call %s(?)}", sproc);
        final var cs = connection.prepareCall(sql);
        var param = 1;
        cs.setObject(param, pgObject);
        return cs;
    }

    private <T> CallableStatement generateCallableStatementWithIdAndBody(
            final String id,
            final T body,
            final String sproc,
            final Serializer<T> serializer,
            final Connection connection) throws SQLException {
        final var pgObject = buildPgObject(serializer.serialize(body));
        final var sql = String.format("{call %s(?, ?)}", sproc);
        final var cs = connection.prepareCall(sql);
        var param = 1;
        cs.setObject(param++, id);
        cs.setObject(param, pgObject);
        return cs;
    }

    private CallableStatement generateCallableStatementWithName(
            final String name,
            final String sproc,
            final Connection connection) throws SQLException {
        final var sql = String.format("{call %s(?)}", sproc);
        final var cs = connection.prepareCall(sql);
        cs.setObject(1, name);
        return cs;
    }

    private CallableStatement generateCallableStatementWithId(
            final String id,
            final String sproc,
            final Connection connection) throws SQLException {
        final var sql = String.format("{call %s(?)}", sproc);
        final var cs = connection.prepareCall(sql);
        cs.setObject(1, id);
        return cs;
    }

    private static PGobject buildPgObject(String requestJson) throws SQLException {
        final var jsonObject = new PGobject();
        jsonObject.setType("jsonb");
        jsonObject.setValue(requestJson);
        return jsonObject;
    }
}
