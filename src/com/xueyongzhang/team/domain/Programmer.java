package com.xueyongzhang.team.domain;

import com.xueyongzhang.team.domain.Employee;
import com.xueyongzhang.team.domain.Equipment;

public class Programmer extends Employee {
    private Integer memberId;
    private String status = "FREE";
    private Equipment equipment;

    public Programmer(Integer id, String name, Integer age,
                       double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public String getStatus() {
        return status;
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
        return getDetails() + "\t\tProgrammer\t\t" + status + "\t\t\t\t\t\t\t\t" + equipment.getDescription() ;
    }
}
