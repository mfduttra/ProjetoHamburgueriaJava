/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hamburgueria;

import Model.Fornecedor;
import Model.Ingrediente;
import Model.Oferta;
import Result.ResultadoIngrxOferta;
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
public class TELA5_FORNECEDORES_Controller implements Initializable {

    @FXML
    private Button adicionarFornecedor;
    @FXML
    private TableView<ResultadoIngrxOferta> fornecedoresTable;
    @FXML
    private TableColumn<Fornecedor, Integer> idFor;
    @FXML
    private TableColumn<Fornecedor, String> nomeForn;
    @FXML
    private TableColumn<Ingrediente, String> ingrFor;
    @FXML
    private TableColumn<Oferta, Double> valorForn;
    @FXML
    private Button editarFornecedor;
    @FXML
    private Button removerOferta;
    @FXML
    private Button atualizarFornecedor;
    @FXML
    private TableView<Oferta> ofertasTable;
    @FXML
    private TableColumn<Oferta, Integer> idOfertas;
    @FXML
    private TableColumn<Oferta, String> ingrOfertas;
    @FXML
    private TableColumn<Oferta, Double> valorOfertas;
    @FXML
    private TextField nomeFornecedor;
    @FXML
    private TextField idFornecedor;
    @FXML
    private Button removerForn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idOfertas.setCellValueFactory(new PropertyValueFactory("id")); //id de classe, nao de banco
        ingrOfertas.setCellValueFactory(new PropertyValueFactory("ingrediente"));
        valorOfertas.setCellValueFactory(new PropertyValueFactory("preco"));

        List<Oferta> ofs = new ArrayList();   
        ofs = Oferta.getAll();

        ofertasTable.getItems().clear();
    
        for(Oferta o : ofs){
            ofertasTable.getItems().add(o);

        } 
        
         idFor.setCellValueFactory(new PropertyValueFactory("id")); //id de classe, nao de banco
            nomeForn.setCellValueFactory(new PropertyValueFactory("forn"));
            ingrFor.setCellValueFactory(new PropertyValueFactory("ingr"));
            valorForn.setCellValueFactory(new PropertyValueFactory("ofta"));

        List<ResultadoIngrxOferta> forns = new ArrayList<>();   
        forns = ResultadoIngrxOferta.getAll();

        fornecedoresTable.getItems().clear();
    
        for(ResultadoIngrxOferta fornecedor : forns){
            fornecedoresTable.getItems().add(fornecedor); //como adicionar outra coisa alem do objeto?? ver c o Marcio

        }   
    }    

    @FXML
    private void adicionarFornecedor(ActionEvent event) {
      Fornecedor f = new Fornecedor();
    
        f.setNome(nomeFornecedor.getText());

        if (f.insert()){ //printar na tabela
            idFor.setCellValueFactory(new PropertyValueFactory("id")); //id de classe, nao de banco
            nomeForn.setCellValueFactory(new PropertyValueFactory("forn"));
            ingrFor.setCellValueFactory(new PropertyValueFactory("ingr"));
            valorForn.setCellValueFactory(new PropertyValueFactory("ofta"));

        List<ResultadoIngrxOferta> forns = new ArrayList<>();   
        forns = ResultadoIngrxOferta.getAll();

        fornecedoresTable.getItems().clear();
    
        for(ResultadoIngrxOferta fornecedor : forns){
            fornecedoresTable.getItems().add(fornecedor);

        }   
    }
    
    }

    @FXML
    private void editarFornecedor(ActionEvent event) {
   //alterei o botão de editar
        editarFornecedor.setText("Editando");
        editarFornecedor.setDisable(true);
        
        ResultadoIngrxOferta fSelecionado = fornecedoresTable.getSelectionModel().getSelectedItem();// EU pego quem está selecionado
        
        //Peguei o que tem no objeto e puxei para interface.
        nomeFornecedor.setText(String.valueOf(fSelecionado.getForn().getNome()));
        idFornecedor.setText(String.valueOf(fSelecionado.getForn().getId()));
        //..Passar todos os campos
    
    }

    @FXML
    private void removerOferta(ActionEvent event) {
        ResultadoIngrxOferta fSelecionado = fornecedoresTable.getSelectionModel().getSelectedItem();// EU pego quem está selecionado
   
        Oferta.removeByFornecedor(fSelecionado.getForn());
        
        List<ResultadoIngrxOferta> forns = new ArrayList<>();   
        forns = ResultadoIngrxOferta.getAll();

        fornecedoresTable.getItems().clear();
    
        for(ResultadoIngrxOferta fornecedor : forns){
            fornecedoresTable.getItems().add(fornecedor);

        } 
        
        fornecedoresTable.refresh();
    }
    
    @FXML
    private void removerForn(ActionEvent event) {
        ResultadoIngrxOferta fSelecionado = fornecedoresTable.getSelectionModel().getSelectedItem();
        Oferta.removeByFornecedor(fSelecionado.getForn());
        fSelecionado.getForn().remove();
        
        fornecedoresTable.getItems().clear();
        
        List<ResultadoIngrxOferta> forns = new ArrayList<>();   
        forns = ResultadoIngrxOferta.getAll();
        
        for(ResultadoIngrxOferta fornecedor : forns){
            fornecedoresTable.getItems().add(fornecedor);

        } 
        
        fornecedoresTable.refresh();
    }

    @FXML
    private void adicionarOferta(ActionEvent event) {
        Oferta oSelecionado = ofertasTable.getSelectionModel().getSelectedItem();
        ResultadoIngrxOferta fSelecionado = fornecedoresTable.getSelectionModel().getSelectedItem();
        oSelecionado.updateFornecedor(fSelecionado.getForn().getId());
        //System.out.println(fSelecionado);
        
        List<ResultadoIngrxOferta> forns = new ArrayList<>();   
        forns = ResultadoIngrxOferta.getAll();

        fornecedoresTable.getItems().clear();
    
        for(ResultadoIngrxOferta fornecedor : forns){
            fornecedoresTable.getItems().add(fornecedor);

        } 
        
        fornecedoresTable.refresh();
    }

    @FXML
    private void atualizarFornecedor(ActionEvent event) {
        ResultadoIngrxOferta f = fornecedoresTable.getSelectionModel().getSelectedItem();// Não tenho como passar por argumento
        
        f.getForn().setNome(String.valueOf(nomeFornecedor.getText()));
        
        
        f.getForn().update();  
        
        editarFornecedor.setText("Editar");     
        editarFornecedor.setDisable(false);
                        
        //atualizando a view para refletir as alterações
        List<ResultadoIngrxOferta> forns = new ArrayList<>();   
        forns = ResultadoIngrxOferta.getAll();

        fornecedoresTable.getItems().clear();
    
        for(ResultadoIngrxOferta fornecedor : forns){
            fornecedoresTable.getItems().add(fornecedor);

        } 
        
        fornecedoresTable.refresh();
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
