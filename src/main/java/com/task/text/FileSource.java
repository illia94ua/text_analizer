package com.task.text;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileSource {

    String inputFileName;

    public String readFile() {
        try (FileInputStream is = new FileInputStream(inputFileName);) {
            return createContentStringFromFile(is);
        } catch (IOException e) {
            System.err.println("Error while reading file");
            e.printStackTrace();
            return StringUtils.EMPTY;
        }
    }

    private String createContentStringFromFile(FileInputStream inputStream) throws IOException {
        byte[] fileBytes = new byte[inputStream.available()];
        inputStream.read(fileBytes);
        return new String(fileBytes);
    }

}
