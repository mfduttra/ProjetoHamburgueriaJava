/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hamburgueria;

import Model.Milkshake;
import Model.Hamburguer;
import Model.Acompanhamento;
import Model.Cerveja;
import Model.Ingrediente;
import Model.Prato;
import Model.Receita;
import Model.Refrigerante;
import Result.IngredientesxPratos;
import Result.ResultadoPratos;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author mellzi
 */
public class TELA2_RECEITAS_Controller implements Initializable {

    @FXML
    private TableView<Ingrediente> tabelaingr;
    @FXML
    private TableColumn<Ingrediente, Integer> idColIngr;
    @FXML
    private TableColumn<Ingrediente, String> IngrColIngr;
    @FXML
    private TableColumn<Ingrediente, Integer> qtdadeColIngr;
    @FXML
    private TableView<ResultadoPratos> listaPratos;
    @FXML
    private TableColumn<ResultadoPratos, Integer> idpratoCol;
    @FXML
    private TableColumn<ResultadoPratos, String> nomePratoCol;
    @FXML
    private ListView<Ingrediente> listaingr;
    @FXML
    private TextField nome;
    @FXML
    private TableColumn<ResultadoPratos, String> tipoPratoCol;
    @FXML
    private ComboBox<String> pratosDisponiveis;
    @FXML
    private TextField quantidade;
    @FXML
    private Button editarPrato;
    @FXML
    private Button removerPrato;
    @FXML
    private Button atualizarPrato;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColIngr.setCellValueFactory(new PropertyValueFactory("id")); //id de classe, nao de banco
        IngrColIngr.setCellValueFactory(new PropertyValueFactory("nome"));
        qtdadeColIngr.setCellValueFactory(new PropertyValueFactory("quantidade"));
    
        List<Ingrediente> ingrs = new ArrayList();   
        ingrs = Ingrediente.getAll();

        tabelaingr.getItems().clear();

            for(Ingrediente i : ingrs){
                tabelaingr.getItems().add(i);

            } 
            
        pratosDisponiveis.getItems().clear();
        pratosDisponiveis.getItems().add("Milkshake");
        pratosDisponiveis.getItems().add("Hamburguer");
        pratosDisponiveis.getItems().add("Acompanhamento");
        pratosDisponiveis.getItems().add("Cerveja");
        pratosDisponiveis.getItems().add("Refrigerante");
        
        idpratoCol.setCellValueFactory(new PropertyValueFactory("id")); //id de classe, nao de banco
        nomePratoCol.setCellValueFactory(new PropertyValueFactory("nome"));
        tipoPratoCol.setCellValueFactory(new PropertyValueFactory("tipo"));
    
        List<ResultadoPratos> pratos = new ArrayList();   
        pratos = ResultadoPratos.getAll();

        listaPratos.getItems().clear();

            for(ResultadoPratos i : pratos){
                listaPratos.getItems().add(i);

            }
        
        listaPratos.setOnMouseClicked( new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                ResultadoPratos c = listaPratos.getSelectionModel().getSelectedItem();
                
                listaingr.getItems().clear();
                for (Ingrediente i : c.getAllIngredientes()) {
                        listaingr.getItems().add(i);
                }
                
            }
          
        });    
            
    }    
    
    public void adicionarIngrediente(URL url, ResourceBundle rb) {
        ObservableList<String> options = 
        FXCollections.observableArrayList(
        "Milshake",
        "Acompanhamento",
        "Hamburguer"
    );
        
    //PratoCozinhavel.getItems().removeAll(PratoCozinhavel.getItems());
    //PratoCozinhavel.getItems().addAll(options);
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
    
    @FXML
    private void adicionarPrato(ActionEvent event){
        Receita rec = new Receita();

    switch(pratosDisponiveis.getSelectionModel().getSelectedItem()){
        case "Milkshake":
            Milkshake m = new Milkshake();
            m.setNome(nome.getText());
            m.insert();
            
            rec.setNome(m.getNome());
            rec.insert(m.getId());
            break;
        case "Hamburguer":
            Hamburguer h = new Hamburguer();
            h.setNome(nome.getText());
            h.insert();
            rec.setNome(h.getNome());
            rec.insert(h.getId());
            break;
        case "Acompanhamento":
            Acompanhamento a = new Acompanhamento();
            a.setNome(nome.getText());
            a.insert();
            rec.setNome(a.getNome());
            rec.insert(a.getId());
            break;
        case "Cerveja":
            Cerveja c = new Cerveja();
            c.setNome(nome.getText());
            c.insert();
            rec.setNome(c.getNome());
            rec.insert(c.getId());
            break;
        case "Refrigerante":
            Refrigerante r = new Refrigerante();
            r.setNome(nome.getText());
            r.insert();
            
            rec.setNome(r.getNome());
            rec.insert(r.getId());
            break;
    } 
 
    
        idpratoCol.setCellValueFactory(new PropertyValueFactory("id")); //id de classe, nao de banco
        nomePratoCol.setCellValueFactory(new PropertyValueFactory("nome"));
        tipoPratoCol.setCellValueFactory(new PropertyValueFactory("tipo"));
    
        List<ResultadoPratos> ingrs = new ArrayList();   
        ingrs = ResultadoPratos.getAll();

        listaPratos.getItems().clear();

            for(ResultadoPratos i : ingrs){
                listaPratos.getItems().add(i);

            } 
        
    }

    @FXML
    private void adicionarIngrAoPrato(ActionEvent event) {
         Ingrediente fSelecionado = tabelaingr.getSelectionModel().getSelectedItem();
         ResultadoPratos pSelecionado = listaPratos.getSelectionModel().getSelectedItem();
         
         System.out.println(pSelecionado.getR().getId() + " ---- " + fSelecionado.getId());
         IngredientesxPratos ip = new IngredientesxPratos();
         ip.setIngrediente(fSelecionado);         
     
         ip.setReceita(pSelecionado.getR());

         ip.setQuantidade(Integer.parseInt(quantidade.getText()));
         
         ip.insert();
         
         listaingr.getItems().clear();
                for (Ingrediente i : pSelecionado.getAllIngredientes()) {
                        listaingr.getItems().add(i);
                }
         
        
         //fazer uma classe para tratar o retorno do banco
         
    }
    
    @FXML
    private void editarPrato(ActionEvent event) {
    //alterei o botão de editar
        editarPrato.setText("Editando");
        editarPrato.setDisable(true);
        
        ResultadoPratos iSelecionado = listaPratos.getSelectionModel().getSelectedItem();// EU pego quem está selecionado
        
        //Peguei o que tem no objeto e puxei para interface.
        nome.setText(String.valueOf(iSelecionado.getNome()));
        //..Passar todos os campos
    
    }
    
   @FXML
    private void atualizarPrato(ActionEvent event) {
        ResultadoPratos o = listaPratos.getSelectionModel().getSelectedItem();// Não tenho como passar por argumento
        o.setNome(nome.getText());
        System.out.println(o.getTipo());
        switch(o.getTipo()){
            case "Milkshake":
            Milkshake m = new Milkshake();
            m.setNome(nome.getText());
            m.setId(o.getId());
            m.update();

            break;
        case "Hamburguer":
            Hamburguer h = new Hamburguer();
            h.setNome(nome.getText());
            h.setId(o.getId());
            h.update();
            
            break;
        case "Acompanhamento":
            Acompanhamento a = new Acompanhamento();
            a.setNome(nome.getText());
            a.setId(o.getId());
            a.update();
            
            break;
        case "Cerveja":
            Cerveja c = new Cerveja();
            c.setNome(nome.getText());
            c.setId(o.getId());
            c.update();
            
            break;
        case "Refrigerante":
            Refrigerante r = new Refrigerante();
            r.setNome(nome.getText());
            r.setId(o.getId());
            r.update();
            
           
            break;
        }
   
        
        atualizarPrato.setText("Editar");     
        atualizarPrato.setDisable(false);
                        
        //atualizando a view para refletir as alterações
        listaPratos.refresh();
    
    }

    
}
