package com.TencentClient.view;

import com.TencentClient.model.ClientConServer;
import com.TencentClient.tools.ManageClientThread;
import com.TencentCommon.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static java.awt.event.KeyEvent.*;

public class QQClientChat extends JFrame implements ActionListener,KeyListener{

    JTextArea jta;
    JButton jb;
    JScrollPane jsp;
    JTextField jtf;
    JPanel jp;
    int x, y;
    String ownerId,friend;
    ClientConServer ccs;
    Message m;

    public QQClientChat(String ownerId,String friend) {
        this.setLayout(new BorderLayout());
        this.ownerId = ownerId;
        this.friend = friend;

        x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250;
        y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150;

        jta = new JTextArea();
        jb = new JButton("发送");
        jtf = new JTextField(20);
        jsp = new JScrollPane(jta);
        jp = new JPanel();

        jp.add(jtf);
        jp.add(jb);
        jtf.addKeyListener(this);
        jb.addActionListener(this);

        this.add(jsp, BorderLayout.CENTER);
        this.add(jp, BorderLayout.SOUTH);
        this.setTitle(ownerId+ " 与" +friend+" 聊天中...");
        this.setSize(400, 300);
        this.setLocation(x, y);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb) {
            sendMessage();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==VK_ENTER){
            sendMessage();
        }
    }
    public void showMessage(Message m){
        String info = m.getSendTime() + "\r\n" + m.getSender() + " 对 " + m.getReceiver() + " 说: " + m.getContent()+ "\r\n\n";
        this.jta.append(info);
    }

    public void sendMessage(){
        m = new Message();
        m.setSender(this.ownerId);
        m.setReceiver(this.friend);
        m.setContent(jtf.getText());
        m.setSendTime(new java.util.Date().toString());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getManageClientThread(ownerId).getSt().getOutputStream());
            oos.writeObject(m);
            String info = m.getSendTime() + "\r\n" + m.getSender() + " 对 " + m.getReceiver() + " 说: " + m.getContent()+ "\r\n\n";
            this.jta.append(info);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void keyReleased(KeyEvent e) {

    }

//    public void run() {
//        while(true){
//            try {
//                ObjectInputStream ois = new ObjectInputStream(ClientConServer.st.getInputStream());
//                Message m = (Message) ois.readObject();
//                String info = m.getSendTime() + "\r\n" + m.getSender() + " 对 " + m.getReceiver() + " 说: " + m.getContent()+ "\r\n\n";
//                this.jta.append(info);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
}

