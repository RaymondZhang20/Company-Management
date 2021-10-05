package com.xueyongzhang.team.model;


public class Data {
    public static final int EMPLOYEE = 10;
    public static final int PROGRAMMER = 11;
    public static final int DESIGNER = 12;
    public static final int ARCHITECT = 13;

    public static final int PC = 21;
    public static final int LAPTOP = 22;
    public static final int PRINTER = 23;

    //Employee  :  10, id, name, age, salary
    //Programmer:  11, id, name, age, salary
    //Designer  :  12, id, name, age, salary, bonus
    //Architect :  13, id, name, age, salary, bonus, stock
    public static final String[][] EMPLOYEES = {
        {"10", "1", "Raymond", "22", "3000"},
        {"13", "2", "William", "32", "18000", "15000", "2000"},
        {"11", "3", "Tom    ", "23", "7000"},
        {"11", "4", "Harry  ", "24", "7300"},
        {"12", "5", "Robert ", "28", "10000", "5000"},
        {"11", "6", "Jack   ", "22", "6800"},
        {"12", "7", "Mike   ", "29", "10800", "5200"},
        {"13", "8", "Tony   ", "30", "19800", "15000", "2500"},
        {"12", "9", "Bill   ", "26", "9800", "5500"},
        {"11", "10", "Henry ", "21", "6600"},
        {"11", "11", "Joey  ", "25", "7100"},
        {"12", "12", "Maven ", "27", "9600", "4800"}
    };
    

    //PC      :21, model, display
    //Laptop  :22, model, price
    //Printer :23, type, name
    public static final String[][] EQIPMENTS = {
        {},
        {"22", "LenovoT4", "6000"},
        {"21", "Dell    ", "17inch"},
        {"21", "Dell    ", "17inch"},
        {"23", "Canon   ", "2900"},
        {"21", "Mac     ", "13inch"},
        {"21", "MacPro  ", "15inch"},
        {"23", "Fuji    ", "20K"},
        {"22", "HPm6    ", "5800"},
        {"21", "Dell    ", "17inch"},
        {"21", "MacAir  ","11inch"},
        {"22", "HPm6    ", "5800"}
    };
}
