package main.java.enuns;

public enum TipoDoProduto {
	VIOLAO(0),
	GUITARRA(1),
	VIOLINO(2),
	CAVACO(3);

	private int instrumento;
	
	TipoDoProduto(int instrumento) {
		this.instrumento = instrumento;
	}
	
	public int getInstrumento() {
		return this.instrumento;
	}
}
