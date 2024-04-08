package org.example;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotBlank;

public class LearnConfiguration extends Configuration {
    @NotBlank
    @JsonProperty("nameOfSubject")
    private String nameOfSubject;

    private Integer learningDuration;

    @NotBlank
    @JsonProperty("urlGetRandomNumbers")
    private String urlGetRandomNumbers;

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
