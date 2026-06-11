package com.zellepayment.mapper;

import com.zellepayment.entity.BankAccounts;
import com.zellepayment.entity.ZelleAlias;
import com.zellepayment.model.BankAccountsTO;
import com.zellepayment.model.ZelleAliasTO;

public class ZelleMapper {

    public static ZelleAliasTO getZelleMapper(ZelleAlias zelleAlias){
        ZelleAliasTO zelleAliasTO= new ZelleAliasTO();
        zelleAliasTO.setAliasId(zelleAlias.getAliasId());
        zelleAliasTO.setAliasType(zelleAliasTO.getAliasType());
        zelleAliasTO.setAliasValue(zelleAlias.getAliasValue());
        zelleAliasTO.setCreatedAt(zelleAlias.getCreatedAt());
        return zelleAliasTO;
    }
}
