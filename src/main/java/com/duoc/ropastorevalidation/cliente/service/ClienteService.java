package com.duoc.ropastorevalidation.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.duoc.ropastorevalidation.cliente.model.Cliente;
import com.duoc.ropastorevalidation.cliente.repository.ClienteRepository;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getByRut(String rut) {
        return clienteRepository.findByRut(rut);
    }

    public Optional<Cliente> getById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente cliente) {
        Optional<Cliente> oldCliente = getById(id);
        if (oldCliente.isPresent()) {
            oldCliente.get().setRut(cliente.getRut());
            oldCliente.get().setNombre(cliente.getNombre());
            oldCliente.get().setCorreo(cliente.getCorreo());
            oldCliente.get().setDireccion(cliente.getDireccion());
            oldCliente.get().setTelefono(cliente.getTelefono());
            return clienteRepository.save(oldCliente.get());
        } else {
            return null;
        }
    }

    public Long deleteById(Long id) {
        Optional<Cliente> oldCliente = getById(id);
        if (oldCliente.isPresent()) {
            clienteRepository.deleteById(id);
            return id;
        } else {
            return null;
        }
    }
}
