package com.xanxa.objectfight.model;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends GameObject {
    private int lives;
    private int maxLives;
    private Color color;
    private Color borderColor;

    public Wall(double x, double y, double width, double height, int lives, Color color) {
        super(x, y, width, height);
        this.lives = lives;
        this.maxLives = lives;
        this.color = color;

    }

    public void setBorderColor(Color c) {
        this.borderColor = c;
    }

    @Override
    public boolean paint(Graphics g) {
        Color old = g.getColor();
        g.setColor(this.color);
        g.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());

        g.setColor(this.borderColor);
        g.drawRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());

        super.paint(g);

        g.setColor(old);
        return isAlive();
    }

    @Override
    public boolean isAlive() {
        return lives > 0;
    }

    public void touched() {
        this.lives--;

        if (this.lives > 0) {
            float ratio = (float) lives / (float) maxLives;
            int alpha = (int) (255 * ratio);
            if (alpha > 255)
                alpha = 255;
            if (alpha < 0)
                alpha = 0;

            this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
        }
    }
}
