import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int[] snakeXLenght = new int[750];
    private int[] snakeYLenght = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
//    private boolean isRunning = true;

    private ImageIcon rightFace;
    private ImageIcon leftFace;
    private ImageIcon upFace;
    private ImageIcon downFace;

    private int lenghtOfSnake = 3;


    private Timer timer;
    private int delay = 100;

    private ImageIcon snakeImage;

    private int[] enemyXPos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425,
            450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] enemyYPos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425,
            450, 475, 500, 525, 575, 600, 625};

    private ImageIcon enemyImage;
    private ImageIcon cupImage;
    private Random random = new Random();

    private int xPos = random.nextInt(34);
    private int yPos = random.nextInt(22);

    private int score = 0;

    private ImageIcon titleImage;

    private int moves = 0;

    public Gameplay() {

        addKeyListener(this);
        setFocusable(true);
        setRequestFocusEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics graphics) {

        if (moves == 0) {
            snakeXLenght[2] = 50;
            snakeXLenght[1] = 75;
            snakeXLenght[0] = 100;

            snakeYLenght[2] = 100;
            snakeYLenght[1] = 100;
            snakeYLenght[0] = 100;
        }

        // draw title image border
        graphics.setColor(Color.white);
        graphics.drawRect(24, 10, 851, 55);

        // draw the title image

        titleImage = new ImageIcon("images/snaketitle.jpg");
        titleImage.paintIcon(this, graphics, 25, 11);

        //draw border for gameplay

        graphics.setColor(Color.white);
        graphics.drawRect(24, 74, 851, 577);

        // draw background for the game play
        graphics.setColor(Color.black);
        graphics.fillRect(25, 75, 850, 575);

        // draw scores
        enemyImage = new ImageIcon("images/enemy.png");
        graphics.setColor(Color.white);
        graphics.setFont(new Font("comic sans MS", Font.PLAIN, 14));
        enemyImage.paintIcon(this, graphics, 780, 10);
        graphics.drawString(": " + score, 806, 33);

        //draw lenght of snake
        cupImage = new ImageIcon("images/cupImage.png");
        graphics.setColor(Color.white);
        graphics.setFont(new Font("comic sans MS", Font.PLAIN, 14));
        cupImage.paintIcon(this, graphics, 783, 40);
        graphics.drawString(": " + lenghtOfSnake, 806, 57);


        rightFace = new ImageIcon("images/rightmouth.png");
        rightFace.paintIcon(this, graphics, snakeXLenght[0], snakeYLenght[0]);


        for (int i = 0; i < lenghtOfSnake; i++) {
            if (i == 0 && right) {
                rightFace = new ImageIcon("images/rightmouth.png");
                rightFace.paintIcon(this, graphics, snakeXLenght[i], snakeYLenght[i]);
            }

            if (i == 0 && left) {
                leftFace = new ImageIcon("images/leftmouth.png");
                leftFace.paintIcon(this, graphics, snakeXLenght[i], snakeYLenght[i]);
            }
            if (i == 0 && up) {
                upFace = new ImageIcon("images/upmouth.png");
                upFace.paintIcon(this, graphics, snakeXLenght[i], snakeYLenght[i]);
            }
            if (i == 0 && down) {
                downFace = new ImageIcon("images/downmouth.png");
                downFace.paintIcon(this, graphics, snakeXLenght[i], snakeYLenght[i]);
            }
            if (i != 0) {
                snakeImage = new ImageIcon("images/snakeimage.png");
                snakeImage.paintIcon(this, graphics, snakeXLenght[i], snakeYLenght[i]);
            }
        }

        if (enemyXPos[xPos] == snakeXLenght[0] && enemyYPos[yPos] == snakeYLenght[0]) {

            score++;
            lenghtOfSnake++;
            xPos = random.nextInt(34);
            yPos = random.nextInt(22);
        }

        enemyImage.paintIcon(this, graphics, enemyXPos[xPos], enemyYPos[yPos]);

        for (int b = 1; b < lenghtOfSnake; b++) {
            if (snakeXLenght[b] == snakeXLenght[0] && snakeYLenght[b] == snakeYLenght[0]) {
                right = false;
                left = false;
                up = false;
                down = false;

                graphics.setColor(Color.white);
                graphics.setFont(new Font("arial", Font.BOLD, 50));
                graphics.drawString("YOU LOSE", 300, 300);

                graphics.setFont(new Font("arial", Font.BOLD, 20));
                graphics.drawString("Space to  RESTART", 350, 340);
            }
        }

        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right) {

            for (int a = lenghtOfSnake - 1; a >= 0; a--) {
                snakeYLenght[a + 1] = snakeYLenght[a];
            }
            for (int a = lenghtOfSnake; a >= 0; a--) {
                if (a == 0) {
                    snakeXLenght[a] = snakeXLenght[a] + 25;
                } else {
                    snakeXLenght[a] = snakeXLenght[a - 1];
                }
                if (snakeXLenght[a] > 850) {
                    snakeXLenght[a] = 25;
                }
            }
            repaint();
        }
        if (left) {

            for (int a = lenghtOfSnake - 1; a >= 0; a--) {
                snakeYLenght[a + 1] = snakeYLenght[a];
            }
            for (int a = lenghtOfSnake; a >= 0; a--) {
                if (a == 0) {
                    snakeXLenght[a] = snakeXLenght[a] - 25;
                } else {
                    snakeXLenght[a] = snakeXLenght[a - 1];
                }
                if (snakeXLenght[a] < 25) {
                    snakeXLenght[a] = 850;
                }
            }
            repaint();
        }
        if (up) {
            for (int a = lenghtOfSnake - 1; a >= 0; a--) {
                snakeXLenght[a + 1] = snakeXLenght[a];
            }
            for (int a = lenghtOfSnake; a >= 0; a--) {
                if (a == 0) {
                    snakeYLenght[a] = snakeYLenght[a] - 25;
                } else {
                    snakeYLenght[a] = snakeYLenght[a - 1];
                }
                if (snakeYLenght[a] < 75) {
                    snakeYLenght[a] = 625;
                }
            }
            repaint();

        }
        if (down) {
            for (int a = lenghtOfSnake - 1; a >= 0; a--) {
                snakeXLenght[a + 1] = snakeXLenght[a];
            }
            for (int a = lenghtOfSnake; a >= 0; a--) {
                if (a == 0) {
                    snakeYLenght[a] = snakeYLenght[a] + 25;
                } else {
                    snakeYLenght[a] = snakeYLenght[a - 1];
                }
                if (snakeYLenght[a] > 625) {
                    snakeYLenght[a] = 75;
                }
            }
            repaint();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            moves = 0;
            score = 0;
            lenghtOfSnake = 3;
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            right = true;
            if (!left) {
                right = true;
            } else {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moves++;
            left = true;
            if (!right) {
                left = true;
            } else {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            up = true;
            if (!down) {
                up = true;
            } else {
                up = false;
                down = true;
            }
            left = false;
            right = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            down = true;
            if (!up) {
                down = true;
            } else {
                down = false;
                up = true;
            }
            left = false;
            right = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
