/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fit.cis.simplejdbcdemo;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rhianresnick
 */
public class MainFancy {

    // From https://www.codejava.net/java-se/jdbc/connect-to-apache-derby-java-db-via-jdbc
    public static void main(String args[]) throws SQLException {
        
        // You need to update this string to put your database in a persistent location like My Documents or c:\\ or /Users/username/dbname
        // change "codejava/webdb" to "c:\\db_demo\\" or "/Users/rhianresnick/db_demo" for example
        String dbURL = "jdbc:derby:codejava/webdb;create=true";

        DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());

        Connection conn = DriverManager.getConnection(dbURL);

        Statement stmt = conn.createStatement();
              
        String dropRegistrationSql = "drop table REGISTRATION";

        // Do not use this in your projects this is for demo purposes
        // create a table named registration
        String createRegistrationSql = "CREATE TABLE REGISTRATION "
                + "(id INTEGER not NULL, "
                + " fname VARCHAR(255), "
                + " lname VARCHAR(255), "
                + " age INTEGER, "
                + " PRIMARY KEY ( id ))";
        
                      
        String dropVendorSql = "drop table VENDOR";

        // Do not use this in your projects this is for demo purposes
        // create a table named registration
        String createVendorSql = "CREATE TABLE VENDOR "
                + "(id INTEGER not NULL, "
                + " fname VARCHAR(255), "
                + " lname VARCHAR(255), "
                + " age INTEGER, "
                + " PRIMARY KEY ( id ))";
        
        // Create an array to store our statements
        List<String> statements = new ArrayList();
        
        // Create statements for tables
        statements.add( dropRegistrationSql ); 
        statements.add( createRegistrationSql );        
        statements.add( dropVendorSql ); 
        statements.add( createVendorSql );
        
        // Registration inserts
        statements.add( "insert into REGISTRATION VALUES( 1, 'RHIAN', 'RESNICK', 41 )");
        statements.add( "insert into REGISTRATION VALUES( 1, 'RHIAN', 'RESNICK', 41 )");
        statements.add( "insert into REGISTRATION VALUES( 2, 'TAMALYN', 'RESNICK', 38 )");
        statements.add( "insert into REGISTRATION VALUES( 3, 'KAELYN', 'RESNICK', 13 )");
        statements.add( "insert into REGISTRATION VALUES( 4, 'MACKENZIE', 'RESNICK', 10 )");
        // Vendor Inserts
        statements.add( "insert into VENDOR VALUES( 1, 'RHIAN', 'RESNICK', 41 )");
        statements.add( "insert into VENDOR VALUES( 1, 'RHIAN', 'RESNICK', 41 )");
        statements.add( "insert into VENDOR VALUES( 2, 'TAMALYN', 'RESNICK', 38 )");
        statements.add( "insert into VENDOR VALUES( 3, 'KAELYN', 'RESNICK', 13 )");
        statements.add( "insert into VENDOR VALUES( 4, 'MACKENZIE', 'RESNICK', 10 )");
        
        
        // Execute each statement in order
        for( String statment : statements ) {
            try {
                // execute the statement
                stmt.executeUpdate(statment);
            } catch( Exception ex ) {
                System.err.println( ex.getMessage() );
            }
        }
        

        
        
        
        
        
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
