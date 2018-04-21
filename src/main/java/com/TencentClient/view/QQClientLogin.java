package com.TencentClient.view;

import com.TencentClient.model.ClientAuth;
import com.TencentClient.tools.ManageClientThread;
import com.TencentClient.tools.ManageFriendList;
import com.TencentCommon.Message;
import com.TencentCommon.MessageType;
import com.TencentCommon.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class QQClientLogin extends JFrame implements ActionListener{
    //North
    JLabel jl1;

    //South
    JPanel jp1;
    JButton jb1,jb2,jb3;

    //Center
    JTabbedPane jtp;
    JPanel jp2,jp3,jp4;
    JLabel jl2,jl3,jl4,jl5;
    JTextField jtf;
    JPasswordField jpf;
    JButton jb4;
    JCheckBox jcb1,jcb2;
    ImageIcon im1,im2,im3,im4;

    int x,y;

    public static void main(String[] args) {

        QQClientLogin qql= new QQClientLogin();
    }

    public QQClientLogin(){

        jl2 = new JLabel("QQ号码: ", JLabel.CENTER);
        jl3 = new JLabel("QQ密码: ",JLabel.CENTER);
        jl4 = new JLabel("忘记密码",JLabel.CENTER);
        jl4.setForeground(Color.blue);
        jl5 = new JLabel("<html><a href='www.qq.com'>申请密码保护</a></html>");
        jl5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        jtf = new JTextField();
        jpf = new JPasswordField();

        im4 = new ImageIcon("src/main/resources/image/clear.gif");
        jb4 = new JButton("清除号码");

        jcb1 = new JCheckBox("隐身登录");
        jcb2 = new JCheckBox("记住密码");

        //North Part
        jl1 = new JLabel(new ImageIcon("src/main/resources/image/tou.gif"));

        //South Part
        im1= new ImageIcon("src/main/resources/image/denglu.gif");
        im2= new ImageIcon("src/main/resources/image/quxiao.gif");
        im3= new ImageIcon("src/main/resources/image/xiangdao.gif");

        jp1 = new JPanel();
        jb1 = new JButton(im1);
        jb2 = new JButton(im2);
        jb3 = new JButton(im3);

        jb1.addActionListener(this);
        jb2.addActionListener(this);
        //Center Part
        jtp = new JTabbedPane();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp3.setBackground(Color.RED);
        jp4 = new JPanel();
        jp4.setBackground(new Color(0,0,255));
        jtp.add("QQ号码",jp2);
        jtp.add("手机号码",jp3);
        jtp.add("电子邮箱",jp4);
        jp2.setLayout(new GridLayout(3,3));


        jp1.add(jb1);
        jp1.add(jb2);
        jp1.add(jb3);

        jp2.add(jl2);
        jp2.add(jtf);
        jp2.add(jb4);
        jp2.add(jl3);
        jp2.add(jpf);
        jp2.add(jl4);
        jp2.add(jcb1);
        jp2.add(jcb2);
        jp2.add(jl5);

        x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250;
        y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150;

        this.setLocation(x,y);
        this.add(jp1,BorderLayout.SOUTH);
        this.add(jl1,BorderLayout.NORTH);
        this.add(jtp,BorderLayout.CENTER);

        this.setTitle("QQ 登陆");
        this.setSize(350,250);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb1){
            ClientAuth ca = new ClientAuth();
            User user = new User();
            user.setUsername(jtf.getText().trim());
            user.setPassword(new String(jpf.getPassword()));
            if(ca.checkUser(user)){
                try {
                    QQFriendList qqFriendList = new QQFriendList(user.getUsername());
                    ManageFriendList.addQQFriendList(user.getUsername(),qqFriendList);
                    //发送一个要求返回在线好友的请求包
                    ObjectOutputStream oos = new ObjectOutputStream
                            (ManageClientThread.getManageClientThread(user.getUsername()).getSt().getOutputStream());
                    Message message = new Message();
                    message.setType(MessageType.message_get_onlinelist);
                    //指明要的是zhegeQQ号的好友
                    message.setSender(user.getUsername());
                    oos.writeObject(message);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"Sorry, wrong password or username..");
            }
        }
    }
}
