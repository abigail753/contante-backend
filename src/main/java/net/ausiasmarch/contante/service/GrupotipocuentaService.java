package net.ausiasmarch.contante.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.contante.entity.GrupotipocuentaEntity;
import net.ausiasmarch.contante.exception.ResourceNotFoundException;
import net.ausiasmarch.contante.repository.GrupotipocuentaRepository;

@Service
public class GrupotipocuentaService implements ServiceInterface<GrupotipocuentaEntity> {

    @Autowired
    private GrupotipocuentaRepository oGrupotipocuentaRepository;

    @Autowired
    RandomService oRandomService;

    @Autowired
    TipocuentaService oTipocuentaService;

    @Autowired
    BalanceService oBalanceService;

    /*
     * @Autowired
     * private RandomService oRandomService;
     * 
     * private String[] arrDescripcion = {
     * "Compra de materiales", "Venta de producto", "Gasto de oficina",
     * "Servicio prestado", "Compra de activos",
     * "Ingreso por consultoría", "Compra de licencias", "Reembolso de gastos",
     * "Publicidad en redes",
     * "Venta de servicios",
     * "Mantenimiento de equipo", "Cobro por formación",
     * "Adquisición de suministros", "Ingresos de eventos",
     * "Gastos de viaje", "Facturación de servicios", "Alquiler de oficina",
     * "Ingreso por publicidad",
     * "Servicios externos",
     * "Venta de activos"
     * };
     * 
     * // Datos de la columna credito o debito entre 0 y 1
     * private Long[] arrCreditoOdebito = {
     * 0L, 1L
     * };
     * 
     * // Datos de la columna real o nominal
     * private Long[] arrRealOnominal = {
     * 0L, 1L
     * };
     * 
     * // Datos de la columna comentarios
     * private String[] arrComentarios = {
     * "Pago con transferencia", "Ingreso en efectivo", "Compra de papelería",
     * "Cobro a cliente",
     * "Equipo de computo",
     * "Proyecto ABC", "Software anual", "Viaje de negocios", "Campaña en redes",
     * "Contrato mensual",
     * "Reparación de PC", "Curso de verano", "Compra de tinta",
     * "Charla en empresa", "Hotel y transporte",
     * "Soporte técnico", "Pago mensual", "Banner web", "Asesoría fiscal",
     * "Mobiliario antiguo"
     * };
     * 
     */

    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            GrupotipocuentaEntity ogGrupoSubCuentaEntity = new GrupotipocuentaEntity();
            ogGrupoSubCuentaEntity.setTitulo("Grupo tipo cuenta " + i + oRandomService.getRandomInt(999, 9999));
            ogGrupoSubCuentaEntity.setDescripcion(ogGrupoSubCuentaEntity.getTitulo());
            ogGrupoSubCuentaEntity.setOrden(i);
            ogGrupoSubCuentaEntity.setTipocuenta(oTipocuentaService.randomSelection());
            ogGrupoSubCuentaEntity.setBalance(oBalanceService.randomSelection());
            oGrupotipocuentaRepository.save(ogGrupoSubCuentaEntity);
        }
        return oGrupotipocuentaRepository.count();

    }

    public Page<GrupotipocuentaEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (filter.isPresent()) {
            return oGrupotipocuentaRepository
                    .findByTituloContainingOrDescripcionContaining(filter.get(), filter.get(), oPageable);
        } else {
            return oGrupotipocuentaRepository.findAll(oPageable);
        }
    }

    public GrupotipocuentaEntity get(Long id) {
        return oGrupotipocuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apunte no encontrado"));
    }

    public Long count() {
        return oGrupotipocuentaRepository.count();
    }

    public Long delete(Long id) {
        oGrupotipocuentaRepository.deleteById(id);
        return 1L;
    }

    public GrupotipocuentaEntity create(GrupotipocuentaEntity oGrupoTipoCuentaEntity) {
        return oGrupotipocuentaRepository.save(oGrupoTipoCuentaEntity);
    }

    public GrupotipocuentaEntity update(GrupotipocuentaEntity oGrupoTipoCuentaEntity) {
        GrupotipocuentaEntity oGrupoTipoCuentaEntityFromDatabase = oGrupotipocuentaRepository
                .findById(oGrupoTipoCuentaEntity.getId()).get();
        if (oGrupoTipoCuentaEntity.getTitulo() != null) {
            oGrupoTipoCuentaEntityFromDatabase.setTitulo(oGrupoTipoCuentaEntity.getTitulo());
        }
        if (oGrupoTipoCuentaEntity.getDescripcion() != null) {
            oGrupoTipoCuentaEntityFromDatabase.setDescripcion(oGrupoTipoCuentaEntity.getDescripcion());
        }
        if (oGrupoTipoCuentaEntity.getOrden() != 0) {
            oGrupoTipoCuentaEntityFromDatabase.setOrden(oGrupoTipoCuentaEntity.getOrden());
        }
        if (oGrupoTipoCuentaEntity.getTipocuenta() != null) {
            oGrupoTipoCuentaEntityFromDatabase
                    .setTipocuenta(oTipocuentaService.get(oGrupoTipoCuentaEntity.getTipocuenta().getId()));
        }
        if (oGrupoTipoCuentaEntity.getBalance() != null) {
            oGrupoTipoCuentaEntityFromDatabase
                    .setBalance(oBalanceService.get(oGrupoTipoCuentaEntity.getBalance().getId()));
        }
        return oGrupotipocuentaRepository.save(oGrupoTipoCuentaEntityFromDatabase);
    }

    public Long deleteAll() {
        oGrupotipocuentaRepository.deleteAll();
        return this.count();
    }

    public GrupotipocuentaEntity randomSelection() {
        return oGrupotipocuentaRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oGrupotipocuentaRepository.count() - 1)));
    }



    public Long deleteByIds(Long id_balance, Long id_tipocuenta) {
        return Long.valueOf( oGrupotipocuentaRepository.deleteByIds(id_balance, id_tipocuenta));
    }

}
