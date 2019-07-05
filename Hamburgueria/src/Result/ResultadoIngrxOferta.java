/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Result;

import Model.Fornecedor;
import Model.Ingrediente;
import Model.Oferta;
import hamburgueria.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mellzi
 */
public class ResultadoIngrxOferta {
    private Fornecedor forn;
    private Ingrediente ingr;
    private Oferta ofta;
    private int id;

    public ResultadoIngrxOferta(Fornecedor forn, Ingrediente ingr, Oferta ofta) {
        this.forn = forn;
        this.ingr = ingr;
        this.ofta = ofta;
        this.id = id;
    }
    
    public ResultadoIngrxOferta(){}

    public Fornecedor getForn() {
        return forn;
    }

    public void setForn(Fornecedor forn) {
        this.forn = forn;
    }

    public Ingrediente getIngr() {
        return ingr;
    }

    public void setIngr(Ingrediente ingr) {
        this.ingr = ingr;
    }

    public Oferta getOfta() {
        return ofta;
    }

    public void setOfta(Oferta ofta) {
        this.ofta = ofta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public static List<ResultadoIngrxOferta> getAll(){
        ArrayList<ResultadoIngrxOferta> forn = new ArrayList<>();
        
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
//        String sql = "SELECT FORN.ID, FORN.NOME, INGT.ID AS ID_INGREDIENTE, INGT.NOME AS NOME_INGREDIENTE, INGT.QUANTIDADE AS "
//                + "QUANTIDADE_INGREDIENTE, OFTA.ID AS ID_OFERTA, OFTA.VALOR AS OFERTA_VALOR, OFTA.FORNECEDOR " +
//"FROM POO1_FORNECEDORES FORN" +
//"LEFT JOIN POO1_OFERTAS OFTA" +
//"ON OFTA.FORNECEDOR = FORN.ID" +
//"LEFT JOIN POO1_INGREDIENTES INGT" +
//"ON INGT.ID = OFTA.INGREDIENTE;";
        String sql = "SELECT FORN.ID, FORN.NOME, INGT.ID AS ID_INGREDIENTE, INGT.NOME AS NOME_INGREDIENTE, INGT.QUANTIDADE AS QUANTIDADE_INGREDIENTE, "
                + "OFTA.ID AS ID_OFERTA, OFTA.VALOR AS OFERTA_VALOR, OFTA.FORNECEDOR "
                + "FROM POO1_FORNECEDORES FORN "
                + "LEFT JOIN POO1_OFERTAS OFTA "
                + "ON OFTA.FORNECEDOR = FORN.ID "
                + "LEFT JOIN POO1_INGREDIENTES INGT "
                + "ON INGT.ID = OFTA.INGREDIENTE";
        
        try{
            Statement st = con.createStatement();//Não é um prepared este é mais rápido
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                ResultadoIngrxOferta res = new ResultadoIngrxOferta();
               
                Fornecedor f = new Fornecedor();
                f.setNome(rs.getString("nome"));
                f.setId(rs.getInt("id"));
                
                Ingrediente i = new Ingrediente();
                i.setNome(rs.getString("nome_ingrediente"));
                i.setQuantidade(rs.getInt("quantidade_ingrediente"));
                
                Oferta o = new Oferta();
                o.setId(rs.getInt("id_oferta"));
                o.setPreco(rs.getDouble("oferta_valor"));
                o.setIngrediente(i);
              
                res.setForn(f);
                res.setIngr(i);
                res.setOfta(o);
                res.setId(f.getId());
                
                forn.add(res);
                
            }            
        }catch(SQLException e){
            
            System.out.println("Tente novamente");
            e.printStackTrace();
            return null;
        }
        
        return forn; //Retorna a lista completa
    }
}
