import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DiceSimulator extends JFrame {

    // 宣告統計用的全域變數
    private int count = 0;       // 記錄擲骰子的總次數 (N)
    private int totalSum = 0;    // 記錄點數的累計總和 (M)
    private final Random random = new Random(); // 隨機數生成器

    // 宣告需要動態更新內容或顏色的 UI 元件
    private final JLabel statsLabel; // 顯示統計資訊的標籤 (上方)
    private final JLabel diceLabel;  // 顯示骰子點數的標籤 (中央)

    public DiceSimulator() {
        // 1. 視窗基本規格設定
        setTitle("骰子模擬器");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 設定視窗開啟時居中顯示
        setLayout(new BorderLayout()); // 使用邊界版面管理器

        // 2. 建立上方統計標籤
        statsLabel = new JLabel("已擲 0 次，總和 0，平均 0.00", SwingConstants.CENTER);
        statsLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        add(statsLabel, BorderLayout.NORTH); // 放置在視窗上方

        // 3. 建立中央點數標籤 (字體 60pt)
        diceLabel = new JLabel("-", SwingConstants.CENTER);
        diceLabel.setFont(new Font("SansSerif", Font.BOLD, 60)); // 設定 60pt 字體
        diceLabel.setForeground(Color.BLACK); // 預設顏色為黑色
        add(diceLabel, BorderLayout.CENTER); // 放置在視窗中央

        // 4. 建立下方「擲骰子」按鈕
        JButton rollButton = new JButton("擲骰子");
        rollButton.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        rollButton.addActionListener(e -> rollDice()); // 綁定點擊事件呼叫 rollDice()
        add(rollButton, BorderLayout.SOUTH); // 放置在視窗下方

        // 5. 元件設定完畢後顯示視窗
        setVisible(true);
    }

    // 擲骰子事件處理邏輯
    private void rollDice() {
        // 產生 1 到 6 的隨機點數
        int diceValue = random.nextInt(6) + 1;

        // 更新統計數據
        count++; // 次數加 1
        totalSum += diceValue; // 累加點數
        double average = (double) totalSum / count; // 計算平均值 (保留小數)

        // 更新中央點數標籤文字
        diceLabel.setText(String.valueOf(diceValue));

        // 根據點數變更顏色 (6點綠色、1點紅色、其餘黑色)
        if (diceValue == 6) {
            diceLabel.setForeground(Color.GREEN);
        } else if (diceValue == 1) {
            diceLabel.setForeground(Color.RED);
        } else {
            diceLabel.setForeground(Color.BLACK);
        }

        // 更新上方統計資訊標籤 (平均值格式化為小數點後兩位 X.XX)
        statsLabel.setText(String.format("已擲 %d 次，總和 %d，平均 %.2f", count, totalSum, average));
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // 在 Swing 事件調度執行緒 (EDT) 中啟動 GUI 視窗，確保執行緒安全
        SwingUtilities.invokeLater(() -> {
            DiceSimulator frame = new DiceSimulator();
        });
    }
}
