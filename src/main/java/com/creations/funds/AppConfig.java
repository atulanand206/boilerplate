package com.creations.funds;

import com.creations.funds.client.APIClient;
import com.creations.funds.gson.ISODateAdapter;
import com.creations.funds.models.Developer;
import com.creations.funds.models.Team;
import com.creations.funds.postgres.RepoConfig;
import com.creations.funds.postgres.RepositoryJdbcDaoSupport;
import com.creations.funds.service.IRepository;
import com.creations.funds.service.MFService;
import com.creations.funds.service.PostgresRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.RowMapper;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.Date;
import java.util.UUID;

@Configuration
@Import(DataSourceConfig.class)
public class AppConfig {

    @Bean
    MFService configureMFService(final APIClient apiClient, final IRepository mfRepository) {
        return new MFService(apiClient, mfRepository);
    }

    @Bean
    APIClient configureMFAPIClient(@Value("${send_alert_message.api.url}") final String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(new Gson().newBuilder()
                        .registerTypeAdapter(Date.class, new ISODateAdapter()).create()))
                .build().create(APIClient.class);
    }

    @Bean
    IRepository configureMFRepository(final RepositoryJdbcDaoSupport jdbcDaoSupport,
                                      final RowMapper<Team> rowMapper,
                                      RowMapper<Developer> developerRowMapper,
                                      final RepoConfig repoConfig) {
        return new PostgresRepository(jdbcDaoSupport, rowMapper, developerRowMapper, repoConfig);
    }

    @Bean
    RowMapper<Team> configureTeamRowMapper() {
        return (final ResultSet rs, final int rowCount) -> {
            final var id = rs.getObject("id", UUID.class);
            final var name = rs.getString("name");
            return new Team(id, name);
        };
    }

    @Bean
    RowMapper<Developer> configureDeveloperRowMapper() {
        return (final ResultSet rs, final int rowCount) -> {
            final var id = rs.getObject("id", UUID.class);
            final var teamId = rs.getObject("team_id", UUID.class);
            final var name = rs.getString("name");
            final var phone = rs.getString("phone_number");
            return new Developer(id, teamId, name, phone);
        };
    }

    @Bean
    RepoConfig configureRepoConfig(@Value("${function.team.create}") String createUserSproc,
                                   @Value("${function.developer.create}") String createDeveloperSproc,
                                   @Value("${function.team.get}") String getTeamSproc,
                                   @Value("${function.developer.get}") String getDevelopersSproc,
                                   @Value("${function.team.get.by.id") String getTeamByIdSproc) {
        return new RepoConfig(createUserSproc, createDeveloperSproc, getTeamSproc, getDevelopersSproc, getTeamByIdSproc);
    }

    @Bean
    RepositoryJdbcDaoSupport configureJdbc(DataSource dataSource,
                                           RepoConfig repoConfig) {
        return new RepositoryJdbcDaoSupport(dataSource, repoConfig);
    }
}
