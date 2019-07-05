/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/**
 *
 * @author Mellanie F. Dutra
 * @version 1.0.0
 */
import java.util.ArrayList;
import java.util.List;


public class Opcao extends Prato {

    List<Prato> pratos = new ArrayList();
    float preco;
    
    public Opcao(float preco) {
        this.preco = preco;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
     
     
}
