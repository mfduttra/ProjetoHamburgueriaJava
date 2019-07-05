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
public class Oferta {
    private int id;
    private Ingrediente ingrediente;
    private double preco;

    public Oferta(){}

    public Oferta(int id, Ingrediente ingrediente, double preco) {
        this.id = id;
        this.ingrediente = ingrediente;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    /** Método para inserir
    * @return void */
    
     public boolean insert(){
        Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "INSERT INTO POO1_OFERTAS(id, ingrediente, valor) "
                //         1 2 3 
                + "VALUES (OFERTAS_SEQ.nextval,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, this.ingrediente.getId());
            ps.setDouble(2, this.preco);
            //JDBC se vira basta criar um tipo int ou char
            
            ps.executeUpdate();           
           
            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
            return false;
        }
    
        return true;
    }
     
     
    /** Método para obter uma lista de ofertas
    * @return lista do tipo Oferta */
    
    
    public static List<Oferta> getAll(){
        ArrayList<Oferta> ofs = new ArrayList<>();
        
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
        String sql = "select OFT.ID, OFT.VALOR, ING.ID AS ID_INGREDIENTE, ING.NOME AS NOME_INGREDIENTE, "
                + "ING.QUANTIDADE AS QUANTIDADE_INGREDIENTE from poo1_ofertas OFT INNER JOIN POO1_INGREDIENTES ING ON OFT.ingrediente = ING.id "
                + "ORDER BY OFT.ingrediente";
        
        try{
            Statement st = con.createStatement();//Não é um prepared este é mais rápido
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Oferta o = new Oferta();
                Ingrediente i = new Ingrediente();
                i.setId(rs.getInt("id_ingrediente"));
                i.setNome(rs.getString("nome_ingrediente"));
                i.setQuantidade(rs.getInt("quantidade_ingrediente"));
                
                o.setId(rs.getInt("id")); //do banco
                o.setIngrediente(i); //do banco
                o.setPreco(rs.getDouble("valor")); //do banco
        
                ofs.add(o);//Adiciono na lista.
                
            }            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
            return null;
        }
        
        return ofs; //Retorna a lista completa
    }
    
    /** Método para atualizar
    * @return um valor booleano se a atualização for realizada */
    
    
    public boolean update() {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "UPDATE POO1_OFERTAS SET valor= ?"   
                     + "WHERE ( id = ?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(2, this.id); //Observe o indice do ID
            
            ps.setDouble(1, this.preco);
            
            ps.executeUpdate();           
            
            
        }catch(SQLException e){
            
            System.out.println("Tente Novamente");
            e.printStackTrace();
            return false;
        }
    
        return true;
    }
     
    /** Método para remover
    * @return um valor booleano se a remoção for realizada */
     
     
    public boolean remove() {
        Conexao c = new Conexao();
            Connection con = c.getConexao();

            String sql =  "DELETE FROM POO1_OFERTAS WHERE id = ?";
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
    
     
    /** Método para atualizar um fornecedor inserido
    * @return um valor booleano se a atualização for realizada
    * @param fornecedor int - id fornecedor*/
     
    
     public boolean updateFornecedor(int fornecedor) {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "UPDATE POO1_OFERTAS SET fornecedor= ?"   
                     + "WHERE id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(2, this.id); //Observe o indice do ID
            
            ps.setInt(1, fornecedor);
            
            ps.executeUpdate();           
            
            
        }catch(SQLException e){
            
            System.out.println("Tente Novamente");
            e.printStackTrace();
            return false;
        }
    
        return true;
    }
     
      
    /** Método para imprimir Strings
    * @return String */
     
     public String toString(){
         return String.valueOf(this.preco);
     }
     
      
    /** Método para remover a partir de um Fornecedor
     * @param f Fornecedor - fornecedor cadastrado*/

     
     
    public static void removeByFornecedor(Fornecedor f){
        Conexao c = new Conexao();
        Connection con = c.getConexao();

        String sql = "UPDATE POO1_OFERTAS set fornecedor = null where fornecedor=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, f.getId());

            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Tente Novamente");
            e.printStackTrace();
        }
    } 
    
}
