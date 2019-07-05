package hamburgueria;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Mellanie F. Dutra
 * @version 1.0.0
 */
public class Conexao {

    private String usuario = "PROG_OO_1";
    private String senha = "prog";
    private String servidor = "oracle.canoas.ifrs.edu.br";
    private int porta = 1521;

    private Connection conexao = null;

    public Conexao() {
    }//inicia com os valores padrões

    public Conexao(
            String usuario,
            String senha) {
        this.senha = senha;
        this.usuario = usuario;
    }
    
    
    /** Método para obter conexão
    * @return Connection*/
    

    public Connection getConexao() {
        if (conexao == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conexao = DriverManager.getConnection(
                        "jdbc:oracle:thin:@" + this.servidor + ":" + this.porta + ":XE",
                        this.usuario,
                        this.senha);

            } catch (ClassNotFoundException e) {
                System.out.println("Senhor programador! Importe o pacote do DB antes de chingar o java");
            } catch(SQLException e){
                e.printStackTrace(); //Sei lá que diabos tu fez então olhe com calma as coisas.
            }

        }
        return conexao;
    }
    
    
    /** Método para desconectar*/
 
    
    public void desconecta() {
        try {
            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
