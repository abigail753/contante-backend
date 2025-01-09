package net.ausiasmarch.contante.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.ApunteEntity;
import net.ausiasmarch.contante.entity.SubcuentaEntity;
import net.ausiasmarch.contante.entity.SumasProjection;
import net.ausiasmarch.contante.entity.TipoapunteEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.ApunteRepository;

@Service
public class ApunteService implements ServiceInterface<ApunteEntity> {

    @Autowired
    private ApunteRepository oApunteRepository;

    @Autowired
    private AsientoService oAsientoService;

    @Autowired
    private SubcuentaService oSubcuentaService;

    @Autowired
    private TipoapunteService oTipoapunteService;

    @Autowired
    private RandomService oRandomService;

    private BigDecimal[] arrdebe = {
            new BigDecimal("200.0000"), new BigDecimal("0.0000"), new BigDecimal("50.0000"), new BigDecimal("0.0000"),
            new BigDecimal("120.0000"), new BigDecimal("0.0000"), new BigDecimal("300.0000"), new BigDecimal("0.0000"),
            new BigDecimal("150.0000"), new BigDecimal("0.0000"), new BigDecimal("75.0000"), new BigDecimal("0.0000"),
            new BigDecimal("200.0000"), new BigDecimal("0.0000"), new BigDecimal("90.0000"), new BigDecimal("0.0000"),
            new BigDecimal("175.0000"), new BigDecimal("0.0000"), new BigDecimal("80.0000"), new BigDecimal("0.0000")
    };

    // Datos de la columna haber como BigDecimal
    private BigDecimal[] arrhaber = {
            new BigDecimal("0.0000"), new BigDecimal("150.0000"), new BigDecimal("0.0000"), new BigDecimal("75.0000"),
            new BigDecimal("0.0000"), new BigDecimal("200.0000"), new BigDecimal("0.0000"), new BigDecimal("100.0000"),
            new BigDecimal("0.0000"), new BigDecimal("250.0000"), new BigDecimal("0.0000"), new BigDecimal("175.0000"),
            new BigDecimal("0.0000"), new BigDecimal("100.0000"), new BigDecimal("0.0000"), new BigDecimal("220.0000"),
            new BigDecimal("0.0000"), new BigDecimal("135.0000"), new BigDecimal("0.0000"), new BigDecimal("160.0000")
    };

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

    // Datos de la columna comentarios
    private String[] arrcomentarios = {
            "Pago con transferencia", "Ingreso en efectivo", "Compra de papelería", "Cobro a cliente",
            "Equipo de computo",
            "Proyecto ABC", "Software anual", "Viaje de negocios", "Campaña en redes", "Contrato mensual",
            "Reparación de PC", "Curso de verano", "Compra de tinta", "Charla en empresa", "Hotel y transporte",
            "Soporte técnico", "Pago mensual", "Banner web", "Asesoría fiscal", "Mobiliario antiguo"
    };

    LocalDateTime[] arrdateTimes = {
            LocalDateTime.of(2023, 1, 1, 10, 0),
            LocalDateTime.of(2023, 2, 2, 12, 30),
            LocalDateTime.of(2023, 3, 3, 14, 15),
            LocalDateTime.of(2023, 4, 4, 16, 45),
            LocalDateTime.of(2023, 5, 5, 18, 0),
            LocalDateTime.of(2023, 6, 6, 20, 30),
            LocalDateTime.of(2023, 7, 7, 22, 15),
            LocalDateTime.of(2023, 8, 8, 9, 45),
            LocalDateTime.of(2023, 9, 9, 11, 0),
            LocalDateTime.of(2023, 10, 10, 13, 30),
            LocalDateTime.of(2023, 11, 11, 15, 15),
            LocalDateTime.of(2023, 12, 12, 17, 45),
            LocalDateTime.of(2024, 1, 13, 19, 0),
            LocalDateTime.of(2024, 2, 14, 21, 30),
            LocalDateTime.of(2024, 3, 15, 23, 15),
            LocalDateTime.of(2024, 4, 16, 8, 45),
            LocalDateTime.of(2024, 5, 17, 10, 0),
            LocalDateTime.of(2024, 6, 18, 12, 30),
            LocalDateTime.of(2024, 7, 19, 14, 15),
            LocalDateTime.of(2024, 8, 20, 16, 45)
    };

    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            ApunteEntity oApunteEntity = new ApunteEntity();
            oApunteEntity.setDebe(arrdebe[oRandomService.getRandomInt(0, arrdebe.length - 1)]);
            oApunteEntity.setHaber(arrhaber[oRandomService.getRandomInt(0, arrhaber.length - 1)]);
            oApunteEntity.setDescripcion(arrdescripcion[oRandomService.getRandomInt(0, arrdescripcion.length - 1)]);
            oApunteEntity.setComentarios(arrcomentarios[oRandomService.getRandomInt(0, arrcomentarios.length - 1)]);
            oApunteEntity.setMomentstamp(arrdateTimes[oRandomService.getRandomInt(0, arrdateTimes.length - 1)]);
            oApunteEntity.setOrden(oRandomService.getRandomInt(0, 100));
            oApunteEntity.setAsiento(oAsientoService.randomSelection());
            oApunteEntity.setSubcuenta(oSubcuentaService.randomSelection());
            oApunteEntity.setTipoapunte(oTipoapunteService.randomSelection());
            oApunteRepository.save(oApunteEntity);
        }
        return oApunteRepository.count();
    }

    public Page<ApunteEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (filter.isPresent()) {
            return oApunteRepository
                    .findByDescripcionContainingOrComentariosContaining(
                            filter.get(), filter.get(), oPageable);
        } else {
            return oApunteRepository.findAll(oPageable);
        }
    }

    public Page<ApunteEntity> getPageXAsiento(Pageable oPageable, Optional<String> filter, Optional<Long> id_asiento) {
        if (filter.isPresent()) {
            if (id_asiento.isPresent()) {
                return oApunteRepository
                        .findByAsientoIdAndDescripcionContainingOrComentariosContaining(
                                filter.get(), filter.get(), id_asiento.get(),
                                oPageable);
            } else {
                throw new ResourceNotFoundException("Asiento no encontrado");
            }
        } else {
            if (id_asiento.isPresent()) {
                return oApunteRepository.findByAsientoId(id_asiento.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Asiento no encontrado");
            }
        }
    }

    public Page<ApunteEntity> getPageXTipoApunte(Pageable oPageable, Optional<String> filter,
            Optional<Long> id_tipoapunte) {
        if (filter.isPresent()) {
            if (id_tipoapunte.isPresent()) {
                return oApunteRepository
                        .findByTipoApunteIdAndDescripcionContainingOrComentariosContaining(
                                filter.get(), filter.get(), id_tipoapunte.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Apunte no encontrado");
            }
        } else {
            if (id_tipoapunte.isPresent()) {
                return oApunteRepository.findByTipoapunteId(id_tipoapunte.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Apunte no encontrado");
            }
        }
    }

    public Page<ApunteEntity> getPageXSubcuenta(Pageable oPageable, Optional<String> filter,
            Optional<Long> id_subcuenta) {
        if (filter.isPresent()) {
            if (id_subcuenta.isPresent()) {
                return oApunteRepository
                        .findBySubcuentaIdAndDescripcionContainingOrComentariosContaining(
                                filter.get(), filter.get(), id_subcuenta.get(),
                                oPageable);
            } else {
                throw new ResourceNotFoundException("Subcuenta no encontrada");
            }
        } else {
            if (id_subcuenta.isPresent()) {
                return oApunteRepository.findBySubcuentaId(id_subcuenta.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Subcuenta no encontrada");
            }
        }
    }

    public SumasProjection getTotalAsiento(Long id_asiento) {      
        return oApunteRepository.totalByAsientoId(id_asiento);
    }

    public SumasProjection getTotalTipoapunte(Long id_tipoapunte) {
        return oApunteRepository.totalByTipoapunteId(id_tipoapunte);
    }

    public SumasProjection getTotalSubcuenta(Long id_subcuenta) {
        return oApunteRepository.totalBySubcuentaId(id_subcuenta);
    }

    public ApunteEntity get(Long id) {
        return oApunteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apunte no encontrado"));
    }

    public Long count() {
        return oApunteRepository.count();
    }

    public Long delete(Long id) {
        oApunteRepository.deleteById(id);
        return 1L;
    }

    public ApunteEntity create(ApunteEntity oApunteEntity) {
        return oApunteRepository.save(oApunteEntity);
    }

    public ApunteEntity update(ApunteEntity oApunteEntity) {
        ApunteEntity oApunteEntityFromDatabase = oApunteRepository.findById(oApunteEntity.getId()).get();
        if (oApunteEntity.getDebe() != null) {
            oApunteEntityFromDatabase.setDebe(oApunteEntity.getDebe());
        }
        if (oApunteEntity.getHaber() != null) {
            oApunteEntityFromDatabase.setHaber(oApunteEntity.getHaber());
        }
        if (oApunteEntity.getDescripcion() != null) {
            oApunteEntityFromDatabase.setDescripcion(oApunteEntity.getDescripcion());
        }
        if (oApunteEntity.getComentarios() != null) {
            oApunteEntityFromDatabase.setComentarios(oApunteEntity.getComentarios());
        }
        if (oApunteEntity.getMomentstamp() != null) {
            oApunteEntityFromDatabase.setMomentstamp(oApunteEntity.getMomentstamp());
        }
        if (oApunteEntity.getOrden() != 0) {
            oApunteEntityFromDatabase.setOrden(oApunteEntity.getOrden());
        }
        /*
         * if (oApunteEntity.getId_asiento() != 0) {
         * oApunteEntityFromDatabase.setId_asiento(oApunteEntity.getId_asiento());
         * }
         * if (oApunteEntity.getId_subcuenta() != 0) {
         * oApunteEntityFromDatabase.setId_subcuenta(oApunteEntity.getId_subcuenta());
         * }
         * if (oApunteEntity.getId_tipoapunte() != 0) {
         * oApunteEntityFromDatabase.setId_tipoapunte(oApunteEntity.getId_tipoapunte());
         * }
         */ return oApunteRepository.save(oApunteEntityFromDatabase);
    }

    public Long deleteAll() {
        oApunteRepository.deleteAll();
        return this.count();
    }

    public ApunteEntity randomSelection() {
        return oApunteRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oApunteRepository.count() - 1)));
    }

    public ApunteEntity setTipoApunte(Long id, Long idtipoapunte) {
        ApunteEntity oApunteEntity = oApunteRepository.findById(id).get();
        TipoapunteEntity oTipoapunteEntity = oTipoapunteService.get(idtipoapunte);
        oApunteEntity.setTipoapunte(oTipoapunteEntity);
        return oApunteRepository.save(oApunteEntity);
    }

    public ApunteEntity setSubcuenta(Long id, Long idsubcuenta) {
        ApunteEntity oApunteEntity = oApunteRepository.findById(id).get();
        SubcuentaEntity oSubcuentaEntity = oSubcuentaService.get(idsubcuenta);
        oApunteEntity.setSubcuenta(oSubcuentaEntity);
        return oApunteRepository.save(oApunteEntity);
    }

}
