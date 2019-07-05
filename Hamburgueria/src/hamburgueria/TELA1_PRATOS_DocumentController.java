/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hamburgueria;

import Model.Acompanhamento;
import Model.Cerveja;
import Model.Hamburguer;
import Model.Ingrediente;
import Model.Milkshake;
import Model.Pedido;
import Model.Prato;
import Model.Refrigerante;
import Result.PratosXPedidos;
import Result.ResultadoPratos;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author mellzi
 */
public class TELA1_PRATOS_DocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField nomePrato;
    @FXML
    private TableColumn<Pedido, ?> idOpcaoCol;
    @FXML
    private TableColumn<Pedido, ?> idObsCol;
    @FXML
    private TableColumn<ResultadoPratos, ?> idPratoCol;
    @FXML
    private TableColumn<ResultadoPratos, ?> pratoCol;
    @FXML
    private TableColumn<ResultadoPratos, ?> tipoPrato;
    @FXML
    private TableView<ResultadoPratos> listaPratos;
    @FXML
    private ListView<Ingrediente> listaingr;
    @FXML
    private ListView<Prato> pratosInclusos;
    
    Pedido p = null;
    List<Prato> pratos = new ArrayList<>();
    @FXML
    private Button adicionarObs;
    @FXML
    private Button abrirPedido;
    @FXML
    private TableView<Pedido> tabelaOpcoes;
    

    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        idPratoCol.setCellValueFactory(new PropertyValueFactory("id")); //id de classe, nao de banco
        pratoCol.setCellValueFactory(new PropertyValueFactory("nome"));
        tipoPrato.setCellValueFactory(new PropertyValueFactory("tipo"));
        
        idOpcaoCol.setCellValueFactory(new PropertyValueFactory("id"));
        idObsCol.setCellValueFactory(new PropertyValueFactory("observacao"));
    
        List<ResultadoPratos> pratos = new ArrayList();   
        pratos = ResultadoPratos.getAll();

        listaPratos.getItems().clear();

            for(ResultadoPratos i : pratos){
                listaPratos.getItems().add(i);

            }
            
        tabelaOpcoes.getItems().clear();
       
        for (Pedido p : Pedido.getAll()) {
                tabelaOpcoes.getItems().add(p);
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
        
        tabelaOpcoes.setOnMouseClicked( new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
              Pedido pt = tabelaOpcoes.getSelectionModel().getSelectedItem();
                System.out.println(pt.getId());
                pratosInclusos.getItems().clear();
                for (Prato p : pt.getPratosByID()) {
                        pratosInclusos.getItems().add(p);
                }
                
            }
          
        });    
            
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
    public void adicionarPrato(ActionEvent event){
        ResultadoPratos pSelecionado = listaPratos.getSelectionModel().getSelectedItem();
        System.out.println(pSelecionado.getTipo());
        switch(pSelecionado.getTipo()){
            case "Milkshake":
            Milkshake m = new Milkshake();
            m.setNome(pSelecionado.getNome());
            m.setId(pSelecionado.getId());
            pratos.add(m);

            break;
        case "Hamburguer":
            Hamburguer h = new Hamburguer();
            h.setNome(pSelecionado.getNome());
            h.setId(pSelecionado.getId());
            pratos.add(h);
            break;
        case "Acompanhamento":
            Acompanhamento a = new Acompanhamento();
            a.setNome(pSelecionado.getNome());
            a.setId(pSelecionado.getId());
            pratos.add(a);
            break;
        case "Cerveja":
            Cerveja c = new Cerveja();
            c.setNome(pSelecionado.getNome());
            c.setId(pSelecionado.getId());
            pratos.add(c);
            break;
        case "Refrigerante":
            Refrigerante r = new Refrigerante();
            r.setNome(pSelecionado.getNome());
            r.setId(pSelecionado.getId());
            pratos.add(r);
            break;
        }
    }

    @FXML
    private void adicionarObs(ActionEvent event) {
       
       p.setObservacao(nomePrato.getText());
       
       p.insert();
       
       for (Prato paux : pratos){
           PratosXPedidos pratoped = new PratosXPedidos();
           pratoped.setPed(p);
           pratoped.setPra(paux);
           pratoped.insert();
       }
       
       tabelaOpcoes.getItems().clear();
       
        for (Pedido p : Pedido.getAll()) {
                tabelaOpcoes.getItems().add(p);
        }
       
    }


    @FXML
    private void removerOpcao(ActionEvent event) {
        Pedido pt = tabelaOpcoes.getSelectionModel().getSelectedItem();
        Prato p = pratosInclusos.getSelectionModel().getSelectedItem();
        
        PratosXPedidos pp = new PratosXPedidos();
        pp.setPed(pt);
        pp.setPra(p);
        
        pp.removeByID();

        pratosInclusos.getItems().clear();
            for (Prato paux : pt.getPratosByID()) {
                pratosInclusos.getItems().add(paux);
            }
    }

    @FXML
    private void atualizarOpcao(ActionEvent event) {
        Pedido pt = tabelaOpcoes.getSelectionModel().getSelectedItem();
        
        PratosXPedidos pp = new PratosXPedidos();

        ResultadoPratos pSelecionado = listaPratos.getSelectionModel().getSelectedItem();
        System.out.println(pSelecionado.getTipo());
        switch(pSelecionado.getTipo()){
            case "Milkshake":
            Milkshake m = new Milkshake();
            m.setNome(pSelecionado.getNome());
            m.setId(pSelecionado.getId());
            pp.setPed(pt);
            pp.setPra(m);
            pp.insert();
            pratosInclusos.getItems().add(m);
                  

            break;
        case "Hamburguer":
            Hamburguer h = new Hamburguer();
            h.setNome(pSelecionado.getNome());
            h.setId(pSelecionado.getId());
            pp.setPed(pt);
            pp.setPra(h);
            pp.insert();
            pratosInclusos.getItems().add(h);
            break;
            
        case "Acompanhamento":
            Acompanhamento a = new Acompanhamento();
            a.setNome(pSelecionado.getNome());
            a.setId(pSelecionado.getId());
            pp.setPed(pt);
            pp.setPra(a);
            pp.insert();
            pratosInclusos.getItems().add(a);
            break;
            
        case "Cerveja":
            Cerveja c = new Cerveja();
            c.setNome(pSelecionado.getNome());
            c.setId(pSelecionado.getId());
            pp.setPed(pt);
            pp.setPra(c);
            pp.insert();
            pratosInclusos.getItems().add(c);
            break;
            
        case "Refrigerante":
            Refrigerante r = new Refrigerante();
            r.setNome(pSelecionado.getNome());
            r.setId(pSelecionado.getId());
            pp.setPed(pt);
            pp.setPra(r);
            pp.insert();
            pratosInclusos.getItems().add(r);
            break;
        }
        
        

    
    }

   

     @FXML
    private void abrirPedido(ActionEvent event) {
        p = new Pedido();
        pratos = new ArrayList<>();
        adicionarObs.setDisable(false);
    }

    @FXML
    private void removerPedido(ActionEvent event) {
        Pedido pt = tabelaOpcoes.getSelectionModel().getSelectedItem();
        PratosXPedidos pratopedidos = new PratosXPedidos();
        pratopedidos.setPed(pt);
        pratopedidos.remove();
        
        pt.remove();

        tabelaOpcoes.getItems().clear();
       
        for (Pedido p : Pedido.getAll()) {
                tabelaOpcoes.getItems().add(p);
        }
    }

    
}
