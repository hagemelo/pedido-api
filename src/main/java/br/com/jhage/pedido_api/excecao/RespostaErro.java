package br.com.jhage.pedido_api.excecao;

public class RespostaErro {

	private String mensagem;

	public RespostaErro() {
	}

	public RespostaErro(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
