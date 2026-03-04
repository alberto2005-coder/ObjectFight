package com.xanxa.objectfight.view;

import com.xanxa.objectfight.logic.GameManager;
import com.xanxa.objectfight.model.Ball;

import com.xanxa.objectfight.model.Player;
import com.xanxa.objectfight.model.Wall;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class GameWindow extends JFrame implements KeyListener {
    private GameManager manager;
    private GameCanvas canvas;
    private boolean running;
    private final double FPMILLIS = 16.666;

    public GameWindow() {
        this.manager = new GameManager();
        this.canvas = new GameCanvas(manager);

        setTitle("ObjectFight");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(canvas);
        canvas.addKeyListener(this);
        canvas.setFocusable(true);
        canvas.requestFocusInWindow();

        this.running = true;

        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {

        while (canvas.getWidth() == 0) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }

        int margin = 50;
        manager.setGameZone(
                new Rectangle(margin, margin, canvas.getWidth() - margin * 2, canvas.getHeight() - margin * 2));
        startPhase(1);

        long lastTime = System.currentTimeMillis();

        while (running) {
            long now = System.currentTimeMillis();
            if (now - lastTime >= FPMILLIS) {
                manager.fixedUpdate();

                checkLevelGeneration();

                canvas.repaint();
                lastTime = now;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int lastLoadedPhase = 0;

    private void checkLevelGeneration() {
        if (manager.isVictory())
            return;

        if (manager.getCurrentPhase() > lastLoadedPhase) {
            startPhase(manager.getCurrentPhase());
        }
    }

    private void startPhase(int phase) {
        manager.clearGameObjects();
        lastLoadedPhase = phase;

        Rectangle zone = manager.getGameZone();
        int width = zone.width;
        int height = zone.height;
        int startX = zone.x;
        int startY = zone.y;

        double playerW = 100;
        double playerH = 20;
        double playerX = startX + width / 2.0 - playerW / 2.0;
        double playerY = startY + height - playerH * 2.0;

        Player p1 = new Player(playerX, playerY, playerW, playerH, Color.GREEN);
        manager.addGameObject(p1);

        Ball ball = new Ball(startX + width / 2.0, startY + height / 2.0, 20, 20, Color.RED);
        ball.setSpeedX((3.0 + phase) / 2.0);
        ball.setSpeedY((-3.0 - phase) / 2.0);
        manager.addGameObject(ball);

        generateWalls(phase, zone);
    }

    private void generateWalls(int phase, Rectangle zone) {
        int rows = 2 + phase;
        int cols = 5 + phase;

        int width = zone.width;
        int startXZone = zone.x;
        int startYZone = zone.y;

        int wallW = width / (cols + 2);
        int wallH = 30;
        int gap = 5;

        int startX = startXZone + (width - (cols * (wallW + gap))) / 2;
        int startY = startYZone + 50;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                int lives = 1;

                Color color = new Color(
                        (255 / (r + 1)),
                        (255 / (c + 1)),
                        255);

                Wall w = new Wall(
                        startX + c * (wallW + gap),
                        startY + r * (wallH + gap),
                        wallW, wallH,
                        lives,
                        color);
                manager.addGameObject(w);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            manager.rightPushed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            manager.leftPushed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            manager.setPaused(!manager.isPaused());
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (manager.isGameOver() || manager.isVictory()) {
                resetGame();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            if (manager.isPaused() || manager.isGameOver() || manager.isVictory()) {

                this.dispose();
                new MainMenu().setVisible(true);
                running = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            manager.rightPushed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            manager.leftPushed(false);
        }
    }

    private void resetGame() {
        manager.resetGame();
        lastLoadedPhase = 0;

    }
}
