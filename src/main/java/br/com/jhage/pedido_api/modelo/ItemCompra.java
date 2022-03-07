package br.com.jhage.pedido_api.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.jhage.pedido_api.constante.Unidade;
import br.com.jhage.pedido_api.constante.ValoresConstante;
import br.com.jhage.pedido_api.helper.NumberHelp;

/**
 * 
 * @author Alexnsander Melo
 * @since 21/04/2018
 *
 */
@Entity
@Table
public class ItemCompra implements JhageEntidade{
	
	private static final long serialVersionUID = 1L;
	
	@Version
	Integer versao;

	@Id
	@Column(name = "compar_id", nullable = false)
	@SequenceGenerator(name = "compraid", sequenceName = "GEN_compra_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compraid")
	private Long id;
	
	@JsonBackReference
	@OneToOne
	private Ingrediente ingrediente;
	
	@Enumerated(EnumType.STRING)
	private Unidade unidade;
	
	@JsonBackReference
	@ManyToOne
	private Compra compra;
	
	private Double valor;
		
	ItemCompra(){}
	
	public ItemCompra( Double valor) {
		
		this.valor = valor;
		this.unidade = Unidade.KG;
	}
	
	public ItemCompra addIngrediente(Ingrediente ingrediente) {
		
		this.ingrediente = ingrediente;
		return this;
	}
	
	public ItemCompra addCompra(Compra compra) {
		
		this.compra = compra;
		return this;
	}

	@JsonProperty
	public String valorString(){
		
		return NumberHelp.parseDoubleToString(this.getValor());
	}
	
	@Override
	public Long getId() {

		return this.id;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public Compra getCompra() {
		return compra;
	}

	public Double getValor() {
		return valor;
	}
	
	public Unidade getUnidade() {
		return unidade;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((this.id == null) ? ValoresConstante.ZERO : id.hashCode());
		result = prime * result + ((this.valor == null) ? ValoresConstante.ZERO : this.valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Pedido)) {
			return false;
		}
		ItemCompra other = (ItemCompra) obj;

		return super.equals(obj) 
				&&  this.id.equals(other.id)
				&&  this.valor.equals(other.valor);
	}
}
