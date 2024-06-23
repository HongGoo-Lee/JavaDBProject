package view;

import view.product.AddProductView;
import view.product.EditProductView;
import view.product.DeleteProductView;
import view.product.ViewProductsView;
import view.category.AddCategoryView;
import view.category.EditCategoryView;
import view.category.DeleteCategoryView;
import view.category.ViewCategoriesView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {

    public AdminView() {
        setTitle("관리자 화면");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton addProductButton = new JButton("물건 등록");
        addProductButton.setBounds(50, 50, 150, 30);
        add(addProductButton);

        JButton editProductButton = new JButton("물건 수정");
        editProductButton.setBounds(50, 100, 150, 30);
        add(editProductButton);

        JButton deleteProductButton = new JButton("물건 삭제");
        deleteProductButton.setBounds(50, 150, 150, 30);
        add(deleteProductButton);

        JButton viewProductsButton = new JButton("물건 조회");
        viewProductsButton.setBounds(50, 200, 150, 30);
        add(viewProductsButton);

        JButton addCategoryButton = new JButton("카테고리 등록");
        addCategoryButton.setBounds(250, 50, 150, 30);
        add(addCategoryButton);

        JButton editCategoryButton = new JButton("카테고리 수정");
        editCategoryButton.setBounds(250, 100, 150, 30);
        add(editCategoryButton);

        JButton deleteCategoryButton = new JButton("카테고리 삭제");
        deleteCategoryButton.setBounds(250, 150, 150, 30);
        add(deleteCategoryButton);

        JButton viewCategoriesButton = new JButton("카테고리 조회");
        viewCategoriesButton.setBounds(250, 200, 150, 30);
        add(viewCategoriesButton);

        // 각 버튼에 대한 이벤트 핸들러 추가
        addProductButton.addActionListener(new ButtonHandler());
        editProductButton.addActionListener(new ButtonHandler());
        deleteProductButton.addActionListener(new ButtonHandler());
        viewProductsButton.addActionListener(new ButtonHandler());
        addCategoryButton.addActionListener(new ButtonHandler());
        editCategoryButton.addActionListener(new ButtonHandler());
        deleteCategoryButton.addActionListener(new ButtonHandler());
        viewCategoriesButton.addActionListener(new ButtonHandler());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("물건 등록")) {
                new AddProductView().setVisible(true);
            } else if (command.equals("물건 수정")) {
                new EditProductView().setVisible(true);
            } else if (command.equals("물건 삭제")) {
                new DeleteProductView().setVisible(true);
            } else if (command.equals("물건 조회")) {
                new ViewProductsView().setVisible(true);
            } else if (command.equals("카테고리 등록")) {
                new AddCategoryView().setVisible(true);
            } else if (command.equals("카테고리 수정")) {
                new EditCategoryView().setVisible(true);
            } else if (command.equals("카테고리 삭제")) {
                new DeleteCategoryView().setVisible(true);
            } else if (command.equals("카테고리 조회")) {
                new ViewCategoriesView().setVisible(true);
            }
        }
    }
}
