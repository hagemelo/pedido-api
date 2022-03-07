package br.com.jhage.pedido_api.excecao;

public class FormatarDataParaPadraoException extends PedidoException{
	
	private static final long serialVersionUID = 1L;

	private final static String MENSAGEM = "Erro ao Formatar Data Para Padrao";

	public FormatarDataParaPadraoException(){
		
		super(MENSAGEM);
	}
}
