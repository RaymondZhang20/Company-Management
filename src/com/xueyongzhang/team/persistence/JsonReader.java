package com.xueyongzhang.team.persistence;

import com.xueyongzhang.team.domain.*;
import com.xueyongzhang.team.model.Staff;
import com.xueyongzhang.team.model.Team;
import com.xueyongzhang.team.model.TeamException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class JsonReader {
    private String staffSource;
    private String teamsSource;
    private Staff staff;


    public JsonReader(String staffSource, String teamsSource) {
        this.staffSource = staffSource;
        this.teamsSource = teamsSource;
    }


    public Staff readStaff() throws IOException {
        String jsonData = readFile(staffSource);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStaff(jsonObject);
    }

    public List<Team> readTeams() throws IOException {
        String jsonData = readFile(teamsSource);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeams(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private Staff parseStaff(JSONObject jsonObject) {
        staff = new Staff();
        JSONArray jsonArray = jsonObject.getJSONArray("staff");

        for (Object json : jsonArray) {
            JSONObject jsonObjectStaff = (JSONObject) json;
            staff.add(parseEmployee(jsonObjectStaff));
        }
        return staff;
    }

    private List<Team> parseTeams(JSONObject jsonObject) {
        List<Team> teams = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("teams");

        for (Object json : jsonArray) {
            JSONObject jsonObjectTeam = (JSONObject) json;
            teams.add(parseTeam(jsonObjectTeam));
        }
        return teams;
    }

    private Team parseTeam(JSONObject jsonObject) {
        Team team = new Team();
        String name = jsonObject.getString("name");
        team.setName(name);
        JSONArray jsonArray = jsonObject.getJSONArray("team");

        for (Object json : jsonArray) {
            JSONObject jsonObjectTeam = (JSONObject) json;
            Integer id = jsonObjectTeam.getInt("id");
            try {
                team.addMember(staff.getEmployee(id));
            } catch (TeamException e) {
                e.printStackTrace();
            }
        }
        return team;
    }


    private Employee parseEmployee(JSONObject jsonObject) {
        Integer id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        Integer age = jsonObject.getInt("age");
        Double salary = jsonObject.getDouble("salary");
        String type = jsonObject.getString("type");
        if (type.equals("Employee")) {
            return new Employee(id, name, age, salary);
        }
        return subdivision(jsonObject, id, name, age, salary, type);
    }

    // MODIFIES: disasters
    // EFFECTS: subdivide disaster into specific types and parses them from JSON object and adds it to disasters
    private Employee subdivision(JSONObject jsonObject, Integer id, String name, Integer age, Double salary, String type) {
        Equipment equipment = parseEquipment(jsonObject.getJSONObject("equipment"));
        switch (type) {
            case "Programmer":
                return new Programmer(id, name, age, salary, equipment);
            case "Designer":
                Double bonus = jsonObject.getDouble("bonus");
                return new Designer(id, name, age, salary, equipment, bonus);
            default:
                Double bonusA = jsonObject.getDouble("bonus");
                Integer stock = jsonObject.getInt("stock");
                return new Architect(id, name, age, salary, equipment, bonusA, stock);
        }
    }

    private Equipment parseEquipment(JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        switch (type) {
            case "Laptop":
                String model = jsonObject.getString("model");
                Double price = jsonObject.getDouble("price");
                return new Laptop(model, price);
            case "PC":
                String modelPC = jsonObject.getString("model");
                String display = jsonObject.getString("display");
                return new PC(modelPC, display);
            default:
                String name = jsonObject.getString("name");
                String typePrinter = jsonObject.getString("typePrinter");
                return  new Printer(name, typePrinter);
        }
    }


}
