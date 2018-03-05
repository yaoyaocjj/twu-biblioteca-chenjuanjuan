package com.twu.biblioteca.utils;

import com.twu.biblioteca.beans.User;
import com.twu.biblioteca.beans.Role;

import java.io.*;


public class UserFileUtil {

    private boolean write(String filepath, String text) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filepath, true));
            bw.write(text);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }


    private String query(String filepath, String content) {
        String line;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            try {
                while ((line = br.readLine()) != null)
                    if (line.indexOf(content) == 0)
                        return line.substring(content.length() + 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public Role getUser(User user) {
        String text = query(Constant.USERS_FILE, user.getUsername() + " " + user.getPassword());
        String[] strs = text.split(" ");

        if (strs.length != 3)
            return Role.NONE;

        user.setRole(Role.toRole(strs[0]));
        user.setEmail(strs[1]);
        user.setTel(strs[2]);
        return user.getRole();
    }

    public boolean insertUser(User user) {
        return write(Constant.USERS_FILE, user.getUsername() +
                " " + user.getPassword() + " " + user.getRole().toString()
                + " " + user.getEmail() + " " + user.getTel()
        );
    }
}
