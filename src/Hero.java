import java.awt.*;

//***OBJECT CLASS***
//objects are more complicated and encompass a lot of data
//astro.xpos --> go to all astro data, retrieve xpos value
//astro.rec --> both astro and rec are objects
//entire apodosis is a boolean --> if(astro.rec.intersects(star.rec)) is a boolean
public class Hero {
    //declaration

    public int xpos;            //the x position
    public int ypos;            //the y position
    public int dx;              //the speed of the hero in the x direction
    public int dy;              //the speed of the hero in the y direction
    public int width;           //image width
    public int height;          //image height
    public boolean isAlive;     //a boolean to denote if the hero is alive or not
    public Rectangle rec;
    //movement booleans
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean upPressed;
    public boolean downPressed;

    //constructor
    public Hero(int pXpos, int pYpos, int pDx, int pDy, int pWidth, int pHeight, boolean pIsAlive) {
        xpos = pXpos;
        ypos = pYpos;
        dx=pDx;
        dy=pDy;
        width=pWidth;
        height=pHeight;
        isAlive=pIsAlive;
        rec=new Rectangle(xpos,ypos,width,height);
    }

    public void printInfo() {
        System.out.println("(x,y): ("+xpos+","+ypos+")");
        System.out.println("dx: " + dx);
        System.out.println("dy: " + dy);
        System.out.println("width: " + width);
        System.out.println("height: " + height);
        System.out.println("isAlive: " + isAlive);
    }

    //***MOVE METHODS***
    public void move() {//user control move method
        //horizontal motion
        if(leftPressed==true){
            dx=-2;
        }else if(rightPressed==true){
            dx=2;
        }else{
            dx=0;
        }
        //vertical motion
        if(upPressed==true){
            dy=-2;
        }else if(downPressed==true){
            dy=2;
        }else{
            dy=0;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle (xpos,ypos,width,height);
    }

    public void bounce() {
        if (xpos>1000-width) {
            dx = -dx;
        }
        if (xpos<0) {
            dx = -dx;
        }
        if (ypos>700-height) {
            dy = -dy;
        }
        if (ypos<0) {
            dy = -dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle (xpos,ypos,width,height);
    }
    public void changeDirection() {
        dx=-dx;
        dy=-dy;
    }

    public void stop() {
        dx=0;
        dy=0;
    }

    public void restart() {
        dx=5;
        dy=5;
    }

    public void randomMove() {
        for(int i=0; i<3; i=i+1) {
            dx=0;
            dy=5*1;
        }
        dx=(int)(Math.random()*10);
        dy=(int)(Math.random()*10);
    }

    public void teleport() {
        xpos=(int)(Math.random()*1000+100);
        ypos=(int)(Math.random()*500+100);
        dx=(int)(Math.random()*10);
        dy=(int)(Math.random()*10);
    }

    public void wrap() {
        if (xpos>1000) {
            xpos = 0;
        }
        if (xpos<0) {
            xpos = 1000;
        }
        if (ypos>700) {
            ypos = 0;
        }
        if (ypos<0) {
            ypos = 700;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle (xpos,ypos,width,height);
    }
}