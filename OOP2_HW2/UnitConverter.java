
import javax.swing.*;
import java.awt.*;

public class UnitConverter extends JFrame {

    String[] lengthUnits = {"公尺", "公分", "英吋", "英尺"};
    String[] weightUnits = {"公斤", "公克", "磅", "盎司"};
    String[] tempUnits = {"攝氏", "華氏", "克氏"};

    double[] lengthFactors = {1.0, 0.01, 0.0254, 0.3048};
    double[] weightFactors = {1.0, 0.001, 0.45359237, 0.028349523125};

    JComboBox<String> typeBox =
        new JComboBox<>(new String[]{"長度", "重量", "溫度"});

    JComboBox<String> fromBox = new JComboBox<>(lengthUnits);
    JComboBox<String> toBox = new JComboBox<>(lengthUnits);

    JTextField input = new JTextField(12);
    JTextField output = new JTextField(12);

    JButton btn = new JButton("換算");

    public UnitConverter() {

        setTitle("單位換算器");
        setSize(480, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("換算類型："));
        topPanel.add(typeBox);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));

        JPanel row1 = new JPanel();
        row1.add(new JLabel("來源："));
        row1.add(input);
        row1.add(fromBox);

        JPanel row2 = new JPanel();
        row2.add(new JLabel("目標："));
        row2.add(output);
        row2.add(toBox);

        output.setEditable(false);
        toBox.setSelectedIndex(1);

        centerPanel.add(row1);
        centerPanel.add(row2);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btn);
        add(bottomPanel, BorderLayout.SOUTH);

        typeBox.addActionListener(e -> {

            String[] units;

            if (typeBox.getSelectedIndex() == 0) {
                units = lengthUnits;
            } else if (typeBox.getSelectedIndex() == 1) {
                units = weightUnits;
            } else {
                units = tempUnits;
            }

            fromBox.removeAllItems();
            toBox.removeAllItems();

            for (String unit : units) {
                fromBox.addItem(unit);
                toBox.addItem(unit);
            }

            toBox.setSelectedIndex(1);
            output.setText("");
        });

        btn.addActionListener(e -> {

            try {
                double value = Double.parseDouble(input.getText());

                int type = typeBox.getSelectedIndex();
                int from = fromBox.getSelectedIndex();
                int to = toBox.getSelectedIndex();

                double result = 0;

                if (type == 0) {

                    double meters = value * lengthFactors[from];
                    result = meters / lengthFactors[to];

                } else if (type == 1) {

                    double kilograms = value * weightFactors[from];
                    result = kilograms / weightFactors[to];

                } else {

                    double celsius;

                    if (from == 0) {
                        celsius = value;
                    } else if (from == 1) {
                        celsius = (value - 32) * 5 / 9;
                    } else {
                        celsius = value - 273.15;
                    }

                    if (to == 0) {
                        result = celsius;
                    } else if (to == 1) {
                        result = celsius * 9 / 5 + 32;
                    } else {
                        result = celsius + 273.15;
                    }
                }

                output.setText(String.format("%.4f", result));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    this, "請輸入有效的數字！");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new UnitConverter();
    }
}
