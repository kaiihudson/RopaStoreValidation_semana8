package com.duoc.ropastorevalidation.sucursal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.ropastorevalidation.producto.model.Producto;
import com.duoc.ropastorevalidation.producto.service.ProductoService;
import com.duoc.ropastorevalidation.sucursal.model.Sucursal;
import com.duoc.ropastorevalidation.sucursal.service.SucursalService;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;
    private final ProductoService productoService;

    public SucursalController(SucursalService sucursalService, ProductoService productoService) {
        this.sucursalService = sucursalService;
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Sucursal>> findAll() {
        return ResponseEntity.ok(sucursalService.findAll());
    }

    @GetMapping("/{id}/inventario")
    public ResponseEntity<List<Producto>> inventario(@PathVariable Long id) {
        if (sucursalService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoService.findBySucursalId(id));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> findById(@PathVariable Long id) {
        Optional<Sucursal> sucursal = sucursalService.findById(id);
        return sucursal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sucursal> save(@RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(sucursalService.save(sucursal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> update(@PathVariable Long id, @RequestBody Sucursal sucursal) {
        Sucursal updated = sucursalService.update(id, sucursal);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        Optional<Sucursal> sucursal = sucursalService.findById(id);
        if (sucursal.isPresent()) {
            Long oldId = sucursal.get().getId();
            sucursalService.delete(id);
            return ResponseEntity.ok(oldId);
        } else return ResponseEntity.notFound().build();
    }
}
