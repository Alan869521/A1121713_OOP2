
import javax.swing.*;

public class Login extends JFrame {

    public Login() {

        setTitle("登入視窗");
        setSize(350, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("帳號：");
        l1.setBounds(50, 30, 80, 30);
        add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(130, 30, 150, 30);
        add(t1);

        JLabel l2 = new JLabel("密碼：");
        l2.setBounds(50, 80, 80, 30);
        add(l2);

        JPasswordField t2 = new JPasswordField();
        t2.setBounds(130, 80, 150, 30);
        add(t2);

        JButton btn = new JButton("登入");
        btn.setBounds(120, 140, 100, 30);
        add(btn);

        btn.addActionListener(e -> {

            String account = t1.getText();
            String password = new String(t2.getPassword());

            if (account.equals("admin") &&
                password.equals("1234")) {

                JOptionPane.showMessageDialog(
                    this, "登入成功！");

            } else {

                JOptionPane.showMessageDialog(
                    this, "帳號或密碼錯誤！");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}
