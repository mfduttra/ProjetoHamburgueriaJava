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
public abstract class Prato {
    private String nome;
    private int id;
    private Receita r;

    
    public Prato() {
    }

    public Prato(String nome, int id, Receita r) {
        this.nome = nome;
        this.id = id;
        this.r = r;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Receita getR() {
        return r;
    }

    public void setR(Receita r) {
        this.r = r;
    }


    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void insert(){
    }
    
    
    /** Método para obter uma lista de receitas
    * @return lista do tipo Receita */
    
    
    public static List<Receita> getAll(){
        ArrayList<Receita> receita = new ArrayList<>();
        
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
        String sql = "SELECT * FROM receita ORDER BY nome";
        
        try{
            Statement st = con.createStatement();//Não é um prepared este é mais rápido
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Receita r = new Receita();
                r.setId(rs.getInt("id"));
                r.setNome(rs.getString("nome"));
                
        
                receita.add(r);//Adiciono na lista.
                
            }            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
            return null;
        }
        
        return receita; //Retorna a lista completa
    }
    
    
    /** Método para remover
    * @return um valor booleano se a remoção for realizada */
    

    public boolean remove() {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "DELETE FROM receita WHERE id = ?";
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
    
    
    /** Método para atualizar
    * @return um valor booleano se a atualização for realizada */
    

    public boolean update() {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "UPDATE POO1_Pratos SET nome = ?"   
                     + "WHERE ( id = ?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(2, this.id); //Observe o indice do ID
            
            ps.setString(1, this.nome);
            
            ps.executeUpdate();           
            
            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
            return false;
        }
    
        return true;
    }
    
    
    /** Método para imprimir String
    * @return uma String */
    
    
    public String toString(){
        return this.nome;
    }
}
