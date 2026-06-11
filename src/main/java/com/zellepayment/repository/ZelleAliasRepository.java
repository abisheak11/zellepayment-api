package com.zellepayment.repository;

import com.zellepayment.entity.ZelleAlias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZelleAliasRepository extends JpaRepository<ZelleAlias,Integer> {

    ZelleAlias findByAliasValue(String aliasValue);
}
