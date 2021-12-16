package com.ua.alevel;

import com.ua.alevel.resources.FilesResources;
import com.ua.alevel.service.DateParseService;
import com.ua.alevel.service.FilterUniqService;
import com.ua.alevel.service.WeigthGraphService;

import java.io.IOException;
import java.net.URISyntaxException;

public class ApplicationRun {

    public static void main(String[] args) throws IOException, URISyntaxException {
        new FilesResources().run();
    }
}
