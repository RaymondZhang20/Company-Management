package com.xueyongzhang.team.gui;

import com.xueyongzhang.team.domain.Architect;
import com.xueyongzhang.team.domain.Designer;
import com.xueyongzhang.team.domain.Employee;
import com.xueyongzhang.team.domain.Programmer;
import com.xueyongzhang.team.model.Staff;
import com.xueyongzhang.team.model.TeamException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class StaffPage extends JPanel implements ActionListener {
    public static final int WIDTH = 1300;
    public static final int HEIGHT = 1000;
    private JTable table;
    private Vector<String> titles;
    private Vector<Vector> tableData;
    private DefaultTableModel tableModel;
    private MainFrame main;
    protected Staff staff;

    public StaffPage(MainFrame main) {
        this.main = main;
        this.staff = main.staff;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLUE);
        setLayout(new BorderLayout());
        initializeTable();
        add(initializeButtons(),BorderLayout.NORTH);
    }

    private void initializeTable() {
        String[] title = {"ID","name","age","salary","position","status","bonus","stock","equipment"};
        titles = new Vector<>();
        for (String s : title) {
            titles.add(s);
        }
        tableData = new Vector<>();
        for (Employee e : staff) {
            String[] information;
            if (e instanceof Architect) {
                information =
                        new String[]{String.valueOf(e.getId()), e.getName(), String.valueOf(e.getAge()),
                                String.valueOf(e.getSalary()), "Architect", ((Architect) e).getStatus(),
                                String.valueOf(((Architect) e).getBonus()), String.valueOf(((Architect) e).getStock())
                                , ((Architect) e).getEquipment().getDescription()};
            } else if (e instanceof Designer) {
                information =
                        new String[]{String.valueOf(e.getId()), e.getName(), String.valueOf(e.getAge()),
                                String.valueOf(e.getSalary()), "Designer", ((Designer) e).getStatus(),
                                String.valueOf(((Designer) e).getBonus()), "",
                                ((Designer) e).getEquipment().getDescription()};
            } else if (e instanceof Programmer) {
                information =
                        new String[]{String.valueOf(e.getId()), e.getName(), String.valueOf(e.getAge()),
                                String.valueOf(e.getSalary()), "Programmer", ((Programmer) e).getStatus(),
                                "", "", ((Programmer) e).getEquipment().getDescription()};
            } else {
                information =
                        new String[]{String.valueOf(e.getId()), e.getName(), String.valueOf(e.getAge()),
                                String.valueOf(e.getSalary()), "Employee", "", "", "", ""};
            }
            Vector<String> informationV = new Vector<>();
            for (String s : information) {
                informationV.add(s);
            }
            tableData.add(informationV);
        }
        tableModel = new DefaultTableModel(tableData,titles);
        table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        table.getTableHeader().setFont(new Font("TimesRoman", Font.BOLD, 15));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private JPanel initializeButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(WIDTH, 50));
        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        JButton removeButton = new JButton("Remove");
        removeButton.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(editButton);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        editButton.addActionListener(this);
        return panel;
    }

    public void updateData() {
        tableData.clear();
        for (Employee e : main.staff) {
            String[] information;
            if (e instanceof Architect) {
                information =
                        new String[]{String.valueOf(e.getId()), e.getName(), String.valueOf(e.getAge()),
                                String.valueOf(e.getSalary()), "Architect", ((Architect) e).getStatus(),
                                String.valueOf(((Architect) e).getBonus()), String.valueOf(((Architect) e).getStock())
                                , ((Architect) e).getEquipment().getDescription()};
            } else if (e instanceof Designer) {
                information =
                        new String[]{String.valueOf(e.getId()), e.getName(), String.valueOf(e.getAge()),
                                String.valueOf(e.getSalary()), "Designer", ((Designer) e).getStatus(),
                                String.valueOf(((Designer) e).getBonus()), "",
                                ((Designer) e).getEquipment().getDescription()};
            } else if (e instanceof Programmer) {
                information =
                        new String[]{String.valueOf(e.getId()), e.getName(), String.valueOf(e.getAge()),
                                String.valueOf(e.getSalary()), "Programmer", ((Programmer) e).getStatus(),
                                "", "", ((Programmer) e).getEquipment().getDescription()};
            } else {
                information =
                        new String[]{String.valueOf(e.getId()), e.getName(), String.valueOf(e.getAge()),
                                String.valueOf(e.getSalary()), "Employee", "", "", "", ""};
            }
            Vector<String> informationV = new Vector<>();
            for (String s : information) {
                informationV.add(s);
            }
            tableData.add(informationV);
        }
        tableModel.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add":
                EmployeeFrame employeeFrame = new EmployeeFrame(this);
                break;
            case "Remove":
                int id = Integer.parseInt(JOptionPane.showInputDialog("Please type the employee's ID you want remove"));
                try {
                    staff.remove(staff.getEmployee(id));
                } catch (TeamException ex) {
                    JOptionPane.showMessageDialog(new JFrame("error")
                            ,"Failure,reason: cannot find the employee with the given ID");
                }
                updateData();
                break;
            case "Edit":
                int idEdit = Integer.parseInt(JOptionPane.showInputDialog("Please type the employee's ID you want edit"));
                try {
                    EmployeeFrame employeeFrameEdit = new EmployeeFrame(this,staff.getEmployee(idEdit));
                } catch (TeamException ex) {
                    JOptionPane.showMessageDialog(new JFrame("error")
                            ,"Failure,reason: cannot find the employee with the given ID");
                }
                break;
        }
    }
}
