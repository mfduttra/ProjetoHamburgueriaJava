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
import oracle.net.aso.i;

/**
 *
 * @author Mellanie F. Dutra
 * @version 1.0.0
 */
public class Pedido {

    private List<Prato> pratos = new ArrayList();
    private String observacao;
    private int id;

    public Pedido(String observacao, int id) {
        this.observacao = observacao;
        this.id = id;
    }


    

    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    
    /** Método para inserir
    * @return void */
    
    public boolean insert(){
     Conexao c = new Conexao();
        Connection con = c.getConexao();
    
        String sql =  "INSERT INTO POO1_PEDIDOS(id, observacao) "
                //         1 2 3 
                + "VALUES (PEDIDOS_SEQ.nextval,?)";
        try{
           String generatedColumns[] = {"id"};
            PreparedStatement ps = con.prepareStatement(sql, generatedColumns); 
            
            ps.setString(1, this.observacao);
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
            return false;
        }
    
        return true;
    }
     
    /** Método para obter uma lista de pedidos
    * @return lista do tipo Pedido */
    
   
    public static List<Pedido> getAll(){
        ArrayList<Pedido> pedidos = new ArrayList<>();
        
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
        String sql = "select * from POO1_PEDIDOS";
        
        try{
            Statement st = con.createStatement();//Não é um prepared este é mais rápido
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Pedido p = new Pedido();
                p.setId(rs.getInt("id"));
                p.setObservacao(rs.getString("observacao"));
                
                
                pedidos.add(p);//Adiciono na lista.
                
            }            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
            return null;
        }
        
        return pedidos; //Retorna a lista completa
    }
    
     
    /** Método para obter pratos a partir de uma ID
    * @return lista do tipo Prato */
    
    
    public List<Prato> getPratosByID() {

         Conexao c = new Conexao();
         Connection con = c.getConexao();
      
        List<Prato> pratos = new ArrayList<>();
        
        String query = "SELECT P.ID, P.NOME, P.TIPO FROM POO1_PRATOS P" +
" LEFT JOIN POO1_PRATOS_PEDIDOS PP ON PP.ID_PRATO = P.ID" +
" WHERE PP.ID_PEDIDO = ?";
        try {
            
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, this.getId());       
        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                 System.out.println(rs.getString("tipo"));
            switch(rs.getString("tipo")){
              
                case "Milkshake":
                    Milkshake m = new Milkshake();
                    m.setNome(rs.getString("nome"));
                    m.setId(rs.getInt("id"));
                    pratos.add(m);

                    break;
                case "Hamburguer":
                    Hamburguer h = new Hamburguer();
                    h.setNome(rs.getString("nome"));
                    h.setId(rs.getInt("id"));
                    pratos.add(h);
                    break;
                case "Acompanhamento":
                    Acompanhamento a = new Acompanhamento();
                    a.setNome(rs.getString("nome"));
                    a.setId(rs.getInt("id"));
                    pratos.add(a);
                    break;
                case "Cerveja":
                    Cerveja cerv = new Cerveja();
                    cerv.setNome(rs.getString("nome"));
                    cerv.setId(rs.getInt("id"));
                    pratos.add(cerv);
                    break;
                case "Refrigerante":
                    Refrigerante r = new Refrigerante();
                    r.setNome(rs.getString("nome"));
                    r.setId(rs.getInt("id"));
                    pratos.add(r);
                    break;
                }
            }

        } catch ( SQLException e ) {
            System.out.println("Tente novamente");
            e.printStackTrace();
            
        }
        
        return pratos;

    }
    
     
    /** Método para remover valores
    * @return um valor booleano se a remoção for realizada */
    
    
     public boolean remove() {
        Conexao c = new Conexao();
            Connection con = c.getConexao();

            String sql =  "DELETE FROM POO1_PEDIDOS WHERE id = ?";
            try{
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, this.getId());//Apenas 1 elemento

                ps.executeUpdate();

            }catch(SQLException e){

                System.out.println("Tente novamente");
                e.printStackTrace();
                return false;
            }

            return true;
    }
}


