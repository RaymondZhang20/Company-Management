package com.xueyongzhang.team.domain;

public class Architect extends Designer {
    private Integer stock;

    public Architect(Integer id, String name, Integer age, double salary,
                     Equipment equipment, double bonus, Integer stock) {
        super(id, name, age, salary, equipment, bonus);
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDetailsForTeam() {
        return getMemberDetails() + "\t\tArchitect\t\t" +
               getBonus() + "\t\t" + getStock();
    }

    @Override
    public String toString() {
        return getDetails() + "\t\tArchitect\t\t" + getStatus() + "\t\t" +
               getBonus() + "\t\t" + getStock() + "\t\t" + getEquipment().getDescription();
    }
}
