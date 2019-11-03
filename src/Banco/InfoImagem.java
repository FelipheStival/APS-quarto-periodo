package Banco;

public class InfoImagem {
	private int id;
	private String nome;
	private double tamanho;
	private byte[] imagem;

	public InfoImagem(int id, String NomeImagem, double tamanho, byte[] imagem) {
		this.id = id;
		this.setNome(NomeImagem);
		this.setTamanho(tamanho);
		this.setImagem(imagem);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getTamanho() {
		return tamanho;
	}

	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
