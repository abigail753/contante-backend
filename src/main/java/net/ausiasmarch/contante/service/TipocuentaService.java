package net.ausiasmarch.contante.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.TipocuentaEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.TipocuentaRepository;

@Service
public class TipocuentaService implements ServiceInterface<TipocuentaEntity> {

    @Autowired
    private TipocuentaRepository oTipoCuentaRepository;

    @Autowired
    private RandomService oRandomService;

    private String[] arrDescripcion = {
            "Compra de materiales", "Venta de producto", "Gasto de oficina", "Servicio prestado", "Compra de activos",
            "Ingreso por consultoría", "Compra de licencias", "Reembolso de gastos", "Publicidad en redes",
            "Venta de servicios",
            "Mantenimiento de equipo", "Cobro por formación", "Adquisición de suministros", "Ingresos de eventos",
            "Gastos de viaje", "Facturación de servicios", "Alquiler de oficina", "Ingreso por publicidad",
            "Servicios externos",
            "Venta de activos"
    };

    // Datos de la columna credito o debito entre 0 y 1
    private Long[] arrCreditoOdebito = {
            0L, 1L
    };

    // Datos de la columna real o nominal
    private Long[] arrRealOnominal = {
            0L, 1L
    };

    // Datos de la columna comentarios
    private String[] arrComentarios = {
            "Pago con transferencia", "Ingreso en efectivo", "Compra de papelería", "Cobro a cliente",
            "Equipo de computo",
            "Proyecto ABC", "Software anual", "Viaje de negocios", "Campaña en redes", "Contrato mensual",
            "Reparación de PC", "Curso de verano", "Compra de tinta", "Charla en empresa", "Hotel y transporte",
            "Soporte técnico", "Pago mensual", "Banner web", "Asesoría fiscal", "Mobiliario antiguo"
    };

    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            TipocuentaEntity oTipoCuentaEntity = new TipocuentaEntity();
            oTipoCuentaEntity.setDescripcion(arrDescripcion[oRandomService.getRandomInt(0, arrDescripcion.length - 1)]);
            oTipoCuentaEntity
                    .setCreditoodebito(arrCreditoOdebito[oRandomService.getRandomInt(0, arrCreditoOdebito.length - 1)]);
            oTipoCuentaEntity
                    .setRealonominal(arrRealOnominal[oRandomService.getRandomInt(0, arrRealOnominal.length - 1)]);
            oTipoCuentaEntity.setComentarios(arrComentarios[oRandomService.getRandomInt(0, arrComentarios.length - 1)]);
            oTipoCuentaRepository.save(oTipoCuentaEntity);
        }
        return oTipoCuentaRepository.count();
    }

    public Page<TipocuentaEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (filter.isPresent()) {
            return oTipoCuentaRepository
                    .findByDescripcionContainingOrComentariosContaining(
                            filter.get(), filter.get(), oPageable);
        } else {
            return oTipoCuentaRepository.findAll(oPageable);
        }
    }

    public TipocuentaEntity get(Long id) {
        return oTipoCuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apunte no encontrado"));
    }

    public Long count() {
        return oTipoCuentaRepository.count();
    }

    public Long delete(Long id) {
        oTipoCuentaRepository.deleteById(id);
        return 1L;
    }

    public TipocuentaEntity create(TipocuentaEntity oTipoCuentaEntity) {
        return oTipoCuentaRepository.save(oTipoCuentaEntity);
    }

    public TipocuentaEntity update(TipocuentaEntity oTipoCuentaEntity) {
        TipocuentaEntity oTipoCuentaEntityFromDatabase = oTipoCuentaRepository.findById(oTipoCuentaEntity.getId())
                .get();
        if (oTipoCuentaEntity.getDescripcion() != null) {
            oTipoCuentaEntityFromDatabase.setDescripcion(oTipoCuentaEntity.getDescripcion());
        }
        if (oTipoCuentaEntity.getCreditoodebito() != null) {
            oTipoCuentaEntityFromDatabase.setCreditoodebito(oTipoCuentaEntity.getCreditoodebito());
        }
        if (oTipoCuentaEntity.getComentarios() != null) {
            oTipoCuentaEntityFromDatabase.setComentarios(oTipoCuentaEntity.getComentarios());
        }
        if (oTipoCuentaEntity.getRealonominal() != null) {
            oTipoCuentaEntityFromDatabase.setRealonominal(oTipoCuentaEntity.getRealonominal());
        }
        return oTipoCuentaRepository.save(oTipoCuentaEntityFromDatabase);
    }

    public Long deleteAll() {
        oTipoCuentaRepository.deleteAll();
        return this.count();
    }

    public TipocuentaEntity randomSelection() {
        return oTipoCuentaRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oTipoCuentaRepository.count() - 1)));
    }

    public Long getPageSubcuenta(Long id) {
        return oTipoCuentaRepository.getPageSubcuenta(id);
    }

    public Page<TipocuentaEntity> getPageXBalance(Long id_balance, Pageable oPageable){

        return oTipoCuentaRepository.findAllXBalance(id_balance, oPageable);
    }

    public Page<TipocuentaEntity> getPageXBalanceNoTiene(Long id_balance, Pageable oPageable){

        return oTipoCuentaRepository.findAllXBalanceNoTiene(id_balance, oPageable);
    }

}
