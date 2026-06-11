package com.zellepayment.repository;

import com.zellepayment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UsersRepository extends JpaRepository<Users,Integer> {

    Users findByPhoneNumber(String phoneNumber);

    Users findByFullName(String fullName);

    Users deleteByFullName(String fullName);

//    List<Users> findByUserId(Iterable<Long> userIds);
}
