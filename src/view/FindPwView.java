package view;

import controller.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindPwView extends JFrame {
    private UserController userController;

    public FindPwView() {
        userController = new UserController();

        setTitle("비밀번호 찾기");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelUserId = new JLabel("아이디:");
        labelUserId.setBounds(50, 50, 100, 30);
        add(labelUserId);
        JTextField textUserId = new JTextField();
        textUserId.setBounds(200, 50, 200, 30);
        add(textUserId);

        JLabel labelPhoneNumber = new JLabel("전화번호:");
        labelPhoneNumber.setBounds(50, 100, 100, 30);
        add(labelPhoneNumber);
        JTextField textPhoneNumber = new JTextField();
        textPhoneNumber.setBounds(200, 100, 200, 30);
        add(textPhoneNumber);

        JButton findPwButton = new JButton("비밀번호 찾기");
        findPwButton.setBounds(150, 200, 150, 30);
        add(findPwButton);

        findPwButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = textUserId.getText();
                String phoneNumber = textPhoneNumber.getText();
                String password = userController.findUserPassword(userId, phoneNumber);
                if (password != null) {
                    JOptionPane.showMessageDialog(null, "비밀번호: " + password);
                } else {
                    JOptionPane.showMessageDialog(null, "비밀번호를 찾을 수 없습니다.");
                }
            }
        });
    }
}
