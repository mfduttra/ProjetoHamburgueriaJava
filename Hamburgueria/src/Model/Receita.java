/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import hamburgueria.Conexao;
import hamburgueria.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mellanie F. Dutra
 * @version 1.0.0
 */
public class Receita extends PratoCozinhavel{
    private List<Ingrediente> receitas = new ArrayList<>();
    private String nome;
    private int id;

    public Receita(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }
    

    public Receita() {
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
 
    
    public List<Ingrediente> getIngredientes() {
        return receitas;
    }

    public void setIngredientes(List<Ingrediente> receitas) {
        this.receitas = receitas;
    }

    
    public void adicionarIngrediente(Ingrediente i){
        this.receitas.add(i);
    
    }
   
    public Receita preparar(Estoque estoque){
        return null;
    
    }
    
     
    /** Método para inserir
     * @param ident int - identificacao da receita*/
    
    
    public void insert(int ident){
    Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "INSERT INTO POO1_RECEITAS(id, nome, prato) "
                //         1 2 3 
                + "VALUES (RECEITAS_SEQ.nextval,?, ?)";
        try{
              String generatedColumns[] = {"id"};
            PreparedStatement ps = con.prepareStatement(sql, generatedColumns); 
            
            
            ps.setString(1, this.nome);
            ps.setInt(2, ident);
            //JDBC se vira basta criar um tipo int ou char
            
            ps.executeUpdate();   
            
            ResultSet rs = ps.getGeneratedKeys();
            int chaveGerada = 0;
            
            while(rs.next()){
                chaveGerada = rs.getInt(1);
            }
            this.setId(chaveGerada);
           
            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
        }
    
    }
}
