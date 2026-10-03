import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnitConverter extends JFrame {

    // --- 成員變數 (UI 元件與數據選單) ---
    private JComboBox<String> categoryComboBox;
    private JTextField inputField;
    private JTextField outputField;
    private JComboBox<String> sourceUnitComboBox;
    private JComboBox<String> targetUnitComboBox;
    private JButton convertButton;

    // 各類型對應的單位選項陣列
    private final String[] lengthUnits = {"公尺", "公分", "英寸", "英尺"};
    private final String[] weightUnits = {"公斤", "公克", "磅", "盎司"};
    private final String[] tempUnits = {"攝氏", "華氏", "克氏"};

    public UnitConverter() {
        // 1. 設定視窗基本規格
        setTitle("單位換算器");
        setSize(480, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 2. NORTH 區塊：類別選擇 (長度 / 重量 / 溫度)
        String[] categories = {"長度", "重量", "溫度"};
        categoryComboBox = new JComboBox<>(categories);
        add(categoryComboBox, BorderLayout.NORTH);

        // 3. CENTER 區塊：兩列 (輸入框+來源單位、輸出框+目標單位)
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        inputField = new JTextField();
        sourceUnitComboBox = new JComboBox<>(lengthUnits); // 預設帶入長度單位
        outputField = new JTextField();
        outputField.setEditable(false); // 輸出框設為唯讀
        targetUnitComboBox = new JComboBox<>(lengthUnits); // 預設帶入長度單位

        centerPanel.add(inputField);
        centerPanel.add(sourceUnitComboBox);
        centerPanel.add(outputField);
        centerPanel.add(targetUnitComboBox);

        add(centerPanel, BorderLayout.CENTER);

        // 4. SOUTH 區塊：換算按鈕
        convertButton = new JButton("換算");
        add(convertButton, BorderLayout.SOUTH);

        // 5. 事件監聽器：切換類型時更新單位下拉選單
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUnitOptions();
            }
        });

        // 6. 事件監聽器：按下按鈕時進行換算
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });
    }

    // 更新單位下拉選單選項
    private void updateUnitOptions() {
        String selectedCategory = (String) categoryComboBox.getSelectedItem();

        sourceUnitComboBox.removeAllItems();
        targetUnitComboBox.removeAllItems();

        String[] currentUnits;
        if ("重量".equals(selectedCategory)) {
            currentUnits = weightUnits;
        } else if ("溫度".equals(selectedCategory)) {
            currentUnits = tempUnits;
        } else {
            currentUnits = lengthUnits;
        }

        for (String unit : currentUnits) {
            sourceUnitComboBox.addItem(unit);
            targetUnitComboBox.addItem(unit);
        }
    }

    // 進行單位換算邏輯
    private void performConversion() {
        try {
            double value = Double.parseDouble(inputField.getText().trim());
            String category = (String) categoryComboBox.getSelectedItem();
            String fromUnit = (String) sourceUnitComboBox.getSelectedItem();
            String toUnit = (String) targetUnitComboBox.getSelectedItem();

            double result = 0;

            if ("長度".equals(category)) {
                result = convertLength(value, fromUnit, toUnit);
            } else if ("重量".equals(category)) {
                result = convertWeight(value, fromUnit, toUnit);
            } else if ("溫度".equals(category)) {
                result = convertTemperature(value, fromUnit, toUnit);
            }

            outputField.setText(String.format("%.4f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "請輸入有效的數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 長度換算 (統一換算成「公尺」再轉出)
    private double convertLength(double val, String from, String to) {
        double meters = 0;
        switch (from) {
            case "公尺": meters = val; break;
            case "公分": meters = val / 100.0; break;
            case "英寸": meters = val * 0.0254; break;
            case "英尺": meters = val * 0.3048; break;
        }
        switch (to) {
            case "公尺": return meters;
            case "公分": return meters * 100.0;
            case "英寸": return meters / 0.0254;
            case "英尺": return meters / 0.3048;
            default: return 0;
        }
    }

    // 重量換算 (統一換算成「公斤」再轉出)
    private double convertWeight(double val, String from, String to) {
        double kg = 0;
        switch (from) {
            case "公斤": kg = val; break;
            case "公克": kg = val / 1000.0; break;
            case "磅":   kg = val * 0.45359237; break;
            case "盎司": kg = val * 0.0283495231; break;
        }
        switch (to) {
            case "公斤": return kg;
            case "公克": return kg * 1000.0;
            case "磅":   return kg / 0.45359237;
            case "盎司": return kg / 0.0283495231;
            default: return 0;
        }
    }

    // 溫度換算 (先轉「攝氏」再轉出)
    private double convertTemperature(double val, String from, String to) {
        double celsius = 0;
        switch (from) {
            case "攝氏": celsius = val; break;
            case "華氏": celsius = (val - 32) * 5 / 9; break;
            case "克氏": celsius = val - 273.15; break;
        }
        switch (to) {
            case "攝氏": return celsius;
            case "華氏": return celsius * 9 / 5 + 32;
            case "克氏": return celsius + 273.15;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UnitConverter().setVisible(true);
        });
    }
}