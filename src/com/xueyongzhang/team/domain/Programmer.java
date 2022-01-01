package com.xueyongzhang.team.domain;

import com.xueyongzhang.team.domain.Employee;
import com.xueyongzhang.team.domain.Equipment;
import com.xueyongzhang.team.model.Team;
import org.json.JSONObject;

public class Programmer extends Employee {
    private Integer memberId = 0;
    private String status = "FREE";
    private Equipment equipment;
    private Team team;

    public Programmer(Integer id, String name, Integer age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public void setTeam(Team t) {
        if (t == null) {
            team = null;
        }
        team = t;
    }

    public Team getTeam() {
        return team;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus() {
        if (team == null) {
            status = "FREE";
        } else {
            status = "In " + team.getName();
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    protected String getMemberDetails() {
        return getMemberId() + "/" + getDetails();
    }

    public String getDetailsForTeam() {
        return getMemberDetails() + "\t\tProgrammer";
    }

    @Override
    public String toString() {
        return getDetails() + "\t\tProgrammer\t\t" + status + "\t\t\t\t\t\t\t\t" + equipment.getDescription();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        jsonObject.put("equipment", equipment.toJson());
        jsonObject.put("type", "Programmer");
        return jsonObject;
    }
}
