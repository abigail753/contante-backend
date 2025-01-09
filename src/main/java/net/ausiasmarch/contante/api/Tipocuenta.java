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
import net.ausiasmarch.contante.entity.TipocuentaEntity;
import net.ausiasmarch.contante.service.TipocuentaService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/tipoCuenta")
public class Tipocuenta {

    @Autowired
    TipocuentaService oTipoCuentaService;

    @GetMapping("")
    public ResponseEntity<Page<TipocuentaEntity>> getPage(
            Pageable oPageable,
            @RequestParam Optional<String> filter) {
        return new ResponseEntity<Page<TipocuentaEntity>>(oTipoCuentaService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipocuentaEntity> getTipoCuenta(@PathVariable Long id) {
        return new ResponseEntity<TipocuentaEntity>(oTipoCuentaService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oTipoCuentaService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oTipoCuentaService.delete(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<TipocuentaEntity> create(@RequestBody TipocuentaEntity oTipoCuentaEntity) {
        return new ResponseEntity<TipocuentaEntity>(oTipoCuentaService.create(oTipoCuentaEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TipocuentaEntity> update(@RequestBody TipocuentaEntity oTipoCuentaEntity) {
        return new ResponseEntity<TipocuentaEntity>(oTipoCuentaService.update(oTipoCuentaEntity), HttpStatus.OK);
    }

    @PutMapping("/random/{cantidad}")
    public ResponseEntity<Long> create(@PathVariable Long cantidad) {
        return new ResponseEntity<Long>(oTipoCuentaService.randomCreate(cantidad), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oTipoCuentaService.deleteAll(), HttpStatus.OK);
    }

    @GetMapping("/xbalance/{id}")
    public ResponseEntity<Page<TipocuentaEntity>> getPageXBalance(
            Pageable oPageable, @PathVariable Long id) {
        return new ResponseEntity<Page<TipocuentaEntity>>(oTipoCuentaService.getPageXBalance(id, oPageable),
                HttpStatus.OK);
    }

    @GetMapping("/xbalancenotiene/{id}")
    public ResponseEntity<Page<TipocuentaEntity>> getPageXBalanceNotiene(
            Pageable oPageable, @PathVariable Long id) {
        return new ResponseEntity<Page<TipocuentaEntity>>(oTipoCuentaService.getPageXBalanceNoTiene(id, oPageable),
                HttpStatus.OK);
    }

    @GetMapping("/subcuenta/{id}")
    public ResponseEntity<Long> getPageSubcuenta(@PathVariable Long id) {
        return new ResponseEntity<Long>(oTipoCuentaService.getPageSubcuenta(id), HttpStatus.OK);
    }

}
