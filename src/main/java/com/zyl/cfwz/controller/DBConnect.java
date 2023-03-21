package com.zyl.cfwz.controller;

import java.sql.*;

public class DBConnect {
    //你的数据库名字
    public static final String dbname = "root";

    //你的数据库密码
    public static final String dbpassword = "wakk1130!";

    //地址连接
    public static final String dburl = "jdbc:mysql:///mvc?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8";

    //设置连接
    public static Connection getCon(){
        try{
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            return DriverManager.getConnection(dburl,dbname,dbpassword);
        }
        catch (SQLException | ClassNotFoundException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    /**
     * statement是根据连接来获取可以操作数据库的一个对象，他用来操作数据库
     */
    public static Statement getStatement(Connection connection){
        try {
            return connection.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //获取结果集
    public static ResultSet getResultSet(Statement statement,String sql){
        try{
            return statement.executeQuery(sql);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void insertData(Connection connection, String sql) {
        try {
            //创建Statement对象
            Statement statement = connection.createStatement();
            //执行SQL INSERT命令
            int rows = statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
