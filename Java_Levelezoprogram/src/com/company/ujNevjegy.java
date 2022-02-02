package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class ujNevjegy extends JFrame{
    private JTextArea textAreaNevjegy;
    private JTextField textFieldNevjegyNeve;
    private JButton megsemButton;
    private JPanel ujNevjegyPanel;
    private JButton buttonHozzaad;
    private JLabel labelAllapot;
    private JTextField textFieldNevjegy;
    Statement stmt = null;

    public ujNevjegy(){
        add(ujNevjegyPanel);
        setTitle("Email projekt - Névjegyek felvétele");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        buttonHozzaad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ujNevjegyFeltoltese(textFieldNevjegyNeve.getText(), textFieldNevjegy.getText());
                } catch (Exception exception) {
                    exception.printStackTrace();
                    labelAllapot.setText("Sikertelen névjegy felöltés!");
                }
                labelAllapot.setText("Sikeres névjegy felöltés!");
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

    public void ujNevjegyFeltoltese(String nevjegyNeve, String nevjegy) throws Exception {
                var connection = Main.getConnection();
                String query = "INSERT INTO `nevjegyek` (`nevjegyID`, `nevjegyNev`, `nevjegyEmai`) VALUES (NULL, ?, ?)";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString (1, nevjegyNeve);
                preparedStmt.setString (2, nevjegy);
                preparedStmt.execute();
        }
    }