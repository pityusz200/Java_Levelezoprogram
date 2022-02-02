package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoOldalForm extends JFrame {
    private JButton bejelentkezesButton;
    private JTextField textFieldFelhasznaloNev;
    private JTextField textFieldJelszo;
    private JPanel panelMain;

    public FoOldalForm(){
        add(panelMain);
        setTitle("Email projekt - Bejelentkezés");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        textFieldFelhasznaloNev.setText("admin");
        textFieldJelszo.setText("admin");

        bejelentkezesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldFelhasznaloNev.getText().equals("admin") && textFieldJelszo.getText().equals("admin")){
                    AlapFelulet form = null;
                    try {
                        form = new AlapFelulet();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    form.setVisible(true);
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "Felhasználónév vagy jelszó helytelen!");
                }
            }
        });
    }
}