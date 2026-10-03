package com.duoc.ropastorevalidation.pedido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.duoc.ropastorevalidation.cliente.model.Cliente;
import com.duoc.ropastorevalidation.pedido.model.Estado;
import com.duoc.ropastorevalidation.pedido.model.Pedido;
import com.duoc.ropastorevalidation.pedido.repository.PedidoRepository;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> getById(Long id) {
        return pedidoRepository.findById(id);
    }
    public List<Pedido> getAllByClient(Cliente cliente) {
        return pedidoRepository.getAllByCliente(cliente);
    }

    public Pedido create(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido update(Long id, Pedido pedido) {
    Optional<Pedido> oldPedido = getById(id);
        if (oldPedido.isPresent()) {
            oldPedido.get().setEstado(pedido.getEstado());
            oldPedido.get().setFechaPedido(pedido.getFechaPedido());
            oldPedido.get().setTotal(pedido.getTotal());
            if (pedido.getCliente() != null) {
                oldPedido.get().setCliente(pedido.getCliente());
            }
            return pedidoRepository.save(oldPedido.get());
        } else {
            return null;
        }
    }
    public Pedido updateStatusById(Long id, Estado estado) {
        Optional<Pedido> oldPedido = getById(id);
        if (oldPedido.isPresent()) {
            oldPedido.get().setEstado(estado);
            return pedidoRepository.save(oldPedido.get());
        } else  {
            return null;
        }
    }

    public Long deleteById(Long id) {
        Optional<Pedido> oldPedido = getById(id);
        if (oldPedido.isPresent()) {
            pedidoRepository.deleteById(id);
            return id;
        } else {
            return null;
        }
    }
}
