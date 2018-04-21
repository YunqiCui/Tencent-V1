package com.TencentClient.tools;

import com.TencentClient.view.QQClientChat;

import java.util.HashMap;

public class ManageClientChat {
    private static HashMap hm = new HashMap<String, QQClientChat>();

    public static void addQQChat(String ownerId, QQClientChat qqcc){
        hm.put(ownerId,qqcc);
    }

    public static QQClientChat getQQClientChat(String ownerId){
        return (QQClientChat) hm.get(ownerId);
    }
}
