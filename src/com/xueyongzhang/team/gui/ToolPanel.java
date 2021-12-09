package com.xueyongzhang.team.gui;

import com.xueyongzhang.team.model.Stuff;
import com.xueyongzhang.team.model.Team;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ToolPanel extends JPanel {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 1000;

    public ToolPanel(Stuff stuff, List<Team> teams) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JTree tree = new JTree();
        tree.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(tree);
    }
}
