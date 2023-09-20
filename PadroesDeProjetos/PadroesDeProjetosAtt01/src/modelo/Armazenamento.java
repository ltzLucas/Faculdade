package modelo;

import java.sql.SQLException;
import java.util.List;

public interface Armazenamento {
	void adicionarVeiculo(Veiculo veiculo) throws SQLException;
	List<Veiculo> recuperarVeiculos() throws SQLException;

}
