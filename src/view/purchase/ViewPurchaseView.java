package view.purchase;

import controller.PurchaseController;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewPurchaseView extends JFrame {
    private PurchaseController purchaseController;
    private User loggedInUser;

    public ViewPurchaseView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        purchaseController = new PurchaseController();

        setTitle("구매 조회");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(50, 50, 400, 200);
        add(textArea);

        JButton viewButton = new JButton("조회");
        viewButton.setBounds(200, 260, 100, 30);
        add(viewButton);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> purchases = purchaseController.viewPurchases(loggedInUser.getUserId());
                StringBuilder sb = new StringBuilder();
                for (String purchase : purchases) {
                    sb.append(purchase).append("\n");
                }
                textArea.setText(sb.toString());
            }
        });
    }
}
