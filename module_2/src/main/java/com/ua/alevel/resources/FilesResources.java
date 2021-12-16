package com.ua.alevel.resources;

import com.ua.alevel.exception.InputFileException;
import com.ua.alevel.service.DateParseService;
import com.ua.alevel.service.FilterUniqService;
import com.ua.alevel.service.WeigthGraphService;
import com.ua.alevel.util.ResourceFilesHelper;
import lombok.AllArgsConstructor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.PatternSyntaxException;

import static com.ua.alevel.constant.ColorFont.*;
import static com.ua.alevel.util.ResourceFilesHelper.clearOutputFiles;
import static ua.com.alevel.StringerUtil.exception;
import static ua.com.alevel.StringerUtil.print;

@AllArgsConstructor
public class FilesResources {

    private final DateParseService dateParseService;
    private final FilterUniqService uniqService;
    private final WeigthGraphService weigthGraphService;

    public FilesResources() {
        this.dateParseService = new DateParseService();
        this.uniqService = new FilterUniqService();
        this.weigthGraphService = new WeigthGraphService();
    }

    public void run() {
        menu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String in = "";
        try {
            while ((in = reader.readLine()) != null) {
                choose(in, reader);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void menu() {
        System.out.println(

                "\n\t\t| If you entry : |\n" +
                        " 1 - Start module app\n" +
                        " 2 - Clear output files\n" +
                        " 3 - Exit"
        );
    }

    private void choose(String in, BufferedReader reader) throws IOException {

        switch (in) {
            case "1":
                dateParse();
                findUniqName();
                findInDepth();
                print(ANSI_GREEN + "CHECK output file." + ANSI_RESET);
                break;
            case "2":
                clearOutputResourceFiles();
                print(ANSI_GREEN +  "Your output files clear successfull." + ANSI_RESET);
                break;
            case "3":
                print(ANSI_GREEN + "Exit..." + ANSI_RESET);
                System.exit(0);
                break;
            default:
                exception(ANSI_YELLOW + "Empty wrong..." + ANSI_RESET);
        }
        menu();
    }

    private void dateParse() {
        try {
            dateParseService.readFromFileToBuffer();
        }catch (InputFileException e) {
            print(ANSI_RED + e.getMessage() +ANSI_RESET);
        } catch (NullPointerException e) {
            print(ANSI_YELLOW + "Check you data in input files!" + ANSI_RESET);
        } catch (PatternSyntaxException e) {
            print(ANSI_YELLOW + "Not found normal date in list with regex." + ANSI_RESET);
        } catch (IOException e) {
            print(ANSI_YELLOW + "File not created.Please create, and try again" + ANSI_RESET);
        }
    }

    private void findUniqName() {
        try {
            uniqService.readFromFileToBuffer();
        } catch (InputFileException e) {
            print(ANSI_RED + e.getMessage() +ANSI_RESET);
        } catch (NullPointerException e) {
            print(ANSI_YELLOW + "Check you data in input files!" + ANSI_RESET);
        } catch (IOException e) {
            print(ANSI_YELLOW + "File not created.Please create, and try again" + ANSI_RESET);
        }
    }

    private void findInDepth() {
        try {
            weigthGraphService.readFromFile();
        } catch (InputFileException e) {
            print(ANSI_RED + e.getMessage() +ANSI_RESET);
        } catch (NullPointerException e) {
            print(ANSI_YELLOW + "Check you data in input files!" + ANSI_RESET);
        } catch (IOException e) {
            print(ANSI_YELLOW + "File not created.Please create, and try again" + ANSI_RESET);
        }
    }

    private void clearOutputResourceFiles() throws IOException {
        clearOutputFiles();
    }

}
