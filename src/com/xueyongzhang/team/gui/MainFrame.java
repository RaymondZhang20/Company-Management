package com.xueyongzhang.team.gui;

import com.xueyongzhang.team.model.Staff;
import com.xueyongzhang.team.model.Team;
import com.xueyongzhang.team.persistence.JsonReader;
import com.xueyongzhang.team.persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame implements TreeSelectionListener {
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 1000;
    protected Staff staff = new Staff();
    protected StaffPage staffPage;
    protected List<Team> teams = new ArrayList<>();
    private JPanel informationPanel = new JPanel();
    private CardLayout cardLayout= new CardLayout();
    private JSplitPane splitPane;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE_STAFF = "./data/staff.json";
    private static final String JSON_STORE_TEAMS = "./data/teams.json";

    public MainFrame() {
        jsonWriter = new JsonWriter(JSON_STORE_STAFF, JSON_STORE_TEAMS);
        jsonReader = new JsonReader(JSON_STORE_STAFF, JSON_STORE_TEAMS);
        load();
        setJMenuBar(InitializeMenuBar());
        staffPage = new StaffPage(this);
        initializeInformationPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new ToolPanel(this),informationPanel);
        add(splitPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(320);
        setTitle("Team Management");
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void load() {
        try {
            staff = jsonReader.readStaff();
            teams = jsonReader.readTeams();
            JOptionPane.showMessageDialog(new JFrame(),"Data are successfully loaded from the database");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(),"Unable to find the database file");
        }
    }

    public void save() {
        try {
            jsonWriter.openStuff();
            jsonWriter.writeStaff(staff);
            jsonWriter.close();
            jsonWriter.openTeams();
            jsonWriter.writeTeams(teams);
            jsonWriter.close();
            JOptionPane.showMessageDialog(new JFrame(),"Successfully saved to the database");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(),"Unable to find the database file");
        }
    }

    private void initializeInformationPanel() {
        informationPanel.setLayout(cardLayout);
        informationPanel.add("staff",staffPage);
        for (int i = 0; i < teams.size(); i++) {
            informationPanel.add(teams.get(i).getName(), new TeamPage(this,i));
        }
    }

    private JMenuBar InitializeMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Settings");
        JMenuItem quit = new JMenuItem("quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
                System.exit(0);
            }
        });
        menu.add(quit);
        menuBar.add(menu);
        return menuBar;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        String event = e.getNewLeadSelectionPath().getLastPathComponent().toString();
        switch (event) {
            case "Staff":
                cardLayout.show(informationPanel,"staff");
                break;
            case "Click to create new team":
                String name = JOptionPane.showInputDialog("Please type the name for the new team");
                Team team = new Team(name);
                teams.add(team);
                informationPanel.add(name,new TeamPage(this,(teams.size() - 1)));
                updateUI();
                cardLayout.show(informationPanel,name);
                break;
            default:
                cardLayout.show(informationPanel,event);
        }
    }

    public void updateUI() {
        splitPane.updateUI();
        splitPane.setLeftComponent(new ToolPanel(this));
        splitPane.setDividerLocation(320);
        cardLayout.show(informationPanel,"staff");
    }
}
