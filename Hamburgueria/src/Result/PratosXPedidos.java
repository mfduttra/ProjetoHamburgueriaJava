/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Result;

import Model.Pedido;
import Model.Prato;
import hamburgueria.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author mellzi
 */
public class PratosXPedidos {
    private Pedido ped;
    private Prato pra;

    public PratosXPedidos() {
    }

    public PratosXPedidos(Pedido ped, Prato pra) {
        this.ped = ped;
        this.pra = pra;
    }

    public Pedido getPed() {
        return ped;
    }

    public void setPed(Pedido ped) {
        this.ped = ped;
    }

    public Prato getPra() {
        return pra;
    }

    public void setPra(Prato pra) {
        this.pra = pra;
    }
    
    
    
    public void insert(){
        Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "INSERT INTO POO1_PRATOS_PEDIDOS(id_pedido, id_prato) "
                //         1 2 3 
                + "VALUES (?, ?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(2, this.pra.getId());
            ps.setInt(1, this.ped.getId());
            //JDBC se vira basta criar um tipo int ou char
            
            ps.executeUpdate();   
            
                      
            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
        }
    
    }
    
       public boolean remove() {
        Conexao c = new Conexao();
            Connection con = c.getConexao();

            String sql =  "DELETE FROM POO1_PRATOS_PEDIDOS WHERE id_pedido = ?";
            try{
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, this.ped.getId());//Apenas 1 elemento

                ps.executeUpdate();

            }catch(SQLException e){

                System.out.println("Tente novamente");
                e.printStackTrace();
                return false;
            }

            return true;
    }
       
       public boolean removeByID() {
        Conexao c = new Conexao();
            Connection con = c.getConexao();

            String sql =  "DELETE FROM POO1_PRATOS_PEDIDOS WHERE id_pedido = ? AND id_prato = ?";
            try{
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, this.ped.getId());//Apenas 1 elemento
                ps.setInt(2, this.pra.getId());//Apenas 1 elemento

                ps.executeUpdate();

            }catch(SQLException e){

                System.out.println("Tente novamente");
                e.printStackTrace();
                return false;
            }

            return true;
    }
}
