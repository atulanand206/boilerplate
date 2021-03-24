package com.creations.funds.postgres;

public class RepoConfig {

    private final String fCreateSproc;
    private final String fGetSproc;
    private final String fUpdateSproc;
    private final String fDeleteSproc;

    public RepoConfig() {
        fCreateSproc = null;
        fGetSproc = null;
        fUpdateSproc = null;
        fDeleteSproc = null;
    }

    public RepoConfig(String createSproc,
                      String getSproc,
                      String updateSproc,
                      String deleteSproc) {
        fCreateSproc = createSproc;
        fGetSproc = getSproc;
        fUpdateSproc = updateSproc;
        fDeleteSproc = deleteSproc;
    }

    public String getCreateSproc() {
        return fCreateSproc;
    }

    public String getGetSproc() {
        return fGetSproc;
    }

    public String getUpdateSproc() {
        return fUpdateSproc;
    }

    public String getDeleteSproc() {
        return fDeleteSproc;
    }
}
