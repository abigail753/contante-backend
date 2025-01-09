package net.ausiasmarch.contante.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.BalanceEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.BalanceRepository;

@Service
public class BalanceService implements ServiceInterface<BalanceEntity> {

    @Autowired
    BalanceRepository oBalanceRepository;

    @Autowired
    RandomService oRandomService;

    @Autowired
    TipobalanceService oTipobalanceService;

    String[] arrTitulo = {
            "Activo corriente",
            "Activo no corriente",
            "Pasivo corriente",
            "Pasivo no corriente",
            "Patrimonio neto"
    };

    // Array de descripciones para cada partida del balance
    String[] arrDescripcion = {
            "Recursos y activos de la empresa que se espera se conviertan en efectivo en el corto plazo (menos de un año).",
            "Activos que se utilizan a largo plazo para generar ingresos, como edificios, maquinaria y equipo.",
            "Deudas y obligaciones de la empresa que deben pagarse en el corto plazo (menos de un año).",
            "Obligaciones financieras de la empresa a largo plazo, que no se espera pagar en el próximo año.",
            "Valor residual que queda después de restar los pasivos de los activos; representa la inversión de los propietarios."
    };

    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            BalanceEntity oBalanceEntity = new BalanceEntity();
            oBalanceEntity.setTitulo(arrTitulo[oRandomService.getRandomInt(0, arrTitulo.length - 1)]);
            oBalanceEntity.setDescripcion(arrDescripcion[oRandomService.getRandomInt(0, arrDescripcion.length - 1)]);
            oBalanceEntity.setTipobalance(oTipobalanceService.randomSelection());
            oBalanceRepository.save(oBalanceEntity);
        }
        return oBalanceRepository.count();
    }

    public Page<BalanceEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oBalanceRepository
                    .findByTituloContainingOrDescripcionContaining(
                            filter.get(), filter.get(),
                            oPageable);
        } else {
            return oBalanceRepository.findAll(oPageable);
        }
    }

    public Page<BalanceEntity> getPageXTipoapunte(Pageable oPageable, Optional<String> filter,
            Optional<Long> id_tipoapunte) {
        if (filter.isPresent()) {
            if (id_tipoapunte.isPresent()) {
                return oBalanceRepository
                        .findByTituloContainingOrDescripcionContainingXTipoapunte(id_tipoapunte.get(), filter.get(),
                                oPageable);
            } else {
                return oBalanceRepository
                        .findByTituloContainingOrDescripcionContaining(
                                filter.get(), filter.get(),
                                oPageable);
            }
        } else {
            if (id_tipoapunte.isPresent()) {
                return oBalanceRepository.findAllXTipoapunte(id_tipoapunte.get(), oPageable);
            } else {
                return oBalanceRepository.findAll(oPageable);            
            }

        }
    }

    public Page<BalanceEntity> getPageXTipoasiento(Pageable oPageable, Optional<String> filter,
            Optional<Long> id_tipoasiento) {
        if (filter.isPresent()) {
            if (id_tipoasiento.isPresent()) {
                return oBalanceRepository
                        .findByTituloContainingOrDescripcionContainingXTipoasiento(id_tipoasiento.get(), filter.get(),
                                oPageable);
            } else {
                return oBalanceRepository
                        .findByTituloContainingOrDescripcionContaining(
                                filter.get(), filter.get(),
                                oPageable);
            }
        } else {
            if (id_tipoasiento.isPresent()) {
                return oBalanceRepository.findAllXTipoasiento(id_tipoasiento.get(), oPageable);
            } else {
                return oBalanceRepository.findAll(oPageable);            
            }

        }
    }

    public Page<BalanceEntity> getPageXTipocuenta(Pageable oPageable, Optional<String> filter,
            Optional<Long> id_tipocuenta) {
        if (filter.isPresent()) {
            if (id_tipocuenta.isPresent()) {
                return oBalanceRepository
                        .findByTituloContainingOrDescripcionContainingXTipocuenta(id_tipocuenta.get(), filter.get(),
                                oPageable);
            } else {
                return oBalanceRepository
                        .findByTituloContainingOrDescripcionContaining(
                                filter.get(), filter.get(),
                                oPageable);
            }
        } else {
            if (id_tipocuenta.isPresent()) {
                return oBalanceRepository.findAllXTipocuenta(id_tipocuenta.get(), oPageable);
            } else {
                return oBalanceRepository.findAll(oPageable);            
            }

        }
    }

    public BalanceEntity get(Long id) {
        return oBalanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Balance no encontrado"));
        // return oUsuarioRepository.findById(id).get();
    }

    public Long count() {
        return oBalanceRepository.count();
    }

    public Long delete(Long id) {
        oBalanceRepository.deleteById(id);
        return 1L;
    }

    public BalanceEntity create(BalanceEntity oBalanceEntity) {
        return oBalanceRepository.save(oBalanceEntity);
    }

    public BalanceEntity update(BalanceEntity oBalanceEntity) {
        BalanceEntity oBalanceEntityFromDatabase = oBalanceRepository.findById(oBalanceEntity.getId()).get();
        if (oBalanceEntity.getTitulo() != null) {
            oBalanceEntityFromDatabase.setTitulo(oBalanceEntity.getTitulo());
        }
        if (oBalanceEntity.getDescripcion() != null) {
            oBalanceEntityFromDatabase.setDescripcion(oBalanceEntity.getDescripcion());
        }
        return oBalanceRepository.save(oBalanceEntityFromDatabase);
    }

    public Long deleteAll() {
        oBalanceRepository.deleteAll();
        return this.count();
    }

    public BalanceEntity randomSelection() {
        return oBalanceRepository.findAll().get(oRandomService.getRandomInt(0, (int) (oBalanceRepository.count() - 1)));
    }
}
