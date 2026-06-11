package com.zellepayment.services;

import com.zellepayment.exception.UsersException;
import com.zellepayment.model.UserRequest;
import com.zellepayment.model.UserRequestUpdate;
import com.zellepayment.model.UsersTO;


import java.util.List;

public interface UserService {

    List<UsersTO> getUsers ()throws UsersException;

    UsersTO getUserId(int id)throws UsersException;

    UsersTO getUserByPhoneNumber(String phoneNumber)throws UsersException;

    Integer saveUser(UserRequest userRequests)throws UsersException;

    Integer updateUser(UserRequestUpdate requestUpdate)throws UsersException;

    String deleteUser(int userId)throws UsersException;

    String deleteUserByFullName(String fullName);

   // List<UsersTO> getRecords(String userId)throws UsersException;


}

