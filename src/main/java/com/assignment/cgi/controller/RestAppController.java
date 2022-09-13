package com.assignment.cgi.controller;

import com.assignment.cgi.model.WordModel;
import com.assignment.cgi.service.WordLogicService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class RestAppController {

    private final WordLogicService service;

    public RestAppController(WordLogicService service) {
        this.service = service;
    }


    @ApiOperation(value = "Get the String Word / Sentence / Song Lyrics, Returns the count of word occurrence. [By Using Text/Plain]", notes = "One is often interested in which words occur most" + "often in a mass of text. To make it easier for those who work with" + "this, A small Web-API that takes a mass of" + "text as input and returns the 10 most frequent words along with" + "the frequency. [By Using Text/Plain]", response = WordModel.class)
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.TEXT_PLAIN_VALUE}, consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/count")
    @ResponseBody
    public String getCount(@RequestBody String word) {

        Map<String, Long> objectSet1;
        objectSet1 = service.wordCount(word);
        for (Map.Entry entry : objectSet1.entrySet()) {
            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
        }
        Gson gson = new Gson();
        String json = gson.toJson(objectSet1);
        System.out.println(json);

        return json;
    }

    @ApiOperation(value = "Get the String Word / Sentence / Song Lyrics, Returns the count of word occurrence. [By Using Json]", notes = "One is often interested in which words occur most" + "often in a mass of text. To make it easier for those who work with" + "this, A small Web-API that takes a mass of" + "text as input and returns the 10 most frequent words along with" + "the frequency.[By Using Json]", response = WordModel.class)
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}, value = "/countJson")
    @ResponseBody
    public String getCountJson(@RequestBody WordModel request) {
        String json = "";
        Gson gson = new Gson();
        if (!StringUtils.isEmpty(request.getWord())) {
            Map<String, Long> objectSet1;
            objectSet1 = service.wordCountJson(request);
            for (Map.Entry entry : objectSet1.entrySet()) {
                System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
            }
            json = gson.toJson(objectSet1);
            System.out.println(json);
        } else {
            json = gson.toJson("Please Provide The Input Text / String.");
        }
        return json;
    }


}
