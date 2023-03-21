package com.zyl.cfwz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;

@Controller
public class MyController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping ("/register")
    public String register(User user) throws SQLException {
       return "register";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping ("/dologin")
    public String dologin(User user) {
        Connection connection=DBConnect.getCon();
        Statement statement=DBConnect.getStatement(connection);
        String sql="select * from user where name='"+user.getName()+"' and password='"+user.getPassword()+"'";

        System.out.println(sql);

        ResultSet rs=DBConnect.getResultSet(statement,sql);

        if(rs!=null){
            try {
                if(rs.next()){
                    System.out.println("数据库里面有结果!");
                    String username=rs.getString(1);
                    String password=rs.getString(2);
                    System.out.println(username);
                    System.out.println(password);
                    return "index";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return "error";
            }
        }
        return "error";
    }
    @PostMapping("/doregister")
    public String doregister(User user) throws SQLException {
        Connection connection=DBConnect.getCon();
        Statement statement=DBConnect.getStatement(connection);
        String sql="select * from user where name='"+user.getName()+"'";
        ResultSet rs=DBConnect.getResultSet(statement,sql);
        if(rs.next()){
            return "register";
        }else{
            sql="insert into user(name,password) values('"+user.getName()+"','"+user.getPassword()+"')";
            int count=statement.executeUpdate(sql);
            if(count==0){
                return "error";
            }else{
                return "index";
            }
        }
    }
}