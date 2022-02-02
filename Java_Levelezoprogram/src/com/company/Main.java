package com.company;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws Exception {
        FoOldalForm form = new FoOldalForm();
        form.setVisible(true);

        //JavaMailUtil.sendMail("pitymakan@gmail.com");
        getConnection();
    }

    public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/java_email_kuldo_alkalmazas";
            String username = "root";
            String password = "";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java_email_kuldo_alkalmazas?useUnicode=true&characterEncoding=utf-8", username, password);
            //Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Sikeres kapcsolodás!");
            return conn;
        } catch(Exception e){System.out.println(e);}


        return null;
    }

public static class JavaMailUtil{
    public static void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email!");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "javasendmail111@gmail.com";
        String password = "asdfasdfff";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient);
        Transport.send(message);
        System.out.println("Message sent succesfully!");
    }
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Test levél");
            message.setText("Szia! Ez egy teszt levél!");
            return  message;
        }catch (Exception ex){
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
}