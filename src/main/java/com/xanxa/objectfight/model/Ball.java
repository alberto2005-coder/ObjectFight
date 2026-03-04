package com.xanxa.objectfight.model;

import com.xanxa.objectfight.logic.Collider;
import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject {
    private Color color;
    private boolean alive;
    private double speedX;
    private double speedY;

    public Ball(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);
        this.alive = true;
        this.speedX = -1.0;
        this.speedY = -1.0;
        this.color = color;
    }

    @Override
    public boolean paint(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(this.color);
        g.fillOval((int) col.getX(), (int) col.getY(), (int) col.getWidth(), (int) col.getHeight());
        g.setColor(oldColor);
        super.paint(g);
        return isAlive();
    }

    @Override
    public boolean behaviour() {
        super.behaviour();
        boolean active = isAlive();

        double nextX = getX() + speedX;
        double nextY = getY() + speedY;

        updatePosition(nextX, nextY);

        return active;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    public boolean goAway(GameObject other) {
        this.col.setDebugColor(Color.CYAN);
        Collider otherCol = other.getCollider();

        boolean hit = false;

        if (otherCol.collide(this.getBottom())) {
            if (this.speedY > 0) {
                this.speedY = -this.speedY;
            }
            hit = true;
        } else if (otherCol.collide(this.getTop())) {
            if (this.speedY < 0) {
                this.speedY = -this.speedY;
            }
            hit = true;
        } else if (otherCol.collide(this.getLeft())) {
            if (this.speedX < 0) {
                this.speedX = -this.speedX;
            }
            hit = true;
        } else if (otherCol.collide(this.getRight())) {
            if (this.speedX > 0) {
                this.speedX = -this.speedX;
            }
            hit = true;
        }

        return hit;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }
}
