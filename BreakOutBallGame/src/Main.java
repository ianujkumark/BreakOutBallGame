import javax.swing.*;
//import the packages which has inbuild classes to build a GUI project
public class Main {
    public static void main(String[] args){

        JFrame project=new JFrame(); //JFrame is a top-level container that provides a window on the screen
        Gameplay gamePlay = new Gameplay();

        project.setSize(700,600);
        project.setTitle("Breakout Ball");
        project.setResizable(false);
        project.setVisible(true);
        project.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        project.add(gamePlay);
        ImageIcon icon=new ImageIcon("ball_icon.png");
        project.setIconImage(icon.getImage());

    }
}