package net.ausiasmarch.contante.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.contante.entity.TipoasientoEntity;
import net.ausiasmarch.contante.service.TipoasientoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/tipoasiento")
public class Tipoasiento {

    @Autowired
    TipoasientoService oTipoAsientoService;

    @GetMapping("")
    public ResponseEntity<Page<TipoasientoEntity>> getPage(
            Pageable oPageable,
            @RequestParam Optional<String> filter) {
        return new ResponseEntity<Page<TipoasientoEntity>>(oTipoAsientoService.getPage(oPageable, filter),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoasientoEntity> getTipoAsiento(@PathVariable Long id) {
        return new ResponseEntity<TipoasientoEntity>(oTipoAsientoService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oTipoAsientoService.count(), HttpStatus.OK);
    }

    @GetMapping("/xbalance/{id}")
    public ResponseEntity<Page<TipoasientoEntity>> getByBalance(Pageable oPageable, @PathVariable Long id,
            @RequestParam Optional<String> filter) {
        return new ResponseEntity<Page<TipoasientoEntity>>(oTipoAsientoService.getPageByBalance(oPageable, id, filter),
                HttpStatus.OK);
    }

    @GetMapping("/wherebalanceisnot/{id}")
    public ResponseEntity<Page<TipoasientoEntity>> getTipoasientoSinRelacionar(Pageable oPageable,
            @PathVariable Long id, @RequestParam Optional<String> filter) {
        return new ResponseEntity<Page<TipoasientoEntity>>(
                oTipoAsientoService.getTipoasientoSinRelacionar(id, oPageable, filter),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oTipoAsientoService.delete(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<TipoasientoEntity> create(@RequestBody TipoasientoEntity oTipoAsientoEntity) {
        return new ResponseEntity<TipoasientoEntity>(oTipoAsientoService.create(oTipoAsientoEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TipoasientoEntity> update(@RequestBody TipoasientoEntity oTipoAsientoEntity) {
        return new ResponseEntity<TipoasientoEntity>(oTipoAsientoService.update(oTipoAsientoEntity), HttpStatus.OK);
    }

    @PutMapping("/random/{cantidad}")
    public ResponseEntity<Long> create(@PathVariable Long cantidad) {
        return new ResponseEntity<Long>(oTipoAsientoService.randomCreate(cantidad), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oTipoAsientoService.deleteAll(), HttpStatus.OK);
    }

    // quitar relacion con parametros de tipoasiento y balance
    @DeleteMapping("/quitarBalance")
    public ResponseEntity<Long> deleteRelation(@RequestParam Long idTipoasiento, @RequestParam Long idBalance) {
        return new ResponseEntity<Long>(oTipoAsientoService.deleteRelation(idTipoasiento, idBalance), HttpStatus.OK);
    }

    // agregar relacion con parametros de tipoasiento y balance
    @PutMapping("/agregarBalance")
    public ResponseEntity<Long> addRelation(@RequestParam Long idTipoasiento, @RequestParam Long idBalance) {
        return new ResponseEntity<Long>(oTipoAsientoService.addRelation(idTipoasiento, idBalance), HttpStatus.OK);
    }
}
