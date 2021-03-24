package com.creations.funds;

import com.creations.funds.client.APIClient;
import com.creations.funds.gson.ISODateAdapter;
import com.creations.funds.postgres.RepoConfig;
import com.creations.funds.postgres.RepositoryJdbcDaoSupport;
import com.creations.funds.service.MFRepository;
import com.creations.funds.service.MFService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.sql.DataSource;
import java.util.Date;

@Configuration
@Import(DataSourceConfig.class)
public class AppConfig {

    @Bean
    MFService configureMFService(final APIClient apiClient, final MFRepository mfRepository) {
        return new MFService(apiClient, mfRepository);
    }

    @Bean
    APIClient configureMFAPIClient(@Value("${mutual_fund_returns.api.url}") final String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(new Gson().newBuilder().registerTypeAdapter(Date.class, new ISODateAdapter()).create()))
                .build().create(APIClient.class);
    }

    @Bean
    MFRepository configureMFRepository() {
        return new MFRepository();
    }

    @Bean
    RepoConfig configureRepoConfig(/*@Value("${function.user.create}") String createUserSproc,
                                   @Value("${function.user.get}") String getUserSproc,
                                   @Value("${function.user.update}") String updateUserSproc,
                                   @Value("${function.user.delete}") String deleteUserSproc*/) {
        return new RepoConfig();
    }

    @Bean
    RepositoryJdbcDaoSupport configureJdbc(DataSource dataSource,
                                           RepoConfig repoConfig) {
        return new RepositoryJdbcDaoSupport(dataSource, repoConfig);
    }
}
