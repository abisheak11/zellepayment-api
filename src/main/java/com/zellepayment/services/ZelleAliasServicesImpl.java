package com.zellepayment.services;

import com.zellepayment.entity.ZelleAlias;
import com.zellepayment.exception.UsersException;
import com.zellepayment.exception.ZelleAliasException;
import com.zellepayment.mapper.ZelleMapper;
import com.zellepayment.model.BankAccountsTO;
import com.zellepayment.model.ZelleAliasTO;
import com.zellepayment.repository.ZelleAliasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ZelleAliasServicesImpl implements ZelleAliasServices{

    @Autowired
    public ZelleAliasRepository zelleAliasRepository;

    @Override
    public List<ZelleAliasTO> getAllZelleAlias() throws ZelleAliasException {

        log.info("inside the ZelleAliasServicesImpl.getAllZelleAlias");

        List<ZelleAlias>zelleAliases = zelleAliasRepository.findAll();
        if (CollectionUtils.isEmpty(zelleAliases)){
            log.info("Here No Alias Founded");
            throw new ZelleAliasException("Here No Alias Founded");
        }
        return zelleAliases.stream().map(zelleAlias -> {
            ZelleAliasTO zelleAliasTO = new ZelleAliasTO();
            zelleAliasTO.setAliasId(zelleAlias.getAliasId());
            zelleAliasTO.setAliasType(zelleAlias.getAliasType());
            zelleAliasTO.setAliasValue(zelleAlias.getAliasValue());
            zelleAliasTO.setCreatedAt(zelleAlias.getCreatedAt());
//           zelleAliasTO.setUsersTO(zelleAlias.getUserAlias());
//            zelleAliasTO.setBankAccountsTO(zelleAlias.getBankAccounts());
            return zelleAliasTO;
        }).collect(Collectors.toList());
    }
    @Override
    public ZelleAliasTO getZelleAliasById(int zelleId) throws ZelleAliasException {
      ZelleAliasTO zelleAliasTO = zelleAliasRepository.findById(zelleId).map(ZelleMapper::getZelleMapper).orElseThrow(()->{
                  log.info(" ZelleAlias not found for this zelleId:{}", zelleId);
                  return new ZelleAliasException("ZelleAlias not found for this zelleId");
        });
//        zelleAliasTO.setUsersTO(zelleAlias.get().getUserAlias());
//        zelleAliasTO.setBankAccountsTO(zelleAlias.get().getBankAccounts());

        return zelleAliasTO;
    }

    @Override
    public ZelleAliasTO getZelleAliasByAliasValue(String aliasValue) throws ZelleAliasException {
        ZelleAlias zelleAlias = zelleAliasRepository.findByAliasValue(aliasValue);
        ZelleAliasTO zelleAliasTO= new ZelleAliasTO();
        zelleAliasTO.setAliasId(zelleAlias.getAliasId());
        zelleAliasTO.setAliasType(zelleAliasTO.getAliasType());
        zelleAliasTO.setAliasValue(zelleAlias.getAliasValue());
        zelleAliasTO.setCreatedAt(zelleAlias.getCreatedAt());
        return zelleAliasTO;
    }


}
