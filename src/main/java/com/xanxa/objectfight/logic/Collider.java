package com.xanxa.objectfight.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public interface Collider {
    boolean collide(Collider other);
    boolean collide(Point2D point);
    void updatePosition(double x, double y);

    double getWidth();
    double getHeight();
    double getX();
    double getY();

    void setX(double x);
    void setY(double y);
    void setWidth(double width);
    void setHeight(double height);

    void paintDebug(Graphics g);
    void setDebugColor(Color color);

    Point2D getLeft();
    Point2D getRight();
    Point2D getBottom();
    Point2D getTop();

    Rectangle getRectangle();
}
