import java.awt.*;

public class goal {
    public int xpos;                //the x position
    public int ypos;
    public int width;
    public int height;
    public Rectangle net;

    public goal(int Pxpos,int Pypos){
        xpos=Pxpos;
        ypos=Pypos;
        width=30;
        height=75;
        net=new Rectangle(xpos,ypos,width,height);

    }
}
