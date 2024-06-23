package view.sale;

import controller.SaleController;
import model.User;
import view.product.ViewProductsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSaleView extends JFrame {
    private SaleController saleController;
    private User loggedInUser;

    public AddSaleView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        saleController = new SaleController();

        setTitle("판매 등록");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelProductId = new JLabel("제품 ID:");
        labelProductId.setBounds(50, 50, 100, 30);
        add(labelProductId);
        JTextField textProductId = new JTextField();
        textProductId.setBounds(150, 50, 200, 30);
        add(textProductId);

        JLabel labelPrice = new JLabel("가격:");
        labelPrice.setBounds(50, 90, 100, 30);
        add(labelPrice);
        JTextField textPrice = new JTextField();
        textPrice.setBounds(150, 90, 200, 30);
        add(textPrice);

        JButton addButton = new JButton("등록");
        addButton.setBounds(50, 150, 100, 30);
        add(addButton);

        JButton viewProductsButton = new JButton("물건 조회");
        viewProductsButton.setBounds(200, 150, 100, 30);
        add(viewProductsButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int productId = Integer.parseInt(textProductId.getText());
                    long price = Long.parseLong(textPrice.getText());
                    String userId = loggedInUser.getUserId();
                    boolean result = saleController.addSale(userId, productId, price);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "판매 등록 성공");
                    } else {
                        JOptionPane.showMessageDialog(null, "판매 등록 실패");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "유효한 숫자를 입력하세요.");
                }
            }
        });

        viewProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewProductsView().setVisible(true);
            }
        });
    }
}
