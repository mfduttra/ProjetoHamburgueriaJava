/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import hamburgueria.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mellanie F. Dutra
 * @version 1.0.0
 */
public class Ingrediente implements Estocavel{
    public int id;
    public String nome;
    public int quantidade;
    

    public Ingrediente() {
    }

    public Ingrediente(int id, String nome, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    /** Método para inserir
     * @return Boolean*/

    
    public boolean insert(){
        Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "INSERT INTO POO1_INGREDIENTES(id, nome, quantidade) "
                //         1 2 3 
                + "VALUES (INGREDIENTES_SEQ.nextval,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, this.nome);
            ps.setInt(2, this.quantidade);
            //JDBC se vira basta criar um tipo int ou char
            
            ps.executeUpdate();           
           
            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
            return false;
        }
    
        return true;
    }
    
    /** Método para obter uma lista de ingredientes
    * @return List  */
    
    public static List<Ingrediente> getAll(){
        ArrayList<Ingrediente> ingrs = new ArrayList<>();
        
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
        String sql = "SELECT * FROM POO1_INGREDIENTES ORDER BY nome";
        
        try{
            Statement st = con.createStatement();//Não é um prepared este é mais rápido
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Ingrediente i = new Ingrediente();
                i.setId(rs.getInt("id")); //do banco
                i.setNome(rs.getString("nome")); //do banco
                i.setQuantidade(rs.getInt("quantidade")); //do banco
        
                ingrs.add(i);//Adiciono na lista.
                
            }            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
            return null;
        }
        
        return ingrs; //Retorna a lista completa
    }
    
    /** Método para atualizar
    * @return boolean*/
    
     public boolean update() {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "UPDATE POO1_INGREDIENTES SET nome= ?, quantidade = ?"   
                     + "WHERE ( id = ?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(3, this.id); //Observe o indice do ID
            
            ps.setString(1, this.nome);
            ps.setInt(2, this.quantidade);
            
            ps.executeUpdate();           
            
            
        }catch(SQLException e){
            
            System.out.println("Tente Novamente");
            e.printStackTrace();
            return false;
        }
    
        return true;
    }
     
    /** Método para remover
    * @return boolean */
     
    public boolean remove() {
        Conexao c = new Conexao();
            Connection con = c.getConexao();

            String sql =  "DELETE FROM POO1_INGREDIENTES WHERE id = ?";
            try{
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, this.id);//Apenas 1 elemento

                ps.executeUpdate();

            }catch(SQLException e){

                System.out.println("Tente novamente");
                e.printStackTrace();
                return false;
            }

            return true;
    }
    
    /** Método para imprimir String
    * @return String */
    
    public String toString(){
        return this.nome;
    }
     
    
}
