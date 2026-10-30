
import javax.swing.*;
import java.awt.*;

public class DiceSimulatorpro extends JFrame {

    int count = 0;
    int sum = 0;
    int[] counts = new int[6];

    JLabel info = new JLabel("已擲 0 次，總和 0，平均 0.00");
    JLabel dice = new JLabel("0");
    JLabel stats = new JLabel("1點:0次  2點:0次  3點:0次  4點:0次  5點:0次  6點:0次");

    JTextArea history = new JTextArea(5, 25);

    JButton btn = new JButton("擲骰子");
    JButton btnTen = new JButton("連擲10次");
    JButton btnReset = new JButton("重置");

    public DiceSimulatorpro() {

        setTitle("骰子模擬器");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 上方資訊區
        JPanel topPanel = new JPanel(new GridLayout(2, 1));

        info.setHorizontalAlignment(JLabel.CENTER);
        stats.setHorizontalAlignment(JLabel.CENTER);
        stats.setFont(new Font("Dialog", Font.PLAIN, 11));

        topPanel.add(info);
        topPanel.add(stats);
        add(topPanel, BorderLayout.NORTH);

        // 中央骰子區
        dice.setFont(new Font("Dialog", Font.BOLD, 60));
        dice.setHorizontalAlignment(JLabel.CENTER);
        add(dice, BorderLayout.CENTER);

        // 下方功能區
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btn);
        buttonPanel.add(btnTen);
        buttonPanel.add(btnReset);

        bottomPanel.add(buttonPanel, BorderLayout.NORTH);

        // 歷史紀錄區
        history.setEditable(false);
        history.setLineWrap(true);
        history.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(history);

        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.add(new JLabel("投擲歷史紀錄："), BorderLayout.NORTH);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        bottomPanel.add(historyPanel, BorderLayout.CENTER);
        bottomPanel.setPreferredSize(new Dimension(400, 230));

        add(bottomPanel, BorderLayout.SOUTH);

        // 單次擲骰
        btn.addActionListener(e -> {
            rollDice();
        });

        // 連續擲骰10次
        btnTen.addActionListener(e -> {
            for (int i = 0; i < 10; i++) {
                rollDice();
            }
        });

        // 重置功能
        btnReset.addActionListener(e -> {

            count = 0;
            sum = 0;

            for (int i = 0; i < 6; i++) {
                counts[i] = 0;
            }

            dice.setText("0");
            dice.setForeground(Color.BLACK);

            info.setText("已擲 0 次，總和 0，平均 0.00");

            stats.setText("1點:0次  2點:0次  3點:0次  4點:0次  5點:0次  6點:0次");

            history.setText("");
        });

        setVisible(true);
    }

    // 擲骰子的方法
    public void rollDice() {

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

        // 更新點數統計
        counts[number - 1]++;

        stats.setText(String.format(
            "1點:%d次  2點:%d次  3點:%d次  4點:%d次  5點:%d次  6點:%d次",
            counts[0], counts[1], counts[2],
            counts[3], counts[4], counts[5]));

        // 新增歷史紀錄
        history.append(number + "  ");
    }

    public static void main(String[] args) {
        new DiceSimulatorpro();
    }
}
