import java.awt.*;

public class Soccer_ball {


    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive; //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;


    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Soccer_ball(int pXpos, int pYpos) {
        int randdy2=(int)(Math.random()*7)+1;
        int randdx2=(int)(Math.random()*7)+1;
        xpos = pXpos;
        ypos = pYpos;
        dx = randdx2;
        dy = randdy2;
        width = 60;
        height = 60;
        isAlive = true;
        hitbox= new Rectangle(xpos,ypos,width,height);


    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox= new Rectangle(xpos,ypos,width,height);
        if(xpos>1061){
            xpos=-60;
        }
        if(xpos<-61){
            xpos=1060;
        }
        if(ypos>761){
            ypos=-60;
        }
        if(ypos<-61){
            ypos=760;
        }


    }
}
