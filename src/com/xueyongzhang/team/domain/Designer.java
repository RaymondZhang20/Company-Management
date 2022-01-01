package com.xueyongzhang.team.domain;

import com.xueyongzhang.team.domain.Programmer;
import org.json.JSONObject;

public class Designer extends Programmer {
    private double bonus;

    public Designer(Integer id, String name, Integer age, double salary,
                    Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getDetailsForTeam() {
        return getMemberDetails() + "\t\tDesigner\t\t" + getBonus();
    }

    @Override
    public String toString() {
        return getDetails() + "\t\tDesigner\t\t" + getStatus() + "\t\t\t\t\t" +
               getBonus() +"\t\t" + getEquipment().getDescription();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        jsonObject.put("bonus", bonus);
        jsonObject.put("type", "Designer");
        return jsonObject;
    }
}
