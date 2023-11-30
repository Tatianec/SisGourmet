package com.prss6.sisgourmet.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.prss6.sisgourmet.model.PedidoProduct;
import com.prss6.sisgourmet.service.PedidoProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/pedido_product")
public class PedidoProductResource {

    @Autowired
    private PedidoProductService pedidoProductService;

    @PostMapping
    public ResponseEntity<PedidoProduct> createPedidoProduct(@RequestBody PedidoProduct pedidoProduct) {
        PedidoProduct savedPedidoProduct = pedidoProductService.savePedidoProduct(pedidoProduct);
        return ResponseEntity.ok(savedPedidoProduct);
    }
    
    

    @PutMapping("/{id}")
    public ResponseEntity<PedidoProduct> updatePedidoProduct(@PathVariable Long id,
            @RequestBody PedidoProduct pedidoProduct) {
        PedidoProduct updatedPedidoProduct = pedidoProductService.update(id, pedidoProduct);
        return ResponseEntity.ok(updatedPedidoProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoProduct(@PathVariable Long id) {
        pedidoProductService.deletePedidoProduct(id);
        return ResponseEntity.noContent().build();
    }
}
