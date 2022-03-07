package br.com.jhage.pedido_api.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jhage.pedido_api.constante.StatusPedido;

/**
 * 
 * @author Alexnsander Melo
 * @since 18/02/2017
 *
 */
@Component
@Entity
@Table
public class HistoricoPedido implements JhageEntidade{
	
	private static final long serialVersionUID = 1L;
	
	@Version
	Integer versao;

	@Id
	@Column(name = "historico_ID", nullable = false)
	@SequenceGenerator(name = "hisid", sequenceName = "GEN_historicopedido_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hisid")
	private Long id;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id", referencedColumnName = "pedido_id")
	private Pedido pedido;
	
	@JsonFormat(pattern="dd/MM/YYYY HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ocorrencia;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	
	HistoricoPedido(){
		
	}
	
	public HistoricoPedido(Pedido pedido){
	
		this.pedido = pedido;
		this.ocorrencia = new Date();
		this.status = pedido.getStatus();
		this.id =new Long(0);
	}

	@Override
	public Long getId() {

		return this.id;
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public Date getOcorrencia() {
		return ocorrencia;
	}

	public StatusPedido getStatus() {
		return status;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((this.id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof HistoricoPedido)) {
			return false;
		}
		HistoricoPedido other = (HistoricoPedido) obj;

		return super.equals(obj) 
				&&  this.id.equals(other.id);
	}
}
