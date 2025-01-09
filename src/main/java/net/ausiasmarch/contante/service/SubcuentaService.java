package net.ausiasmarch.contante.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.SubcuentaEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.SubcuentaRepository;

@Service
public class SubcuentaService implements ServiceInterface<SubcuentaEntity> {

    @Autowired
    private SubcuentaRepository oSubCuentaRepository;

    @Autowired
    private CuentaService oCuentaService;

    @Autowired
    private RandomService oRandomService;

    // Datos de la columna codigo
    private int[] arrcodigo = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
    // Datos de la columna descripcion
    private String[] arrdescripcion = {
            "Compra de materiales", "Venta de producto", "Gasto de oficina", "Servicio prestado", "Compra de activos",
            "Ingreso por consultoría", "Compra de licencias", "Reembolso de gastos", "Publicidad en redes",
            "Venta de servicios",
            "Mantenimiento de equipo", "Cobro por formación", "Adquisición de suministros", "Ingresos de eventos",
            "Gastos de viaje", "Facturación de servicios", "Alquiler de oficina", "Ingreso por publicidad",
            "Servicios externos",
            "Venta de activos"
    };

    public Page<SubcuentaEntity> getPageXCuenta(Pageable oPageable, Optional<String> filter,
            Optional<Long> id_cuenta) {
        if (filter.isPresent()) {
            if (id_cuenta.isPresent()) {
                return oSubCuentaRepository
                        .findByCuentaIdAndCodigoContainingOrDescripcionContaining(
                                filter.get(), filter.get(), id_cuenta.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Subcuenta no encontrada");
            }
        } else {
            if (id_cuenta.isPresent()) {
                return oSubCuentaRepository.findByCuentaId(id_cuenta.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Subcuenta no encontrada");
            }
        }
    }

    public Long countTipocuentas(Long id_tipocuenta) {
        return oSubCuentaRepository.countByTipocuentaId(id_tipocuenta);
    }

    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            SubcuentaEntity oSubCuentaEntity = new SubcuentaEntity();
            oSubCuentaEntity.setCodigo(arrcodigo[oRandomService.getRandomInt(0, arrcodigo.length - 1)]);
            oSubCuentaEntity.setDescripcion(arrdescripcion[oRandomService.getRandomInt(0, arrdescripcion.length - 1)]);            
            oSubCuentaEntity.setMomentstamp(LocalDateTime.now().minusDays(oRandomService.getRandomInt(0, 365)));
            oSubCuentaEntity.setCuenta(oCuentaService.randomSelection());
            oSubCuentaRepository.save(oSubCuentaEntity);
        }
        return oSubCuentaRepository.count();
    }

    public Page<SubcuentaEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (filter.isPresent()) {
            return oSubCuentaRepository
                    .findByDescripcionContaining(
                            filter.get(), oPageable);
        } else {
            return oSubCuentaRepository.findAll(oPageable);
        }
    }

    public SubcuentaEntity get(Long id) {
        return oSubCuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SubCuenta no encontrado"));
    }

    public Long count() {
        return oSubCuentaRepository.count();
    }

    public Long delete(Long id) {
        oSubCuentaRepository.deleteById(id);
        return 1L;
    }

    public SubcuentaEntity create(SubcuentaEntity oSubCuentaEntity) {
        return oSubCuentaRepository.save(oSubCuentaEntity);
    }

    public SubcuentaEntity update(SubcuentaEntity oSubCuentaEntity) {
        SubcuentaEntity oSubCuentaEntityFromDatabase = oSubCuentaRepository.findById(oSubCuentaEntity.getId()).get();
        if (oSubCuentaEntity.getCodigo() != 0) {
            oSubCuentaEntityFromDatabase.setCodigo(oSubCuentaEntity.getCodigo());
        }
        if (oSubCuentaEntity.getDescripcion() != null) {
            oSubCuentaEntityFromDatabase.setDescripcion(oSubCuentaEntity.getDescripcion());
        }
        if (oSubCuentaEntity.getCuenta() != null) {
            oSubCuentaEntityFromDatabase.setCuenta(oCuentaService.get(oSubCuentaEntity.getCuenta().getId()));
        }
        if (oSubCuentaEntity.getMomentstamp() != null) {
            oSubCuentaEntityFromDatabase.setMomentstamp(oSubCuentaEntity.getMomentstamp());
        }
        return oSubCuentaRepository.save(oSubCuentaEntityFromDatabase);
    }

    public Long deleteAll() {
        oSubCuentaRepository.deleteAll();
        return this.count();
    }

    public SubcuentaEntity randomSelection() {
        return oSubCuentaRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oSubCuentaRepository.count() - 1)));
    }

}
