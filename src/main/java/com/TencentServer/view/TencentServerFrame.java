package com.TencentServer.view;

/*
 * 服务器控制界面，启动，关闭服务器，管理，监控用户
 */
import com.TencentServer.model.TencentServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TencentServerFrame extends JFrame implements ActionListener{
    JPanel jp1;
    JButton jb1,jb2;
    TencentServer ts;
    int x,y;

    public static void main(String[] args) {
        TencentServerFrame tsf = new TencentServerFrame();
    }
    public TencentServerFrame(){
        jp1 = new JPanel();
        jb1 = new JButton("启动服务器");
        jb2 = new JButton("关闭服务器");

        jp1.add(jb1);
        jp1.add(jb2);

        jb1.addActionListener(this);
        jb2.addActionListener(this);

        x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250;
        y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150;

        this.add(jp1);
        this.setSize(500,400);
        this.setLocation(x,y);
        this.setTitle("腾讯服务器管理");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb1){
            ts = new TencentServer();

        }else if(e.getSource() == jb2){
            ts.close();
        }

    }
}
