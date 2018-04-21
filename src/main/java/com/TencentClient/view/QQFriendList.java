package com.TencentClient.view;

import com.TencentClient.tools.ManageClientChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class QQFriendList extends JFrame implements ActionListener,MouseListener{
    //Card1
    JButton jb1,jb2,jb3;
    JPanel jp1,jp2,jp3;
    JScrollPane jsp1;
    JLabel []jll;
    CardLayout cl;

    //Card2
    JButton jbmsr1,jbmsr2,jbmsr3;
    JPanel jpmsr1,jpmsr2,jpmsr3;
    JScrollPane jsp2;
    JLabel []jll2;

    //Card3
    JButton jbbl1,jbbl2,jbbl3;
    JPanel jpbl1,jpbl2,jpbl3;
    JScrollPane jsp3;
    JLabel []jll3;

    String ownerId;
    public QQFriendList(String ownerId){
        this.ownerId = ownerId;
        initCard1();
        initCard2();
        initCard3();

        cl = new CardLayout();
        this.setLayout(cl);
        this.add(jp1,"1");
        this.add(jpmsr1,"2");
        this.add(jpbl1,"3");
//        this.add(bp,"3");

        this.setSize(150,500);
        this.setTitle(ownerId);
        this.setLocation(300,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void initCard1(){
        jp1 = new JPanel(new BorderLayout());
        jp2 = new JPanel(new GridLayout(50,1,4,4));
        jp3 = new JPanel(new GridLayout(2,1));

        jb1 = new JButton("我的好友");
        jb2 = new JButton("陌生人");
        jb3 = new JButton("黑名单");

        jb2.addActionListener(this);
        jb3.addActionListener(this);

        jp3.add(jb2);
        jp3.add(jb3);

        this.initLabelList(this.jll,jsp1,jp2,jp1,50);
        jp1.add(jb1,BorderLayout.NORTH);
        jp1.add(jp3,BorderLayout.SOUTH);
    }

    public void initCard2(){
        jpmsr1 = new JPanel(new BorderLayout());
        jpmsr2 = new JPanel(new GridLayout(20,1,4,4));
        jpmsr3 = new JPanel(new GridLayout(2,1));

        jbmsr1 = new JButton("我的好友");
        jbmsr2 = new JButton("陌生人");
        jbmsr3 = new JButton("黑名单");

        jbmsr1.addActionListener(this);
        jbmsr3.addActionListener(this);

        jpmsr3.add(jbmsr1);
        jpmsr3.add(jbmsr2);


        this.initLabelList(this.jll2,jsp2,jpmsr2,jpmsr1,20);

        jpmsr1.add(jbmsr3,BorderLayout.SOUTH);
        jpmsr1.add(jpmsr3,BorderLayout.NORTH);

    }
    public void initCard3(){
        jpbl1 = new JPanel(new BorderLayout());
        jpbl2 = new JPanel(new GridLayout(50,1,4,4));
        jpbl3 = new JPanel(new GridLayout(3,1));

        jbbl1 = new JButton("我的好友");
        jbbl2 = new JButton("陌生人");
        jbbl3 = new JButton("黑名单");
        jbbl1.addActionListener(this);
        jbbl2.addActionListener(this);

        jpbl3.add(jbbl1);
        jpbl3.add(jbbl2);
        jpbl3.add(jbbl3);

        this.initLabelList(this.jll3,jsp3,jpbl2,jpbl1,5);

        jpbl1.add(jpbl3,BorderLayout.NORTH);

    }

    public JLabel[] initLabelList(JLabel []jll,JScrollPane jsp,JPanel jpanel,JPanel jp11,int count) {
        jll = new JLabel[count];

        for (int i = 0; i < jll.length; i++) {
            jll[i] = new JLabel(i + 1 + "", new ImageIcon("src/main/resources/image/mm.jpg"), JLabel.LEFT);
            if(jll[i].getText().equals(ownerId)){
                jll[i].setEnabled(true);
            }else{
                jll[i].setEnabled(false);
            }

            jll[i].addMouseListener(this);
            jpanel.add(jll[i]);
        }
        jsp = new JScrollPane(jpanel);
        jp11.add(jsp, BorderLayout.CENTER);

        return jll;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb2){
            cl.show(this.getContentPane(),"2");
        }else if(e.getSource() == jb3){
            cl.show(this.getContentPane(),"3");
        }else if(e.getSource() == jbmsr1){
            cl.show(this.getContentPane(),"1");
        }else if(e.getSource() == jbmsr3){
            cl.show(this.getContentPane(),"3");
        }else if(e.getSource() == jbbl1){
            cl.show(this.getContentPane(),"1");
        }else if(e.getSource() == jbbl2){
            cl.show(this.getContentPane(),"2");
        }
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
             String friend = ((JLabel) e.getSource()).getText();
             QQClientChat qqcc = new QQClientChat(this.ownerId,friend);
            ManageClientChat.addQQChat(this.ownerId + " " + friend,qqcc);
//            QQClientChat qqcc =
//             Thread t = new Thread(qqcc);
//             t.start();

        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        JLabel jl1 = (JLabel) e.getSource();
        jl1.setForeground(Color.red);
    }

    public void mouseExited(MouseEvent e) {
        JLabel jl1 = (JLabel) e.getSource();
        jl1.setForeground(Color.black);
    }
}
