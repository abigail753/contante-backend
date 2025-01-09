package net.ausiasmarch.contante.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.ausiasmarch.contante.entity.AsientoEntity;
import net.ausiasmarch.contante.entity.PeriodoEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.AsientoRepository;

@Service
public class AsientoService implements ServiceInterface<AsientoEntity> {

    @Autowired
    AsientoRepository oAsientoRepository;

    @Autowired
    TipoasientoService oTipoasientoService;

    @Autowired
    UsuarioService oUsuarioService;

    @Autowired
    PeriodoService oPeriodoService;

    @Autowired
    RandomService oRandomService;

    String[] arrDescripciones = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Nullam varius, turpis et commodo pharetra.",
            "Maecenas suscipit, mauris nec venenatis commodo, est erat pretium ante, id molestie eros magna at orci."
    };

    String[] arrComentarios = {
            "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.",
            "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium.",
            "Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui.",
            "Quisque id odio. Praesent mauris. Fusce neque. In est risus, auctor sed, tristique in, tempus sit amet, sem.",
            "Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae.",
            "Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Curabitur ullamcorper ultricies nisi."
    };

    @Override
    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            AsientoEntity oAsientoEntity = new AsientoEntity();
            oAsientoEntity
                    .setDescripcion(arrDescripciones[oRandomService.getRandomInt(0, arrDescripciones.length - 1)]);
            oAsientoEntity.setComentarios(arrComentarios[oRandomService.getRandomInt(0, arrComentarios.length - 1)]);
            oAsientoEntity.setInventariable(oRandomService.getRandomInt(0, 1));
            oAsientoEntity.setMomentstamp(LocalDateTime.now());
            oAsientoEntity.setTipoasiento(oTipoasientoService.randomSelection());
            oAsientoEntity.setUsuario(oUsuarioService.randomSelection());
            oAsientoEntity.setPeriodo(oPeriodoService.randomSelection());
            oAsientoRepository.save(oAsientoEntity);
        }
        return oAsientoRepository.count();
    }

    @Override
    public Page<AsientoEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oAsientoRepository
                    .findByDescripcionContainingOrComentariosContaining(
                            filter.get(), filter.get(),
                            oPageable);
        } else {
            return oAsientoRepository.findAll(oPageable);
        }
    }

    public Page<AsientoEntity> getPageXUsuario(Pageable oPageable, Optional<String> filter, Optional<Long> id_usuario) {
        if (filter.isPresent()) {
            if (id_usuario.isPresent()) {
                return oAsientoRepository
                        .findByUsuarioIdAndDescripcionContainingOrComentariosContaining(
                                filter.get(), filter.get(), id_usuario.get(),
                                oPageable);
            } else {
                throw new ResourceNotFoundException("Usuario no encontrado");
            }
        } else {
            if (id_usuario.isPresent()) {
                return oAsientoRepository.findByUsuarioId(id_usuario.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Usuario no encontrado");
            }
        }
    }

    public Page<AsientoEntity> getPageXTipoasiento(Pageable oPageable, Optional<String> filter,
            Optional<Long> id_tipoasiento) {
        if (filter.isPresent()) {
            if (id_tipoasiento.isPresent()) {
                return oAsientoRepository
                        .findByTipoasientoIdAndDescripcionContainingOrComentariosContaining(
                                filter.get(), filter.get(), id_tipoasiento.get(),
                                oPageable);
            } else {
                throw new ResourceNotFoundException("Tipo de Asiento no encontrado");
            }
        } else {
            if (id_tipoasiento.isPresent()) {
                return oAsientoRepository.findByTipoasientoId(id_tipoasiento.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Tipo de Asiento no encontrado");
            }
        }
    }

    public Page<AsientoEntity> getPageXPeriodo(Pageable oPageable, Optional<String> filter, Optional<Long> id_periodo) {
        if (filter.isPresent()) {
            if (id_periodo.isPresent()) {
                return oAsientoRepository
                        .findByPeriodoIdAndDescripcionContainingOrComentariosContaining(
                                filter.get(), filter.get(), id_periodo.get(),
                                oPageable);
            } else {
                throw new ResourceNotFoundException("Periodo no encontrado");
            }
        } else {
            if (id_periodo.isPresent()) {
                return oAsientoRepository.findByPeriodoId(id_periodo.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Periodo no encontrado");
            }
        }
    }

    @Override
    public AsientoEntity get(Long id) {
        return oAsientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asiento no encontrado"));
    }

    @Override
    public Long count() {
        return oAsientoRepository.count();

    }

    @Override
    public Long delete(Long id) {
        oAsientoRepository.deleteById(id);
        return 1L;
    }

    @Override
    public AsientoEntity create(AsientoEntity oAsientoEntity) {
        return oAsientoRepository.save(oAsientoEntity);

    }

    @Override
    public AsientoEntity update(AsientoEntity oAsientoEntity) {
        AsientoEntity oAsientoEntityFromDatabase = oAsientoRepository.findById(oAsientoEntity.getId()).get();
       
        if (oAsientoEntity.getDescripcion() != null) {
            oAsientoEntityFromDatabase.setDescripcion(oAsientoEntity.getDescripcion());
        }
        
        if (oAsientoEntity.getComentarios() != null) {
            oAsientoEntityFromDatabase.setComentarios(oAsientoEntity.getComentarios());
        }
        if (oAsientoEntity.getInventariable() == 0 || oAsientoEntity.getInventariable() == 1) {
            oAsientoEntityFromDatabase.setInventariable(oAsientoEntity.getInventariable());
        }
        if (oAsientoEntity.getMomentstamp() != null) {
            oAsientoEntityFromDatabase.setMomentstamp(oAsientoEntity.getMomentstamp());
        }
        if (oAsientoEntity.getTipoasiento() != null) {
            oAsientoEntityFromDatabase
                    .setTipoasiento(oTipoasientoService.get(oTipoasientoService.randomSelection().getId()));
        }
        if (oAsientoEntity.getUsuario() != null) {
            oAsientoEntityFromDatabase.setUsuario(oUsuarioService.get(oUsuarioService.randomSelection().getId()));
        }
        if (oAsientoEntity.getPeriodo() != null) {
            oAsientoEntityFromDatabase.setPeriodo(oPeriodoService.get(oPeriodoService.randomSelection().getId()));
        }
        return oAsientoRepository.save(oAsientoEntityFromDatabase);
    }

    public AsientoEntity change(Long id) {
        AsientoEntity oAsientoEntityFromDatabase = oAsientoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Asiento no encontrado"));
        oAsientoEntityFromDatabase.setInventariable(oAsientoEntityFromDatabase.getInventariable() == 0 ? 1 : 0);
        return oAsientoRepository.save(oAsientoEntityFromDatabase); 
    }

    @Override
    public Long deleteAll() {
        oAsientoRepository.deleteAll();
        return this.count();
    }

    @Override
    public AsientoEntity randomSelection() {
        return oAsientoRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oAsientoRepository.count() - 1)));
    }

}