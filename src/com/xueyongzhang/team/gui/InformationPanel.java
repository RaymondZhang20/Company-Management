package com.xueyongzhang.team.gui;

import com.xueyongzhang.team.model.Stuff;
import com.xueyongzhang.team.model.Team;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InformationPanel extends JPanel {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 1000;

    public InformationPanel(Stuff stuff, List<Team> teams) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
