import java.util.List;

public class Principal {

	public static void main(String[] args) {
		
		List<Usuario> lista = UsuarioDao.listarContatos();
		lista.forEach(System.out::println);

	}

}


