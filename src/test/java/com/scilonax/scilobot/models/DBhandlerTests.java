package com.scilonax.scilobot.models;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBhandlerTests {

    @Autowired
    DBhandler dBhandler;

    //This is for local test only, it won't work on heroku!!
    @BeforeClass
    public static void clearTableonDB(){
        try{
            String username = System.getenv("username");
            String password = System.getenv("password");

            String dbUrl = System.getenv("dbUrl");

            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl(dbUrl);
            basicDataSource.setUsername(username);
            basicDataSource.setPassword(password);
            Statement stmt = basicDataSource.getConnection().createStatement();
            stmt.executeUpdate("DROP TABLE urls;");
            basicDataSource.close();
        } catch(SQLException sql){
            System.out.println("Unable to delete the table, got an SQL exception, stacktrace below");
            sql.printStackTrace();
        }
    }

    @Test
    public void dbHandlerGetsCalledWithNoURL(){
        Assert.assertFalse(dBhandler.handleUrlOnDB("null"));
    }

    @Test
    public void dbHandlerGetsCalledWithAUrl(){
        Assert.assertTrue(dBhandler.handleUrlOnDB("http://news.mit.edu/2019/mit-postdocs-find-common-ground-at-homecoming-event-0401"));
    }

    @Test
    public void dbHandlerGetsCalledWithAAlreadyUsedUrl(){
        Assert.assertFalse(dBhandler.handleUrlOnDB("http://news.mit.edu/2019/mit-postdocs-find-common-ground-at-homecoming-event-0401"));
    }
}
