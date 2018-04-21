package com.TencentClient.tools;
//客户端与服务器保持通讯
import com.TencentClient.view.QQClientChat;
import com.TencentCommon.Message;
import com.TencentCommon.MessageType;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConServerThread implements Runnable{
    private Socket st;

    public ClientConServerThread(Socket socket){
        this.st = socket;
    }
    public void run() {
        while(true){
            try{
                ObjectInputStream ois = new ObjectInputStream(st.getInputStream());
                Message message = (Message) ois.readObject();
                //正常聊天
                if(message.getType().equals(MessageType.message_comm)){
                    QQClientChat qqcc = ManageClientChat.getQQClientChat(message.getReceiver()+ " " +message.getSender());
                    qqcc.showMessage(message);
                }else if(message.getType().equals(MessageType.message_ret_onlinelist)){
                    String con = message.getContent();
                    String friends[] = con.split(" ");
                    String receiver = message.getReceiver();
                }

            }catch (Exception e){

            }
        }

    }

    public Socket getSt() {
        return st;
    }
}
