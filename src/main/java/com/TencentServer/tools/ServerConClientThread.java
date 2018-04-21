package com.TencentServer.tools;

import com.TencentCommon.Message;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConClientThread extends Thread {

    Socket s;
    ObjectInputStream ois;

    public ServerConClientThread(Socket s) {
        this.s = s;
    }

    public void run() {
        while (true) {
            try {
                this.ois = new ObjectInputStream(s.getInputStream());
                Message m = (Message) ois.readObject();
                System.out.println(m.getSender() + " 对 " + m.getReceiver() + " 说 " + m.getContent());
                ServerConClientThread scct =  ManageClientThread.getServerConClientThread(m.getReceiver());
                ObjectOutputStream oos = new ObjectOutputStream(scct.s.getOutputStream());
                oos.writeObject(m);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
