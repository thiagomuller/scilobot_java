package com.scilonax.scilobot.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBhandler {

    @Autowired
    DataSource dataSource;

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
                return false;
            }
            stmt.executeUpdate("INSERT INTO urls(url) VALUES('" + url + "');");
        }catch(SQLException sqlexception){
            sqlexception.printStackTrace();
        }
        return true;
    }
}