/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hamburgueria;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author mellzi
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
     @FXML
    private void trocarTelaPratos(ActionEvent event) {
        Hamburgueria.trocarTela("TELA1_PRATOS_FXMLDocument.fxml");
    }

    @FXML
    private void trocarTelaReceitas(ActionEvent event) {
        Hamburgueria.trocarTela("TELA2_RECEITAS_.fxml");
    }

    @FXML
    private void trocarTelaEstoque(ActionEvent event) {
        Hamburgueria.trocarTela("TELA3_ESTOQUE_.fxml");
    }

    @FXML
    private void trocarTelaOfertas(ActionEvent event) {
        Hamburgueria.trocarTela("TELA4_OFERTAS_.fxml");
    }

    @FXML
    private void trocarTelaForn(ActionEvent event) {
        Hamburgueria.trocarTela("TELA5_FORNECEDORES_.fxml");
    }
    
     @FXML
    private void trocarTelaVoltar(ActionEvent event) {
        Hamburgueria.trocarTela("Menu.fxml");
    }
    
}
