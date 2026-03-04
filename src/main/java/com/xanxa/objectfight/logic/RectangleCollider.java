package com.xanxa.objectfight.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class RectangleCollider implements Collider {
    private double x;
    private double y;
    private double width;
    private double height;
    private Rectangle collisionRect;
    private Color debugColor;
    private Collider colliderDebug;

    public RectangleCollider(double x, double y, double width, double height) {
        this.debugColor = Color.RED;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collisionRect = new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    @Override
    public void updatePosition(double x, double y) {
        this.x = x;
        this.y = y;
        this.collisionRect.setLocation((int) x, (int) y);
    }

    @Override
    public boolean collide(Collider other) {
        boolean intersects = this.collisionRect.intersects(
                other.getX(), other.getY(), other.getWidth(), other.getHeight());
        this.colliderDebug = other;
        if (intersects) {
            this.debugColor = Color.GREEN;
        } else {
            this.debugColor = Color.MAGENTA;
        }
        return intersects;
    }

    @Override
    public boolean collide(Point2D point) {
        return this.collisionRect.contains(point);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void setX(double x) {
        this.x = x;
        this.collisionRect.setLocation((int) x, (int) this.y);
    }

    @Override
    public void setY(double y) {
        this.y = y;
        this.collisionRect.setLocation((int) this.x, (int) y);
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
        this.collisionRect.setSize((int) width, (int) this.height);
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
        this.collisionRect.setSize((int) this.width, (int) height);
    }

    @Override
    public void paintDebug(Graphics g) {
    }

    @Override
    public void setDebugColor(Color color) {
        this.debugColor = color;
    }

    @Override
    public Point2D getLeft() {
        return new Point2D.Double(x - width, y);
    }

    @Override
    public Point2D getRight() {
        return new Point2D.Double(x + width, y);
    }

    @Override
    public Point2D getBottom() {
        return new Point2D.Double(x, y + height);
    }

    @Override
    public Point2D getTop() {
        return new Point2D.Double(x, y - height);
    }

    @Override
    public Rectangle getRectangle() {
        return collisionRect;
    }
}
