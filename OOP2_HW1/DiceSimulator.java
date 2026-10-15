
import javax.swing.*;
import java.awt.*;

public class DiceSimulator extends JFrame {

    int count = 0;
    int sum = 0;

    JLabel info = new JLabel("已擲 0 次，總和 0，平均 0.00");
    JLabel dice = new JLabel("0");
    JButton btn = new JButton("擲骰子");

    public DiceSimulator() {

        setTitle("骰子模擬器");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        info.setHorizontalAlignment(JLabel.CENTER);
        add(info, BorderLayout.NORTH);

        dice.setFont(new Font("Dialog", Font.BOLD, 60));
        dice.setHorizontalAlignment(JLabel.CENTER);
        add(dice, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.add(btn);
        add(panel, BorderLayout.SOUTH);

        btn.addActionListener(e -> {

            int number = (int)(Math.random() * 6) + 1;

            dice.setText("" + number);

            if (number == 6) {
                dice.setForeground(Color.GREEN);
            } else if (number == 1) {
                dice.setForeground(Color.RED);
            } else {
                dice.setForeground(Color.BLACK);
            }

            count++;
            sum += number;

            double average = (double) sum / count;

            info.setText(String.format(
                "已擲 %d 次，總和 %d，平均 %.2f",
                count, sum, average));
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new DiceSimulator();
    }
}
