package main;


import java.sql.SQLException;
import java.util.List;

import dao.ArquivoArmazenamento;
import modelo.Carro;

import modelo.Moto;
import modelo.Veiculo;

public class main2 {

	public static void main(String[] args) throws SQLException {
		
		 // Caminho do arquivo onde os veículos serão armazenados
        String caminhoArquivo = "./Arquivo01.txt";

        // Crie uma instância de ArquivoArmazenamento
        ArquivoArmazenamento arquivoArmazenamento = new ArquivoArmazenamento(caminhoArquivo);

        // Crie alguns veículos para armazenar
        Carro carro1 = new Carro("Toyota", "Corolla", 2020, 25000.0, 4);
        Moto moto1 = new Moto("Honda", "CBR500R", 2021, 8000.0, 471);

        // Adicione os veículos ao arquivo de armazenamento
        arquivoArmazenamento.adicionarVeiculo(carro1);
        arquivoArmazenamento.adicionarVeiculo(moto1);

        // Recupere a lista de veículos do arquivo
        List<Veiculo> veiculos = arquivoArmazenamento.recuperarVeiculos();

        // Exiba os veículos recuperados
        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo.toString());
            System.out.println("---------------");
        }
//
	}

}
