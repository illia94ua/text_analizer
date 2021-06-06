package com.task.text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class FileProcessor {

    private static final String WORD_PATTERN = "[a-zA-Z0-9_-]+";

    String fileContent;

    public FileProcessor(String fileContent) {
        this.fileContent = fileContent;
    }

    public int countWords() {
        Pattern pattern = Pattern.compile(WORD_PATTERN);
        Matcher matcher = pattern.matcher(fileContent.toLowerCase());
        int wordsCount = 0;

        while (matcher.find()) {
            wordsCount++;
        }

        return wordsCount;
    }

    public List<Entry<String, Integer>> topUsedWords() {
        Pattern pattern = Pattern.compile(WORD_PATTERN);
        Matcher matcher = pattern.matcher(fileContent.toLowerCase());
        Map<String, Integer> result = new HashMap<>();

        while (matcher.find()) {
            String word = matcher.group();
            result.merge(word, NumberUtils.INTEGER_ONE, Integer::sum);
        }

        return result.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .limit(10)
                .collect(Collectors.toList());
    }

    public String lastSentenceWithWord(String word) {
        String sentence = StringUtils.EMPTY;
        Pattern pattern = Pattern.compile("[A-Z][^\\.!?]* " + word + " [^\\.!?]*[\\.!?]");
        Matcher matcher = pattern.matcher(fileContent);

        while (matcher.find()) {
            sentence = matcher.group();
        }

        return sentence;
    }

}
