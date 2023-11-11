package com.zebrunner.carina.commons.artifact;

// move to the carina-commons repo
public interface IAppInfo {

    String getDirectLink();

    default String getVersion() {
        throw new UnsupportedOperationException();

    }

    default String getBuild() {
        throw new UnsupportedOperationException();
    }

}
