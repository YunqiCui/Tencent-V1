package com.TencentServer.tools;

import java.util.HashMap;

public class ManageClientThread implements Runnable{

    public static HashMap hm = new HashMap<String,ServerConClientThread>();

    public static void addClientThread(String ownerId, ServerConClientThread scct){
        hm.put(ownerId,scct);
    }

    public static ServerConClientThread getServerConClientThread(String ownerId){
        return (ServerConClientThread) hm.get(ownerId);
    }

    public void run() {

    }
}
