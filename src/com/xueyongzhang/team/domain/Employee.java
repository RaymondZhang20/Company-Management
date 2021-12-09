package com.xueyongzhang.team.domain;

public class Employee {
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

        if (Double.compare(employee.getSalary(), getSalary()) != 0) return false;
        if (getId() != null ? !getId().equals(employee.getId()) : employee.getId() != null) return false;
        if (getName() != null ? !getName().equals(employee.getName()) : employee.getName() != null) return false;
        return getAge() != null ? getAge().equals(employee.getAge()) : employee.getAge() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
        temp = Double.doubleToLongBits(getSalary());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
