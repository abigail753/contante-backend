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

import net.ausiasmarch.contante.entity.SubcuentaEntity;
import net.ausiasmarch.contante.service.SubcuentaService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/subcuenta")

public class Subcuenta {

    @Autowired
    SubcuentaService oSubCuentaService;

    @GetMapping("/xcuenta/{id}")
        public ResponseEntity<Page<SubcuentaEntity>> getPageXCuenta(
                Pageable oPageable,
                @RequestParam Optional<String> filter,
                @PathVariable Optional<Long> id) {
            return new ResponseEntity<Page<SubcuentaEntity>>(oSubCuentaService.getPageXCuenta(oPageable, filter, id),
                    HttpStatus.OK);
        }

        @GetMapping("/xtipocuenta/{id}")
        public ResponseEntity<Long> countTipocuentas(@PathVariable Long id) {
           return new ResponseEntity<Long>(oSubCuentaService.countTipocuentas(id), HttpStatus.OK);
        }

    @GetMapping("")
    public ResponseEntity<Page<SubcuentaEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<SubcuentaEntity>>(oSubCuentaService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcuentaEntity> getApunte(@PathVariable Long id) {
        return new ResponseEntity<SubcuentaEntity>(oSubCuentaService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oSubCuentaService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oSubCuentaService.delete(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<SubcuentaEntity> create(@RequestBody SubcuentaEntity oApunteEntity) {
        return new ResponseEntity<SubcuentaEntity>(oSubCuentaService.create(oApunteEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<SubcuentaEntity> update(@RequestBody SubcuentaEntity oApunteEntity) {
        return new ResponseEntity<SubcuentaEntity>(oSubCuentaService.update(oApunteEntity), HttpStatus.OK);
    }


    @PutMapping("/random/{cantidad}")
    public ResponseEntity<Long> create(@PathVariable Long cantidad) {
        return new ResponseEntity<Long>(oSubCuentaService.randomCreate(cantidad), HttpStatus.OK);
    }
    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oSubCuentaService.deleteAll(), HttpStatus.OK);
    }

    
}
