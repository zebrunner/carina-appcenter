package com.zebrunner.carina.appcenter;

import com.zebrunner.carina.commons.artifact.IAppInfo;

public final class AppCenterApp implements IAppInfo {
    private String directLink;
    private String version;
    private String build;

    AppCenterApp() {
    }

    @Override
    public String getDirectLink() {
        return this.directLink;
    }

    void setDirectLink(String directLink) {
        this.directLink = directLink;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getBuild() {
        return this.build;
    }

    void setBuild(String build) {
        this.build = build;
    }
}

