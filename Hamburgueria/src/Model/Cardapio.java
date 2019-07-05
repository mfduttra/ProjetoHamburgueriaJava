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

public class Cardapio {
    List<Opcao> opcoes = new ArrayList();

    public Cardapio() {
    }
    
    

    public List<Opcao> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<Opcao> opcoes) {
        this.opcoes = opcoes;
    }
    
     /** Método para realizar um pedido
    * @return Pedido*/
    
    public Pedido fazerPedido(List<Opcao> opcoes){
        return null;
        
    }
}
