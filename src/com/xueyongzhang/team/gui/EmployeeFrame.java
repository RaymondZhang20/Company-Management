package com.xueyongzhang.team.gui;

import com.xueyongzhang.team.domain.*;
import com.xueyongzhang.team.model.Staff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EmployeeFrame extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private JLabel nameLabel = new JLabel("name:");
    private JLabel ageLabel = new JLabel("age:");
    private JLabel salaryLabel = new JLabel("salary:");
    private JLabel bonusLabel = new JLabel("bonus:");
    private JLabel stockLabel = new JLabel("stock:");
    private JLabel equipInfor1Label = new JLabel("model:");
    private JLabel equipInfor2Label = new JLabel("display:");
    private JTextField name = new JTextField(15);
    private JTextField age = new JTextField(15);
    private JTextField salary = new JTextField(15);
    private JTextField bonus = new JTextField(15);
    private JTextField stock = new JTextField(15);
    private JTextField equipInfor1 = new JTextField(15);
    private JTextField equipInfor2 = new JTextField(15);
    private JComboBox<String> employee = new JComboBox<>();
    private JComboBox<String> equipment = new JComboBox<>();
    private JButton button = new JButton("Confirm");

    public EmployeeFrame(StaffPage staffPage) {
        Integer maxID = staffPage.staff.getMaxID();
        init();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipment equip;
                Employee employ;
                switch ((String) equipment.getSelectedItem()) {
                    case "PC":
                        equip = new PC(equipInfor1.getText(), equipInfor2.getText());
                        break;
                    case "Laptop":
                        equip = new Laptop(equipInfor1.getText(), Double.parseDouble(equipInfor2.getText()));
                        break;
                    default:
                        equip = new Printer(equipInfor1.getText(), equipInfor2.getText());
                        break;
                }
                switch ((String) employee.getSelectedItem()) {
                    case "Employee":
                        employ = new Employee(maxID + 1, name.getText(), Integer.parseInt(age.getText())
                                , Double.parseDouble(salary.getText()));
                        break;
                    case "Programmer":
                        employ = new Programmer(maxID + 1, name.getText(), Integer.parseInt(age.getText())
                                , Double.parseDouble(salary.getText()), equip);
                        break;
                    case "Designer":
                        employ = new Designer(maxID + 1, name.getText(), Integer.parseInt(age.getText())
                                , Double.parseDouble(salary.getText()), equip, Double.parseDouble(bonus.getText()));
                        break;
                    default:
                        employ = new Architect(maxID + 1, name.getText(), Integer.parseInt(age.getText())
                                , Double.parseDouble(salary.getText()), equip, Double.parseDouble(bonus.getText())
                        , Integer.parseInt(stock.getText()));
                        break;
                }
                staffPage.staff.add(employ);
                staffPage.updateData();
                dispose();
            }
        });
    }

    public EmployeeFrame(StaffPage staffPage, Employee employ) {
        init();
        if (employ.getClass().equals(Employee.class)) {
            name.setText(employ.getName());
            age.setText(String.valueOf(employ.getAge()));
            salary.setText(String.valueOf(employ.getSalary()));
        } else {
            Programmer pro = (Programmer) employ;
            Equipment equip = pro.getEquipment();
            if (equip.getClass().equals(PC.class)) {
                equipment.setSelectedIndex(0);
                equipInfor1.setText(((PC) equip).getModel());
                equipInfor2.setText(((PC) equip).getDisplay());
            } else if (equip.getClass().equals(Laptop.class)) {
                equipment.setSelectedIndex(1);
                equipInfor1.setText(((Laptop) equip).getModel());
                equipInfor2.setText(String.valueOf(((Laptop) equip).getPrice()));
            } else if (equip.getClass().equals(Printer.class)) {
                equipment.setSelectedIndex(2);
                equipInfor1.setText(((Printer) equip).getName());
                equipInfor2.setText(((Printer) equip).getType());
            }
            if (employ.getClass().equals(Programmer.class)) {
                employee.setSelectedIndex(1);
                name.setText(employ.getName());
                age.setText(String.valueOf(employ.getAge()));
                salary.setText(String.valueOf(employ.getSalary()));
            } else if (employ.getClass().equals(Designer.class)) {
                employee.setSelectedIndex(2);
                name.setText(employ.getName());
                age.setText(String.valueOf(employ.getAge()));
                salary.setText(String.valueOf(employ.getSalary()));
                bonus.setText(String.valueOf(((Designer) employ).getBonus()));
            } else if (employ.getClass().equals(Architect.class)) {
                employee.setSelectedIndex(3);
                name.setText(employ.getName());
                age.setText(String.valueOf(employ.getAge()));
                salary.setText(String.valueOf(employ.getSalary()));
                bonus.setText(String.valueOf(((Designer) employ).getBonus()));
                stock.setText(String.valueOf(((Architect) employ).getStock()));
            }
        }
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipment equip;
                switch ((String) equipment.getSelectedItem()) {
                    case "PC":
                        equip = new PC(equipInfor1.getText(), equipInfor2.getText());
                        break;
                    case "Laptop":
                        equip = new Laptop(equipInfor1.getText(), Double.parseDouble(equipInfor2.getText()));
                        break;
                    default:
                        equip = new Printer(equipInfor1.getText(), equipInfor2.getText());
                        break;
                }
                if (employ.getClass().equals(Employee.class)) {
                    employ.setName(name.getText());
                    employ.setAge(Integer.valueOf(age.getText()));
                    employ.setSalary(Double.parseDouble(salary.getText()));
                }else if (employ.getClass().equals(Programmer.class)) {
                    employ.setName(name.getText());
                    employ.setAge(Integer.valueOf(age.getText()));
                    employ.setSalary(Double.parseDouble(salary.getText()));
                    ((Programmer) employ).setEquipment(equip);
                } else if (employ.getClass().equals(Designer.class)) {
                    employ.setName(name.getText());
                    employ.setAge(Integer.valueOf(age.getText()));
                    employ.setSalary(Double.parseDouble(salary.getText()));
                    ((Programmer) employ).setEquipment(equip);
                    ((Designer) employ).setBonus(Double.parseDouble(bonus.getText()));
                } else if (employ.getClass().equals(Architect.class)) {
                    employ.setName(name.getText());
                    employ.setAge(Integer.valueOf(age.getText()));
                    employ.setSalary(Double.parseDouble(salary.getText()));
                    ((Programmer) employ).setEquipment(equip);
                    ((Designer) employ).setBonus(Double.parseDouble(bonus.getText()));
                    ((Architect) employ).setStock(Integer.valueOf(stock.getText()));
                }
                staffPage.updateData();
                dispose();
            }
        });
    }

    private void init() {
        setLayout(new GridLayout(6, 1, 10, 50));
        initEmployee();
        initEquipment();

        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();

        box1.add(employee);
        box1.add(Box.createHorizontalStrut(400));
        add(box1);

        box2.add(nameLabel);
        box2.add(name);
        box2.add(Box.createHorizontalStrut(50));
        box2.add(ageLabel);
        box2.add(age);
        box2.add(Box.createHorizontalStrut(50));
        box2.add(salaryLabel);
        box2.add(salary);
        add(box2);

        box3.add(bonusLabel);
        box3.add(bonus);
        box3.add(Box.createHorizontalStrut(100));
        box3.add(stockLabel);
        box3.add(stock);
        add(box3);

        box4.add(equipment);
        box4.add(Box.createHorizontalStrut(400));
        add(box4);

        box5.add(equipInfor1Label);
        box5.add(equipInfor1);
        box5.add(Box.createHorizontalStrut(100));
        box5.add(equipInfor2Label);
        box5.add(equipInfor2);
        add(box5);

        add(button);

        bonus.setEditable(false);
        stock.setEditable(false);
        equipment.setEditable(false);
        equipInfor1.setEditable(false);
        equipInfor2.setEditable(false);

        setFont(new Font("TimesRoman", Font.PLAIN, 20));
        setTitle("Information");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initEquipment() {
        equipment.addItem("PC");
        equipment.addItem("Laptop");
        equipment.addItem("Printer");
        equipment.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch ((String) equipment.getSelectedItem()) {
                    case "PC":
                        equipInfor1Label.setText("model");
                        equipInfor2Label.setText("display");
                        break;
                    case "Laptop":
                        equipInfor1Label.setText("model");
                        equipInfor2Label.setText("price");
                        break;
                    case "Printer":
                        equipInfor1Label.setText("name");
                        equipInfor2Label.setText("type");
                        break;
                }
            }
        });
    }

    private void initEmployee() {
        employee.addItem("Employee");
        employee.addItem("Programmer");
        employee.addItem("Designer");
        employee.addItem("Architect");
        employee.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch ((String) employee.getSelectedItem()) {
                    case "Employee":
                        bonus.setEditable(false);
                        stock.setEditable(false);
                        equipment.setEditable(false);
                        equipInfor1.setEditable(false);
                        equipInfor2.setEditable(false);
                        break;
                    case "Programmer":
                        bonus.setEditable(false);
                        stock.setEditable(false);
                        equipment.setEditable(true);
                        equipInfor1.setEditable(true);
                        equipInfor2.setEditable(true);
                        break;
                    case "Designer":
                        bonus.setEditable(true);
                        stock.setEditable(false);
                        equipment.setEditable(true);
                        equipInfor1.setEditable(true);
                        equipInfor2.setEditable(true);
                        break;
                    case "Architect":
                        bonus.setEditable(true);
                        stock.setEditable(true);
                        equipment.setEditable(true);
                        equipInfor1.setEditable(true);
                        equipInfor2.setEditable(true);
                        break;
                }
            }
        });
    }
}
