package app;
import java.util.EventListener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class SnakeGame extends JPanel implements KeyListener, ActionListener{

    private int boardHeight;
    private int boardWidth;
    private Tile snakeHead;
    private Tile food;
    private Random random;
    private Timer gameLoop;
    private int velocityX;
    private int velocityY;
    private ArrayList<Tile> snakeBody;
    
    public SnakeGame(int boardHeight, int boardWidth) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;

        // Create game canvas
        setPreferredSize(new DimensionUIResource(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
        System.out.println(5 * Tile.tileWidth());
        this.snakeHead = new Tile(5, 5);
        this.food = new Tile(10, 10);
        this.random = new Random();
        //placeFood();

        this.snakeBody = new ArrayList<>();

        this.velocityX = 1;
        this.velocityY = 0;
        
		//game timer
        // tells the listener what to do every 100ms
		gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        // Removes the preivous painting done
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {

        g.setColor(Color.darkGray);

        // Draws the grid
        for(int i = 0; i < boardWidth/Tile.tileWidth(); i++) {
            // this methods draws a line between 2 points
            // Draws the horizontal line from y = 0 to y = windowWidth seperated by 25 each
            // Draws a 1 pixel line
            g.drawLine(i * Tile.tileWidth(), 0, i * Tile.tileWidth(), this.boardHeight);
            g.drawLine(0, i * Tile.tileWidth(), this.boardWidth, i * Tile.tileWidth());
        }

        //Snake Head
        g.setColor(Color.green);
        g.fill3DRect(snakeHead.getPixelX(), snakeHead.getPixelY(), Tile.tileWidth(), Tile.tileWidth(), true);

        // Draws snakeBody
        g.setColor(Color.green);
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            g.fill3DRect(snakePart.getPixelX(), snakePart.getPixelY(), Tile.tileWidth(), Tile.tileWidth(), true);
        }


        g.setColor(Color.red);
        g.fill3DRect(food.getPixelX(), food.getPixelY(), Tile.tileWidth(), Tile.tileWidth(), true);

        //Score
        g.setFont(new Font("Arial", Font.PLAIN, 16));

        if (!isGameOver()) {
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(snakeBody.size()), Tile.tileWidth() - 16, Tile.tileWidth());
        }
        else {
            g.drawString("Score: " + String.valueOf(snakeBody.size()), Tile.tileWidth() - 16, Tile.tileWidth());
        }
	}

    private void placeFood() {
        // generates a tile number from 0 to 24
        int y = random.nextInt(24);
        int x = random.nextInt(24);

        food.setX(x);
        food.setY(y);
    }

    private static boolean collision(Tile tile1, Tile tile2) {
        return tile1.getX() == tile2.getX() && tile1.getY() == tile2.getY();
    }

    private void move() {
        if (collision(snakeHead, food)) {
            // if you eat a food you can add the tile of the food to the end of the snake's body
            snakeBody.add(new Tile(food.getX(), food.getY()));
            placeFood();
        }

        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            // update the first part of the snake to the original position of the head
            // thats why we cant update the snake's head beforehands
            if (i == 0) {
                snakePart.setX(snakeHead.getX());
                snakePart.setY(snakeHead.getY());
            } else {
                Tile lastPart = snakeBody.get(i - 1);
                snakePart.setX(lastPart.getX());
                snakePart.setY(lastPart.getY());
            }
        }


        int newX = snakeHead.getX() + velocityX;
        int newY = snakeHead.getY() + velocityY; 
    
        snakeHead.setX(newX);
        snakeHead.setY(newY);

    }

    private boolean isGameOver() {
        if (snakeHead.getPixelX() >= boardWidth || snakeHead.getPixelX() < 0 || snakeHead.getPixelY() >= boardHeight || snakeHead.getPixelY() < 0) {
            return false;
        }

        for (Tile snakePart: snakeBody) {
            if (collision(snakePart, snakeHead)) {
                return false;
            }
        }
        
        return true;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        // If the key is up and they are not already going down
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        // If the key is down and they are not already going up
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        // If the key is left and they are not going right
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        // If the key is right and they are not going left
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // calls draw over and over aagain
        move();
        repaint();
        if (!isGameOver()) {
            gameLoop.stop();
        }
    }

}
