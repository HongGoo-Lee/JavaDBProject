package view.product;

import controller.ProductController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductView extends JFrame {
    private ProductController productController;

    public AddProductView() {
        productController = new ProductController();

        setTitle("물건 등록");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelProductName = new JLabel("물건 이름:");
        labelProductName.setBounds(50, 50, 100, 30);
        add(labelProductName);
        JTextField textProductName = new JTextField();
        textProductName.setBounds(150, 50, 200, 30);
        add(textProductName);

        JLabel labelCategory = new JLabel("카테고리:");
        labelCategory.setBounds(50, 90, 100, 30);
        add(labelCategory);
        JTextField textCategory = new JTextField();
        textCategory.setBounds(150, 90, 200, 30);
        add(textCategory);

        JButton addButton = new JButton("등록");
        addButton.setBounds(150, 150, 100, 30);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = textProductName.getText();
                String category = textCategory.getText();
                boolean result = productController.addProduct(productName, category);
                if (result) {
                    JOptionPane.showMessageDialog(null, "물건 등록 성공");
                } else {
                    JOptionPane.showMessageDialog(null, "물건 등록 실패");
                }
            }
        });
    }
}
