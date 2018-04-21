package com.TencentClient.tools;

import com.TencentClient.view.QQFriendList;

import java.util.HashMap;

public class ManageFriendList {
    private static HashMap hm = new HashMap<String,QQFriendList>();

    public static void addQQFriendList(String ownerId, QQFriendList qqFriendList){
        hm.put(ownerId,qqFriendList);
    }

    public static QQFriendList getQQFriendList(String ownerId){
        return (QQFriendList) hm.get(ownerId);
    }

}
