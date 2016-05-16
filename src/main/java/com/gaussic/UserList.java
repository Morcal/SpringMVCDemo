package com.gaussic;

import java.util.List;

/**
 * Created by lyqdhgo on 2016/5/16.
 */
public class UserList {
    private List<User> users;

    public List<User> getList() {
        return users;
    }

    public void setList(List<User> list) {
        this.users = list;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "list=" + users +
                '}';
    }
}
