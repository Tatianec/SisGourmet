package com.prss6.sisgourmet.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.prss6.sisgourmet.model.Pedido;
import com.prss6.sisgourmet.model.Product;
import com.prss6.sisgourmet.repository.DeskRepository;
import com.prss6.sisgourmet.repository.EmployeeRepository;
import com.prss6.sisgourmet.repository.PedidoRepository;
import com.prss6.sisgourmet.repository.ProductRepository;

import jakarta.validation.ValidationException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeRepository employeeRepository;  

    @Autowired
    private DeskRepository deskRepository;  

    public Pedido savePedido(Pedido pedido, List<Long> productIds) {
        List<Product> productList = productRepository.findAllById(productIds);
        pedido.setProducts(productList);
        return pedidoRepository.save(pedido);
    }


    public Pedido update(Long id, Pedido pedido) {
        Pedido pedidoSaved = findPedidoById(id);
        BeanUtils.copyProperties(pedido, pedidoSaved, "id");
        return pedidoRepository.save(pedidoSaved);
    }

    public Pedido findPedidoById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }
}
