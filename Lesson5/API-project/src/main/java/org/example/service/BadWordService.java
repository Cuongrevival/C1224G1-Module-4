package org.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BadWordService {

    private final List<String> badWords;

    public BadWordService(@Value("${bad.words}") String badWordsProperty) {
        this.badWords = Arrays.asList(badWordsProperty.toLowerCase().split(","));
    }

    public boolean containsBadWords(String comment) {
        if (comment == null) return false;
        String normalizedComment = comment.toLowerCase();
        return badWords.stream().anyMatch(normalizedComment::contains);
    }
}
