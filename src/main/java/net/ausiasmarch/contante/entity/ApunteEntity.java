package net.ausiasmarch.contante.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "apunte")
public class ApunteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Digits(integer = 4, fraction = 4)
    private BigDecimal debe;

    @NotNull
    @Digits(integer = 4, fraction = 4)
    private BigDecimal haber;

    @NotNull
    @Size(min = 3, max = 255)
    private String descripcion;

    @NotNull
    @Size(min = 3, max = 255)
    private String comentarios;

    @NotNull
      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")    
    private LocalDateTime momentstamp;
    
    @NotNull
    @Max(value = 128)
    private Long orden;

    @ManyToOne (fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_asiento")
    private AsientoEntity asiento;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_subcuenta")
    private SubcuentaEntity subcuenta;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_tipoapunte")
    private TipoapunteEntity tipoapunte;

    public ApunteEntity() {
    }

    public ApunteEntity(Long id, BigDecimal debe, BigDecimal haber, String descripcion, String comentarios,
            LocalDateTime momentstamp,
            long orden, AsientoEntity id_asiento, SubcuentaEntity id_subcuenta, TipoapunteEntity id_tipoapunte) {
        this.id = id;
        this.debe = debe;
        this.haber = haber;
        this.descripcion = descripcion;
        this.comentarios = comentarios;
        this.momentstamp = momentstamp;
        this.orden = orden;
        this.asiento = id_asiento;
        this.subcuenta = id_subcuenta;
        this.tipoapunte = id_tipoapunte;
    }

    public ApunteEntity(BigDecimal debe, BigDecimal haber, String descripcion, String comentarios, LocalDateTime momentstamp,
            long orden, AsientoEntity id_asiento, SubcuentaEntity id_subcuenta, TipoapunteEntity idtipo) {
        this.debe = debe;
        this.haber = haber;
        this.descripcion = descripcion;
        this.comentarios = comentarios;
        this.momentstamp = momentstamp;
        this.orden = orden;
        this.asiento = id_asiento;
        this.subcuenta = id_subcuenta;
        this.tipoapunte = idtipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public LocalDateTime getMomentstamp() {
        return momentstamp;
    }

    public void setMomentstamp(LocalDateTime momentstamp) {
        this.momentstamp = momentstamp;
    }

    public long getOrden() {
        return orden;
    }

    public void setOrden(long orden) {
        this.orden = orden;
    }

    public AsientoEntity getAsiento() {
        return asiento;
    }    

    public void setAsiento(AsientoEntity asiento) {
        this.asiento = asiento;
    }

    public SubcuentaEntity getSubcuenta() {
        return subcuenta;
    }

    public void setSubcuenta(SubcuentaEntity subcuenta) {    
        this.subcuenta = subcuenta;
    }

    public TipoapunteEntity getTipoapunte() {
        return tipoapunte;
    }

    public void setTipoapunte(TipoapunteEntity tipoapunte) {
        this.tipoapunte = tipoapunte;
    }

}
