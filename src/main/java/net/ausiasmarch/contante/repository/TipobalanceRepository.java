package net.ausiasmarch.contante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.contante.entity.TipobalanceEntity;




public interface TipobalanceRepository extends JpaRepository<TipobalanceEntity, Long> {

    Page<TipobalanceEntity> findByDescripcionContaining(String filter, Pageable oPageable);

}
