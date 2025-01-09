package net.ausiasmarch.contante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.contante.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Page<UsuarioEntity> findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
            String filter2, String filter3, String filter4, String filter5, Pageable oPageable);

    @Query(value = "SELECT COUNT(*) FROM asiento, apunte WHERE asiento.id_usuario=:id AND apunte.id_asiento=asiento.id", nativeQuery = true)
    Long getApuntes(Long id);

    @Query(value = "SELECT COUNT(*) FROM asiento, apunte, periodo WHERE asiento.id_usuario=:id AND apunte.id_asiento=asiento.id AND periodo.id=asiento.id_periodo AND periodo.cerrado=0", nativeQuery = true)
    Long getApuntesAbiertos(Long id);
}
