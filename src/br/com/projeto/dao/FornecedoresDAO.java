/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.WebServiceCep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author JAVA
 */
public class FornecedoresDAO {
    
    private  Connection con;
            
    public FornecedoresDAO(){
            
         this.con = new ConnectionFactory().getConnection();
    
    }
      // Busca CEP
 public Fornecedores buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Fornecedores obj = new Fornecedores();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setEstado(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
    // Metodo Cadastrar Fornecedores
    public void cadastrarFornecedores(Fornecedores obj){
        
        try{
            // 1 criar o comando sql
            String sql= "insert into tb_fornecedores(nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)" 
                                       + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
            try ( // 2 conectar o banco sql e organizar comando sql
                PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                
                stmt.setString(2, obj.getCnpj());
                
                stmt.setString(3, obj.getEmail());
                stmt.setString(4, obj.getTelefone());
                stmt.setString(5, obj.getCelular());
                stmt.setString(6, obj.getCep());
                stmt.setString(7, obj.getEndereco());
                stmt.setInt(8, obj.getNumero());
                stmt.setString(9, obj.getComplemento());
                stmt.setString(10, obj.getBairro());
                stmt.setString(11, obj.getCidade());
                stmt.setString(12, obj.getEstado());
                
                // 3 executar o comando sql
                
                stmt.execute();
                stmt.close();
            }
           
           JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
           
        }catch(SQLException erro){
           JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }
    
    // Metodo Alterar Fornecedor
    public void alterarFornecedor(Fornecedores obj){
        try{
            // 1 criar o comando sql
            String sql= "update tb_fornecedores set nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,"
                      + "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?"; 
                                      
            try ( // 2 conectar o banco sql e organizar comando sql
                   
                PreparedStatement stmt = con.prepareStatement(sql)) {
               
                
                stmt.setString(1, obj.getNome());
                
                stmt.setString(2, obj.getCnpj());
                
                stmt.setString(3, obj.getEmail());
                stmt.setString(4, obj.getTelefone());
                stmt.setString(5, obj.getCelular());
                stmt.setString(6, obj.getCep());
                stmt.setString(7, obj.getEndereco());
                stmt.setInt(8, obj.getNumero());
                stmt.setString(9, obj.getComplemento());
                stmt.setString(10, obj.getBairro());
                stmt.setString(11, obj.getCidade());
                stmt.setString(12, obj.getEstado());
                
                stmt.setInt(13, obj.getId()); 
                
                // 3 executar o comando sql
                
                stmt.execute();
                stmt.close();
            }
           
           JOptionPane.showMessageDialog(null, "Alterado com sucesso");
           
        }catch(SQLException erro){
           JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }
   
    // Metodo Excluir fornecedor
    public void excluirFornecedor(Fornecedores obj){
        try{
            // 1 criar o comando sql
            String sql = "delete from tb_fornecedores where id = ?";
            try ( // 2 conectar o banco sql e organizar comando sql
                    PreparedStatement stmt = con.prepareStatement(sql)){ 
                stmt.setInt(1, obj.getId());
                
                
                // 3 executar o comando sql
                
                stmt.execute();
                stmt.close();
            }
           JOptionPane.showMessageDialog(null, "Excluido com sucesso");
             
        }catch(SQLException erro){
           JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }
    
     //Metodo Listar Todos Fornecedores
    public List<Fornecedores> listarFornecedores() {
        try {

            //1 passo criar a lista
            List<Fornecedores> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                
                obj.setCnpj(rs.getString("cnpj"));
              
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
    
    //metodo consulta fornecedor por nome
    public Fornecedores consultaPorNome(String nome){
        try{
            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_fornecedores where nome=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            Fornecedores obj = new Fornecedores();
            
             if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                
                obj.setCnpj(rs.getString("cnpj"));
              
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
            }
            return obj;
        }catch (Exception erro){
            JOptionPane.showMessageDialog(null,"Fornecedor não encontrado");
            return null;
        }   
    }
         
    // metodo buscar cliente por Nome
     public List<Fornecedores> buscarFornecedorPorNome(String nome) {
        try {

            //1 passo criar a lista
            List<Fornecedores> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_fornecedores where nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
             ResultSet rs = stmt.executeQuery();
            Fornecedores obj = new Fornecedores();
            
             if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                
                obj.setCnpj(rs.getString("cnpj"));
              
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
     }
}


