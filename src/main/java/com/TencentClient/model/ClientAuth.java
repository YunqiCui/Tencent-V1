package com.TencentClient.model;

import com.TencentCommon.User;


public class ClientAuth {
    public boolean checkUser(User user){
        return new ClientConServer().sendAuth(user);
    }
}
