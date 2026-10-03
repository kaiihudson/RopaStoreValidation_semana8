package com.duoc.ropastorevalidation.producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.duoc.ropastorevalidation.producto.model.Producto;
import com.duoc.ropastorevalidation.producto.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> findById(Long id) {
        return  productoRepository.findById(id);
    }

    public List<Producto> findBySucursalId(Long sucursalId) {
        return productoRepository.findBySucursalId(sucursalId);
    }

    public Producto create(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto update(Long id, Producto producto) {
        Optional<Producto> oldProduct  = findById(id);
        if(oldProduct.isPresent()){
            oldProduct.get().setNombre(producto.getNombre());
            oldProduct.get().setPrecio(producto.getPrecio());
            oldProduct.get().setCategoria(producto.getCategoria());
            oldProduct.get().setStock(producto.getStock());
            if(producto.getSucursal() != null) {
                oldProduct.get().setSucursal(producto.getSucursal());
            }
            return  productoRepository.save(oldProduct.get());
        } else {
            return  null;
        }
    }
    public Long deleteById(Long id) {
        Optional<Producto> oldProduct = findById(id);
        if(oldProduct.isPresent()){
            productoRepository.deleteById(id);
            return id;
        } else {
            return null;
        }
    }
}
