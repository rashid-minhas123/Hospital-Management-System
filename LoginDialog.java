import javax.swing.*;
import java.awt.*;

class LoginDialog extends JDialog {
    boolean authenticated = false;
    private int attempts = 0;
    private JTextField     userF = HMS.field();
    private JPasswordField passF = HMS.pass();

    LoginDialog() {
        super((Frame) null, "HMS Login", true);
        setSize(400, 370);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JPanel bg = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(new GradientPaint(0, 0, new Color(8, 12, 30),
                                              getWidth(), getHeight(), new Color(15, 25, 60)));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setPaint(new GradientPaint(0, 0, HMS.C_ACCENT2, getWidth(), 0, HMS.C_ACCENT));
                g2.fillRect(0, 0, getWidth(), 3);
                g2.dispose();
            }
        };
        bg.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        setContentPane(bg);

        JPanel brand = new JPanel();
        brand.setOpaque(false);
        brand.setLayout(new BoxLayout(brand, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel("[ HMS ]", SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI", Font.BOLD, 28));
        icon.setForeground(HMS.C_ACCENT);
        icon.setAlignmentX(CENTER_ALIGNMENT);

        JLabel title = new JLabel("Hospital Management", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(HMS.C_TEXT);
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel sub = new JLabel("Sign in to continue", SwingConstants.CENTER);
        sub.setFont(HMS.F_SMALL);
        sub.setForeground(HMS.C_MUTED);
        sub.setAlignmentX(CENTER_ALIGNMENT);

        brand.add(icon);
        brand.add(Box.createVerticalStrut(8));
        brand.add(title);
        brand.add(Box.createVerticalStrut(4));
        brand.add(sub);
        brand.add(Box.createVerticalStrut(22));

        JPanel form = new JPanel(new GridLayout(4, 1, 0, 8));
        form.setOpaque(false);
        form.add(HMS.lbl("Username"));
        form.add(userF);
        form.add(HMS.lbl("Password"));
        form.add(passF);

        JButton loginBtn = HMS.btnPrimary("  Sign In  ");
        loginBtn.setPreferredSize(new Dimension(300, 38));
        JPanel btnWrap = new JPanel(new BorderLayout());
        btnWrap.setOpaque(false);
        btnWrap.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));
        btnWrap.add(loginBtn, BorderLayout.CENTER);

        loginBtn.addActionListener(e -> doLogin());
        passF.addActionListener(e -> doLogin());

        bg.add(brand,   BorderLayout.NORTH);
        bg.add(form,    BorderLayout.CENTER);
        bg.add(btnWrap, BorderLayout.SOUTH);
    }

    private void doLogin() {
        String u = userF.getText().trim();
        String p = new String(passF.getPassword());
        if ("admin".equals(u) && "admin123".equals(p)) {
            authenticated = true;
            dispose();
        } else {
            attempts++;
            if (attempts >= 3) {
                JOptionPane.showMessageDialog(this, "Too many failed attempts. Exiting.");
                System.exit(0);
            }
            JOptionPane.showMessageDialog(this,
                "Wrong credentials. " + (3 - attempts) + " attempt(s) left.", "Login Failed",
                JOptionPane.ERROR_MESSAGE);
            passF.setText("");
        }
    }
}
