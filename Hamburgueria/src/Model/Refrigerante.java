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


/**
 *
 * @author Mellanie F. Dutra
 * @version 1.0.0
 */
public class Refrigerante extends PratoEstocavel{

    public Refrigerante() {
    }
    
    @Override
    
    /** Método para inserir
    * @return void */
    
    public void insert(){
    Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "INSERT INTO POO1_PRATOS(id, nome, tipo) "
                //         1 2 3 
                + "VALUES (PRATOS_SEQ.nextval,?,'Refrigerante')";
        try{
              String generatedColumns[] = {"id"};
            PreparedStatement ps = con.prepareStatement(sql, generatedColumns); 
            
            
            ps.setString(1, super.getNome());
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
