package view;

import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends JFrame {
    private User loggedInUser;

    public UserView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        setTitle("회원 화면");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton purchaseButton = new JButton("구매 관련");
        purchaseButton.setBounds(50, 50, 150, 30);
        add(purchaseButton);

        JButton saleButton = new JButton("판매 관련");
        saleButton.setBounds(50, 100, 150, 30);
        add(saleButton);

        JButton transactionButton = new JButton("거래 내역 확인");
        transactionButton.setBounds(50, 150, 150, 30);
        add(transactionButton);

        purchaseButton.addActionListener(new ButtonHandler());
        saleButton.addActionListener(new ButtonHandler());
        transactionButton.addActionListener(new ButtonHandler());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("구매 관련")) {
                new PurchaseView(loggedInUser).setVisible(true);
            } else if (command.equals("판매 관련")) {
                new SaleView(loggedInUser).setVisible(true);
            } else if (command.equals("거래 내역 확인")) {
                new TransactionView(loggedInUser).setVisible(true);
            }
        }
    }
}
