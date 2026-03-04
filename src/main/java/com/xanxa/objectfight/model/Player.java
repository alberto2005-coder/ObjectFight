package com.xanxa.objectfight.model;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
    private Color color;
    private Color borderColor;
    private double speedX;
    private double accelerationX;
    private int lessLife;

    public Player(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);
        this.speedX = 0;
        this.accelerationX = 0.1;
        this.lessLife = 50;
        this.color = color;
        this.borderColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 150);
    }

    @Override
    public boolean paint(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(this.color);
        g.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());

        g.setColor(this.borderColor);
        g.drawRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());

        g.setColor(oldColor);
        super.paint(g);
        return true;
    }

    @Override
    public boolean behaviour() {
        super.behaviour();

        setX(getX() + speedX);
        return true;
    }

    @Override
    public boolean isAlive() {
        return color.getAlpha() > 0;
    }

    public void updateSpeed(double right, double left) {

        if (right > 0) {
            speedX += accelerationX;
        } else if (left > 0) {
            speedX -= accelerationX;
        } else {
            speedX = 0;
        }
    }

    public void touched() {

        int alpha = color.getAlpha() - lessLife;
        if (alpha < 0)
            alpha = 0;
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setLessLife(int lessLife) {
        this.lessLife = lessLife;
    }
}
