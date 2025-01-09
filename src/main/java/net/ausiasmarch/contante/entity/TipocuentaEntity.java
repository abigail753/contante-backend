package net.ausiasmarch.contante.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tipocuenta")
public class TipocuentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 255)
    private String descripcion;
    @NotNull
    @Digits(integer = 1, fraction = 0)
    private Long creditoodebito;
    @NotNull
    @Size(min = 3, max = 255)
    private String comentarios;
    @NotNull
    @Digits(integer = 1, fraction = 0)
    private Long realonominal;
  
    @OneToMany(mappedBy = "tipocuenta",fetch = FetchType.LAZY)
    private java.util.List<CuentaEntity> cuentas;

    @OneToMany(mappedBy = "tipocuenta",fetch = FetchType.LAZY)
    private java.util.List<GrupotipocuentaEntity> grupotipocuentas;
    

    public TipocuentaEntity() {
        this.cuentas = new java.util.ArrayList<>();
        this.grupotipocuentas = new java.util.ArrayList<>();
    }

    public TipocuentaEntity(Long id, String descripcion, Long creditoodebito, String comentarios, Long realonominal) {
        this.id = id;
        this.descripcion = descripcion;
        this.creditoodebito = creditoodebito;
        this.comentarios = comentarios;
        this.realonominal = realonominal;
    }

    public TipocuentaEntity(String descripcion, Long creditoodebito, String comentarios, Long realonominal) {
        this.descripcion = descripcion;
        this.creditoodebito = creditoodebito;
        this.comentarios = comentarios;
        this.realonominal = realonominal;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCreditoodebito() {
        return creditoodebito;
    }

    public void setCreditoodebito(Long creditoodebito) {
        this.creditoodebito = creditoodebito;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Long getRealonominal() {
        return realonominal;
    }

    public void setRealonominal(Long realonominal) {
        this.realonominal = realonominal;
    }
    public int getCuentas() {
        return cuentas.size();
    }

    public int getGrupotipocuentas() {
        return grupotipocuentas.size();
    }
}
