
import java.awt.*;
import javax.swing.*;

public class MyHouse extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // 1. Draw the sky
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, 500, 300);

        // 2. Draw the grass
        g.setColor(new Color(120, 200, 80));
        g.fillRect(0, 300, 500, 100);

        // 3. Draw the sun
        g.setColor(new Color(255, 214, 0));
        g.fillOval(200, 40, 60, 60);

        // Draw eight sun rays
        g.drawLine(230, 20, 230, 40);
        g.drawLine(230, 100, 230, 120);

        g.drawLine(180, 70, 200, 70);
        g.drawLine(260, 70, 280, 70);

        g.drawLine(245, 45, 265, 30);
        g.drawLine(215, 45, 195, 30);

        g.drawLine(245, 95, 265, 110);
        g.drawLine(215, 95, 195, 110);

        // 4. Draw the cloud
        g.setColor(Color.WHITE);
        g.fillOval(320, 70, 70, 40);
        g.fillOval(370, 55, 70, 50);
        g.fillOval(420, 70, 70, 40);

        // 5. Draw the house
        g.setColor(new Color(245, 215, 161));
        g.fillRect(260, 200, 200, 200);

        g.setColor(Color.BLACK);
        g.drawRect(260, 200, 200, 200);

        // 6. Draw the roof
        g.setColor(new Color(139, 69, 19));

        g.drawLine(260, 200, 360, 150);
        g.drawLine(360, 150, 460, 200);
        g.drawLine(260, 200, 460, 200);

        // 7. Draw the door
        g.setColor(new Color(160, 82, 45));
        g.fillRoundRect(270, 290, 80, 110, 20, 20);

        // Draw the door handle
        g.setColor(new Color(255, 215, 0));
        g.fillOval(275, 350, 8, 8);

        // 8. Draw the left window
        g.setColor(new Color(10, 186, 181));
        g.fillRect(380, 225, 50, 35);

        g.setColor(Color.BLACK);
        g.drawRect(380, 225, 50, 35);

        g.drawLine(380, 242, 430, 242);
        g.drawLine(405, 225, 405, 260);

        // 9. Draw the right window
        g.setColor(new Color(10, 186, 181));
        g.fillRect(380, 300, 45, 40);

        g.setColor(Color.BLACK);
        g.drawRect(380, 300, 45, 40);

        g.drawLine(380, 320, 425, 320);
        g.drawLine(402, 300, 402, 340);

        // 10. Draw the signature
        g.setColor(Color.BLACK);
        g.setFont(new Font("Dialog", Font.PLAIN, 14));
        g.drawString("A1121713 陳冠智", 15, 385);

        g.drawOval(170, 170, 40, 40);
        g.drawLine(190, 210, 190, 250);
        g.drawLine(190, 230, 170, 215);
        g.drawLine(190, 230, 210, 215);
        g.drawLine(190, 250, 170, 300);
        g.drawLine(190, 250, 210, 300);

        g.setColor(new Color(139, 69, 19));
        g.fillRect(60, 190, 35, 180);

        g.setColor(new Color(34, 139, 34));
        g.fillOval(10, 45, 145, 145);
 
        g.setColor(new Color(220, 20, 60));
        g.drawOval(40, 65, 15, 15);
        g.drawOval(75, 65, 15, 15);
        g.drawOval(110, 65, 15, 15);
        g.fillOval(25, 100, 25, 25);
        g.fillOval(70, 100, 25, 25);
        g.fillOval(115, 100, 25, 25);
        g.fillOval(50, 130, 30, 30);
        g.fillOval(95, 130, 30, 30);





    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("我的小房子");

        MyHouse panel = new MyHouse();

        panel.setPreferredSize(new Dimension(500, 400));

        frame.add(panel);

        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
