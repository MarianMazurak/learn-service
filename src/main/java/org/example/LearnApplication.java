package org.example;


import io.dropwizard.Application;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.example.cli.DatabaseAppliers;
import org.example.entity.TestEntity;
import org.example.resource.HealthCheckResource;
import org.example.resource.HelloResource;
import org.example.config.LocalPropertiesSubstitutor;
import org.jdbi.v3.core.Jdbi;

public class LearnApplication extends Application<LearnConfiguration> {

    public static void main(String[] args) throws Exception {
        for (int i=0; args.length > i; i++)
        {
            System.out.println(args[i]);
        }
        new LearnApplication().run(args);
    }

    @Override
    public void run(LearnConfiguration configuration, Environment environment) {
        HealthCheckResource healthCheckResource = new HealthCheckResource();

        //Db initializing
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        TestEntity testEntity = new TestEntity(jdbi);


        HelloResource helloResource = new HelloResource(testEntity);
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

        //CLI create database and tables
        bootstrap.addCommand(new DatabaseAppliers());
    }

}
