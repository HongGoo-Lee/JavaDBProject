package view.product;

import controller.ProductController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProductView extends JFrame {
    private ProductController productController;

    public DeleteProductView() {
        productController = new ProductController();

        setTitle("물건 삭제");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelProductId = new JLabel("물건 ID:");
        labelProductId.setBounds(50, 50, 100, 30);
        add(labelProductId);
        JTextField textProductId = new JTextField();
        textProductId.setBounds(150, 50, 200, 30);
        add(textProductId);

        JButton deleteButton = new JButton("삭제");
        deleteButton.setBounds(150, 120, 100, 30);
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int productId = Integer.parseInt(textProductId.getText());
                boolean result = productController.deleteProduct(productId);
                if (result) {
                    JOptionPane.showMessageDialog(null, "물건 삭제 성공");
                } else {
                    JOptionPane.showMessageDialog(null, "물건 삭제 실패");
                }
            }
        });
    }
}
