package net.ausiasmarch.contante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.contante.entity.SubcuentaEntity;

public interface SubcuentaRepository extends JpaRepository<SubcuentaEntity, Long> {

    Page<SubcuentaEntity> findByDescripcionContaining(
        String filter1, Pageable oPageable);

    Page<SubcuentaEntity> findByCodigo(
        String filter1, Pageable oPageable);
    
    Page<SubcuentaEntity> findByCuentaId(
        Long id_cuenta, Pageable oPageable);

        
    @Query(value = "SELECT COUNT(*) FROM subcuenta, cuenta WHERE cuenta.id_tipocuenta=:id_tipocuenta AND subcuenta.id_cuenta=cuenta.id", nativeQuery = true)
    Long countByTipocuentaId(
        Long id_tipocuenta);

    
    @Query(value = "SELECT * FROM subcuenta WHERE descripcion LIKE %:strDescripcion% OR codigo LIKE %:strCodigo% AND id_cuenta=:id_cuenta", nativeQuery = true)
    Page<SubcuentaEntity> findByCuentaIdAndCodigoContainingOrDescripcionContaining(String strDescripcion,
        String strCodigo, Long id_cuenta, Pageable oPageable);
}
