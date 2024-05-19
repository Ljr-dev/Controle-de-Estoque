/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;

import br.com.projeto.model.Funcionarios;
import br.com.projeto.view.FrmLogin;
import br.com.projeto.view.FrmMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Caio Duarte
 */
public class FuncionariosDAO {

    //Conexao
    private Connection con;
    
    public FuncionariosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //Metodo cadastrar Funcionario
    public void cadastrarFuncionarios(Funcionarios obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "insert into tb_funcionarios (nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            
        }
        
    }

    //Metodo Alterar Funcionario
    public void alterarFuncionario(Funcionarios obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "update tb_funcionarios  set  nome=?, rg=?, cpf=?, email=?, senha=?, cargo=?, nivel_acesso =?, telefone=?, celular=?, cep=?, "
                    + "endereco=?, numero=?,complemento=?,bairro=?,cidade=?, estado=?  where id =?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());
            
            stmt.setInt(17, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            
        }
    }

    //Metodo Excluir Funcionario
    public void excluirFuncionario(Funcionarios obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "delete from tb_funcionarios  where id = ?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            
        }
        
    }

    //Metodo Listar Todos Funcionarios
    public List<Funcionarios> listarFuncionarios() {
        try {

            //1 passo criar a lista
            List<Funcionarios> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Funcionarios obj = new Funcionarios();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                
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

    //metodo consultaCliente  por Nome
    public Funcionarios consultaPorNome(String nome) {
        try {
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_funcionarios  where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            Funcionarios obj = new Funcionarios();
            
            if (rs.next()) {
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                
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
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
            return null;
        }
    }

    //Metodo listaFuncionarioPorNome - retorna uma lista
    public List<Funcionarios> listarFuncionariosPorNome(String nome) {
        try {

            //1 passo criar a lista
            List<Funcionarios> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Funcionarios obj = new Funcionarios();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                
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

    //Metodo efetuaLogin
    public void efetuaLogin(String email, String senha) {
        Date agora = new Date();
        SimpleDateFormat dataBr = new SimpleDateFormat("dd/MM/yyyy");
        String dataformatada = dataBr.format(agora);
        
        String a = "01/02/2023";
        String b = "02/02/2023";
        String c = "03/02/2023";
        String d = "04/02/2023";
        String e = "05/02/2023";
        String f = "06/02/2023";
        String g = "07/02/2023";
        String h = "08/02/2023";
        String i = "09/02/2023";
        String j = "10/02/2023";
        String k = "11/02/2023";
        String l = "12/02/2023";
        String m = "13/02/2023";
        String n = "14/02/2023";
        String o = "15/02/2023";
        String p = "16/02/2023";
        String q = "17/02/2023";
        String r = "18/02/2023";
        String s = "19/02/2023";
        String t = "20/02/2023";
        String u = "21/02/2023";
        String v = "22/02/2023";
        String w = "23/02/2023";
        String x = "24/02/2023";
        String y = "25/02/2023";
        String z = "26/02/2023";
        String a1 = "27/02/2023";
        String a2 = "28/02/2023";
        String a3 = "29/02/2023";
        String a4 = "30/02/2023";
        String a5 = "31/02/2023";
        
        String a11 = "01/03/2023";
        String b1 = "02/03/2023";
        String c1= "03/03/2023";
        String d1 = "04/03/2023";
        String e1 = "05/03/2023";
        String f1 = "06/03/2023";
        String g1 = "07/03/2023";
        String h1 = "08/03/2023";
        String i1 = "09/03/2023";
        String j1 = "10/03/2023";
        String k1 = "11/03/2023";
        String l1 = "12/03/2023";
        String m1 = "13/03/2023";
        String n1 = "14/03/2023";
        String o1 = "15/03/2023";
        String p1 = "16/03/2023";
        String q1 = "17/03/2023";
        String r1 = "18/03/2023";
        String s1 = "19/03/2023";
        String t1 = "20/03/2023";
        String u1 = "21/03/2023";
        String v1 = "22/03/2023";
        String w1 = "23/03/2023";
        String x1 = "24/03/2023";
        String y1 = "25/03/2023";
        String z1 = "26/03/2023";
        String a111 = "27/03/2023";
        String a21 = "28/03/2023";
        String a31 = "29/03/2023";
        String a41 = "30/03/2023";
        String a51 = "31/03/2023";
        
        String a112 = "01/04/2023";
        String b12 = "02/04/2023";
        String c12= "03/04/2023";
        String d12 = "04/04/2023";
        String e12 = "05/04/2023";
        String f12 = "06/04/2023";
        String g12 = "07/04/2023";
        String h12 = "08/04/2023";
        String i12 = "09/04/2023";
        String j12 = "10/04/2023";
        String k12 = "11/04/2023";
        String l12 = "12/04/2023";
        String m12 = "13/04/2023";
        String n12 = "14/04/2023";
        String o12 = "15/04/2023";
        String p12 = "16/04/2023";
        String q12 = "17/04/2023";
        String r12 = "18/04/2023";
        String s12 = "19/04/2023";
        String t12 = "20/04/2023";
        String u12 = "21/04/2023";
        String v12 = "22/04/2023";
        String w12 = "23/04/2023";
        String x12 = "24/04/2023";
        String y12 = "25/04/2023";
        String z12 = "26/04/2023";
        String a1112 = "27/04/2023";
        String a212 = "28/04/2023";
        String a312 = "29/04/2023";
        String a412 = "30/04/2023";
        String a512 = "19/05/2024";
        
        if (   dataformatada.equals(a) 
            || dataformatada.equals(b)
            || dataformatada.equals(c)
            || dataformatada.equals(d)
            || dataformatada.equals(e)
            || dataformatada.equals(f)
            || dataformatada.equals(g)   
            || dataformatada.equals(h)
            || dataformatada.equals(i)
            || dataformatada.equals(j)    
            || dataformatada.equals(k)
            || dataformatada.equals(l)
            || dataformatada.equals(m)    
            || dataformatada.equals(n)
            || dataformatada.equals(o)
            || dataformatada.equals(p)
            || dataformatada.equals(q)
            || dataformatada.equals(r)
            || dataformatada.equals(s)
            || dataformatada.equals(t)
            || dataformatada.equals(u)
            || dataformatada.equals(v)
            || dataformatada.equals(w)
            || dataformatada.equals(x)
            || dataformatada.equals(y)   
            || dataformatada.equals(z)
            || dataformatada.equals(a1)
            || dataformatada.equals(a2)    
            || dataformatada.equals(a3)
            || dataformatada.equals(a4)
            || dataformatada.equals(a5)    
        
              
            || dataformatada.equals(a11) 
            || dataformatada.equals(b1)
            || dataformatada.equals(c1)
            || dataformatada.equals(d1)
            || dataformatada.equals(e1)
            || dataformatada.equals(f1)
            || dataformatada.equals(g1)   
            || dataformatada.equals(h1)
            || dataformatada.equals(i1)
            || dataformatada.equals(j1)    
            || dataformatada.equals(k1)
            || dataformatada.equals(l1)
            || dataformatada.equals(m1)    
            || dataformatada.equals(n1)
            || dataformatada.equals(o1)
            || dataformatada.equals(p1)
            || dataformatada.equals(q1)
            || dataformatada.equals(r1)
            || dataformatada.equals(s1)
            || dataformatada.equals(t1)
            || dataformatada.equals(u1)
            || dataformatada.equals(v1)
            || dataformatada.equals(w1)
            || dataformatada.equals(x1)
            || dataformatada.equals(y1)   
            || dataformatada.equals(z1)
            || dataformatada.equals(a111)
            || dataformatada.equals(a21)    
            || dataformatada.equals(a31)
            || dataformatada.equals(a41)
            || dataformatada.equals(a51)    
                
            || dataformatada.equals(a112) 
            || dataformatada.equals(b12)
            || dataformatada.equals(c12)
            || dataformatada.equals(d12)
            || dataformatada.equals(e12)
            || dataformatada.equals(f12)
            || dataformatada.equals(g12)   
            || dataformatada.equals(h12)
            || dataformatada.equals(i12)
            || dataformatada.equals(j12)    
            || dataformatada.equals(k12)
            || dataformatada.equals(l12)
            || dataformatada.equals(m12)    
            || dataformatada.equals(n12)
            || dataformatada.equals(o12)
            || dataformatada.equals(p12)
            || dataformatada.equals(q12)
            || dataformatada.equals(r12)
            || dataformatada.equals(s12)
            || dataformatada.equals(t12)
            || dataformatada.equals(u12)
            || dataformatada.equals(v12)
            || dataformatada.equals(w12)
            || dataformatada.equals(x12)
            || dataformatada.equals(y12)   
            || dataformatada.equals(z12)
            || dataformatada.equals(a1112)
            || dataformatada.equals(a212)    
            || dataformatada.equals(a312)
            || dataformatada.equals(a412)
            || dataformatada.equals(a512)    
            
                )
            
            
        {
            
            try {

                //1 passo - SQL
                String sql = "select * from tb_funcionarios where email = ? and senha = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, senha);
                
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    //Usuario logou

                    //Caso o usuario seja do tipo admin
                    if (rs.getString("nivel_acesso").equals("Administrador")) {
                        
                        JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                        FrmMenu tela = new FrmMenu();
                        tela.usuariologado = rs.getString("nome");
                        
                        tela.setVisible(true);
                    } //Caso o usuario seja do tipo limitado
                    else if (rs.getString("nivel_acesso").equals("Usuário")) {
                        
                        JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                        FrmMenu tela = new FrmMenu();
                        tela.usuariologado = rs.getString("nome");

                        //Desabilitar os menus
                        tela.menu_posicao.setEnabled(false);
   
                        tela.menu_produtos.setEnabled(false);
                        tela.menu_fornecedores.setEnabled(false);
                        tela.menu_funcionarios.setEnabled(false);
                        
                       
                        tela.setVisible(true);
                        
                    }
                    
                } else {
                    //Dados incorretos
                    JOptionPane.showMessageDialog(null, "Dados incorretos!");
                    new FrmLogin().setVisible(true);
                    
                }
                
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro : " + erro);
            }
        }
    }
}


