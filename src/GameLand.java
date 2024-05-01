//Game Example
//Lockwood Version 2023-24
// Learning goals:
// make an object class to go with this main class
// the object class should have a printInfo()
//input picture for background
//input picture for object and paint the object on the screen at a given point
//create move method for the object, and use it
// create a wrapping move method and a bouncing move method
//create a second object class
//give objects rectangles
//start interactions/collisions

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries


//*
// Platform class
// Bird class
// Do eggs later
// Bird move method & hero move method
// Worry about the flying through platforms later
// Start with bird movement and then hero movement
// Gravity (accel/deccel) *//

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

//***MAKING OBJECTS***
//(1) declare
//(2) construct
//(3) use

//*******************************************************************************
// Class Definition Section

public class GameLand implements Runnable, KeyListener {

    //Variable Declaration Section
    //Declare the variables used in the program
    //You can set their initial values here if you want

    public Image background;
    public Image gameOverScreen;
    public Image victoryScreen;
    public Hero knight;
    public Image knightPic;
    public Hero egg;
    public Image eggPic;
    public Cloud[] cumuli;
    public Image cloudPic;
    //Sets the width and height of the program window
    final int WIDTH = 1200;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    public boolean startScreen=true;
    public boolean isPlaying=true;
    public boolean gameOver=false;
    public boolean victory=false;
    public int counter=50;
    public long startTime;
    public long currentTime;
    public long elapsedTime;

    //Declare the objects used in the program below
    //(1)***DECLARING OBJECTS/IMAGES***

    // Main method definition: PSVM
    // This is the code that runs first and automatically
    //***PSVM***
    public static void main(String[] args) {
        GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
        new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
    }

    // Constructor Method
    // This has no return type and has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    //***CONSTRUCTOR***
    public GameLand() {
        setUpGraphics(); //this calls the setUpGraphics() method
        //(2)***CONSTRUCTING OBJECTS***
        knight = new Hero (100,100,10,10,100,100,true);
        egg = new Hero (50,50,0,0,70,50,true);
        knight.printInfo();

        cloudPic = Toolkit.getDefaultToolkit().getImage("cloud.png");
        cumuli = new Cloud[5];
        for(int i = 0; i < cumuli.length; i=i+1) {
            int randX = (int)(Math.random()*1200);
            int randY = (int)(Math.random()*700);
            cumuli[i] = new Cloud(randX,randY,5,5);
        }
        startTime = System.currentTimeMillis();
        knightPic = Toolkit.getDefaultToolkit().getImage("knight.png");
        eggPic = Toolkit.getDefaultToolkit().getImage("egg.png");
        background = Toolkit.getDefaultToolkit().getImage("farmbackground.jpg");
        gameOverScreen = Toolkit.getDefaultToolkit().getImage("gameover.png");
        victoryScreen = Toolkit.getDefaultToolkit().getImage("victoryScreen.png");


        run();
    }// GameLand()

//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever using a while loop
        while (true) {
            timer();
            moveThings();  //move all the game objects
            render();  // paint the graphics
            collisions();

            if(counter>=5000){
                victory=true;
            }
            if(counter<0){
                gameOver=true;
            }
            if(elapsedTime==60) {
                gameOver=true;
            }
            if(victory==true) {
                pause(20);
            }
            if(gameOver==true) {
                pause(20);
            }
        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        if(startScreen==true) {
            g.drawString("press space bar to start", 600,350);
        }

        for(int i=0; i<cumuli.length; i=i+1) {
            g.drawImage(cloudPic, cumuli[i].xpos, cumuli[i].ypos, cumuli[i].width, cumuli[i].height, null);
        }

        if(isPlaying==true) {
            g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
            g.drawString("Score: " + String.valueOf(counter), 1000, 100);
            g.drawString("Time: " + elapsedTime, 1000, 120);
            g.drawString("To win, reach 5000 before the timer gets to 60!", 450, 350);
            g.drawString("Don't let the score get to 0!", 500, 370);
        }

        //draw background

        if(gameOver==true) {

            g.drawImage(gameOverScreen, 0, 0, WIDTH, HEIGHT, null);
            g.dispose();
        }

        if(victory==true) {
            g.drawImage(victoryScreen, 0, 0, WIDTH, HEIGHT, null);
            g.dispose();
        }

        //draw the image of your objects below:
        //DRAWING IMAGES ON SCREEN
        g.drawImage(knightPic, knight.xpos, knight.ypos, knight.width, knight.height, null);
        g.drawImage(eggPic, egg.xpos, egg.ypos, egg.width, egg.height, null);
        for(int i=0; i<cumuli.length; i=i+1) {
            g.drawImage(cloudPic, cumuli[i].xpos, cumuli[i].ypos, cumuli[i].width, cumuli[i].height, null);
            g.dispose();
        }

        if(gameOver==true) {
            g.drawString("game over", 400,400);
        }


        //dispose the images each time(this allows for the illusion of movement).
        g.dispose();

        bufferStrategy.show();
    }

    public void timer() {
        currentTime = System.currentTimeMillis();
        elapsedTime=(int)((currentTime-startTime)*.001);
    }

    //(4)***MOVE THINGS***
    public void moveThings() {
        //call the move() method code from your object class
        //***CALLING move() FOR OBJECTS***
        knight.move();
        egg.move();
        for(int i=0; i<cumuli.length; i=i+1) {
            cumuli[i].bouncingMove();
        }
    }

    public void freeze() {
        knight.dx=0;
        knight.dy=0;
        egg.dx=0;
        egg.dy=0;
    }
    public void collisions() {
        if(knight.rec.intersects(egg.rec)) {
            counter = counter + 200;
            egg.teleport();
        }
        for(int i=1; i<cumuli.length; i=i+1) {
            if(cumuli[i].rec.intersects(knight.rec) && cumuli[i].isAlive==true) {
                counter = counter -1;
            }
        }
    }
    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause (int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }


    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Game Land");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //not using
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        System.out.println("Key: " + key + ", KeyCode: " + keyCode);
        //add if statements to tie key codes to knight.upPressed

        if(keyCode==39) {
            knight.rightPressed=true; 
        }
        if(keyCode==37) {
            knight.leftPressed=true;
        }
        if(keyCode==38) {
            knight.upPressed=true;
        }
        if(keyCode==40) {
            knight.downPressed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        System.out.println("Key: " + key + ", KeyCode: " + keyCode);
        //add if statements to tie key codes to knight.upPressed
        if(keyCode==39) {
            knight.rightPressed=false;
        }
        if(keyCode==37) {
            knight.leftPressed=false;
        }
        if(keyCode==38) {
            knight.upPressed=false;
        }
        if(keyCode==40) {
            knight.downPressed=false;
        }
    }
}