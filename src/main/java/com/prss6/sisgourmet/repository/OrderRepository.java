package com.prss6.sisgourmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prss6.sisgourmet.model.Pedido;

public interface OrderRepository extends JpaRepository<Pedido, Long> {




}
