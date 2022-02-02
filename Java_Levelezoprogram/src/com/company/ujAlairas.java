package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ujAlairas extends JFrame{
    private JPanel ujAlairasPanel;
    private JTextArea textAreaAlairas;
    private JTextField textFieldAlairasNeve;
    private JButton hozzaadButton;
    private JButton megsemButton;
    private JLabel labelAllapot;

    public ujAlairas(){
        add(ujAlairasPanel);
        setTitle("Email projekt - Aláírás");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        hozzaadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ujAlairasFeltoltese(textFieldAlairasNeve.getText(), textAreaAlairas.getText());
                } catch (Exception exception) {
                    exception.printStackTrace();
                    labelAllapot.setText("Sikertelen aláírás felöltés!");
                }
                labelAllapot.setText("Sikeres aláírás felöltés!");
            }
        });

        megsemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlapFelulet form = null;
                try {
                    form = new AlapFelulet();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                form.setVisible(true);
                dispose();
            }
        });
    }

    public void ujAlairasFeltoltese(String alairasNeve, String alairas) throws Exception {
        var connection = Main.getConnection();
        String query = "INSERT INTO `automatikusalairasok` (`automatikusAlairasokID`, `alairasNeve`, `alairas`) VALUES (NULL, ?, ?)";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1, alairasNeve);
        preparedStmt.setString (2, alairas);
        preparedStmt.execute();
    }
}
