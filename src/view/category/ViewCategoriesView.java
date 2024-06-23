package view.category;

import controller.CategoryController;
import model.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewCategoriesView extends JFrame {
    private CategoryController categoryController;

    public ViewCategoriesView() {
        categoryController = new CategoryController();

        setTitle("카테고리 조회");
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
                List<Category> categories = categoryController.getAllCategories();
                StringBuilder sb = new StringBuilder();
                for (Category category : categories) {
                    sb.append("ID: ").append(category.getCategoryId())
                      .append(", 이름: ").append(category.getCategoryName())
                      .append("\n");
                }
                textArea.setText(sb.toString());
            }
        });
    }
}
