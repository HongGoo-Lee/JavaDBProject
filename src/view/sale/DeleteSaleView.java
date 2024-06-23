package view.sale;

import controller.SaleController;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteSaleView extends JFrame {
    private SaleController saleController;
    private User loggedInUser;

    public DeleteSaleView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        saleController = new SaleController();

        setTitle("판매 삭제");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelSaleId = new JLabel("판매 ID:");
        labelSaleId.setBounds(50, 50, 100, 30);
        add(labelSaleId);
        JTextField textSaleId = new JTextField();
        textSaleId.setBounds(150, 50, 200, 30);
        add(textSaleId);

        JButton deleteButton = new JButton("삭제");
        deleteButton.setBounds(150, 120, 100, 30);
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int saleId = Integer.parseInt(textSaleId.getText());
                    boolean result = saleController.deleteSale(loggedInUser.getUserId(), saleId);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "판매 삭제 성공");
                    } else {
                        JOptionPane.showMessageDialog(null, "판매 삭제 실패");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "유효한 숫자를 입력하세요.");
                }
            }
        });
    }
}
