package net.ausiasmarch.contante.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.TipoasientoEntity;

import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.TipoasientoRepository;

@Service
public class TipoasientoService implements ServiceInterface<TipoasientoEntity> {

    @Autowired
    private TipoasientoRepository oTipoAsientoRepository;

    @Autowired
    RandomService oRandomService;

    private String[] arrDescripciones = {
            "Ingreso", "Gasto", "Inversión", "Financiación", "Dividendo", "Impuesto",
            "Regularización", "Cierre", "Apertura", "Compra", "Venta", "Alquiler", "Préstamo", "Devolución", "Intereses"
    };

    public Long randomCreate(Long cantidad) {
        // for each element in the array arrDescripciones, create a new
        // TipoasientoEntity object and save it to the database
        // for each element in the array arrDescripciones, create a new
        // TipoasientoEntity object and save it to the database
        for (String descripcion : arrDescripciones) {
            TipoasientoEntity oTipoAsientoEntity = new TipoasientoEntity();
            oTipoAsientoEntity.setDescripcion(descripcion);
            oTipoAsientoRepository.save(oTipoAsientoEntity);
        }
        return oTipoAsientoRepository.count();

    }

    public Page<TipoasientoEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oTipoAsientoRepository
                    .findByDescripcionContaining(
                            filter.get(), oPageable);
        } else {
            return oTipoAsientoRepository.findAll(oPageable);
        }
    }

    public Page<TipoasientoEntity> getPageByBalance(Pageable oPageable, Long id, Optional<String> filter) {
        return oTipoAsientoRepository.findByBalance(oPageable, id, filter);
    }

    public Page<TipoasientoEntity> getTipoasientoSinRelacionar(Long id, Pageable oPageable, Optional<String> filter) {
        return oTipoAsientoRepository.getTipoasientoSinRelacionar(id, oPageable, filter);
    }

    public TipoasientoEntity get(Long id) {
        return oTipoAsientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoAsiento no encontrado"));
        // return oUsuarioRepository.findById(id).get();
    }

    public Long count() {
        return oTipoAsientoRepository.count();
    }

    public Long delete(Long id) {
        oTipoAsientoRepository.deleteById(id);
        return 1L;
    }

    public TipoasientoEntity create(TipoasientoEntity oTipoAsientoEntity) {
        return oTipoAsientoRepository.save(oTipoAsientoEntity);
    }

    public TipoasientoEntity update(TipoasientoEntity oTipoAsientoEntity) {
        TipoasientoEntity oTipoAsientoEntityFromDatabase = oTipoAsientoRepository.findById(oTipoAsientoEntity.getId())
                .get();
        if (oTipoAsientoEntity.getDescripcion() != null) {
            oTipoAsientoEntityFromDatabase.setDescripcion(oTipoAsientoEntity.getDescripcion());
        }
        return oTipoAsientoRepository.save(oTipoAsientoEntityFromDatabase);
    }

    public Long deleteAll() {
        oTipoAsientoRepository.deleteAll();
        return this.count();
    }

    public TipoasientoEntity randomSelection() {
        return oTipoAsientoRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oTipoAsientoRepository.count() - 1)));
    }

    public Long addRelation(Long idTipoasiento, Long idBalance) {
        int rowsAdded = oTipoAsientoRepository.addRelation(idTipoasiento, idBalance);
        return (long) rowsAdded;
    }

    public Long deleteRelation(Long idTipoasiento, Long idBalance) {
        int rowsDeleted = oTipoAsientoRepository.deleteRelation(idTipoasiento, idBalance);
        return (long) rowsDeleted;
    }
}
