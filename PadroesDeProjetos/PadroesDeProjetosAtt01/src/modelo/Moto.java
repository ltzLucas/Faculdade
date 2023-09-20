package modelo;

public class Moto extends Veiculo {
	private int cilindradas;

	public Moto(String marca, String modelo, int ano, double preco, int cilindradas) {
		super(marca, modelo, ano, preco);
		this.cilindradas = cilindradas;
	}

	 public int getCilindradas() {
	        return cilindradas;
	    }

	 @Override
	    public String toString() {
	        return "Tipo: Motocicleta\n" +
	               "Marca: " + getMarca() + "\n" +
	               "Modelo: " + getModelo() + "\n" +
	               "Ano de Fabricação: " + getAno() + "\n" +
	               "Preço: " + getPreco() + "\n" +
	               "Cilindradas: " + cilindradas;
	    }
}
