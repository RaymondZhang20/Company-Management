package com.xueyongzhang.team.domain;

import com.xueyongzhang.team.persistence.Writable;
import org.json.JSONObject;

public class Employee implements Writable {
    private Integer id;
    private String name;
    private Integer age;
    private double salary;

    public Employee(Integer id, String name, Integer age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    protected String getDetails() {
        return id + "\t\t" + name + "\t\t" + age+ "\t\t" +salary;
    }

    @Override
    public String toString() {
        return getDetails();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        return getId() != null ? getId().equals(employee.getId()) : employee.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "Employee");
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        jsonObject.put("age",age);
        jsonObject.put("salary",salary);
        return jsonObject;
    }
}
