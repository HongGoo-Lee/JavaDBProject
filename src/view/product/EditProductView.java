package view.product;

import controller.ProductController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProductView extends JFrame {
    private ProductController productController;

    public EditProductView() {
        productController = new ProductController();

        setTitle("물건 수정");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelProductId = new JLabel("물건 ID:");
        labelProductId.setBounds(50, 50, 100, 30);
        add(labelProductId);
        JTextField textProductId = new JTextField();
        textProductId.setBounds(150, 50, 200, 30);
        add(textProductId);

        JLabel labelProductName = new JLabel("물건 이름:");
        labelProductName.setBounds(50, 90, 100, 30);
        add(labelProductName);
        JTextField textProductName = new JTextField();
        textProductName.setBounds(150, 90, 200, 30);
        add(textProductName);

        JLabel labelCategory = new JLabel("카테고리:");
        labelCategory.setBounds(50, 130, 100, 30);
        add(labelCategory);
        JTextField textCategory = new JTextField();
        textCategory.setBounds(150, 130, 200, 30);
        add(textCategory);

        JButton editButton = new JButton("수정");
        editButton.setBounds(150, 190, 100, 30);
        add(editButton);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int productId = Integer.parseInt(textProductId.getText());
                String productName = textProductName.getText();
                String category = textCategory.getText();
                boolean result = productController.editProduct(productId, productName, category);
                if (result) {
                    JOptionPane.showMessageDialog(null, "물건 수정 성공");
                } else {
                    JOptionPane.showMessageDialog(null, "물건 수정 실패");
                }
            }
        });
    }
}
