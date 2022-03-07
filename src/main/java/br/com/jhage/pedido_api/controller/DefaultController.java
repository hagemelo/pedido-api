package br.com.jhage.pedido_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.jhage.pedido_api.excecao.PedidoException;
import br.com.jhage.pedido_api.excecao.RespostaErro;

abstract class DefaultController {
	
	@ExceptionHandler(PedidoException.class)
	public ResponseEntity<RespostaErro> campoObrigatorioExceptionHandler(PedidoException ex){
		return ex.respostaErro();
	}

}
