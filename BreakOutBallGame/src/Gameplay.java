import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements ActionListener, KeyListener
{
    private boolean play_=false;
    private int row=4,col=10;
    private int x_s=-2,y_s=-4;
    private int T_bricks=row*col,delay=8,ball_x=200,ball_y=350,b_xd=x_s,b_yd=y_s;
    private int player=350;
    private Timer timer;
    private MapGenerator map;
    private int score=0;
    public Gameplay(){
        timer=new Timer(delay,this);
        addKeyListener(this); //to detect key
        setFocusable(true);

        setFocusTraversalKeysEnabled(true);
        timer.start(); //to start the game cycle

        map=new MapGenerator(row,col);
    }
    public void paint(Graphics g)
    {
        //coloring canvas
        g.setColor(Color.pink);
        g.fillRect(1,1,692,592);

        //border
        g.setColor(Color.pink);
        g.fillRect(0,0,692,3);
        g.fillRect(0,3,3,592);
        g.fillRect(691,3,3,592);

        //creating paddle
        g.setColor(Color.darkGray);
        g.fillRect(player,550,100,8);

        //creating ball
        g.setColor(Color.darkGray);
        g.fillOval(ball_x,ball_y,20,20);

        //creating bricks
        map.draw((Graphics2D)g);

        //current score
        g.setColor(Color.darkGray);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Your Score :"+score,545,30);

        //when game is over
        if(ball_y>=570)
        {
            play_=false;
            b_xd=0;
            b_yd=0;

            g.setColor(Color.BLACK);
            g.setFont(new Font("serif",Font.BOLD,40));
            g.drawString("GAME OVER!! YOU WON :"+score,80,300);

            g.setColor(Color.darkGray);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("PRESS ENTER TO PLAY AGAIN!!",170,350);
        }

        if(T_bricks<=0)
        {
            play_=false;
            b_xd=0;
            b_yd=0;

            g.setColor(Color.BLACK);
            g.setFont(new Font("serif",Font.BOLD,40));
            g.drawString("Congratulation!!\n YOU WON :"+score,80,300);

            g.setColor(Color.darkGray);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("PRESS ENTER TO PLAY AGAIN!!:",170,350);
        }

    }

    private void moveLeft()
    {
        play_=true;
        player-=40;
    }
    private void moveRight() {
        play_ = true;
        player +=40;
    }

    @Override //occurs when a subclass has the same method as the parent class
    public void actionPerformed(ActionEvent e) {
        if(play_)
        {
            if(ball_x<=0)
                b_xd=-b_xd;
            if(ball_y<=0)
                b_yd=-b_yd;
            if(ball_x>=670)
                b_xd=-b_xd;

            Rectangle ballRect=new Rectangle(ball_x,ball_y,20,20);
            Rectangle padRect=new Rectangle(player,550,100,8);

            if(ballRect.intersects(padRect))
            {
                b_yd=-b_yd;
            }

            A: for(int i=0;i<map.map.length;i++)
            {
                for(int j=0;j<map.map[0].length;j++)
                {
                    if(map.map[i][j]>0)
                    {
                        int w=map.brick_W;
                        int h=map.brick_H;
                        int b_x=80+j*w; //taking brick x & y position
                        int b_y=50+i*h;

                        Rectangle brickRect=new Rectangle(b_x,b_y,w,h);
                        if(ballRect.intersects(brickRect))
                        {
                            map.setBrick(0,i,j);
                            T_bricks--;
                            score+=5;
                            if(ball_x+19<=b_x || ball_y+1>=b_y+w)
                            {
                                b_xd=-b_xd;
                            }
                            else
                            {
                                b_yd=-b_yd;
                            }
                            break A;
                        }
                    }
                }
            }

            ball_x+=b_xd;
            ball_y+=b_yd;
        }
        repaint();
    }



    @Override
    public void keyPressed(KeyEvent e) {
        //when left key is pressed
        if(e.getKeyCode()==KeyEvent.VK_LEFT) {
            if(player<=0) //checking if the paddle does not go outside the playing area
                player=0;
            else
            moveLeft();
        }
        //when right key is pressed
        if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
            if(player>=600)
                player=600;
            else
            moveRight();
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER) {
            if(!play_)
            {
                score=0;
                T_bricks=row*col;
                ball_x=200;
                ball_y=350;
                b_xd=x_s;
                b_yd=y_s;
                player=320;
                map=new MapGenerator(row,col);
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}