import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.cj.jdbc.Driver;

class TesteUsuarioDAO {
	
	JdbcDatabaseTester jdt;

	@BeforeEach
	void setUp() throws Exception {
		jdt = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/AcessoBancoDeDados?useTimezone=true&serverTimezone=UTC", "root","Rpv242@pedrosa");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}

	@Test
	void reucpearaTodos() {
		List<Usuario> lista = UsuarioDao.listarContatos();
		assertEquals(2,lista.size());
		assertEquals("Pedro", lista.get(0).getLogin());
	}

}
