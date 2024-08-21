package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Db {
	
	private static Connection conexao; //static não precisa estanciar o método
	private static Properties getPropriedades() throws IOException {
		
		Properties propriedades = new Properties();
		
		FileInputStream arquivo = new FileInputStream("./properties/db.properties");
		propriedades.load(arquivo);
		return propriedades;
	}
	
	public static Connection getConexao() throws IOException, SQLException {
		if (conexao ==null) {
			Properties p = Db.getPropriedades();
			conexao = DriverManager.getConnection(p.getProperty("host"), p.getProperty("user"), p.getProperty("password"));
		}
		return conexao;
	}
	
	public static void fechaConexao() throws SQLException {
		if(conexao != null) {
			conexao.close();
		}
		
	}

}
