package view;

import controller.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class UserRegisterView extends JFrame {
    private JTextField textUserId;
    private JTextField textPassword;
    private JTextField textName;
    private JTextField textBirthDate;
    private JTextField textPhoneNumber;
    private JTextField textGender;
    private JTextField textManager;
    private UserController userController;

    public UserRegisterView(UserController userController) {
        this.userController = userController;
        setTitle("회원가입");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelUserId = new JLabel("아이디:");
        labelUserId.setBounds(50, 50, 100, 30);
        add(labelUserId);
        textUserId = new JTextField();
        textUserId.setBounds(200, 50, 200, 30);
        add(textUserId);

        JLabel labelPassword = new JLabel("비밀번호:");
        labelPassword.setBounds(50, 90, 100, 30);
        add(labelPassword);
        textPassword = new JTextField();
        textPassword.setBounds(200, 90, 200, 30);
        add(textPassword);

        JLabel labelName = new JLabel("이름:");
        labelName.setBounds(50, 130, 100, 30);
        add(labelName);
        textName = new JTextField();
        textName.setBounds(200, 130, 200, 30);
        add(textName);

        JLabel labelBirthDate = new JLabel("생년월일 (YYYY-MM-DD):");
        labelBirthDate.setBounds(50, 170, 150, 30);
        add(labelBirthDate);
        textBirthDate = new JTextField();
        textBirthDate.setBounds(200, 170, 200, 30);
        add(textBirthDate);

        JLabel labelPhoneNumber = new JLabel("전화번호:");
        labelPhoneNumber.setBounds(50, 210, 100, 30);
        add(labelPhoneNumber);
        textPhoneNumber = new JTextField();
        textPhoneNumber.setBounds(200, 210, 200, 30);
        add(textPhoneNumber);

        JLabel labelGender = new JLabel("성별 (남/여):");
        labelGender.setBounds(50, 250, 150, 30);
        add(labelGender);
        textGender = new JTextField();
        textGender.setBounds(200, 250, 200, 30);
        add(textGender);

        JLabel labelManager = new JLabel("관리자 여부 (예/아니요):");
        labelManager.setBounds(50, 290, 150, 30);
        add(labelManager);
        textManager = new JTextField();
        textManager.setBounds(200, 290, 200, 30);
        add(textManager);

        JButton registerButton = new JButton("회원가입");
        registerButton.setBounds(200, 330, 100, 30);
        add(registerButton);

        registerButton.addActionListener(new ButtonHandler());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String userId = textUserId.getText();
                String password = textPassword.getText();
                String name = textName.getText();
                String birthDate = textBirthDate.getText();
                String phoneNumber = textPhoneNumber.getText();
                boolean gender = textGender.getText().equalsIgnoreCase("남");
                boolean state = true;
                boolean manager = textManager.getText().equalsIgnoreCase("예");

                boolean result = userController.registerUser(userId, password, name, Date.valueOf(birthDate), phoneNumber, gender, state, manager);
                if (result) {
                    JOptionPane.showMessageDialog(null, "회원가입 성공!");
                } else {
                    JOptionPane.showMessageDialog(null, "회원가입 실패: 이미 존재하는 아이디 또는 전화번호입니다.");
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "입력한 날짜 형식이 잘못되었습니다.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "회원가입 중 오류가 발생했습니다: " + ex.getMessage());
            }
        }
    }
}
