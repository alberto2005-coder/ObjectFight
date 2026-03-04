package com.xanxa.objectfight.logic;

import com.xanxa.objectfight.model.*;

import java.awt.*;

import java.util.ArrayList;

import java.util.List;

import java.util.concurrent.locks.ReentrantLock;

public class GameManager {
    private final List<GameObject> gameObjects;
    private Rectangle gameZone;
    private double rightPushed;
    private double leftPushed;

    private int score;
    private int currentPhase;
    private int lives;
    private boolean gameOver;
    private boolean victory;
    private boolean paused;

    private final ReentrantLock lock = new ReentrantLock();

    public GameManager() {
        this.gameObjects = new ArrayList<>();
        this.score = 0;
        this.currentPhase = 1;
        this.lives = 3;
        this.gameOver = false;
        this.victory = false;
        this.paused = false;
    }

    public void update(Graphics g) {
        lock.lock();
        try {
            if (gameZone != null) {
                g.setColor(Color.WHITE);
                g.fillRect(gameZone.x, gameZone.y, gameZone.width, gameZone.height);
            }

            for (GameObject obj : gameObjects) {
                obj.paint(g);
            }
        } finally {
            lock.unlock();
        }
    }

    public void fixedUpdate() {
        if (paused || gameOver || victory)
            return;

        lock.lock();
        try {
            for (int i = gameObjects.size() - 1; i >= 0; i--) {
                GameObject obj = gameObjects.get(i);

                List<GameObject> collisions = GameObject.collision(obj, gameObjects);
                solveCollision(obj, collisions);

                if (obj instanceof Player) {
                    Player p = (Player) obj;
                    p.updateSpeed(rightPushed, leftPushed);
                }

                if (!obj.isAlive()) {
                    obj.stop();
                    if (obj instanceof Wall) {
                        score += 100;
                    }
                    gameObjects.remove(i);
                }
            }

            checkPhaseCompletion();
            checkGameOver();
        } finally {
            lock.unlock();
        }
    }

    private void checkPhaseCompletion() {
        boolean wallsRemaining = false;
        for (GameObject obj : gameObjects) {
            if (obj instanceof Wall) {
                wallsRemaining = true;
                break;
            }
        }

        if (!wallsRemaining) {
            nextPhase();
        }
    }

    public void nextPhase() {
        currentPhase++;
        if (currentPhase > 5) {
            victory = true;
        } else {
        }
    }

    private void checkGameOver() {
        if (lives <= 0) {
            gameOver = true;
        }
    }

    private boolean solveCollision(GameObject obj, List<GameObject> others) {
        boolean handled = false;
        if (obj instanceof Ball) {
            Ball ball = (Ball) obj;
            for (GameObject other : others) {
                if (other instanceof Wall) {
                    Wall wall = (Wall) other;
                    wall.touched();
                    ball.goAway(wall);
                } else if (other instanceof Player) {
                    Player p = (Player) other;
                    if (ball.goAway(p)) {
                        ball.setSpeedX(ball.getSpeedX() + p.getSpeedX());
                    }
                }
            }
            checkBallInside(ball);
            handled = true;
        }
        return handled;
    }

    private void checkBallInside(Ball ball) {
        if (gameZone == null)
            return;

        if (!gameZone.contains(ball.getCollider().getRectangle())) {

            if (ball.getLeft().getX() < gameZone.getMinX()) {
                if (ball.getSpeedX() < 0)
                    ball.setSpeedX(-ball.getSpeedX());
            }
            if (ball.getTop().getY() < gameZone.getMinY()) {
                if (ball.getSpeedY() < 0)
                    ball.setSpeedY(-ball.getSpeedY());
            }
            if (ball.getRight().getX() > gameZone.getMaxX()) {
                if (ball.getSpeedX() > 0)
                    ball.setSpeedX(-ball.getSpeedX());
            }

            if (ball.getTop().getY() > gameZone.getMaxY()) {
                lives--;
                if (lives > 0) {
                    resetBallPosition(ball);
                } else {
                    gameOver = true;
                }
            }
        }
    }

    private void resetBallPosition(Ball ball) {
        ball.setX(gameZone.getCenterX() - ball.getWidth() / 2);
        ball.setY(gameZone.getMaxY() - 100);
        ball.setSpeedY(-Math.abs(ball.getSpeedY()));
    }

    public Rectangle getGameZone() {
        return gameZone;
    }

    public void setGameZone(Rectangle gameZone) {
        this.gameZone = gameZone;
    }

    public void addGameObject(GameObject obj) {
        lock.lock();
        try {
            gameObjects.add(obj);
            obj.start();
        } finally {
            lock.unlock();
        }
    }

    public void clearGameObjects() {
        lock.lock();
        try {
            for (GameObject obj : gameObjects) {
                obj.stop();
            }
            gameObjects.clear();
        } finally {
            lock.unlock();
        }
    }

    public void rightPushed(boolean pushed) {
        this.rightPushed = pushed ? 1.0 : 0.0;
    }

    public void leftPushed(boolean pushed) {
        this.leftPushed = pushed ? 1.0 : 0.0;
    }

    public int getScore() {
        return score;
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    public int getLives() {
        return lives;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isVictory() {
        return victory;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void resetGame() {
        lock.lock();
        try {
            score = 0;
            currentPhase = 1;
            lives = 3;
            gameOver = false;
            victory = false;
            paused = false;
            clearGameObjects();
        } finally {
            lock.unlock();
        }
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}