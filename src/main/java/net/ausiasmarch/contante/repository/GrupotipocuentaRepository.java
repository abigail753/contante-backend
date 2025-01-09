package net.ausiasmarch.contante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import net.ausiasmarch.contante.entity.GrupotipocuentaEntity;

public interface GrupotipocuentaRepository extends JpaRepository<GrupotipocuentaEntity, Long> {

    Page<GrupotipocuentaEntity> findByTituloContainingOrDescripcionContaining(
            String filter1, String filter2, Pageable oPageable);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM grupotipocuenta WHERE id_tipocuenta = :id_tipocuenta AND id_balance = :id_balance", nativeQuery = true)
    int deleteByIds(@Param("id_balance") Long id_balance, @Param("id_tipocuenta") Long id_tipocuenta);

}
