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

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.jhage.pedido_api.constante.Status;
import br.com.jhage.pedido_api.helper.Helper;
import br.com.jhage.pedido_api.helper.NumberHelp;

/**
 * 
 * @author Alexnsander Melo
 * @since 15/01/2017
 *
 */
@Entity
@Table
public class Produto implements JhageEntidade{

	private static final long serialVersionUID = 1L;
	
	@Version
	Integer versao;

	@Id
	@Column(name = "PRODUTO_ID", nullable = false)
	@SequenceGenerator(name = "prdid", sequenceName = "GEN_PRODUTO_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prdid")
	private Long id;
	
	private String descricao;
	
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	Produto(){
		
	}
	
	@JsonProperty
	public String valorString(){
		
		return NumberHelp.parseDoubleToString(this.getValor());
	}
	
	@JsonProperty
	public String produto() {
		
		StringBuffer result = new StringBuffer()
				.append("R$ ")
				.append(this.valorString())
				.append(" - ")
				.append(this.getDescricao());
		return result.toString();
	}
		
	public Produto(String descricao , Double valor){
		
		this.descricao = Helper.ENULO.enulo(descricao)?"":descricao;
		this.valor = Helper.ENULO.enulo(valor)?0.:valor;
		this.status = Status.ATIVO;
	}
	
	@Override
	public Long getId() {
		
		return this.id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getValor() {
		return valor;
	}

	public Status getStatus() {
		return status;
	}

	
	public void alterarStatusParaInativa() {
		
		this.status = Status.INATIVO;
	}

	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((this.id == null) ? 0 : id.hashCode());
		result = prime * result + ((this.descricao == null) ? 0 : this.descricao.hashCode());
		result = prime * result + ((this.valor == null) ? 0 : this.valor.hashCode());
		result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Produto)) {
			return false;
		}
		Produto other = (Produto) obj;

		return super.equals(obj) 
				&&  this.id.equals(other.id)
				&&  this.descricao.equals(other.descricao)
				&&  this.valor.equals(other.valor)
				&&  this.status.equals(other.status);
	}

}
