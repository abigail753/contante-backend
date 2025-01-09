package net.ausiasmarch.contante.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "balance")
public class BalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 255)
    private String titulo;
    @NotNull
    @Size(min = 3, max = 255)
    private String descripcion;

    @OneToMany(mappedBy = "balance", fetch = FetchType.LAZY)
    private java.util.List<GrupotipoasientoEntity> grupotipoasientos;

    @OneToMany(mappedBy = "balance", fetch = FetchType.LAZY)
    private java.util.List<GruposubcuentaEntity> gruposubcuentas;

    @OneToMany(mappedBy = "balance", fetch = FetchType.LAZY)
    private java.util.List<GrupotipocuentaEntity> grupotipocuentas;

    @OneToMany(mappedBy = "balance", fetch = FetchType.LAZY)
    private java.util.List<GrupocuentaEntity> grupocuentas;

    @OneToMany(mappedBy = "balance", fetch = FetchType.LAZY)
    private java.util.List<GrupotipoapunteEntity> grupotipoapuntes;


    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_tipobalance")
    private TipobalanceEntity tipobalance;

    public BalanceEntity() {
        this.grupotipoasientos = new java.util.ArrayList<>();
        this.gruposubcuentas = new java.util.ArrayList<>();
        this.grupotipocuentas = new java.util.ArrayList<>();
        this.grupocuentas = new java.util.ArrayList<>();
        this.grupotipoapuntes = new java.util.ArrayList<>();
    }

    public BalanceEntity(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getGrupotipoasientos() {
        return grupotipoasientos.size();
    }

    public int getGruposubcuentas() {
        return gruposubcuentas.size();
    }

    public int getGrupotipocuentas() {
        return grupotipocuentas.size();
    }

    public int getGrupocuentas() {
        return grupocuentas.size();
    }

    public int getGrupotipoapuntes() {
        return grupotipoapuntes.size();
    }

    public TipobalanceEntity getTipobalance() {
        return tipobalance;
    }

    public void setTipobalance(TipobalanceEntity tipobalance) {
        this.tipobalance = tipobalance;
    }

}
