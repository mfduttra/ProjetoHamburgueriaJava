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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mellzi
 */
public class ResultadoPratos {
    private int id;
    private String nome;
    private String tipo;
    private Receita r;

    public ResultadoPratos(int id, String nome, String tipo, Receita r) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.r = r;
    }

    public ResultadoPratos() {
    }

    public Receita getR() {
        return r;
    }

    public void setR(Receita r) {
        this.r = r;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    

    public static List<ResultadoPratos> getAll(){
        ArrayList<ResultadoPratos> pratos = new ArrayList<>();
        
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
//        String sql = "SELECT FORN.ID, FORN.NOME, INGT.ID AS ID_INGREDIENTE, INGT.NOME AS NOME_INGREDIENTE, INGT.QUANTIDADE AS "
//                + "QUANTIDADE_INGREDIENTE, OFTA.ID AS ID_OFERTA, OFTA.VALOR AS OFERTA_VALOR, OFTA.FORNECEDOR " +
//"FROM POO1_FORNECEDORES FORN" +
//"LEFT JOIN POO1_OFERTAS OFTA" +
//"ON OFTA.FORNECEDOR = FORN.ID" +
//"LEFT JOIN POO1_INGREDIENTES INGT" +
//"ON INGT.ID = OFTA.INGREDIENTE;";
        String sql = "SELECT PRATO.ID, PRATO.NOME, PRATO.TIPO, RECEITA.ID AS RECEITA_ID, RECEITA.NOME AS RECEITA_NOME FROM POO1_PRATOS PRATO" +
" LEFT JOIN POO1_RECEITAS RECEITA" +
" ON RECEITA.PRATO = PRATO.ID";
        
        try{
            Statement st = con.createStatement();//Não é um prepared este é mais rápido
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                ResultadoPratos rp = new ResultadoPratos();
                rp.setId(rs.getInt("id"));
                rp.setNome(rs.getString("nome"));
                rp.setTipo(rs.getString("tipo"));
                Receita r = new Receita();
                r.setId(rs.getInt("receita_id"));
                r.setNome(rs.getString("receita_nome"));
                rp.setR(r);
                pratos.add(rp);
                
            }            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
            return null;
        }
        
        return pratos; //Retorna a lista completa
    }
    
    public List<Ingrediente> getAllIngredientes(){
    ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        

        String sql = "SELECT INGREDIENTE.ID, INGREDIENTE.NOME FROM POO1_INGREDIENTES INGREDIENTE" +
" INNER JOIN POO1_RECEITAS_INGREDIENTES R_I ON R_I.ID_INGREDIENTE = INGREDIENTE.ID" +
" WHERE R_I.ID_RECEITA = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.r.getId());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Ingrediente i = new Ingrediente();
                i.setId(rs.getInt("id"));
                i.setNome(rs.getString("nome"));
                ingredientes.add(i);
                
            }            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
            return null;
        }
        
        return ingredientes; //Retorna a lista completa
    }
}