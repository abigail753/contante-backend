package net.ausiasmarch.contante.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipobalance")
public class TipobalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @OneToMany(mappedBy = "tipobalance", fetch = FetchType.LAZY)
    private java.util.List<BalanceEntity> balances;

    public TipobalanceEntity() {
    }

    public TipobalanceEntity(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public TipobalanceEntity(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getBalances() {
        return balances.size();
    }

}