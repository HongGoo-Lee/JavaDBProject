package view.sale;

import controller.SaleController;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditSaleView extends JFrame {
    private SaleController saleController;
    private User loggedInUser;

    public EditSaleView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        saleController = new SaleController();

        setTitle("판매 수정");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelSaleId = new JLabel("판매 ID:");
        labelSaleId.setBounds(50, 50, 100, 30);
        add(labelSaleId);
        JTextField textSaleId = new JTextField();
        textSaleId.setBounds(150, 50, 200, 30);
        add(textSaleId);

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
                    int saleId = Integer.parseInt(textSaleId.getText());
                    long price = Long.parseLong(textPrice.getText());
                    boolean result = saleController.editSale(loggedInUser.getUserId(), saleId, price);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "판매 수정 성공");
                    } else {
                        JOptionPane.showMessageDialog(null, "판매 수정 실패");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "유효한 숫자를 입력하세요.");
                }
            }
        });
    }
}
