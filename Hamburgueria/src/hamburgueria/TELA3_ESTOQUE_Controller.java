/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hamburgueria;

import Model.Ingrediente;
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
public class TELA3_ESTOQUE_Controller implements Initializable {

    @FXML
    private Button addPrato;
    @FXML
    private Button editarPrato;
    @FXML
    private Button removerPrato;
    @FXML
    private Button atualizarPrato;
    @FXML
    private TextField nomeIngr;
    @FXML
    private TextField qtdadeIngr;
    @FXML
    private TextField idIngr;
    @FXML
    private TableView<Ingrediente> ingrTable;
    @FXML
    private TableColumn<Ingrediente, Integer> idCol;
    @FXML
    private TableColumn<Ingrediente, String> ingredientesCol;
    @FXML
    private TableColumn<Ingrediente, Integer> quantidadeCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol.setCellValueFactory(new PropertyValueFactory("id")); //id de classe, nao de banco
        ingredientesCol.setCellValueFactory(new PropertyValueFactory("nome"));
        quantidadeCol.setCellValueFactory(new PropertyValueFactory("quantidade"));
    
        List<Ingrediente> ingrs = new ArrayList();   
        ingrs = Ingrediente.getAll();

        ingrTable.getItems().clear();

            for(Ingrediente i : ingrs){
                ingrTable.getItems().add(i);

            }   
    }    

    @FXML
    private void adicionarPrato(ActionEvent event) {
    Ingrediente ingr = new Ingrediente();
    
    ingr.setNome(nomeIngr.getText());
    ingr.setQuantidade(Integer.parseInt(qtdadeIngr.getText()));
    
    if (ingr.insert()){ //printar na tabela
        idCol.setCellValueFactory(new PropertyValueFactory("id")); //id de classe, nao de banco
        ingredientesCol.setCellValueFactory(new PropertyValueFactory("nome"));
        quantidadeCol.setCellValueFactory(new PropertyValueFactory("quantidade"));
    
    List<Ingrediente> ingrs = new ArrayList();   
    ingrs = Ingrediente.getAll();
        
    ingrTable.getItems().clear();
    
        for(Ingrediente i : ingrs){
            ingrTable.getItems().add(i);

        }   
    }
    }

    @FXML
    private void editarPrato(ActionEvent event) {
    //alterei o botão de editar
        editarPrato.setText("Editando");
        editarPrato.setDisable(true);
        
        Ingrediente iSelecionado = ingrTable.getSelectionModel().getSelectedItem();// EU pego quem está selecionado
        
        nomeIngr.setText(iSelecionado.getNome());//Peguei o que tem no objeto e puxei para interface.
        qtdadeIngr.setText(String.valueOf(iSelecionado.getQuantidade()));
        idIngr.setText(String.valueOf(iSelecionado.getId()));
        //..Passar todos os campos
        
    }

    @FXML
    private void removerPrato(ActionEvent event) {
        Ingrediente iSelecionado = ingrTable.getSelectionModel().getSelectedItem();// EU pego quem está selecionado
        
        iSelecionado.remove();//remove do banco de dados
        ingrTable.getItems().remove(iSelecionado);//Remove apenas da interface
        
    }

    @FXML
    private void atualizarPrato(ActionEvent event) {
        
        Ingrediente i = ingrTable.getSelectionModel().getSelectedItem();// Não tenho como passar por argumento
        i.setNome(nomeIngr.getText());
        i.setId(Integer.parseInt(idIngr.getText()));
        i.setQuantidade(Integer.parseInt(qtdadeIngr.getText()));
        
        i.update();  
        
        editarPrato.setText("Editar");     
        editarPrato.setDisable(false);
                        
        //atualizando a view para refletir as alterações
        ingrTable.refresh();
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
