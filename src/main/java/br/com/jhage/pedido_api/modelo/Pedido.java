package br.com.jhage.pedido_api.modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.jhage.pedido_api.constante.StatusPedido;
import br.com.jhage.pedido_api.constante.ValoresConstante;
import br.com.jhage.pedido_api.helper.Helper;
import br.com.jhage.pedido_api.helper.NumberHelp;
import br.com.jhage.pedido_api.listen.PedidoListen;

/**
 * 
 * @author Alexnsander Melo
 * @since 15/01/2017
 *
 */
@Entity
@Table
@EntityListeners({ PedidoListen.class })
public class Pedido implements JhageEntidade{
	
	private static final long serialVersionUID = 1L;
	
	@Version
	Integer versao;

	@Id
	@Column(name = "pedido_ID", nullable = false)
	@SequenceGenerator(name = "pedid", sequenceName = "GEN_pedido_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedid")
	private Long id;
	
	private String contato;
	
	private String entrega;
	
	private Double troco;
	
	private Double desconto;
	
	@JsonFormat(pattern="dd/MM/YYYY")
	@Temporal(TemporalType.TIMESTAMP)
	private Date cadastro;
	
	@JsonProperty
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@JsonBackReference
	@OneToMany(mappedBy = "pedido", cascade= CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
	private Set<ItemPedido> itens;
	
	Pedido(){
		
	}
	
	public Pedido(String contato , String entrega,  Double troco, Double desconto){
	
		this.contato = Helper.ENULO.enulo(contato)?ValoresConstante.STRING_VAZIO:contato;
		this.entrega = Helper.ENULO.enulo(entrega)?ValoresConstante.STRING_VAZIO:entrega;
		this.troco = Helper.ENULO.enulo(troco)?ValoresConstante.DOUBLE_ZERO:troco;
		this.desconto = Helper.ENULO.enulo(desconto)?ValoresConstante.DOUBLE_ZERO:desconto;
		this.status = StatusPedido.REALIZADO;
		this.itens = new HashSet<ItemPedido>();
		this.cadastro = new Date();
		this.id =new Long(0);
	}
	
	@Override
	public Long getId() {

		return this.id;
	}
	
	@JsonProperty
	public String totalString(){
		
		return  NumberHelp.parseDoubleToString(this.total() == null? ValoresConstante.DOUBLE_ZERO: this.total());
		
	}
	
	@JsonProperty
	public String trocoString() {
		
		return NumberHelp.parseDoubleToString(this.troco);
	}
	
	@JsonProperty
	public String descontoString() {
		
		return NumberHelp.parseDoubleToString(this.getDesconto());
	}
	
	public Double total(){
		
		Double total =  itens.stream().filter(Helper.NAO_E_NULO).mapToDouble(ItemPedido::total).sum();
		total = total - this.getDesconto();
		return total;
	}
	
	public void addItemPedido(ItemPedido item) {
		
		this.getItens().add(item);
	}
	
	@JsonProperty
	public boolean isRealizado(){
		
		return StatusPedido.REALIZADO.equals(this.status);
	}
	
	@JsonProperty
	public boolean isPronto(){
		
		return StatusPedido.PRONTO.equals(this.status);
	}
	
	@JsonProperty
	public boolean isEntregue(){
		
		return StatusPedido.ENTREGUE.equals(this.status);
	}
	
	public void setStatus(StatusPedido status){
		
		this.status = status;
	}
	
	public String getContato() {
		
		return contato;
	}

	public String getEntrega() {
		
		return entrega;
	}

	public Double getTroco() {
		
		return troco;
	}
	
	public Double getDesconto() {
		
		
		if (Helper.ENULO.enulo(this.desconto)) {
			this.desconto = ValoresConstante.DOUBLE_ZERO;
		}
		return desconto;
	}

	public StatusPedido getStatus() {
		
		return status;
	}

	public Set<ItemPedido> getItens() {
		
		if (Helper.ENULO.enulo(this.itens)) {
			
			this.itens = new HashSet<ItemPedido>();
		}
		return itens;
	}
	
	public Date getCadastro() {
		
		return cadastro;
	}
	
	public void tratarNull(){
		
		this.tratarNUllContato();
		this.tratarNUllEntrega();
		this.tratarNUllTroco();
		this.tratarNUllCadastro();
		this.tratarNUllStatus();
		this.tratarNullItens();
		this.tratarNUllDesconto();
	}
	
	private void tratarNUllContato(){
	
		if (Helper.ENULO.enulo(this.contato)){
			
			this.contato = ValoresConstante.STRING_VAZIO;
		}
	}
	
	private void tratarNUllEntrega(){
		
		if (Helper.ENULO.enulo(this.entrega)){
			
			this.entrega = ValoresConstante.STRING_VAZIO;
		}
	}
	
	private void tratarNullItens() {
		
		Pedido p = this;
		if (!Helper.ENULO.enulo(itens)) {
			itens.stream().filter(i-> Helper.ENULO.enulo(i.getPedido())).forEach(i -> i.comPedido(p));
		}
		else {
			this.itens = new HashSet<ItemPedido>();
		}
	}

	private void tratarNUllTroco(){

		if (Helper.ENULO.enulo(this.troco)){
			
			this.troco = ValoresConstante.DOUBLE_ZERO;
		}
	}
	
	private void tratarNUllDesconto(){

		if (Helper.ENULO.enulo(this.desconto)){
			
			this.desconto = ValoresConstante.DOUBLE_ZERO;
		}
	}
	
	private void tratarNUllCadastro(){
		
		if (Helper.ENULO.enulo(this.cadastro)){
			
			this.cadastro = new Date();
		}
	}
	
	private void tratarNUllStatus(){
		
		if (Helper.ENULO.enulo(this.status)){
			
			this.status = StatusPedido.REALIZADO;
		}
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((this.id == null) ? ValoresConstante.ZERO : id.hashCode());
		result = prime * result + ((this.contato == null) ? ValoresConstante.ZERO : this.contato.hashCode());
		result = prime * result + ((this.troco == null) ? ValoresConstante.ZERO : this.troco.hashCode());
		result = prime * result + ((this.desconto == null) ? ValoresConstante.ZERO : this.desconto.hashCode());
		result = prime * result + ((this.entrega == null) ? ValoresConstante.ZERO : this.entrega.hashCode());
		result = prime * result + ((this.cadastro == null) ? ValoresConstante.ZERO : this.cadastro.hashCode());
		result = prime * result + ((this.status == null) ? ValoresConstante.ZERO : this.status.hashCode());
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
		Pedido other = (Pedido) obj;

		return super.equals(obj) 
				&&  this.id.equals(other.id)
				&&  this.contato.equals(other.contato)
				&&  this.entrega.equals(other.entrega)
				&&  this.status.equals(other.status)
				&&  this.cadastro.equals(other.cadastro);
	}
	
}
