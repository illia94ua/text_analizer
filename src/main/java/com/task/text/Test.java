package com.task.text;

import java.util.List;
import java.util.Map.Entry;

public class Test {

    public static void main(String[] args) {
        FileSource source = new FileSource("passage.txt");
        FileProcessor proc = new FileProcessor(source.readFile());

        List<Entry<String, Integer>> topUsedWords = proc.topUsedWords();

        System.out.println("Total word count: " + proc.countWords());
        
        System.out.println("Top 10 words used: " + topUsedWords);

        System.out.println("The last sentence on the file that contains the most used word:");
        System.out.println(proc.lastSentenceWithWord(topUsedWords.stream().findFirst().map(e -> e.getKey()).get()));

    }
}
