package com.ua.alevel.service;

import com.ua.alevel.exception.InputFileException;
import com.ua.alevel.util.ResourceFilesHelper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ua.alevel.constant.ColorFont.ANSI_RED;
import static com.ua.alevel.constant.FileResourcesConst.*;
import static com.ua.alevel.constant.PatternRegex.*;
import static com.ua.alevel.util.ResourceFilesHelper.filesIsExists;

public class DateParseService {

    private List<String> storage = new ArrayList<>();


    public void readFromFileToBuffer()
            throws IOException {
        filesIsExists(INPUT_FIRST_FILE_FROM_RESOURCE, OUTPUT_FIRST__FILE_FROM_RESOURCE);
        if (Paths.get(INPUT_FIRST_FILE_FROM_RESOURCE).toFile().length() < 0 ) {
            throw new InputFileException(ANSI_RED + "File is empty.Please entry date in file and try again.");
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(INPUT_FIRST_FILE_FROM_RESOURCE))) {
            while (reader.ready()) {
                findAndParse(reader.readLine());
            }
        }
        readAllDateInFile();
    }

    private void readAllDateInFile() throws IOException {
        filesIsExists(INPUT_FIRST_FILE_FROM_RESOURCE, OUTPUT_FIRST__FILE_FROM_RESOURCE);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FIRST__FILE_FROM_RESOURCE))) {
            for (String date : storage) {
                writer.write(date + " ");
            }
        }
    }


    private void findAndParse(String date) {
        Pattern pattern = Pattern.compile(REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(date);
        while (matcher.find()) {
            checkAndCreate(matcher.group(0));
        }
    }

    private void checkAndCreate(String group) {
        if (group.contains("/")) {
            parseStandartDate(group);
        }
        if (group.contains("-")) {
            parseNormalDate(group);
        }
    }

    private void parseNormalDate(String group) {
        String[] arr = group.split("-");
        String isHas = group.replaceAll(REGEX_MM_DD_YYYY, "");
        if (isHas.isEmpty()) {
            storage.add(group.replaceAll("-", ""));
        }
    }

    private void parseStandartDate(String group) {
        String[] arr = group.split("/");
        if (arr[0].length() > 2) {
            String isHas = group.replaceAll(REGEX_YYYY_MM_DD, "");
            if (isHas.isEmpty()) {
                storage.add(group.replaceAll("/", ""));
            }
        } else {
            String isHas = group.replaceAll(REGEX_DD_MM_YYYY, "");
            if (isHas.isEmpty()) {
                storage.add(group.replaceAll("/", ""));
            }
        }
    }

}
