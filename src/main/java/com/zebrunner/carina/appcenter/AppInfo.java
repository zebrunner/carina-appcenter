package com.zebrunner.carina.appcenter;

//Beta
public final class AppInfo {

    private String directLink;
    private String version;
    private String build;

    AppInfo() {

    }

    public String getDirectLink() {
        return directLink;
    }

    void setDirectLink(String directLink) {
        this.directLink = directLink;
    }

    public String getVersion() {
        return version;
    }

    void setVersion(String version) {
        this.version = version;
    }

    public String getBuild() {
        return build;
    }

    void setBuild(String build) {
        this.build = build;
    }
}
