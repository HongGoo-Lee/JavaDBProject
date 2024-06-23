package view.category;

import controller.CategoryController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCategoryView extends JFrame {
    private CategoryController categoryController;

    public AddCategoryView() {
        categoryController = new CategoryController();

        setTitle("카테고리 등록");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelCategoryName = new JLabel("카테고리 이름:");
        labelCategoryName.setBounds(50, 50, 100, 30);
        add(labelCategoryName);
        JTextField textCategoryName = new JTextField();
        textCategoryName.setBounds(150, 50, 200, 30);
        add(textCategoryName);

        JButton addButton = new JButton("등록");
        addButton.setBounds(150, 120, 100, 30);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoryName = textCategoryName.getText();
                boolean result = categoryController.addCategory(categoryName);
                if (result) {
                    JOptionPane.showMessageDialog(null, "카테고리 등록 성공");
                } else {
                    JOptionPane.showMessageDialog(null, "카테고리 등록 실패");
                }
            }
        });
    }
}
