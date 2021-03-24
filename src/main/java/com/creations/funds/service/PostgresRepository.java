package com.creations.funds.service;

import com.creations.funds.postgres.RepositoryJdbcDaoSupport;

public class PostgresRepository implements IRepository {

    private final RepositoryJdbcDaoSupport fJdbcDaoSupport;

    public PostgresRepository(RepositoryJdbcDaoSupport jdbcDaoSupport) {
        fJdbcDaoSupport = jdbcDaoSupport;
    }


}
