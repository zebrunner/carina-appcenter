package com.zebrunner.carina.appcenter;

import com.zebrunner.carina.commons.artifact.ArtifactManagerFactory;
import com.zebrunner.carina.commons.artifact.IArtifactManager;
import com.zebrunner.carina.commons.artifact.IArtifactManagerFactory;

import java.util.regex.Pattern;

@ArtifactManagerFactory
public class AppCenterManagerFactory implements IArtifactManagerFactory {
    // appcenter://appName/platformName/buildType/version
    private static final Pattern APPCENTER_ENDPOINT_PATTERN = Pattern.compile(
            "appcenter:\\/\\/([a-zA-Z-0-9][^\\/]*)\\/([a-zA-Z-0-9][^\\/]*)\\/([a-zA-Z-0-9][^\\/]*)\\/([a-zA-Z-0-9][^\\/]*)");

    @Override
    public boolean isSuitable(String url) {
        return APPCENTER_ENDPOINT_PATTERN.matcher(url).find();
    }

    @Override
    public IArtifactManager getInstance() {
        return AppCenterManager.getInstance();
    }
}
