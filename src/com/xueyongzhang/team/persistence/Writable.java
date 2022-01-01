package com.xueyongzhang.team.persistence;

import org.json.JSONObject;

//this class uses code from JsonSerializationDemo, https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns the object as JSON object
    JSONObject toJson();
}
