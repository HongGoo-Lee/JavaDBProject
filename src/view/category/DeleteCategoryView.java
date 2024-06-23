package view.category;

import controller.CategoryController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCategoryView extends JFrame {
    private CategoryController categoryController;

    public DeleteCategoryView() {
        categoryController = new CategoryController();

        setTitle("카테고리 삭제");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel labelCategoryId = new JLabel("카테고리 ID:");
        labelCategoryId.setBounds(50, 50, 100, 30);
        add(labelCategoryId);
        JTextField textCategoryId = new JTextField();
        textCategoryId.setBounds(150, 50, 200, 30);
        add(textCategoryId);

        JButton deleteButton = new JButton("삭제");
        deleteButton.setBounds(150, 120, 100, 30);
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int categoryId = Integer.parseInt(textCategoryId.getText());
                boolean result = categoryController.deleteCategory(categoryId);
                if (result) {
                    JOptionPane.showMessageDialog(null, "카테고리 삭제 성공");
                } else {
                    JOptionPane.showMessageDialog(null, "카테고리 삭제 실패");
                }
            }
        });
    }
}
