package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AlapFelulet extends JFrame {
    private JPanel panelAlapFelulet;
    private JTextArea textAreaUzenethelye;
    private JComboBox comboBoxNevjegy;
    private JComboBox comboBoxAlairas;
    private JButton levelKuldeseButton;
    private JTextField textFieldTargy;
    private JButton ujNevjegyHozzaadadasaButton;
    private JButton ujAlairasHozzaadadasaButton;
    private JTextArea textAreaAláírás;
    private JLabel labelAllapot;
    public ArrayList<Nevjegyek> nevjegyekList = new ArrayList<>();
    public ArrayList<Alairasok> alairasokList = new ArrayList<>();

    public AlapFelulet() throws Exception {
        add(panelAlapFelulet);
        setTitle("Email projekt - Felhasználói felület");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        lekeresNevjegy();
        lekeresAlaira();
        textAreaAláírás.setText(alairasokList.get(comboBoxAlairas.getSelectedIndex()).alairas);

        ujNevjegyHozzaadadasaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ujNevjegy form = new ujNevjegy();
                form.setVisible(true);
                dispose();
            }
        });

        ujAlairasHozzaadadasaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ujAlairas form = new ujAlairas();
                form.setVisible(true);
                dispose();
            }
        });

        comboBoxAlairas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaAláírás.setText(alairasokList.get(comboBoxAlairas.getSelectedIndex()).alairas);
            }
        });
        levelKuldeseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    uzenetKuldese();
                    labelAllapot.setText("Sikeres levél küldés!");
                } catch (Exception exception) {
                    exception.printStackTrace();
                    labelAllapot.setText("Sikertelen levél küldés!");
                }
            }
        });
    }

    public void lekeresNevjegy() throws Exception {
        var connection2 = Main.getConnection();
        String query2 = "SELECT * FROM `nevjegyek`";
        Statement st2 = connection2.createStatement();
        ResultSet rs2 = st2.executeQuery(query2);
        Nevjegyek nevjegyek;
        while (rs2.next()){
            nevjegyek = new Nevjegyek(
                    rs2.getInt("nevjegyID"),
                    rs2.getString("nevjegyNev"),
                    rs2.getString("nevjegyEmai"));
            nevjegyekList.add(nevjegyek);
    }
        for (var item : nevjegyekList) {
            comboBoxNevjegy.addItem(new ComboItem(item.nevjegyNeve + " - " + item.nevjegy, item.nevjegy));
        }
    }

    public void lekeresAlaira() throws Exception {
        var connection = Main.getConnection();
        String query = "SELECT * FROM `automatikusalairasok`";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        Alairasok alairasok;
        while (rs.next()){
            alairasok = new Alairasok(
                    rs.getInt("automatikusAlairasokID"),
                    rs.getString("alairasNeve"),
                    rs.getString("alairas"));
            alairasokList.add(alairasok);
        }
        for (var item : alairasokList) {
            comboBoxAlairas.addItem(new ComboItem(item.alairasNeve,item.alairas));
        }
    }

    public void uzenetKuldese() throws Exception {
        var connection = Main.getConnection();
        String query = "INSERT INTO `uzenetek` (`uzenetID`, `fogadoID`, `uzenetTargy`, `uzenetSzovege`) VALUES (NULL, ?, ?, ?)";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setInt (1, nevjegyekList.get(comboBoxNevjegy.getSelectedIndex()).nevjegyekID);
        preparedStmt.setString (2, textFieldTargy.getText());
        preparedStmt.setString (3, textAreaUzenethelye.getText() + "\n" + textAreaAláírás.getText());
        preparedStmt.execute();
    }
}

/*
 */