package com.creations.funds.postgres;

public class RepoConfig {

    private final String fCreateSproc;
    private final String fCreateDeveloperSproc;
    private final String fGetSproc;
    private final String fGetDeveloperSproc;
    private final String fGetTeamByIdSproc;

    public RepoConfig(String createUserSproc, String createDeveloperSproc, String getTeamSproc, String getDevelopersSproc, String getTeamByIdSproc) {
        fCreateSproc = createUserSproc;
        fCreateDeveloperSproc = createDeveloperSproc;
        fGetSproc = getTeamSproc;
        fGetDeveloperSproc = getDevelopersSproc;
        fGetTeamByIdSproc = getTeamByIdSproc;
    }

    public String getCreateSproc() {
        return fCreateSproc;
    }

    public String getCreateDeveloperSproc() {
        return fCreateDeveloperSproc;
    }

    public String getGetSproc() {
        return fGetSproc;
    }

    public String getGetDeveloperSproc() {
        return fGetDeveloperSproc;
    }

    public String getTeamByIdSproc() {
        return fGetTeamByIdSproc;
    }
}
