package com.TencentServer.model;

import com.TencentCommon.Message;
import com.TencentCommon.User;
import com.TencentServer.tools.ManageClientThread;
import com.TencentServer.tools.ServerConClientThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TencentServer {

    ServerSocket ss;
    Socket st;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    ServerConClientThread scct;

    int port = 9999;

    public static void main(String[] args) {
        TencentServer ts = new TencentServer();
    }

    public TencentServer() {
        try{
            ss = new ServerSocket(port);
            System.out.println("Listening at " + port);

            while(true) {
                st = ss.accept();
                ois = new ObjectInputStream(st.getInputStream());
                User user = (User)ois.readObject();
                Message message = new Message();
                oos = new ObjectOutputStream(st.getOutputStream());
                if(user.getPassword().equals("123456")){
                    message.setType("1");
                    oos.writeObject(message);
                scct = new ServerConClientThread(st);
                ManageClientThread.addClientThread(user.getUsername(),scct);
                //启动与客户端通信线程
                scct.start();
                //通知其他在线用户
                scct.notifyOther(user.getUsername());
                }else{
                    message.setType("2");
                    oos.writeObject(message);
                    st.close();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void close() {
        try{
        System.out.println("Server Closed.");
        if (ois != null) {
            ois.close();
        }
        if(st != null){
            st.close();
        }
        if(ss != null){
            ss.close();
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
