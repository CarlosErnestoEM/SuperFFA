package com.pollogamer.superffa.stats.mysql;

import com.pollogamer.superffa.Principal;

import java.sql.*;

public class MySQLManager{

    private Principal plugin;
    private String host,database,user,pass;
    public Connection connection;
    private int port;

    public MySQLManager(Principal plugin){
        this.plugin = plugin;
        host = plugin.getConfig().getString("MySQL.host");
        port = plugin.getConfig().getInt("MySQL.port");
        database = plugin.getConfig().getString("MySQL.database");
        user = plugin.getConfig().getString("MySQL.username");
        pass = plugin.getConfig().getString("MySQL.password");

    }

    public void openConnection() throws ClassNotFoundException, SQLException{
        if(connection != null && !connection.isClosed()){
            return;
        }
        synchronized (this){
            if(connection != null && !connection.isClosed()){
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.port+"/"+this.database,this.user,pass);
        }

    }

    public ResultSet Query(String qry){
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(qry);
        } catch (SQLException e) {
            try {
                openConnection();
            } catch (ClassNotFoundException e1) {
                plugin.getLogger().info("ERROR! Please report");
            } catch (SQLException e1) {
                plugin.getLogger().info("Please check your config.yml");
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return rs;
    }

    public void Update(String qry){
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(qry);
            stmt.close();
        }catch (Exception e){
            try {
                openConnection();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

}
