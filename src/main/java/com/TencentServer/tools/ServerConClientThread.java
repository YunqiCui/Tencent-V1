package com.TencentServer.tools;

import com.TencentCommon.Message;
import com.TencentCommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class ServerConClientThread extends Thread {

    Socket s;
    ObjectInputStream ois;

    public ServerConClientThread(Socket s) {
        this.s = s;
    }

    public void notifyOther(String myself){
        HashMap hm = ManageClientThread.hm;
        Iterator it = hm.keySet().iterator();

        while(it.hasNext()){
            Message message = new Message();
            message.setContent(myself);
            message.setType(MessageType.message_ret_onlinelist);
            String onLineUserId = it.next().toString();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(
                        ManageClientThread.getServerConClientThread(onLineUserId).s.getOutputStream());
                message.setReceiver(onLineUserId);
                oos.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        while (true) {
            try {
                this.ois = new ObjectInputStream(s.getInputStream());
                Message m = (Message) ois.readObject();
                System.out.println(m.getSender() + " 对 " + m.getReceiver() + " 说 " + m.getContent());
                if(m.getType().equals(MessageType.message_comm)){
                    ServerConClientThread scct =  ManageClientThread.getServerConClientThread(m.getReceiver());
                    ObjectOutputStream oos = new ObjectOutputStream(scct.s.getOutputStream());
                    oos.writeObject(m);
                }else if(m.getType().equals(MessageType.message_get_onlinelist)){
                    String res = ManageClientThread.getAllOnlineUser();
                    Message message = new Message();
                    message.setType(MessageType.message_ret_onlinelist);
                    message.setContent(res);
                    message.setReceiver(m.getSender());
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
