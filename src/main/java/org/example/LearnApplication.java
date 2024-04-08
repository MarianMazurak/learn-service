package org.example;


import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.apache.commons.text.StringSubstitutor;
import org.example.resource.HealthCheckResource;
import org.example.resource.HelloResource;
import org.example.resource.config.LocalPropertiesLookup;
import org.example.resource.config.LocalPropertiesSubstitutor;

public class LearnApplication extends Application<LearnConfiguration> {

    public static void main(String[] args) throws Exception {
        new LearnApplication().run("server", "config.yml");
        if (args.length != 0) {

            System.out.println(args[0]);
        }
    }

    @Override
    public void run(LearnConfiguration configuration, Environment environment) throws Exception {

        HelloResource helloResource = new HelloResource(configuration.getNameOfSubject());
        HealthCheckResource healthCheckResource = new HealthCheckResource();
        System.out.println(configuration.getLearningDuration());
        System.out.println(configuration.getUrlGetRandomNumbers());
        System.out.println(configuration.getNameOfSubject());

        environment.jersey().register(helloResource);
        environment.healthChecks().register("health", healthCheckResource);
    }

    @Override
    public String getName() {
        return "learn-service";
    }

    //An initialize method is used to configure aspects of the application required before the application is run
    @Override
    public void initialize(Bootstrap<LearnConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new LocalPropertiesSubstitutor()));
    }

}
