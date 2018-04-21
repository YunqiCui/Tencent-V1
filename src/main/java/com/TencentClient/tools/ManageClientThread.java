package com.TencentClient.tools;

import com.TencentClient.tools.ClientConServerThread;

import java.util.HashMap;

public class ManageClientThread {
    public static HashMap hm = new HashMap<String,ClientConServerThread>();

    public static void addManageClientThread(String ownerId,ClientConServerThread clientConServerThread){
        hm.put(ownerId,clientConServerThread);
    }
    public static ClientConServerThread  getManageClientThread(String ownerId){
        return (ClientConServerThread) hm.get(ownerId);
    }
}
