package view.category;

import controller.CategoryController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCategoryView extends JFrame {
    private CategoryController categoryController;

    public EditCategoryView() {
        categoryController = new CategoryController();

        setTitle("카테고리 수정");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelCategoryId = new JLabel("카테고리 ID:");
        labelCategoryId.setBounds(50, 50, 100, 30);
        add(labelCategoryId);
        JTextField textCategoryId = new JTextField();
        textCategoryId.setBounds(150, 50, 200, 30);
        add(textCategoryId);

        JLabel labelCategoryName = new JLabel("카테고리 이름:");
        labelCategoryName.setBounds(50, 90, 100, 30);
        add(labelCategoryName);
        JTextField textCategoryName = new JTextField();
        textCategoryName.setBounds(150, 90, 200, 30);
        add(textCategoryName);

        JButton editButton = new JButton("수정");
        editButton.setBounds(150, 150, 100, 30);
        add(editButton);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int categoryId = Integer.parseInt(textCategoryId.getText());
                String categoryName = textCategoryName.getText();
                boolean result = categoryController.editCategory(categoryId, categoryName);
                if (result) {
                    JOptionPane.showMessageDialog(null, "카테고리 수정 성공");
                } else {
                    JOptionPane.showMessageDialog(null, "카테고리 수정 실패");
                }
            }
        });
    }
}
