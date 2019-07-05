/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hamburgueria;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mellanie F. Dutra
 * @version 1.0.0
 */
public class Estoque {
    List<Estoque> estocavel = new ArrayList();

    public Estoque() {
    }

    public List<Estoque> getEstocavel() {
        return estocavel;
    }
    
    public void setEstocavel(List<Estoque> estocavel) {
        this.estocavel = estocavel;
    }
    
    /** Método para realizar inventario*/

    
    public void fazerInventario(){
        
    }
}
