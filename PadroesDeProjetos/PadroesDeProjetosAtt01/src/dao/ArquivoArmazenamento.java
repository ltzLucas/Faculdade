package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import modelo.Armazenamento;
import modelo.Carro;
import modelo.Moto;
import modelo.Veiculo;

public class ArquivoArmazenamento implements Armazenamento {
    private String nomeArquivo;

    public ArquivoArmazenamento(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    @Override
    public void adicionarVeiculo(Veiculo veiculo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
        	
            writer.write(veiculo.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Veiculo> recuperarVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Veiculo veiculo = parseVeiculoFromLine(linha);
                if (veiculo != null) {
                    veiculos.add(veiculo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return veiculos;
    }

    private Veiculo parseVeiculoFromLine(String linha) {
        // Divida a linha em campos com base na vírgula
        String[] campos = linha.split(",");

        if (campos.length < 5) {
            // A linha não possui informações suficientes para criar um veículo
            return null;
        }

        String marca = null;
        String modelo = null;
        int anoFabricacao = 0;
        double preco = 0.0;

        // Analise os campos para extrair as informações do veículo
        for (String campo : campos) {
            String[] partes = campo.trim().split(":");
            if (partes.length == 2) {
                String chave = partes[0].trim();
                String valor = partes[1].trim();

                switch (chave) {
                    case "Marca":
                        marca = valor;
                        break;
                    case "Modelo":
                        modelo = valor;
                        break;
                    case "Ano":
                        anoFabricacao = Integer.parseInt(valor);
                        break;
                    case "Preço":
                        preco = Double.parseDouble(valor);
                        break;
                    default:
                        break;
                }
            }
        }

        // Verifique se todas as informações necessárias foram obtidas
        if (marca != null && modelo != null && anoFabricacao != 0 && preco != 0.0) {
            // Determine se é um carro ou motocicleta com base nas informações disponíveis
            if (linha.contains("Número de Portas")) {
                // É um carro
                int numeroPortas = 0; // Implemente a lógica para extrair o número de portas
                return new Carro(marca, modelo, anoFabricacao, preco, numeroPortas);
            } else if (linha.contains("Cilindradas")) {
                // É uma motocicleta
                int cilindradas = 0; // Implemente a lógica para extrair as cilindradas
                return new Moto(marca, modelo, anoFabricacao, preco, cilindradas);
            }
        }

        return null;
    }
}