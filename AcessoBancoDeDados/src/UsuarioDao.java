import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class UsuarioDao {
	/** The driver. */
	private static String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private  static String url = "jdbc:mysql://127.0.0.1:3306/AcessoBancoDeDados?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private static String user = "root";
	
	/** The password. */
	private static String password = "Rpv242@pedrosa";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private static Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(Usuario contato) {
		String create = "insert into contatos (login,nome,email) values (?,?,?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(2, contato.getLogin());
			pst.setString(1, contato.getNome());
			pst.setString(3, contato.getEmail());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	public static ArrayList<Usuario> listarContatos() {
		ArrayList<Usuario> contatos = new ArrayList<>();
		String read = "select * from contatos order by login";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String login = rs.getString(1);
				String nome = rs.getString(2);
				String email = rs.getString(3);
				contatos.add(new Usuario(login, nome, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(Usuario contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getLogin());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				contato.setLogin(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setEmail(rs.getString(3));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	public void alterarContato(Usuario contato) {
		String update = "update contatos set nome=?,email=? where login=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, contato.getNome());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getLogin());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Deletar contato.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(Usuario contato) {
		String delete = "delete from contatos where login=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getLogin());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
