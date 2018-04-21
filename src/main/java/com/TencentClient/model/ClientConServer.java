package com.TencentClient.model;

import com.TencentClient.tools.ClientConServerThread;
import com.TencentClient.tools.ManageClientThread;
import com.TencentCommon.Message;
import com.TencentCommon.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConServer {
    public Socket st;
    String host = "Localhost";
    ObjectOutputStream oos;
    ObjectInputStream ois;
    int port = 9999;

//    public ClientConServer() {
//        try {
//            ois = new ObjectInputStream(st.getInputStream());
//            oos = new ObjectOutputStream(st.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public boolean sendAuth(Object o) {
        boolean b = false;

        try {
            st = new Socket(host,port);
            oos = new ObjectOutputStream(st.getOutputStream());
            oos.writeObject(o);
            ois = new ObjectInputStream(st.getInputStream());
            Message ms = (Message) ois.readObject();
            if (ms.getType().equals("1")) {
                ClientConServerThread ccst = new ClientConServerThread(st);
                Thread t = new Thread(ccst);
                t.start();
                ManageClientThread.addManageClientThread(((User) o).getUsername(),ccst);
                b = true;
            } else {
                b = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

//    public void close() {
//        try {
//            if (oos != null) {
//                oos.close();
//            }
//            if (st != null) {
//                st.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
