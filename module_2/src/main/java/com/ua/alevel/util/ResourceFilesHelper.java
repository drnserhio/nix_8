package com.ua.alevel.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.ua.alevel.constant.FileResourcesConst.*;

public final class ResourceFilesHelper {
    private ResourceFilesHelper() {}


    public static void filesIsExists(String input, String output)
            throws IOException {
        if (!isExistsInput(input)) {
            createInputFile(input);
        }
        if (!isExistsOutput(output)) {
            createOutputFile(output);
        }
    }

    private static void createInputFile(String input)
            throws IOException {
        Files.createFile(Paths.get(input).toAbsolutePath().normalize());
    }

    private static void createOutputFile(String output)
            throws IOException {
        Files.createFile(Paths.get(output).toAbsolutePath().normalize());
    }


    private static boolean isExistsInput(String file) {
        return Files.exists(Paths.get(file).toAbsolutePath().normalize());
    }
    private static boolean isExistsOutput(String outputFile) {
        return Files.exists(Paths.get(outputFile).toAbsolutePath().normalize());
    }

    public static void clearOutputFiles()
            throws IOException {
        filesIsExists(INPUT_FIRST_FILE_FROM_RESOURCE, OUTPUT_FIRST__FILE_FROM_RESOURCE);
        filesIsExists(INPUT_SECOND_FILE_FROM_RESOURCE, OUTPUT_SECOND_FILE_FROM_RESOURCE);
        filesIsExists(INPUT_THIRD__FILE_FROM_RESOURCE, OUTPUT_THIRD_FILE_FROM_RESOURCE);
        try(BufferedWriter first = new BufferedWriter(new FileWriter(OUTPUT_FIRST__FILE_FROM_RESOURCE));
                BufferedWriter second = new BufferedWriter(new FileWriter(OUTPUT_SECOND_FILE_FROM_RESOURCE));
                    BufferedWriter third = new BufferedWriter(new FileWriter(OUTPUT_THIRD_FILE_FROM_RESOURCE))) {
            first.write("");
            second.write("");
            third.write("");
        }
    }
}
