import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

public class UsuarioDao {
		private static String driver = "com.mysql.cj.jdbc.Driver";
		private static String url = "jdbc:mysql://127.0.0.1:3306/AcessoBancoDeDados?useTimezone=true&serverTimezone=UTC";
		private static String user = "root";
		private static String password = "Rpv242@pedrosa";
		
		static {
				
				try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		
			
		}


		public static List<Usuario> todosUsuarios() {
			ArrayList<Usuario> contatos = new ArrayList<>();
			try 
				(Connection con = DriverManager.getConnection(url, user, password)){
				
				String sql = "select  login,nome,email from contatos";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Usuario u = new Usuario();
					u.setLogin(rs.getString("login"));
					u.setNome(rs.getString("nome"));
					u.setEmail(rs.getString("email"));
					contatos.add(u);
				}
			} catch (SQLException e) {
				throw new RuntimeException("Não foi possivel o acesso", e);
			}
			return contatos;
		
		}
}

