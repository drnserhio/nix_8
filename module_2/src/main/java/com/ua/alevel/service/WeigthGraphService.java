package com.ua.alevel.service;

import com.ua.alevel.domein.Country;
import com.ua.alevel.exception.InputFileException;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import static com.ua.alevel.constant.ColorFont.ANSI_RED;
import static com.ua.alevel.constant.FileResourcesConst.*;
import static com.ua.alevel.util.ResourceFilesHelper.filesIsExists;

public class WeigthGraphService {

    private HashMap<String, HashMap<String, Integer>> graph = new HashMap<>();
    private Map<String, Integer> costs = new HashMap<>();
    private HashMap<String, String> parents = new HashMap<>();
    private ArrayList<String> proccesed = new ArrayList<>();

    private int countCountry;
    private int countWay;

    private List<String> countryStorage = new ArrayList<>();
    private List<String> cloneCountryStorage = new ArrayList<>();
    private List<Country> saveContry = new ArrayList<>();
    private List<String> startCities = new ArrayList<>();


    public void readFromFile() throws IOException {
        filesIsExists(INPUT_SECOND_FILE_FROM_RESOURCE, OUTPUT_SECOND_FILE_FROM_RESOURCE);
        if (Paths.get(INPUT_SECOND_FILE_FROM_RESOURCE).toFile().length() < 0 ) {
            throw new InputFileException(ANSI_RED + "File is empty.Please entry date in file and try again.");
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(INPUT_SECOND_FILE_FROM_RESOURCE))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (reader.ready()) {
                stringBuilder.append(reader.readLine() + "\n");
            }
            parseStingToArray(stringBuilder.toString());
        }
        useAlghoritmDeikstraAndWrite();
    }

    private void parseStingToArray(String data) {
        saveCountCountry(data.split("\n")[0]);
        String[] number = data.split("\\D");
        String[] country = data.split("\\d");


        saveStartCountryToEndCountry(country[country.length-1]);
        saveToCountryStorage(country);
        saveCountWay(number[number.length-1]);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < number.length-1; i++) {
            if (number[i] == "") {
                if (stringBuilder.length() < 1) {
                    continue;
                }
                parseToCountryObject(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
            stringBuilder.append(number[i]);
        }
        parseToCountryObject(stringBuilder.toString());
    }

    private void saveStartCountryToEndCountry(String country) {

        String[] split = country.trim().split("\n");
        for (String s : split) {
            String[] startEndCity = s.split(" ");
                startCities.add(startEndCity[0]);
        }

    }

    private void saveToCountryStorage(String[] country) {
        for (int i = 0; i < country.length; i++) {
            if (country[i].length() > 2) {
                if (countryStorage.size() == countCountry) {
                    cloneCountry();
                    return;
                }
                countryStorage.add(country[i].replaceAll("\n", ""));
            }
        }
    }

    private void cloneCountry() {
        for (String country : countryStorage) {
            this.cloneCountryStorage.add(country);
        }
    }

    private void parseToCountryObject(String numbers) {
        Country country = new Country();
        HashMap<String, Integer> neighborWithCost = new HashMap<>();

        String nameCountry = countryStorage.remove(0);
        String numb = numbers.substring(1, numbers.length());
        int neighbors = Integer.parseInt(String.valueOf(numbers.charAt(0)));
        for (int i = 0; i < numb.length(); i+=2) {
            String nameNeighbor = getNameNeighbor(numb.substring(i, numb.length()));
            int costNeighbor = getCostNeighbor(numb.substring(i, numb.length()));
            neighborWithCost.put(nameNeighbor,costNeighbor);
        }

        country.setNameCountry(nameCountry);
        country.setCountNeighbors(neighbors);
        country.setNeighborWithCost(neighborWithCost);
        saveContry.add(country);

    }

    private int getCostNeighbor(String substring) {
        String[] split = substring.split("");
       return Integer.parseInt(split[1]);
    }

    private String getNameNeighbor(String substring) {
        String[] split = substring.split("");
      return cloneCountryStorage.get(Integer.parseInt(split[0])-1);
    }

    private void saveCountWay(String numberWay) {
        try {
            this.countWay = Integer.parseInt(numberWay);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    private void saveCountCountry(String numberCountCountry) {
        try {
            this.countCountry = Integer.parseInt(numberCountCountry);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void useAlghoritmDeikstraAndWrite() throws IOException {
        createdGraph();
        createCosts();
        createParents();
        deikstra();
        writeToFile();
    }

    private void createParents() {

        for (Map.Entry<String, HashMap<String, Integer>> stringHashMapEntry : graph.entrySet()) {
            String parent = stringHashMapEntry.getKey();
            for (String child : stringHashMapEntry.getValue().keySet()) {
                if (!parents.containsKey(child)) {
                    parents.put(child, parent);
                }
            }
        }
    }

    private void createCosts() {
        for (Country country : saveContry) {
            for (Map.Entry<String, Integer> elemWithCost : country.getNeighborWithCost().entrySet()) {
                    costs.put(elemWithCost.getKey(), elemWithCost.getValue());
            }
        }
    }

    private void createdGraph() {
        for (Country country : saveContry) {
            String name = country.getNameCountry();
                graph.put(name, country.getNeighborWithCost());
        }
    }

    private String findMinForCosts(Map<String, Integer> costs) {
        String minElement = null;
        Integer minValue = (int)Double.POSITIVE_INFINITY;

        for (Map.Entry<String, Integer> elem : costs.entrySet()) {
            Integer value = elem.getValue();
            if (value < minValue && !proccesed.contains(elem.getKey()))
                minElement = elem.getKey();
                minValue = value;
        }
        return minElement;
    }

    private void deikstra() {
        String node = findMinForCosts(costs);
        while (!Objects.isNull(node)) {
            Integer cost = costs.get(node);
            HashMap<String, Integer> neighbors = graph.get(node);
            for (String neighbor : neighbors.keySet()) {
                Integer newCost = cost + neighbors.get(neighbor);
                if (costs.get(neighbor) > newCost) {
                    costs.put(neighbor, newCost);
                    parents.put(neighbor, node);
                }
            }
            proccesed.add(node);
            node = findMinForCosts(costs);
        }
    }

    private String takeCountForCosts() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String city : startCities) {
            stringBuilder.append(costs.get(city) + " ");
        }
        return stringBuilder.toString().replaceAll(" ", "\n");
    }

    private void writeToFile() throws IOException {
        filesIsExists(INPUT_SECOND_FILE_FROM_RESOURCE, OUTPUT_SECOND_FILE_FROM_RESOURCE);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_SECOND_FILE_FROM_RESOURCE))) {
            writer.write(takeCountForCosts());
        }
    }
}

