package view.purchase;

import controller.PurchaseController;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePurchaseView extends JFrame {
    private PurchaseController purchaseController;
    private User loggedInUser;

    public DeletePurchaseView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        purchaseController = new PurchaseController();

        setTitle("구매 삭제");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelPurchaseId = new JLabel("구매 ID:");
        labelPurchaseId.setBounds(50, 50, 100, 30);
        add(labelPurchaseId);
        JTextField textPurchaseId = new JTextField();
        textPurchaseId.setBounds(150, 50, 200, 30);
        add(textPurchaseId);

        JButton deleteButton = new JButton("삭제");
        deleteButton.setBounds(150, 120, 100, 30);
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int purchaseId = Integer.parseInt(textPurchaseId.getText());
                    boolean result = purchaseController.deletePurchase(loggedInUser.getUserId(), purchaseId);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "구매 삭제 성공");
                    } else {
                        JOptionPane.showMessageDialog(null, "구매 삭제 실패");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "유효한 숫자를 입력하세요.");
                }
            }
        });
    }
}
