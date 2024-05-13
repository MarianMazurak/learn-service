package org.example.config;

import io.dropwizard.lifecycle.Managed;

public class StorageManager implements Managed {

    private String host;
    private String port;


    @Override
    public void start() throws Exception {
        System.out.println("Call something");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Call something");
    }
}
