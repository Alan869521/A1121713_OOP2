import javax.swing.*;

public class BadLogin extends JFrame {

    public BadLogin() {
        // 1. 設定視窗基本屬性
        setTitle("登入");
        setSize(300, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // 設定為手動座標排版

        // 2. 建立元件
        JLabel l1 = new JLabel("帳號:");
        JTextField t1 = new JTextField();

        JLabel l2 = new JLabel("密碼:");
        JPasswordField t2 = new JPasswordField(); // 密碼框使用 JPasswordField

        JButton btn = new JButton("登入");

        // 3. 補上座標與大小設定 (setBounds: x, y, width, height)
        l1.setBounds(30, 20, 60, 25);
        t1.setBounds(90, 20, 150, 25);

        l2.setBounds(30, 60, 60, 25);
        t2.setBounds(90, 60, 150, 25);

        btn.setBounds(90, 100, 100, 30);

        // 4. 將元件加入視窗容器中
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(btn);

        // 5. 為按鈕註冊事件監聽器
        btn.addActionListener(e -> {
            String account = t1.getText();
            String password = new String(t2.getPassword());

            // 正確使用 .equals() 比對字串內容
            if (account.equals("admin") && password.equals("1234")) {
                System.out.println("登入成功！");
                JOptionPane.showMessageDialog(this, "登入成功！");
            } else {
                System.out.println("帳號或密碼錯誤！");
                JOptionPane.showMessageDialog(this, "帳號或密碼錯誤！");
            }
        });

        // 6. 最後才設定視窗為可見（解決渲染空白問題）
        setVisible(true);
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // 建立物件並接收，避免 IDE 警告
        BadLogin frame = new BadLogin();
    }
}