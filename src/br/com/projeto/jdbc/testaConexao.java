/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;
import javax.swing.JOptionPane;
/**
 *
 * @author JAVA
 */
public class testaConexao {
    public static void main(String[] args){
    
            try{
                
                new ConnectionFactory().getConnection();
                JOptionPane.showMessageDialog(null,"Conectado com Sucesso!");
                
    }catch (Exception erro){
         JOptionPane.showMessageDialog(null,"Ops aconteceu um erro:"+ erro );
    
    }
   }
}
