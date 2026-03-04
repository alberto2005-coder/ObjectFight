package com.xanxa.objectfight.view;

import com.xanxa.objectfight.logic.GameManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameCanvas extends JPanel {
    private GameManager manager;

    public GameCanvas(GameManager manager) {
        this.manager = manager;
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (manager == null)
            return;

        manager.update(g);

        renderUI(g);
    }

    private void renderUI(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));

        g.drawString("Puntuación: " + manager.getScore(), 20, 30);
        g.drawString("Fase: " + manager.getCurrentPhase(), 200, 30);
        g.drawString("Vidas: " + manager.getLives(), 350, 30);

        if (manager.isGameOver()) {
            drawCenteredString(g, "JUEGO TERMINADO", getWidth() / 2, getHeight() / 2, 40, Color.RED);
            drawCenteredString(g, "Presiona ENTER para reiniciar", getWidth() / 2, getHeight() / 2 + 50, 20,
                    Color.BLACK);
        } else if (manager.isVictory()) {
            drawCenteredString(g, "¡VICTORIA!", getWidth() / 2, getHeight() / 2, 40, Color.BLUE);
            drawCenteredString(g, "Presiona ENTER para reiniciar", getWidth() / 2, getHeight() / 2 + 50, 20,
                    Color.BLACK);
        } else if (manager.isPaused()) {
            drawCenteredString(g, "PAUSADO", getWidth() / 2, getHeight() / 2, 40, Color.ORANGE);
            drawCenteredString(g, "Presiona ESC para continuar", getWidth() / 2, getHeight() / 2 + 50, 20, Color.BLACK);
            drawCenteredString(g, "Presiona Q para salir", getWidth() / 2, getHeight() / 2 + 80, 20, Color.BLACK);
        }
    }

    private void drawCenteredString(Graphics g, String text, int x, int y, int size, Color c) {
        Font font = new Font("Arial", Font.BOLD, size);
        g.setFont(font);
        g.setColor(c);
        int width = g.getFontMetrics().stringWidth(text);
        g.drawString(text, x - width / 2, y);
    }
}
