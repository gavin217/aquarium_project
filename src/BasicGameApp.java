//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image soccerPic;
    public Image soccerPic2;
    public Image Soccerball;
    public Image soccerfield;
    public int left_score;
    public int right_score;
    public int randx=(int)(Math.random()*400)+1;
    public int randy=(int)(Math.random()*600)+1;
    public int randx2=(int)(Math.random()*330)+600;
    public int randy2=(int)(Math.random()*600)+1;


   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private soccer_player messi;
    private soccer_player Van_dijk;
    private Soccer_ball Genericball;
    private goal left_goal;
    private goal right_goal;



   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start(); //stuff                //creates a threads & starts up the code in the run( ) method
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();

       //gonna make the players spawn anywhere on their half
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		soccerPic = Toolkit.getDefaultToolkit().getImage("messi.jpeg");
        soccerPic2 = Toolkit.getDefaultToolkit().getImage("van dijk.jpeg");
        //load the picture
		messi = new soccer_player(randx2,randy2);
        Van_dijk=new soccer_player(randx,randy);
        left_goal=new goal(40,310);
        right_goal=new goal(930,310);
        Soccerball= Toolkit.getDefaultToolkit().getImage("soccerball.jpg");
        Genericball= new Soccer_ball(500,370);
        soccerfield=Toolkit.getDefaultToolkit().getImage("field.jpeg");



	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
		messi.move();
        Van_dijk.move();
        Genericball.move();
        //colliding();//if i can get this to work i will
        jankycolliding();//so this works about half the time the other half it kinda gets stuck
        scoring();

	}
    public void jankycolliding() {
        if (messi.body.intersects(Van_dijk.body)) {
            int randbounce=(int)(Math.random()*10)+1;
            if (randbounce<=5) {
                messi.dy = -messi.dy;
                Van_dijk.dy = -Van_dijk.dy;
            }
            else{
                messi.dx = -messi.dx;
                Van_dijk.dx = -Van_dijk.dx;
            }
        }
        if (messi.body.intersects(Genericball.hitbox)) {
            int randbounce=(int)(Math.random()*10)+1;
            if (randbounce<=5) {
                messi.dy = -messi.dy;
                Genericball.dy = -Genericball.dy;
            }
            else{
                messi.dx = -messi.dx;
                Genericball.dx = -Genericball.dx;
            }
        }
        if (Van_dijk.body.intersects(Genericball.hitbox)) {
            int randbounce=(int)(Math.random()*10)+1;
            if (randbounce<=5) {
                Van_dijk.dy = -Van_dijk.dy;
                Genericball.dy = -Genericball.dy;
            }
            else{
                Van_dijk.dx = -Van_dijk.dx;
                Genericball.dx = -Genericball.dx;
            }

        }
    }
    public void scoring(){
        int brandx=(int)(Math.random()*400)+1;
         int brandy=(int)(Math.random()*600)+1;
         int brandx2=(int)(Math.random()*330)+600;
         int brandy2=(int)(Math.random()*600)+1;
        if(Genericball.hitbox.intersects(left_goal.net)){
            System.out.println("goal");
            right_score=right_score+1;
            System.out.println("the score is"+left_score+"-"+right_score);
            messi.xpos=brandx2;
            messi.ypos=brandy2;
            Van_dijk.xpos=brandx;
            Van_dijk.ypos=brandy;
            Genericball.xpos=500;
            Genericball.ypos=370;

        }
        if (Genericball.hitbox.intersects(right_goal.net)){
            System.out.println("goal");
            left_score=left_score+1;
            System.out.println("the score is"+left_score+"-"+right_score);
            messi.xpos=brandx2;
            messi.ypos=brandy2;
            Van_dijk.xpos=brandx;
            Van_dijk.ypos=brandy;
            Genericball.xpos=500;
            Genericball.ypos=370;
        }
    }

	public void colliding(){
        if(messi.body.intersects(Van_dijk.body)&&messi.dy==-Van_dijk.dy){

            messi.dy = -messi.dy;
            Van_dijk.dy = -Van_dijk.dy;
        }
        if(messi.body.intersects(Van_dijk.body)&&Van_dijk.dy==-messi.dy){

            messi.dy = -messi.dy;
            Van_dijk.dy = -Van_dijk.dy;
        }
        if(messi.body.intersects(Van_dijk.body)&&messi.dx==-Van_dijk.dx){

            messi.dx = -messi.dx;
            Van_dijk.dx = -Van_dijk.dx;
        }
        if(messi.body.intersects(Van_dijk.body)&&Van_dijk.dx==-messi.dx){

            messi.dx = -messi.dx;
            Van_dijk.dx = -Van_dijk.dx;
        }
        if(messi.body.intersects(Genericball.hitbox)&&messi.dy==-Genericball.dy){

            messi.dy = -messi.dy;
            Genericball.dy = -Genericball.dy;
        }
        if(messi.body.intersects(Genericball.hitbox)&&Genericball.dy==-messi.dy){

            messi.dy = -messi.dy;
            Genericball.dy = -Genericball.dy;
        }
        if(messi.body.intersects(Genericball.hitbox)&&messi.dx==-Genericball.dx){

            messi.dx = -messi.dx;
            Genericball.dx = -Genericball.dx;
        }
        if(messi.body.intersects(Genericball.hitbox)&&Genericball.dx==-messi.dx){

            messi.dx = -messi.dx;
            Genericball.dx = -Genericball.dx;
        }
        if(Van_dijk.body.intersects(Genericball.hitbox)&&Van_dijk.dy==-Genericball.dy){

            Van_dijk.dy = -Van_dijk.dy;
            Genericball.dy = -Genericball.dy;
        }
        if(Van_dijk.body.intersects(Genericball.hitbox)&&Genericball.dy==-Van_dijk.dy){

            Van_dijk.dy = -Van_dijk.dy;
            Genericball.dy = -Genericball.dy;
        }
        if(Van_dijk.body.intersects(Genericball.hitbox)&&Van_dijk.dx==-Genericball.dx){

            Van_dijk.dx = -Van_dijk.dx;
            Genericball.dx = -Genericball.dx;
        }
        if(Van_dijk.body.intersects(Genericball.hitbox)&&Genericball.dx==-Van_dijk.dx){

            Van_dijk.dx = -Van_dijk.dx;
            Genericball.dx = -Genericball.dx;
        }
    }
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
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


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image of the astronaut
        g.drawImage(soccerfield,0,0,1000,700,null);
		g.drawImage(soccerPic, messi.xpos, messi.ypos, messi.width, messi.height, null);
        g.drawImage(soccerPic2, Van_dijk.xpos, Van_dijk.ypos, messi.width, messi.height, null);
        g.drawImage(Soccerball, Genericball.xpos, Genericball.ypos, Genericball.width, Genericball.height, null);
        g.setColor(Color.red);
        g.drawRect(Genericball.hitbox.x, Genericball.hitbox.y,Genericball.width,Genericball.height);
        g.drawRect(messi.body.x,messi.body.y,messi.width,messi.height);
        g.drawRect(Van_dijk.body.x,Van_dijk.body.y,Van_dijk.width,Van_dijk.height);
        g.drawRect(left_goal.net.x,left_goal.net.y,left_goal.width,left_goal.height);
        g.drawRect(right_goal.net.x,right_goal.net.y, right_goal.width, right_goal.height);
		g.dispose();

		bufferStrategy.show();
	}
}