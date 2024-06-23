package view.product;

import controller.ProductController;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewProductsView extends JFrame {
    private ProductController productController;

    public ViewProductsView() {
        productController = new ProductController();

        setTitle("물건 조회");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton viewButton = new JButton("조회");
        add(viewButton, BorderLayout.SOUTH);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> products = productController.getAllProducts();
                StringBuilder sb = new StringBuilder();
                for (Product product : products) {
                    sb.append("ID: ").append(product.getProductId())
                      .append(", 이름: ").append(product.getProductName())
                      .append(", 카테고리: ").append(product.getCategory())
                      .append("\n");
                }
                textArea.setText(sb.toString());
            }
        });
    }
}
