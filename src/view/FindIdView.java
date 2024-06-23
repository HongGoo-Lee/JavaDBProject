package view;

import controller.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindIdView extends JFrame {
    private UserController userController;

    public FindIdView() {
        userController = new UserController();

        setTitle("아이디 찾기");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelPhoneNumber = new JLabel("전화번호:");
        labelPhoneNumber.setBounds(50, 50, 100, 30);
        add(labelPhoneNumber);
        JTextField textPhoneNumber = new JTextField();
        textPhoneNumber.setBounds(200, 50, 200, 30);
        add(textPhoneNumber);

        JButton findIdButton = new JButton("아이디 찾기");
        findIdButton.setBounds(150, 150, 150, 30);
        add(findIdButton);

        findIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = textPhoneNumber.getText();
                String userId = userController.findUserId(phoneNumber);
                if (userId != null) {
                    JOptionPane.showMessageDialog(null, "아이디: " + userId);
                } else {
                    JOptionPane.showMessageDialog(null, "아이디를 찾을 수 없습니다.");
                }
            }
        });
    }
}
