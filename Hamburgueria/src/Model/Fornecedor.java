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
public class Fornecedor {
    private List<Oferta> ofertas = new ArrayList();
    private String nome;
    private int id;

    public Fornecedor() {
    }

    public Fornecedor(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
     /** Método para inserir
    * @return void */
    
     public boolean insert(){
        Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "INSERT INTO POO1_FORNECEDORES(id, nome) "
                //         1 2  
                + "VALUES (FORNECEDORES_SEQ.nextval,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, this.nome);
            //JDBC se vira basta criar um tipo int ou char
            
            ps.executeUpdate();           
           
            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
            return false;
        }
    
        return true;
    }
    
    /** Método para listar todos os fornecedores
    * @return Lista do tipo Fornecedor */
    public static List<Fornecedor> getAll(){
        ArrayList<Fornecedor> forn = new ArrayList<>();
        
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
//        String sql = "SELECT FORN.ID, FORN.NOME, INGT.ID AS ID_INGREDIENTE, INGT.NOME AS NOME_INGREDIENTE, INGT.QUANTIDADE AS "
//                + "QUANTIDADE_INGREDIENTE, OFTA.ID AS ID_OFERTA, OFTA.VALOR AS OFERTA_VALOR, OFTA.FORNECEDOR " +
//"FROM POO1_FORNECEDORES FORN" +
//"LEFT JOIN POO1_OFERTAS OFTA" +
//"ON OFTA.FORNECEDOR = FORN.ID" +
//"LEFT JOIN POO1_INGREDIENTES INGT" +
//"ON INGT.ID = OFTA.INGREDIENTE;";
        String sql = "SELECT * FROM POO1_FORNECEDORES";
        
        try{
            Statement st = con.createStatement();//Não é um prepared este é mais rápido
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Fornecedor f = new Fornecedor();
                Ingrediente i = new Ingrediente();
                Oferta o = new Oferta();
//                
//                i.setId(rs.getInt("id_ingrediente"));
//                i.setNome(rs.getString("nome_ingrediente"));
//                i.setQuantidade(rs.getInt("quantidade_ingrediente"));
//                
//                o.setId(rs.getInt("id_oferta"));
//                o.setPreco(rs.getDouble("oferta_valor"));
//                o.setIngrediente(i);
                
                f.setNome(rs.getString("nome"));
                f.setId(rs.getInt("id"));
                f.setOfertas(f.getOfertasFromFornecedor());
                
        
                forn.add(f);//Adiciono na lista.
                
            }            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
            return null;
        }
        
        return forn; //Retorna a lista completa
    }
    
     /** Método para ataulizar os valores inseridos
    * @return valor booleano se a atualização foi realizada */
    
    public boolean update() {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "UPDATE POO1_FORNECEDORES SET nome= ?"   
                     + "WHERE ( id = ?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(2, this.id); //Observe o indice do ID
            
            ps.setString(1, this.nome);
            
            ps.executeUpdate();           
            
            
        }catch(SQLException e){
            
            System.out.println("Tente Novamente");
            e.printStackTrace();
            return false;
        }
    
        return true;
    }
    
    /** Método para remover os valores inseridos
    * @return valor booleano se a remoção foi realizada */
     
    public boolean remove() {
        Conexao c = new Conexao();
            Connection con = c.getConexao();

            String sql =  "DELETE FROM POO1_FORNECEDORES WHERE id = ?";
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
    
    /** Método para obter ofertas a partir dos fornecedores
    * @return uma lista do tipo Oferta */
    
    public List<Oferta> getOfertasFromFornecedor() {

         Conexao c = new Conexao();
         Connection con = c.getConexao();
      
        ArrayList<Oferta> ofs = new ArrayList<>();
        
        String query = "select OFT.ID, OFT.VALOR, ING.ID AS ID_INGREDIENTE, ING.NOME AS NOME_INGREDIENTE, "
                + "ING.QUANTIDADE AS QUANTIDADE_INGREDIENTE from poo1_ofertas OFT INNER JOIN POO1_INGREDIENTES ING ON OFT.ingrediente = ING.id "
                + "WHERE OFT.FORNECEDOR = ? ORDER BY OFT.ingrediente";
        try {
            
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, this.getId());       
        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
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

        } catch ( SQLException e ) {
            System.out.println("Tente novamente");
            e.printStackTrace();
            
        }
        
        return ofs;

    }
    
   /** Método para imprimir Strings
    * @return uma String */
    
    public String toString(){
        return this.nome;
    }
    
    
}
