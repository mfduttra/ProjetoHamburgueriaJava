/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hamburgueria;

import Model.Opcao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mellzi
 */
public class Hamburgueria extends Application {
    
    private static Stage st;
    
    public static void trocarTela (String caminho){
        try{
            Parent root = FXMLLoader.load(Hamburgueria.class.getResource(caminho));
            Scene scene = new Scene(root);
            
            st.setScene(scene);
            st.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        
        st = stage;
        Scene scene = new Scene(root);

        st.setScene(scene);
        st.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        List<Opcao> opções = new ArrayList();
    }
    
}
