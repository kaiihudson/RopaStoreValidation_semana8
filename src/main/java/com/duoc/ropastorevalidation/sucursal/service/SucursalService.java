package com.duoc.ropastorevalidation.sucursal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.duoc.ropastorevalidation.sucursal.model.Sucursal;
import com.duoc.ropastorevalidation.sucursal.repository.SucursalRepository;

@Service
public class SucursalService {
    private final SucursalRepository sucursalRepository;
    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    public Optional<Sucursal> findById(Long id) {
        return sucursalRepository.findById(id);
    }

    public Sucursal update(Long id, Sucursal sucursal) {
        Optional<Sucursal> oldSucursal = findById(id);
        if (oldSucursal.isPresent()) {
            oldSucursal.get().setNombre(sucursal.getNombre());
            oldSucursal.get().setDireccion(sucursal.getDireccion());
            oldSucursal.get().setCiudad(sucursal.getCiudad());
            return sucursalRepository.save(oldSucursal.get());
        } else {
            return null;
        }
    }

    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public void delete(Long id) {
        sucursalRepository.deleteById(id);
    }

}
