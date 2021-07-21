/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fit.cis.simplejdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rhianresnick
 */
public class Main {

    // From https://www.codejava.net/java-se/jdbc/connect-to-apache-derby-java-db-via-jdbc
    public static void main(String args[]) throws SQLException {
        String dbURL = "jdbc:derby:codejava/webdb;create=true";

        DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());

        Connection conn = DriverManager.getConnection(dbURL);

        Statement stmt = conn.createStatement();

        // Do not use this in your projects this is for demo purposes
        // drop the table so we don't get an error 
        String dropSql = "drop table REGISTRATION";

        // Do not use this in your projects this is for demo purposes
        // create a table named registration
        String createSql = "CREATE TABLE REGISTRATION "
                + "(id INTEGER not NULL, "
                + " fname VARCHAR(255), "
                + " lname VARCHAR(255), "
                + " age INTEGER, "
                + " PRIMARY KEY ( id ))";

        // Drop the table, don't exit if this fails
        try {
            System.out.println(dropSql);
            stmt.executeUpdate(dropSql);
        } catch (Exception ex) {
            ex.getMessage();
        }

        // Create the table, do exit if this fails
        System.out.println(createSql);
        stmt.executeUpdate(createSql);
            
        // Inserts data into the table
        String insert1 = "insert into REGISTRATION VALUES( 1, 'RHIAN', 'RESNICK', 41 )";
        String insert2 = "insert into REGISTRATION VALUES( 2, 'TAMALYN', 'RESNICK', 38 )";
        String insert3 = "insert into REGISTRATION VALUES( 3, 'KAELYN', 'RESNICK', 13 )";
        String insert4 = "insert into REGISTRATION VALUES( 4, 'MACKENZIE', 'RESNICK', 10 )";

        System.out.println(insert1);
        stmt.executeUpdate(insert1);

        System.out.println(insert2);
        stmt.executeUpdate(insert2);

        System.out.println(insert3);
        stmt.executeUpdate(insert3);

        System.out.println(insert4);
        stmt.executeUpdate(insert4);

        
        // Select all the rows from the table
        String selectSql = "SELECT * FROM REGISTRATION";

        // execute the select statement
        stmt.execute(selectSql);

        // Get the result set
        ResultSet rs = stmt.getResultSet();

        // Loop over each item in the result set 
        // printing each rows data
        while (rs.next()) {

            System.out.print(rs.getString("fname"));
            System.out.print(" ");
            System.out.print(rs.getString("lname"));
            System.out.print(" ");
            System.out.println(rs.getInt("age"));

        }
    }

}
