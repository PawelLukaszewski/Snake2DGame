import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        ImageIcon img = new ImageIcon("images/snakeIcon.png");

        obj.setBounds(10, 10, 900, 700);
        obj.setTitle("Snake version 1.0");
        obj.setBackground(Color.darkGray);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setIconImage(img.getImage());
        obj.add(gameplay);
    }

}
