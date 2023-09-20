package modelo;

public class Carro extends Veiculo {
	private int nPortas;
	
	
	public Carro(String marca, String modelo, int ano, double preco, int numeroPortas) {
        super(marca, modelo, ano, preco);
        this.nPortas = numeroPortas;
    }
	
	
	public int getNumeroPortas() {
        return nPortas;
    }


	public void setnPortas(int nPortas) {
		this.nPortas = nPortas;
	}


	@Override
    public String toString() {
        return "Tipo: Carro\n" +
               "Marca: " + getMarca() + "\n" +
               "Modelo: " + getModelo() + "\n" +
               "Ano de Fabricação: " + getAno() + "\n" +
               "Preço: " + getPreco() + "\n" +
               "Número de Portas: " + nPortas;
    }
	
	

}
