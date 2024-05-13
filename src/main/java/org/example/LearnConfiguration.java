package org.example;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LearnConfiguration extends Configuration {
    @NotBlank
    @JsonProperty("nameOfSubject")
    private String nameOfSubject;

    private Integer learningDuration;

    @NotBlank
    @JsonProperty("urlGetRandomNumbers")
    private String urlGetRandomNumbers;

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public String getNameOfSubject() {
        return this.nameOfSubject;
    }

    public void setNameOfSubject(String nameOfSubject) {
        this.nameOfSubject = nameOfSubject;
    }

    public Integer getLearningDuration() {
        return learningDuration;
    }

    public void setLearningDuration(Integer learningDuration) {
        this.learningDuration = learningDuration;
    }

    public String getUrlGetRandomNumbers() {
        return urlGetRandomNumbers;
    }

    public void setUrlGetRandomNumbers(String urlGetRandomNumbers) {
        this.urlGetRandomNumbers = urlGetRandomNumbers;
    }
}
