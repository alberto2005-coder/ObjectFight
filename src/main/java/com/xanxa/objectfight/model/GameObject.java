package com.xanxa.objectfight.model;

import com.xanxa.objectfight.logic.Collider;
import com.xanxa.objectfight.logic.RectangleCollider;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class GameObject implements Runnable {
    protected Collider col;
    protected Thread thread;
    protected boolean running;
    protected Image sprite;

    public GameObject(double x, double y, double width, double height) {
        this.col = new RectangleCollider(x, y, width, height);
    }

    public static List<GameObject> collision(GameObject obj, List<GameObject> gameObjects) {
        List<GameObject> collided = new ArrayList<>();
        for (GameObject other : gameObjects) {
            if (obj != other && obj.collides(other)) {
                collided.add(other);
            }
        }
        return collided;
    }

    public boolean paint(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
        } else {
            this.col.paintDebug(g);
        }
        return true;
    }

    public void setSprite(String path) {
        try {
            this.sprite = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println("Error loading sprite: " + path);
            this.sprite = null;
        }
    }

    public void start() {
        if (thread == null || !thread.isAlive()) {
            this.running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running && isAlive()) {
            behaviour();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean behaviour() {
        return isAlive();
    }

    public abstract boolean isAlive();

    public boolean collides(GameObject other) {
        return this.col.collide(other.getCollider());
    }

    public Collider getCollider() {
        return col;
    }

    public double getX() {
        return col.getX();
    }

    public double getY() {
        return col.getY();
    }

    public double getWidth() {
        return col.getWidth();
    }

    public double getHeight() {
        return col.getHeight();
    }

    public void updatePosition(double x, double y) {

        setX(x);
        setY(y);
    }

    public void setX(double x) {
        this.col.setX(x);
    }

    public void setY(double y) {
        this.col.setY(y);
    }

    public void setWidth(double width) {
        col.setWidth(width);
    }

    public void setHeight(double height) {
        col.setHeight(height);
    }

    public Point2D getLeft() {
        return new Point2D.Double(getX(), getY());
    }

    public Point2D getRight() {
        return new Point2D.Double(getX() + getWidth(), getY());
    }

    public Point2D getBottom() {
        return new Point2D.Double(getX(), getY() + getHeight());
    }

    public Point2D getTop() {
        return new Point2D.Double(getX(), getY());
    }
}
