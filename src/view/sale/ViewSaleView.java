package view.sale;

import controller.SaleController;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewSaleView extends JFrame {
    private SaleController saleController;
    private User loggedInUser;

    public ViewSaleView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        saleController = new SaleController();

        setTitle("판매 조회");
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
                List<String> sales = saleController.viewSales(loggedInUser.getUserId());
                StringBuilder sb = new StringBuilder();
                for (String sale : sales) {
                    sb.append(sale).append("\n");
                }
                textArea.setText(sb.toString());
            }
        });
    }
}
