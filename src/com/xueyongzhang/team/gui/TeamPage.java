package com.xueyongzhang.team.gui;

import com.xueyongzhang.team.domain.Architect;
import com.xueyongzhang.team.domain.Designer;
import com.xueyongzhang.team.domain.Employee;
import com.xueyongzhang.team.domain.Programmer;
import com.xueyongzhang.team.model.Staff;
import com.xueyongzhang.team.model.Team;
import com.xueyongzhang.team.model.TeamException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class TeamPage extends JPanel {
    public static final int WIDTH = 1300;
    public static final int HEIGHT = 1000;
    private JTable table;
    private Vector<String> titles;
    private Vector<Vector> tableData;
    private DefaultTableModel tableModel;
    private Staff staff;
    private Team team;
    private MainFrame main;

    public TeamPage(MainFrame main, Integer index) {
        this.main = main;
        this.staff = main.staff;
        this.team = main.teams.get(index);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLUE);
        setLayout(new BorderLayout());
        initializeTable();
        add(initializeButtons(),BorderLayout.NORTH);
    }

    private void initializeTable() {
        String[] title = {"TID/ID","name","age","salary","position","bonus","stock"};
        titles = new Vector<>();
        for (String s : title) {
            titles.add(s);
        }
        tableData = new Vector<>();
        for (Programmer p : team) {
            String[] information;
            if (p instanceof Architect) {
                information =
                        new String[]{p.getMemberId() + "/" + p.getId(), p.getName(), String.valueOf(p.getAge()),
                                String.valueOf(p.getSalary()), "Architect",
                                String.valueOf(((Architect) p).getBonus()), String.valueOf(((Architect) p).getStock())};
            } else if (p instanceof Designer) {
                information =
                        new String[]{p.getMemberId() + "/" + p.getId(), p.getName(), String.valueOf(p.getAge()),
                                String.valueOf(p.getSalary()), "Designer",
                                String.valueOf(((Designer) p).getBonus()), "",};
            } else {
                information =
                        new String[]{p.getMemberId() + "/" + p.getId(), p.getName(), String.valueOf(p.getAge()),
                                String.valueOf(p.getSalary()), "Programmer", "", "",};
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
        table.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        table.getTableHeader().setFont(new Font("TimesRoman", Font.BOLD, 18));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private JPanel initializeButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(WIDTH, 50));
        JLabel label = new JLabel("Team Name:");
        label.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        JTextField teamName = new JTextField(team.getName(),15);
        teamName.setEditable(false);
        teamName.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        JButton removeButton = new JButton("Remove");
        removeButton.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        JButton remindButton = new JButton("Reminder");
        remindButton.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        JButton dismissButton = new JButton("Dismiss team");
        dismissButton.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        panel.add(label);
        panel.add(teamName);
        panel.add(Box.createVerticalStrut(30));
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(remindButton);
        panel.add(dismissButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Please type the ID"));
                try {
                    Employee employee = staff.getEmployee(id);
                    team.addMember(employee);
                } catch (TeamException employee) {
                    JOptionPane.showMessageDialog(new JFrame("error"),"Failure,reason:" + employee.getMessage());
                }
                updateData();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Please type the TID"));
                try {
                    team.removeMember(id);
                } catch (TeamException employee) {
                    JOptionPane.showMessageDialog(new JFrame("error"),"Failure,reason:" + employee.getMessage());
                }
                updateData();
            }
        });
        remindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(new JFrame(),"A team member must be a programmer or superior\n" +
                        "At most 5 members in a team\n" + "At most 1 architect is allowed in the team\n" +
                        "At most 2 designers are allowed in the team\n" + "At most 3 programmers are allowed in the team");
            }
        });
        dismissButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer option = JOptionPane.showConfirmDialog(new JFrame(), "Do you want to dismiss the team?");
                if (option.equals(JOptionPane.YES_OPTION)) {
                    for (Programmer p : team) {
                        p.setTeam(null);
                        p.setStatus();
                    }
                    main.teams.remove(team);
                    main.updateUI();
                    updateData();
                }
            }
        });
        return panel;
    }

    public void updateData() {
        tableData.clear();
        for (Programmer p : team) {
            String[] information;
            if (p instanceof Architect) {
                information =
                        new String[]{p.getMemberId() + "/" + p.getId(), p.getName(), String.valueOf(p.getAge()),
                                String.valueOf(p.getSalary()), "Architect",
                                String.valueOf(((Architect) p).getBonus()), String.valueOf(((Architect) p).getStock())};
            } else if (p instanceof Designer) {
                information =
                        new String[]{p.getMemberId() + "/" + p.getId(), p.getName(), String.valueOf(p.getAge()),
                                String.valueOf(p.getSalary()), "Designer",
                                String.valueOf(((Designer) p).getBonus()), "",};
            } else {
                information =
                        new String[]{p.getMemberId() + "/" + p.getId(), p.getName(), String.valueOf(p.getAge()),
                                String.valueOf(p.getSalary()), "Programmer", "", "",};
            }
            Vector<String> informationV = new Vector<>();
            for (String s : information) {
                informationV.add(s);
            }
            tableData.add(informationV);
        }
        tableModel.fireTableDataChanged();
        main.staffPage.updateData();
    }
}
