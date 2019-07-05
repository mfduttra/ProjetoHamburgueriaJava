/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hamburgueria;

import Model.Ingrediente;
import Model.Oferta;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author mellzi
 */
public class TELA4_OFERTAS_Controller implements Initializable {

    @FXML
    private TableColumn<?, ?> idOfertaCol;
    @FXML
    private TableColumn<?, ?> idIngrOfertaCol;
    @FXML
    private TableColumn<?, ?> valorOfertaCol;
    @FXML
    private TableColumn<Ingrediente, Integer> idCol;
    @FXML
    private TableColumn<Ingrediente, String> ingrCol;
    @FXML
    private TableColumn<Ingrediente, Integer> qtdadeCol;
    @FXML
    private TableView<Ingrediente> ingrTable;
    @FXML
    private Button adicionarOferta;
    @FXML
    private Button editarOferta;
    @FXML
    private Button removerOferta;
    @FXML
    private Button atualizarOferta;
    @FXML
    private TextField preco;
    @FXML
    private TableView<Oferta> ofertasTable;
    @FXML
    private TextField idOferta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       idCol.setCellValueFactory(new PropertyValueFactory("id")); //id de classe, nao de banco
        ingrCol.setCellValueFactory(new PropertyValueFactory("nome"));
        qtdadeCol.setCellValueFactory(new PropertyValueFactory("quantidade"));
    
        List<Ingrediente> ingrs = new ArrayList();   
        ingrs = Ingrediente.getAll();

        ingrTable.getItems().clear();

            for(Ingrediente i : ingrs){
                ingrTable.getItems().add(i);

            }   
            
        idOfertaCol.setCellValueFactory(new PropertyValueFactory("id")); //id de classe, nao de banco
        idIngrOfertaCol.setCellValueFactory(new PropertyValueFactory("ingrediente"));
        valorOfertaCol.setCellValueFactory(new PropertyValueFactory("preco"));

        List<Oferta> ofs = new ArrayList();   
        ofs = Oferta.getAll();

        ofertasTable.getItems().clear();
    
        for(Oferta o : ofs){
            ofertasTable.getItems().add(o);

        } 
    
    }    

    @FXML
    private void adicionarOferta(ActionEvent event) {
        
        Oferta oferta = new Oferta();
    
        oferta.setIngrediente(ingrTable.getSelectionModel().getSelectedItem());
        oferta.setPreco(Double.parseDouble(preco.getText()));

        if (oferta.insert()){ //printar na tabela
            idOfertaCol.setCellValueFactory(new PropertyValueFactory("id")); //id de classe, nao de banco
            idIngrOfertaCol.setCellValueFactory(new PropertyValueFactory("ingrediente"));
            valorOfertaCol.setCellValueFactory(new PropertyValueFactory("preco"));

        List<Oferta> ofs = new ArrayList();   
        ofs = Oferta.getAll();

        ofertasTable.getItems().clear();
    
        for(Oferta o : ofs){
            ofertasTable.getItems().add(o);

        }   
    }
    }

    @FXML
    private void editarOferta(ActionEvent event) {
    //alterei o botão de editar
        editarOferta.setText("Editando");
        editarOferta.setDisable(true);
        
        Oferta iSelecionado = ofertasTable.getSelectionModel().getSelectedItem();// EU pego quem está selecionado
        
        //Peguei o que tem no objeto e puxei para interface.
        preco.setText(String.valueOf(iSelecionado.getPreco()));
        idOferta.setText(String.valueOf(iSelecionado.getId()));
        //..Passar todos os campos
    
    }

    @FXML
    private void removerOferta(ActionEvent event) {
        Oferta oSelecionado = ofertasTable.getSelectionModel().getSelectedItem();// EU pego quem está selecionado
        
        oSelecionado.remove();//remove do banco de dados
        ofertasTable.getItems().remove(oSelecionado);//Remove apenas da interface
        
    }

    
    @FXML
    private void atualizarOferta(ActionEvent event) {
        Oferta o = ofertasTable.getSelectionModel().getSelectedItem();// Não tenho como passar por argumento
        o.setPreco(Double.parseDouble(preco.getText()));
        o.setId(Integer.parseInt(idOferta.getText()));
        
        o.update();  
        
        editarOferta.setText("Editar");     
        editarOferta.setDisable(false);
                        
        //atualizando a view para refletir as alterações
        ofertasTable.refresh();
    
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


    