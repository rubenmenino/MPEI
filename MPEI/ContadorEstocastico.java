package ProjetoMPEI_88867_89185.MPEI;

public class ContadorEstocastico {
	private double probabilidade;
	private int contador;
	
	public ContadorEstocastico(double probabilidade) {
		this.probabilidade = probabilidade;
		this.contador = 0;
	}
	
	public void incrementar() {
		if(Math.random() < (this.probabilidade / 100)) {
			this.contador++;
		}
	}
	
	public int contador() {
		return this.contador;
	}

}