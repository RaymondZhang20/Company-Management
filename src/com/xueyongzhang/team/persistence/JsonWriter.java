package com.xueyongzhang.team.persistence;

import com.xueyongzhang.team.model.Staff;
import com.xueyongzhang.team.model.Team;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;


public class JsonWriter {
    private PrintWriter writer;
    private String stuffDestination;
    private String teamsDestination;

    // EFFECTS: constructs writer to write to the file
    public JsonWriter(String stuffDestination, String teamsDestination) {
        this.stuffDestination = stuffDestination;
        this.teamsDestination = teamsDestination;
    }


    public void openStuff() throws FileNotFoundException {
        writer = new PrintWriter(new File(stuffDestination));
    }

    public void openTeams() throws FileNotFoundException {
        writer = new PrintWriter(new File(teamsDestination));
    }


    public void writeStaff(Staff staff) {
        JSONObject jsonStaff = staff.toJson();
        saveToFile(jsonStaff.toString(4));
    }

    public void writeTeams(List<Team> teams) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Team team : teams) {
            jsonArray.put(team.toJson());
        }

        jsonObject.put("teams", jsonArray);
        saveToFile(jsonObject.toString(4));
    }


    public void close() {
        writer.close();
    }


    private void saveToFile(String json) {
        writer.print(json);
    }

}
