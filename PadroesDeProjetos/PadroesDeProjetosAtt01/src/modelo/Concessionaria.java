package modelo;

import java.sql.SQLException;
import java.util.List;

public class Concessionaria {
	private Armazenamento armazenamento;
	
	public Concessionaria(Armazenamento armazenamento) {
        this.armazenamento = armazenamento;
    }
	
	public void adicionarVeiculo(Veiculo veiculo) throws SQLException {
        armazenamento.adicionarVeiculo(veiculo);
    }
	
	public List<Veiculo> listarVeiculos() throws SQLException {
        return armazenamento.recuperarVeiculos();
    }

}
