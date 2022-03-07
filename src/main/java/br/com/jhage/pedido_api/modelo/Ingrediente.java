package br.com.jhage.pedido_api.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import br.com.jhage.pedido_api.constante.Status;
import br.com.jhage.pedido_api.constante.ValoresConstante;

/**
 * 
 * @author Alexnsander Melo
 * @since 21/04/2018
 *
 */
@Entity
@Table
public class Ingrediente  implements JhageEntidade{
	
	private static final long serialVersionUID = 1L;
	
	@Version
	Integer versao;

	@Id
	@Column(name = "ingrediente_id", nullable = false)
	@SequenceGenerator(name = "ingredienteid", sequenceName = "GEN_ingrediente_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredienteid")
	private Long id;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	Ingrediente (){}
	
	public Ingrediente(String descricao) {
		
		this.descricao = descricao;
		this.status = Status.ATIVO;
	}
	
	@Override
	public Long getId() {

		return this.id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Status getStatus() {
		return status;
	}
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((this.id == null) ? ValoresConstante.ZERO : id.hashCode());
		result = prime * result + ((this.descricao == null) ? ValoresConstante.ZERO : this.descricao.hashCode());
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
		Ingrediente other = (Ingrediente) obj;

		return super.equals(obj) 
				&&  this.id.equals(other.id)
				&&  this.descricao.equals(other.descricao);
	}

}
