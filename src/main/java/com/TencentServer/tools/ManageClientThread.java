package com.TencentServer.tools;

import java.util.HashMap;
import java.util.Iterator;

public class ManageClientThread{

    public static HashMap hm = new HashMap<String,ServerConClientThread>();

    public static void addClientThread(String ownerId, ServerConClientThread scct){
        hm.put(ownerId,scct);
    }

    public static ServerConClientThread getServerConClientThread(String ownerId){
        return (ServerConClientThread) hm.get(ownerId);
    }

    public static String getAllOnlineUser(){
        Iterator it = hm.keySet().iterator();
        String res = "";
        while(it.hasNext()){
            res += it.next().toString()+ " ";
        }
        return res;
    }
}
