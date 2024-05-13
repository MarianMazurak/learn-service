package org.example.config;

import org.apache.commons.text.StringSubstitutor;

public class LocalPropertiesSubstitutor extends StringSubstitutor {

    public LocalPropertiesSubstitutor() {
        super(new LocalPropertiesLookup());
    }
}
