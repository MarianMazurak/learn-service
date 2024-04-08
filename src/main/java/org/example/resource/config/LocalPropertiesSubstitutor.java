package org.example.resource.config;

import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.lookup.StringLookup;

public class LocalPropertiesSubstitutor extends StringSubstitutor {

    public LocalPropertiesSubstitutor() {
        super(new LocalPropertiesLookup());
    }
}
