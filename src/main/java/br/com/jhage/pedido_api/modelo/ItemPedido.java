package br.com.jhage.pedido_api.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.jhage.pedido_api.constante.ValoresConstante;
import br.com.jhage.pedido_api.helper.Helper;
import br.com.jhage.pedido_api.helper.NumberHelp;

/**
 * 
 * @author Alexnsander Melo
 * @since 15/01/2017
 *
 */

@Component
@Entity
@Table
public class ItemPedido implements JhageEntidade{
	
private static final long serialVersionUID = 1L;
	
	@Version
	Integer versao;

	@Id
	@Column(name = "itempedido_ID", nullable = false)
	@SequenceGenerator(name = "itenid", sequenceName = "GEN_itempedido_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itenid")
	private Long id;
	
	private Integer quantidade;
	
	private Double valor;
	
	private String descricao;
	
	@JsonBackReference
	@ManyToOne
	private Pedido pedido;
	
	ItemPedido(){
		
	}
	
	public ItemPedido(String descricao, Double valor, Integer quantidade){
		
		this.quantidade = Helper.ENULO.enulo(quantidade)?ValoresConstante.UM:quantidade;
		this.valor 		= Helper.ENULO.enulo(valor)?ValoresConstante.DOUBLE_ZERO:valor;
		this.descricao 	= Helper.ENULO.enulo(descricao)?ValoresConstante.STRING_VAZIO:descricao;
	}
	
	@Override
	public Long getId() {

		return this.id;
	}
	
    public ItemPedido comPedido(Pedido ped){
    	
    	if(!Helper.ENULO.enulo(ped)){
    		this.pedido = ped;
    	}
		return this;
	}
    
    @JsonProperty
	public String totalString(){
		
		return NumberHelp.parseDoubleToString(this.total());
	}
    
    @JsonProperty
	public String valorString(){
		
		return NumberHelp.parseDoubleToString(this.getValor());
	}
    
	public Double total() {
		
		Double result =ValoresConstante.DOUBLE_ZERO;
		if (this.isOKQuantidadeValor()){
			result =  this.getValor() * this.getQuantidade();
		}
		return result;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public Pedido getPedido() {
		return pedido;
	}

	private boolean isOKQuantidadeValor(){
		
		return !Helper.ENULO.enulo(this.valor) 
				&& !Helper.ENULO.enulo(this.quantidade) 
				&& Double.doubleToRawLongBits(this.valor)!=ValoresConstante.ZERO;
	}
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((this.id == null) ? 0 : id.hashCode());
		result = prime * result + ((this.quantidade == null) ? 0 : this.quantidade.hashCode());
		result = prime * result + ((this.descricao == null) ? 0 : this.descricao.hashCode());
		result = prime * result + ((this.valor == null) ? 0 : this.valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ItemPedido)) {
			return false;
		}
		ItemPedido other = (ItemPedido) obj;

		return super.equals(obj) 
				&&  this.id.equals(other.id)
				&&  this.quantidade.equals(other.quantidade)
				&&  this.descricao.equals(other.descricao)
				&&  this.valor.equals(other.valor);
	}
}
