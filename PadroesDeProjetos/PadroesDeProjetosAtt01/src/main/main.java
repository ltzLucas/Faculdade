package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDeDados;
import jdbc.ConnectionFactory;
import modelo.Carro;
import modelo.Concessionaria;
import modelo.Moto;
import modelo.Veiculo;

public class main {

	public static void main(String[] args) throws SQLException {
		
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		BancoDeDados bancoDeDados = new BancoDeDados(conexao);
		Concessionaria concessionaria = new Concessionaria(bancoDeDados);
		
		Veiculo carro = new Carro("Toyota", "Corolla", 2020, 130000, 4);
		Veiculo moto = new Moto("Yamaha", "z1000", 2020, 45000,1000 );
	
		bancoDeDados.adicionarVeiculo(carro);
		bancoDeDados.adicionarVeiculo(moto);
		
		List<Veiculo> veiculos = concessionaria.listarVeiculos();
		
		// Exiba os veículos
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo em estoque.");
        } else {
            System.out.println("Veículos em estoque:");
            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo);
                System.out.println("---------------");
            }
        }
		

	}

}
