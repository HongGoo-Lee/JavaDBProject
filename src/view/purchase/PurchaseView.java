package view.purchase;

import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaseView extends JFrame {
    private User loggedInUser;

    public PurchaseView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        setTitle("구매 관련");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JButton addPurchaseButton = new JButton("구매 등록");
        addPurchaseButton.setBounds(50, 50, 150, 30);
        add(addPurchaseButton);

        JButton editPurchaseButton = new JButton("구매 수정");
        editPurchaseButton.setBounds(50, 100, 150, 30);
        add(editPurchaseButton);

        JButton deletePurchaseButton = new JButton("구매 삭제");
        deletePurchaseButton.setBounds(50, 150, 150, 30);
        add(deletePurchaseButton);

        JButton viewPurchaseButton = new JButton("구매 조회");
        viewPurchaseButton.setBounds(50, 200, 150, 30);
        add(viewPurchaseButton);

        addPurchaseButton.addActionListener(new ButtonHandler());
        editPurchaseButton.addActionListener(new ButtonHandler());
        deletePurchaseButton.addActionListener(new ButtonHandler());
        viewPurchaseButton.addActionListener(new ButtonHandler());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("구매 등록")) {
                new AddPurchaseView(loggedInUser).setVisible(true);
            } else if (command.equals("구매 수정")) {
                new EditPurchaseView(loggedInUser).setVisible(true);
            } else if (command.equals("구매 삭제")) {
                new DeletePurchaseView(loggedInUser).setVisible(true);
            } else if (command.equals("구매 조회")) {
                new ViewPurchaseView(loggedInUser).setVisible(true);
            }
        }
    }
}
