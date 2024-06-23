package view.purchase;

import controller.PurchaseController;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPurchaseView extends JFrame {
    private PurchaseController purchaseController;
    private User loggedInUser;

    public EditPurchaseView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        purchaseController = new PurchaseController();

        setTitle("구매 수정");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelPurchaseId = new JLabel("구매 ID:");
        labelPurchaseId.setBounds(50, 50, 100, 30);
        add(labelPurchaseId);
        JTextField textPurchaseId = new JTextField();
        textPurchaseId.setBounds(150, 50, 200, 30);
        add(textPurchaseId);

        JLabel labelPrice = new JLabel("가격:");
        labelPrice.setBounds(50, 90, 100, 30);
        add(labelPrice);
        JTextField textPrice = new JTextField();
        textPrice.setBounds(150, 90, 200, 30);
        add(textPrice);

        JButton editButton = new JButton("수정");
        editButton.setBounds(150, 150, 100, 30);
        add(editButton);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int purchaseId = Integer.parseInt(textPurchaseId.getText());
                    long price = Long.parseLong(textPrice.getText());
                    boolean result = purchaseController.editPurchase(loggedInUser.getUserId(), purchaseId, price);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "구매 수정 성공");
                    } else {
                        JOptionPane.showMessageDialog(null, "구매 수정 실패");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "유효한 숫자를 입력하세요.");
                }
            }
        });
    }
}
