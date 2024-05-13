package org.example.cli;

import io.dropwizard.cli.Cli;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.example.LearnConfiguration;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.locator.ClasspathSqlLocator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class DatabaseAppliers extends ConfiguredCommand<LearnConfiguration> {

    public DatabaseAppliers() {
        super("dbApplier", "Description of my custom command");

    }

    @Override
    public void onError(Cli cli, Namespace namespace, Throwable e) {
        super.onError(cli, namespace, e);
    }

    @Override
    public void configure(Subparser subparser) {
        super.addFileArgument(subparser);
        subparser.addArgument("-f").dest("dbApplier").type(String.class).help("Running the database appliers");
    }

    @Override
    protected void run(Bootstrap<LearnConfiguration> bootstrap, Namespace namespace, LearnConfiguration configuration) throws IOException {

        DataSourceFactory dataSourceFactory = configuration.getDataSourceFactory();
        //Create Jdbi + read scripts from resource folder and apply to database
        Jdbi jdbi = Jdbi.create(dataSourceFactory.build(bootstrap.getMetricRegistry(), "postgresql"));


        ClasspathSqlLocator classpathSqlLocator = ClasspathSqlLocator.create();
        String[] split = classpathSqlLocator.getResource("script/").split("\n");
        for (int i = 0; i < split.length; i++) {
            String fileName = split[i];
            //TODO read script by InputStream from java io
            jdbi.useHandle(h -> h.createScript(classpathSqlLocator.getResource("script/" + fileName)).execute());
        }

    }

    public static void main(String[] args) throws Exception {
        getSqlScripts();

    }

    //Reading from the resource folder using the java.io package
    private static List<String> getSqlScripts() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<String> fileNames;
        try (InputStream is = loader.getResourceAsStream("script/")) {
            byte[] bytes = is.readAllBytes();
            fileNames = Arrays.stream(new String(bytes, StandardCharsets.UTF_8).split("\n")).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileNames;
    }


}
