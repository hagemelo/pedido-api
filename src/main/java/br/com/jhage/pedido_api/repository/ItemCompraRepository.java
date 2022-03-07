package br.com.jhage.pedido_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhage.pedido_api.modelo.ItemCompra;

public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long>{

}
