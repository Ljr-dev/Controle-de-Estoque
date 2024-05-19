/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author JAVA
 */
public class ConnectionFactory {

    public Connection getConnection;
    
    public Connection getConnection(){
        
     try{
         
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bd_estoque?serverTimezone=UTC",
                    "admin1",
                    "123"
            );
         
    
     }catch(Exception erro){
     throw new RuntimeException(erro);
     
     }
 }
}
