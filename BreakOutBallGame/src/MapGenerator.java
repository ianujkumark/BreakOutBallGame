import java.awt.*;

public class MapGenerator {
    public int brick_W, brick_H;
    public int map[][];

    public MapGenerator(int row, int col) {
        map = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = 1;
            }
        }
        brick_W = 540 / col;
        brick_H = 150 / row;
    }

    public void setBrick(int value, int r, int c) {
        map[r][c] = value;
    }

    public void draw(Graphics2D g)
    {
        for(int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[0].length;j++)
            {
                if(map[i][j]>0)
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(j*brick_W+80,i*brick_H+50,brick_W,brick_H);
                    //setting border in between
                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j*brick_W+80,i*brick_H+50,brick_W,brick_H);
                }
            }
        }
    }
}