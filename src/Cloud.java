import java.awt.*;

//***OBJECT CLASS***
//objects are more complicated and encompass a lot of data
//astro.xpos --> go to all astro data, retrieve xpos value
//astro.rec --> both astro and rec are objects
//entire apodosis is a boolean --> if(astro.rec.intersects(star.rec)) is a boolean
public class Cloud {
    //declaration
    public String name;
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rec;

    public Cloud(int pXpos, int pYpos, int pDx, int pDy) {
        xpos = pXpos;
        ypos = pYpos;
        dx = pDx;
        dy = pDy;
        width = (int)(Math.random()*40+40);
        height = (int)(Math.random()*40+40);
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);
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

    public void bouncingMove() {
        xpos = (int)(Math.random()*1200);
        ypos = (int)(Math.random()*700);
        dx = (int)(Math.random()*10);
        dy = (int)(Math.random()*10); ;
        if(xpos>1200-width){
            dx=-dx;
        }
        if(xpos<0){
            dx=-dx;
        }
        if(ypos>700-height) {
            dy = -dy;
        }
        if(ypos<0) {
            dy = -dy;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);

    }

    public void teleport() {
        xpos=(int)(Math.random()*1000);
        ypos=(int)(Math.random()*700);
        dx=(int)(Math.random()*10);
        dy=(int)(Math.random()*10);
    }
}