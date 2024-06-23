package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFindView extends JFrame {
    public UserFindView() {
        setTitle("아이디/비밀번호 찾기");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JButton findIdButton = new JButton("아이디 찾기");
        findIdButton.setBounds(50, 100, 150, 30);
        add(findIdButton);

        JButton findPwButton = new JButton("비밀번호 찾기");
        findPwButton.setBounds(200, 100, 150, 30);
        add(findPwButton);

        findIdButton.addActionListener(new ButtonHandler());
        findPwButton.addActionListener(new ButtonHandler());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("아이디 찾기")) {
                new FindIdView().setVisible(true);
            } else if (command.equals("비밀번호 찾기")) {
                new FindPwView().setVisible(true);
            }
        }
    }
}
