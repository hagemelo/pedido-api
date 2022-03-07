package br.com.jhage.pedido_api.listen;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.jhage.pedido_api.modelo.Pedido;


public class PedidoListen {
	
	@PreUpdate
	public void preUpdate(Object obj){
		
		((Pedido)obj).tratarNull();
	}
	
	@PrePersist
	public void prePersist(Object obj){
		
		((Pedido)obj).tratarNull();
	}

}
