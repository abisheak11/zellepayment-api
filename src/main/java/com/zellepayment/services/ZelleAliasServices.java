package com.zellepayment.services;

import com.zellepayment.exception.ZelleAliasException;
import com.zellepayment.model.ZelleAliasTO;

import java.util.List;

public interface ZelleAliasServices {

    List<ZelleAliasTO> getAllZelleAlias()throws ZelleAliasException;

    ZelleAliasTO getZelleAliasById(int zelleId)throws ZelleAliasException;

    ZelleAliasTO getZelleAliasByAliasValue(String aliasValue)throws ZelleAliasException;

}
