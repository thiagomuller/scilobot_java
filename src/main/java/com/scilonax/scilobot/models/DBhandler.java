package com.scilonax.scilobot.models;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBhandler {

    @Autowired
    BasicDataSource dataSource;

    public Boolean handleUrlsonDB(String url){
        try{
            Statement stmt = dataSource.getConnection().createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS urls(url TEXT);");
            List<String> retrievedUrls = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM urls;");
            while(rs.next()){
                retrievedUrls.add(rs.getString("url"));
            }
            if(retrievedUrls.contains(url)){
                dataSource.close();
                return false;
            }
            stmt.executeUpdate("INSERT INTO urls(url) VALUES('" + url + "');");
            dataSource.close();
        }catch(SQLException sqlexception){
            sqlexception.printStackTrace();
        }
        return true;
    }
}
