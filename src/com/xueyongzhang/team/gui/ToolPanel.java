package com.xueyongzhang.team.gui;

import com.xueyongzhang.team.model.Team;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;

public class ToolPanel extends JPanel {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 1000;
    private List<Team> teams;
    private JTree tree = new JTree();

    public ToolPanel(MainFrame main) {
        teams = main.teams;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Company");
        DefaultMutableTreeNode staff = new DefaultMutableTreeNode("Staff");
        DefaultMutableTreeNode team = new DefaultMutableTreeNode("Teams");
        DefaultMutableTreeNode createNewTeam = new DefaultMutableTreeNode("Click to create new team");
        root.add(staff);
        root.add(team);
        team.add(createNewTeam);
        if (teams != null) {
            for (Team t : teams) {
                team.add(new DefaultMutableTreeNode(t.getName()));
            }
        }
        tree.setModel(new DefaultTreeModel(root));
        tree.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        tree.addTreeSelectionListener(main);
        tree.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        add(tree);
    }

    public void updateTeams(List<Team> teams) {
        this.teams = teams;
    }
}
