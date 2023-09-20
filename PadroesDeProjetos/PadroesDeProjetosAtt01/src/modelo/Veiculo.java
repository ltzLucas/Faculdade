package modelo;

public abstract class Veiculo {
	private String marca;
	private String modelo;
	private int ano;
	private double preco;
	
	public Veiculo(String marca, String modelo, int ano, double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
    }
	
	
	
	public String getMarca() {
		return marca;
	}
	public String getModelo() {
		return modelo;
	}
	public int getAno() {
		return ano;
	}
	public double getPreco() {
		return preco;
	}
	
	public abstract String toString();
	
	

}
