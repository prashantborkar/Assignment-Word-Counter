package com.assignment.cgi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;


public class WordModel {


    @NotBlank
    private final String word;

    public WordModel(@JsonProperty("word") String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
