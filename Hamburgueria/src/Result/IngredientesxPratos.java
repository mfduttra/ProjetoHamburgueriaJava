/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Result;

import Model.Ingrediente;
import Model.Receita;
import hamburgueria.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author mellzi
 */
public class IngredientesxPratos {
    
    private Ingrediente ingrediente;
    private Receita receita;
    private int quantidade;

    public IngredientesxPratos() {
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

   
    
    
    public void insert(){
        Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "INSERT INTO POO1_RECEITAS_INGREDIENTES(id_receita, id_ingrediente, quantidade) "
                //         1 2 3 
                + "VALUES (?, ?, ?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(2, this.ingrediente.getId());
            ps.setInt(1, this.receita.getId());
            ps.setInt(3, this.quantidade);
            //JDBC se vira basta criar um tipo int ou char
            
            ps.executeUpdate();   
            
                      
            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
        }
    
    }
    
}
