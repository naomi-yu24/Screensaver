import java.awt.*;

//***OBJECT CLASS***
//objects are more complicated and encompass a lot of data
//astro.xpos --> go to all astro data, retrieve xpos value
//astro.rec --> both astro and rec are objects
//entire apodosis is a boolean --> if(astro.rec.intersects(star.rec)) is a boolean
public class Cloud {
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
    
    //constructor

    public Cloud (int pXpos, int pYpos, int pDx, int pDy, int pWidth, int pHeight, boolean pIsAlive) {
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
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle (xpos,ypos,width,height);
    }
    public void teleport() {
        xpos=(int)(Math.random()*1000);
        ypos=(int)(Math.random()*700);
        dx=(int)(Math.random()*10);
        dy=(int)(Math.random()*10);
    }
}