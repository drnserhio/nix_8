package com.ua.alevel.constant;

import org.springframework.core.io.ClassPathResource;

public class FileResourcesConst {
    public final static String INPUT_FIRST_FILE_FROM_RESOURCE = new ClassPathResource("src/main/resources/input/1-input.txt").getPath();
    public final static String OUTPUT_FIRST__FILE_FROM_RESOURCE = new ClassPathResource("src/main/resources/output/1-output.txt").getPath();

    public final static String INPUT_SECOND_FILE_FROM_RESOURCE = new ClassPathResource("src/main/resources/input/2-input.txt").getPath();
    public final static String OUTPUT_SECOND_FILE_FROM_RESOURCE = new ClassPathResource("src/main/resources/output/2-output.txt").getPath();

    public final static String INPUT_THIRD__FILE_FROM_RESOURCE = new ClassPathResource("src/main/resources/input/3-input.txt").getPath();
    public final static String OUTPUT_THIRD_FILE_FROM_RESOURCE = new ClassPathResource("src/main/resources/output/3-output.txt").getPath();
}
