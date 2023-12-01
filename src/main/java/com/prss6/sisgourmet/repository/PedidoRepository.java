package com.prss6.sisgourmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prss6.sisgourmet.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {


}
