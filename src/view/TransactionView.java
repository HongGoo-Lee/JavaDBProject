package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionView extends JFrame {

    public TransactionView() {
        setTitle("거래 내역 확인");
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
                // 거래 내역 조회 로직 추가
                textArea.setText("거래 내역 조회 결과");
            }
        });
    }
}
