package br.com.jhage.pedido_api.modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import br.com.jhage.pedido_api.constante.ValoresConstante;
import br.com.jhage.pedido_api.helper.Helper;
import br.com.jhage.pedido_api.helper.NumberHelp;

/**
 * 
 * @author Alexnsander Melo
 * @since 21/04/2018
 *
 */
@Entity
@Table
public class Compra implements JhageEntidade {

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "compar_id", nullable = false)
	@SequenceGenerator(name = "compraid", sequenceName = "GEN_compra_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compraid")
	private Long id;

	@JsonFormat(pattern = "dd/MM/YYYY")
	@Temporal(TemporalType.DATE)
	private Date ocorrencia;

	private String localCompra;

	@JsonBackReference
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<ItemCompra> itens;

	Compra() {
	}

	public Compra(String localCompra, Date ocorrencia) {

		this.localCompra = Helper.ENULO.enulo(localCompra) ? ValoresConstante.STRING_VAZIO : localCompra;
		this.ocorrencia = Helper.ENULO.enulo(ocorrencia) ? new Date() : ocorrencia;
		this.itens = new HashSet<ItemCompra>();
	}

	@JsonProperty
	public String totalString() {

		return NumberHelp.parseDoubleToString(this.total());
	}

	public Double total() {

		return itens.stream().filter(Helper.NAO_E_NULO).mapToDouble(ItemCompra::getValor).sum();
	}

	public void addItemCompra(ItemCompra item) {

		this.getItens().add(item);
	}

	@Override
	public Long getId() {

		return this.id;
	}

	public Date getOcorrencia() {
		return ocorrencia;
	}

	public String getLocalCompra() {
		return localCompra;
	}

	public Set<ItemCompra> getItens() {

		if (Helper.ENULO.enulo(this.itens)) {

			this.itens = new HashSet<ItemCompra>();
		}
		return itens;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? ValoresConstante.ZERO : id.hashCode());
		result = prime * result + ((this.localCompra == null) ? ValoresConstante.ZERO : this.localCompra.hashCode());
		result = prime * result + ((this.ocorrencia == null) ? ValoresConstante.ZERO : this.ocorrencia.hashCode());
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
		Compra other = (Compra) obj;

		return super.equals(obj) && this.id.equals(other.id) && this.localCompra.equals(other.localCompra)
				&& this.ocorrencia.equals(other.ocorrencia);
	}

}
