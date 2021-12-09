package com.xueyongzhang.team.gui;

import com.xueyongzhang.team.model.Stuff;
import com.xueyongzhang.team.model.Team;

import javax.swing.*;
import java.util.List;

public class MainFrame extends JFrame {
    private Stuff stuff;
    private List<Team> teams;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 1000;

    public MainFrame() {
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,new ToolPanel(stuff,teams),new InformationPanel(stuff,teams));
        add(splitPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(320);
        setTitle("Team Management");
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
