package com.zebrunner.carina.appcenter.config;

import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.utils.config.IParameter;

import java.util.Optional;

public final class AppCenterConfiguration extends Configuration {

    public enum Parameter implements IParameter {

        APPCENTER_TOKEN("appcenter_token") {
            @Override
            public boolean hidden() {
                return true;
            }
        };

        private final String key;

        Parameter(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    @Override
    public String toString() {
        Optional<String> asString = asString(Parameter.values());
        if (asString.isEmpty()) {
            return "";
        }
        return "\n=========== AppCenter configuration ===========\n" +
                asString.get();
    }
}
