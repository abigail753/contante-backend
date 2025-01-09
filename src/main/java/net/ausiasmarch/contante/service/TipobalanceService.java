package net.ausiasmarch.contante.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.TipobalanceEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.TipobalanceRepository;


@Service
public class TipobalanceService implements ServiceInterface<TipobalanceEntity> {

    @Autowired
    TipobalanceRepository oTipobalanceRepository;

    @Autowired
    RandomService oRandomService;

    public Long randomCreate(Long cantidad) {
        this.create(new TipobalanceEntity("Balance de estado de cuentas"));
        this.create(new TipobalanceEntity("Balance de final de ejercicio"));
        this.create(new TipobalanceEntity("Balance de situación"));
        this.create(new TipobalanceEntity("Balance de comprobación"));        
        return oTipobalanceRepository.count();
    }

    public Page<TipobalanceEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oTipobalanceRepository
                    .findByDescripcionContaining(filter.get(), oPageable);
        } else {
            return oTipobalanceRepository.findAll(oPageable);
        }
    }

    public TipobalanceEntity get(Long id) {
        return oTipobalanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        // return oTipobalanceRepository.findById(id).get();
    }

    public Long count() {
        return oTipobalanceRepository.count();
    }

    public Long delete(Long id) {
        oTipobalanceRepository.deleteById(id);
        return 1L;
    }

    public TipobalanceEntity create(TipobalanceEntity oTipobalanceEntity) {
        return oTipobalanceRepository.save(oTipobalanceEntity);
    }

    public TipobalanceEntity update(TipobalanceEntity oTipobalanceEntity) {
        TipobalanceEntity oTipobalanceEntityFromDatabase = oTipobalanceRepository.findById(oTipobalanceEntity.getId())
                .get();
        if (oTipobalanceEntity.getDescripcion() != null) {
            oTipobalanceEntityFromDatabase.setDescripcion(oTipobalanceEntity.getDescripcion());
        }
        return oTipobalanceRepository.save(oTipobalanceEntityFromDatabase);
    }

    public Long deleteAll() {
        oTipobalanceRepository.deleteAll();
        return this.count();
    }

    public TipobalanceEntity randomSelection() {
        return oTipobalanceRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oTipobalanceRepository.count() - 1)));
    }

}