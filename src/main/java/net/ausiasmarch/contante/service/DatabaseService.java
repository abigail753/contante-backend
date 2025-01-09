package net.ausiasmarch.contante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    PeriodoService oPeriodoService;

    @Autowired
    TipoasientoService oTipoasientoService;

    @Autowired
    TipousuarioService oTipousuarioService;

    @Autowired
    TipocuentaService oTipocuentaService;

    @Autowired
    TipoapunteService oTipoapunteService;

    @Autowired
    BalanceService oBalanceService;

    @Autowired
    UsuarioService oUsuarioService;

    @Autowired
    CuentaService oCuentaService;

    @Autowired
    SubcuentaService oSubcuentaService;

    @Autowired
    AsientoService oAsientoService;

    @Autowired
    ApunteService oApunteService;

    @Autowired
    GrupotipoasientoService oGrupotipoasientoService;

    @Autowired
    GruposubcuentaService oGruposubcuentaService;

    @Autowired
    GrupotipocuentaService oGrupotipocuentaService;

    @Autowired
    GrupocuentaService oGrupocuentaService;

    @Autowired
    GrupotipoapunteService oGrupotipoapunteService;

    @Autowired
    TipobalanceService oTipobalanceService;


    public Long fill() {
        oTipobalanceService.randomCreate(0L);
        oPeriodoService.randomCreate(0L);
        oTipoasientoService.randomCreate(0L);
        oTipousuarioService.randomCreate(0L);
        oUsuarioService.randomCreate(25L);  
        oTipocuentaService.randomCreate(10L);
        oTipoapunteService.randomCreate(10L);
        oBalanceService.randomCreate(15L);
        oCuentaService.randomCreate(100L);
        oSubcuentaService.randomCreate(50L);
        oAsientoService.randomCreate(100L);
        oApunteService.randomCreate(250L);
        oGrupotipoasientoService.randomCreate(25L);
        oGruposubcuentaService.randomCreate(25L);
        oGrupotipocuentaService.randomCreate(25L);
        oGrupocuentaService.randomCreate(25L);
        oGrupotipoapunteService.randomCreate(25L);
        return 0L;
    }

}