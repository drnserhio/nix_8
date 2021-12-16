package com.ua.alevel.service;

import com.ua.alevel.exception.InputFileException;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import static com.ua.alevel.constant.ColorFont.ANSI_RED;
import static com.ua.alevel.constant.FileResourcesConst.INPUT_THIRD__FILE_FROM_RESOURCE;
import static com.ua.alevel.constant.FileResourcesConst.OUTPUT_THIRD_FILE_FROM_RESOURCE;
import static com.ua.alevel.util.ResourceFilesHelper.filesIsExists;

public class FilterUniqService {

    private String uniqName;

    public void readFromFileToBuffer()
            throws IOException {
        filesIsExists(INPUT_THIRD__FILE_FROM_RESOURCE, OUTPUT_THIRD_FILE_FROM_RESOURCE);
        if (Paths.get(INPUT_THIRD__FILE_FROM_RESOURCE).toFile().length() < 0 ) {
            throw new InputFileException(ANSI_RED + "File is empty.Please entry name or any data in file and try again.");
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(INPUT_THIRD__FILE_FROM_RESOURCE))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (reader.ready()) {
                stringBuilder.append(reader.readLine());
            }
            readAllWordsOnSet(stringBuilder.toString());
        }
        readAllDateInFile();
    }

    private void readAllWordsOnSet(String stb) {
       uniqName = Arrays.stream(stb.split(" ")).distinct().findFirst().get();
    }


    private void readAllDateInFile() throws IOException {
        filesIsExists(INPUT_THIRD__FILE_FROM_RESOURCE, OUTPUT_THIRD_FILE_FROM_RESOURCE);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_THIRD_FILE_FROM_RESOURCE))) {
           writer.write(uniqName);
        }
    }

}
