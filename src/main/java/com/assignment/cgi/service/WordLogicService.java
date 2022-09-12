package com.assignment.cgi.service;


import com.assignment.cgi.model.WordModel;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class WordLogicService {

    // The method "wordCountJson" created for using Word Count by Stream above Java 8 version.
    // It's for Json object
    public Map<String, Long> wordCountJson(WordModel request) {

        String wordStringList = request.getWord();
        Map<String, Long> wordCountMap;

        if (wordStringList == null || wordStringList.isEmpty()) return new HashMap<String, Long>();
        wordCountMap = Arrays.stream(wordStringList.trim().toLowerCase().split("\\s+")).map(word -> new MapEntry(word, 1)).collect(groupingBy(MapEntry::getKey, counting()));

        return wordCountMap;

    }

    // The method "wordCount" created for using Word Count by Stream above Java 8 version.
    // It's for String / PlainText format
    public Map<String, Long> wordCount(String wordStringList) {

        if (wordStringList == null || wordStringList.isEmpty()) return new HashMap<String, Long>();
        Map<String, Long> wordCountMap = Arrays.stream(wordStringList.trim().toLowerCase().split("\\s*[^a-zA-Z]+\\s*")).map(word -> new MapEntry(word, 1)).collect(groupingBy(MapEntry::getKey, counting()));
        return wordCountMap;

    }

    // The method "wordCountIterator" created for using Word Count Loops (Iterator) OPTIONAL SOLUTION.
    public Map<String, Integer> wordCountIterator(String wordList) {
        Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
        if (wordList == null || wordList.isEmpty()) return wordCountMap;
        String[] wordArray = wordList.trim().toLowerCase().split("\\s+");
        List<String> wordLists = Arrays.asList(wordArray);
        for (Iterator<String> iterator = wordLists.iterator(); iterator.hasNext(); ) {
            String word = iterator.next();
            if (wordCountMap.containsKey(word)) {
                Integer count = wordCountMap.get(word);
                wordCountMap.put(word, count + 1);
            } else {
                wordCountMap.put(word, 1);
            }
        }
        return wordCountMap;
    }

    private class MapEntry implements Map.Entry<String, Integer> {
        private final String key;
        private Integer count;

        public MapEntry(String key, Integer count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public Integer getValue() {
            return this.count;
        }

        @Override
        public Integer setValue(Integer value) {
            Integer oldCount = value;
            this.count = value;
            return oldCount;
        }

    }

}
