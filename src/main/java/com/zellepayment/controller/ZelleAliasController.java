package com.zellepayment.controller;

import com.zellepayment.exception.UsersException;
import com.zellepayment.exception.ZelleAliasException;
import com.zellepayment.model.UsersTO;
import com.zellepayment.model.ZelleAliasTO;
import com.zellepayment.services.ZelleAliasServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/zellealias")
public class ZelleAliasController {

    @Autowired
    public ZelleAliasServices zelleAliasServices;

    @GetMapping
    public ResponseEntity<List<ZelleAliasTO>> getAllZelle(){
        log.info("fetching all zelleAlias details....");
        List<ZelleAliasTO>zelleAliasTOS = null;
        try {
            zelleAliasTOS=zelleAliasServices.getAllZelleAlias();
        } catch (ZelleAliasException e) {
            log.error("Error Fetching ZelleAlias detail:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("UnExpected error fetching ZelleAlias details ");
        }
        log.info("Successfully fetched {} zelle details",zelleAliasTOS.size());
        return ResponseEntity.ok(zelleAliasTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ZelleAliasTO> getZelleById(@PathVariable("id")int zelleId){
        log.info("fetching all zelleAlias details By Id....");
        ZelleAliasTO zelleAliasTOS = null;
        try {
            zelleAliasTOS=zelleAliasServices.getZelleAliasById(zelleId);
        } catch (ZelleAliasException e) {
            log.error("Error Fetching ZelleAlias detail By Id:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("UnExpected error fetching ZelleAlias details BY Id ");
        }
        log.info("Successfully fetched zelle details By Id");
        return ResponseEntity.ok(zelleAliasTOS);
    }
    @GetMapping("/aliasValue")
    public ResponseEntity<ZelleAliasTO> getZelleByAliasType(@RequestParam("aliasValue")String aliasValue){
        log.info("fetching all zelleAlias details By AliasType....");
        ZelleAliasTO zelleAliasTOS = null;
        try {
            zelleAliasTOS=zelleAliasServices.getZelleAliasByAliasValue(aliasValue);
        } catch (ZelleAliasException e) {
            log.error("Error Fetching ZelleAlias detail By AliasValue:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("UnExpected error fetching ZelleAlias details BY AliasValue ");
        }
        log.info("Successfully fetched zelle details By AliasValue");
        return ResponseEntity.ok(zelleAliasTOS);
    }

}
