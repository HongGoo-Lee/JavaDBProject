package view.sale;

import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleView extends JFrame {
    private User loggedInUser;

    public SaleView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        setTitle("판매 관련");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JButton addSaleButton = new JButton("판매 등록");
        addSaleButton.setBounds(50, 50, 150, 30);
        add(addSaleButton);

        JButton editSaleButton = new JButton("판매 수정");
        editSaleButton.setBounds(50, 100, 150, 30);
        add(editSaleButton);

        JButton deleteSaleButton = new JButton("판매 삭제");
        deleteSaleButton.setBounds(50, 150, 150, 30);
        add(deleteSaleButton);

        JButton viewSaleButton = new JButton("판매 조회");
        viewSaleButton.setBounds(50, 200, 150, 30);
        add(viewSaleButton);

        addSaleButton.addActionListener(new ButtonHandler());
        editSaleButton.addActionListener(new ButtonHandler());
        deleteSaleButton.addActionListener(new ButtonHandler());
        viewSaleButton.addActionListener(new ButtonHandler());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("판매 등록")) {
                new AddSaleView(loggedInUser).setVisible(true);
            } else if (command.equals("판매 수정")) {
                new EditSaleView(loggedInUser).setVisible(true);
            } else if (command.equals("판매 삭제")) {
                new DeleteSaleView(loggedInUser).setVisible(true);
            } else if (command.equals("판매 조회")) {
                new ViewSaleView(loggedInUser).setVisible(true);
            }
        }
    }
}
