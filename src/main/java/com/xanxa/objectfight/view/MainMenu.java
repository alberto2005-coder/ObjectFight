package com.xanxa.objectfight.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Box;
import java.awt.Component;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("ObjectFight - Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("ObjectFight");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 30));

        JButton startBtn = new JButton("Start Game");
        startBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        startBtn.addActionListener((ActionEvent e) -> {
            startGame();
        });

        JButton exitBtn = new JButton("Exit");
        exitBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        exitBtn.addActionListener(e -> System.exit(0));

        Box box = Box.createVerticalBox();
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        box.add(Box.createVerticalStrut(20));
        box.add(title);
        box.add(Box.createVerticalStrut(50));
        box.add(startBtn);
        box.add(Box.createVerticalStrut(20));
        box.add(exitBtn);

        panel.add(box);
        add(panel);
    }

    private void startGame() {
        this.setVisible(false);
        SwingUtilities.invokeLater(() -> {
            new GameWindow().setVisible(true);
        });
    }
}
