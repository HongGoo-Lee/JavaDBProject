package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private UserController userController;
    private JTextField textUserId;
    private JTextField textPassword;

    public MainView() {
        userController = new UserController();
        setTitle("사용자 관리");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel labelUserId = new JLabel("아이디:");
        labelUserId.setBounds(30, 50, 100, 30);
        add(labelUserId);
        textUserId = new JTextField();
        textUserId.setBounds(100, 50, 260, 30);
        add(textUserId);

        JLabel labelPassword = new JLabel("비밀번호:");
        labelPassword.setBounds(30, 90, 100, 30);
        add(labelPassword);
        textPassword = new JTextField();
        textPassword.setBounds(100, 90, 260, 30);
        add(textPassword);

        JButton loginButton = new JButton("로그인");
        loginButton.setBounds(370, 50, 80, 70);
        add(loginButton);

        JButton registerButton = new JButton("회원가입");
        registerButton.setBounds(100, 130, 100, 30);
        add(registerButton);

        JButton findIdPwButton = new JButton("아이디/비밀번호 찾기");
        findIdPwButton.setBounds(230, 130, 150, 30);
        add(findIdPwButton);

        loginButton.addActionListener(new ButtonHandler());
        registerButton.addActionListener(new ButtonHandler());
        findIdPwButton.addActionListener(new ButtonHandler());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("로그인")) {
                String userId = textUserId.getText();
                String password = textPassword.getText();

                User user = userController.loginUser(userId, password);
                if (user != null) {
                    if (user.isManager()) {
                        new AdminView().setVisible(true);
                    } else {
                        new UserView(user).setVisible(true); // UserView로 사용자 정보 전달
                    }
                    MainView.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "로그인 실패.");
                }
            } else if (command.equals("회원가입")) {
                new UserRegisterView(userController).setVisible(true);
            } else if (command.equals("아이디/비밀번호 찾기")) {
                new UserFindView().setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainView().setVisible(true));
    }
}
