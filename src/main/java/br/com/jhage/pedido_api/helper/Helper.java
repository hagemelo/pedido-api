package br.com.jhage.pedido_api.helper;

import java.util.function.Consumer;
import java.util.function.Predicate;

import br.com.jhage.pedido_api.modelo.JhageEntidade;
import br.com.jhage.pedido_api.modelo.Pedido;

public enum Helper implements Predicate<JhageEntidade>, Consumer<JhageEntidade>{

	ENULO, NAO_E_NULO, NAO_E_NULO_E_NAO_E_VAZIO, PEDIDO_FINALIZADO;

	@Override
	public boolean test(JhageEntidade t) {

		switch (this) {

		case ENULO:
			return enulo(t);
		
		case NAO_E_NULO:
			return !enulo(t);
		
		case PEDIDO_FINALIZADO:
			return pedidoFinalizado((Pedido)t);

		case NAO_E_NULO_E_NAO_E_VAZIO:
			// return naoENuloENaoEVazio(t);

		default:
			break;
		}
		return false;
	}

	@Override
	public void accept(JhageEntidade t) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean enulo(Object object) {

		if (object == null) {
			return true;
		}
		return false;
	}
	
	private boolean pedidoFinalizado(Pedido pedido){
		
		return !this.enulo(pedido) && pedido.isRealizado();
	}

	

	// public boolean naoENuloENaoEVazio(final Object... object) {
	//
	// boolean result = true;
	// if (this.enulo(object)) {
	// result = false;
	// } else {
	// for (final String obj : (String)object) {
	// result = result && !this.enulo(obj) && !obj.isEmpty();
	// }
	// }
	// return result;
	// }
}
