package com.zellepayment.services;


import com.zellepayment.entity.BankAccounts;
import com.zellepayment.entity.Transactions;
import com.zellepayment.entity.Users;
import com.zellepayment.entity.ZelleAlias;
import com.zellepayment.exception.UsersException;
import com.zellepayment.model.UserRequest;
import com.zellepayment.model.UserRequestUpdate;
import com.zellepayment.model.UsersTO;
import com.zellepayment.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServicesImplTest {
    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserServicesImpl userServices;

    @Test
    public void getUsers_whenUserDetailsExits_thenReturnUserData(){
        Users user = new Users();
        user.setUserId(1);
        user.setFullName("abisheak");
        user.setEmail("abisheak@gmail.com");
        user.setPhoneNumber("9234567876");
        user.setPasswordHash("hash12334");
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus("Active");

        BankAccounts bankAccounts = new BankAccounts();
        bankAccounts.setAccountId(123);
        bankAccounts.setBankName("Sbi");
        bankAccounts.setAccountNumber("123456789");
        bankAccounts.setIfscCode("ifscSbi2334");
        bankAccounts.setIsPrimary(234);
        bankAccounts.setCreatedAt(LocalDateTime.now());

        List<BankAccounts>bankAcc=Arrays.asList(bankAccounts);
        user.setBankAccounts(bankAcc);

        Transactions transactions = new Transactions();
        transactions.setTransactionId(12);
        transactions.setReceiverAccountId(12);
        transactions.setSenderAccountId(3455);
        transactions.setAmount(1000);
        transactions.setCurrency("rupee");
        transactions.setStatus("cmpt");
        transactions.setReferenceNote("Abc");
        transactions.setCreatedAt(LocalDateTime.now());
        transactions.setCompletedAt(LocalDateTime.now());
        List<Transactions>sndTransaction= Arrays.asList(transactions);
        user.setSenderTransactions(sndTransaction);

        Transactions transaction = new Transactions();
        transaction.setTransactionId(12);
        transaction.setReceiverAccountId(12);
        transaction.setSenderAccountId(3455);
        transaction.setAmount(1000);
        transaction.setCurrency("rupee");
        transaction.setStatus("cmpt");
        transaction.setReferenceNote("Abc");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setCompletedAt(LocalDateTime.now());
        List<Transactions>recTransaction= Arrays.asList(transaction);
        user.setReceiverTransactions(recTransaction);


        ZelleAlias zelleAlias = new ZelleAlias();
        zelleAlias.setAliasId(12);
        zelleAlias.setAliasType("phoneNumber");
        zelleAlias.setAliasValue("2345@gmail.com");

        List<ZelleAlias>zelleAliases=Arrays.asList(zelleAlias);
        user.setZelleAlias(zelleAliases);


        List<Users> users = Arrays.asList(user);

        when(usersRepository.findAll()).thenReturn(users);

        try {
            List<UsersTO> users1 = userServices.getUsers();
            assertEquals(1,users1.size());
        } catch (UsersException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void getUsers_whenUserDetailsNotExist_thenThrowException(){
        List<Users>users = new ArrayList<>();

        when(usersRepository.findAll()).thenReturn(users);
        assertThrows(UsersException.class,()->userServices.getUsers());

    }

    @Test
    public void getUserId_whenUserIdDetailsExist_thenReturnUserData(){
        Users user = new Users();
        user.setUserId(1);
        user.setFullName("abisheak");
        user.setEmail("abisheak@gmail.com");
        user.setPhoneNumber("9234567876");
        user.setPasswordHash("hash12334");
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus("Active");

        BankAccounts bankAccounts = new BankAccounts();
        bankAccounts.setAccountId(123);
        bankAccounts.setBankName("Sbi");
        bankAccounts.setAccountNumber("123456789");
        bankAccounts.setIfscCode("ifscSbi2334");
        bankAccounts.setIsPrimary(234);
        bankAccounts.setCreatedAt(LocalDateTime.now());

        List<BankAccounts>bankAcc=Arrays.asList(bankAccounts);
        user.setBankAccounts(bankAcc);

        Transactions transactions = new Transactions();
        transactions.setTransactionId(12);
        transactions.setReceiverAccountId(12);
        transactions.setSenderAccountId(3455);
        transactions.setAmount(1000);
        transactions.setCurrency("rupee");
        transactions.setStatus("cmpt");
        transactions.setReferenceNote("Abc");
        transactions.setCreatedAt(LocalDateTime.now());
        transactions.setCompletedAt(LocalDateTime.now());
        List<Transactions>sndTransaction= Arrays.asList(transactions);
        user.setSenderTransactions(sndTransaction);

        Transactions transaction = new Transactions();
        transaction.setTransactionId(12);
        transaction.setReceiverAccountId(12);
        transaction.setSenderAccountId(3455);
        transaction.setAmount(1000);
        transaction.setCurrency("rupee");
        transaction.setStatus("cmpt");
        transaction.setReferenceNote("Abc");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setCompletedAt(LocalDateTime.now());
        List<Transactions>recTransaction= Arrays.asList(transaction);
        user.setReceiverTransactions(recTransaction);


        ZelleAlias zelleAlias = new ZelleAlias();
        zelleAlias.setAliasId(12);
        zelleAlias.setAliasType("phoneNumber");
        zelleAlias.setAliasValue("2345@gmail.com");

        List<ZelleAlias>zelleAliases=Arrays.asList(zelleAlias);
        user.setZelleAlias(zelleAliases);

        when(usersRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        try {
            UsersTO usersTO = userServices.getUserId(user.getUserId());
            assertEquals(1,usersTO.getUserId());
        } catch (UsersException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void getUsersId_whenUserIdDetailsNotExist_thenThrowException(){

        int id =0;
        when(usersRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(UsersException.class,()->userServices.getUserId(id));

    }
    @Test
    public void getUserByPhoneNumber_whenUserPhnNumberExist_thenReturnUserData(){
        Users user = new Users();
        user.setUserId(1);
        user.setFullName("abisheak");
        user.setEmail("abisheak@gmail.com");
        user.setPhoneNumber("9234567876");
        user.setPasswordHash("hash12334");
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus("Active");

        BankAccounts bankAccounts = new BankAccounts();
        bankAccounts.setAccountId(123);
        bankAccounts.setBankName("Sbi");
        bankAccounts.setAccountNumber("123456789");
        bankAccounts.setIfscCode("ifscSbi2334");
        bankAccounts.setIsPrimary(234);
        bankAccounts.setCreatedAt(LocalDateTime.now());

        List<BankAccounts>bankAcc=Arrays.asList(bankAccounts);
        user.setBankAccounts(bankAcc);

        Transactions transactions = new Transactions();
        transactions.setTransactionId(12);
        transactions.setReceiverAccountId(12);
        transactions.setSenderAccountId(3455);
        transactions.setAmount(1000);
        transactions.setCurrency("rupee");
        transactions.setStatus("cmpt");
        transactions.setReferenceNote("Abc");
        transactions.setCreatedAt(LocalDateTime.now());
        transactions.setCompletedAt(LocalDateTime.now());
        List<Transactions>sndTransaction= Arrays.asList(transactions);
        user.setSenderTransactions(sndTransaction);

        Transactions transaction = new Transactions();
        transaction.setTransactionId(12);
        transaction.setReceiverAccountId(12);
        transaction.setSenderAccountId(3455);
        transaction.setAmount(1000);
        transaction.setCurrency("rupee");
        transaction.setStatus("cmpt");
        transaction.setReferenceNote("Abc");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setCompletedAt(LocalDateTime.now());
        List<Transactions>recTransaction= Arrays.asList(transaction);
        user.setReceiverTransactions(recTransaction);


        ZelleAlias zelleAlias = new ZelleAlias();
        zelleAlias.setAliasId(12);
        zelleAlias.setAliasType("phoneNumber");
        zelleAlias.setAliasValue("2345@gmail.com");

        List<ZelleAlias>zelleAliases=Arrays.asList(zelleAlias);
        user.setZelleAlias(zelleAliases);

        when(usersRepository.findByPhoneNumber(user.getPhoneNumber())).thenReturn(user);
        try {
            UsersTO usersTO = userServices.getUserByPhoneNumber(user.getPhoneNumber());
            assertEquals(1,usersTO.getUserId());
        } catch (UsersException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void saveUserRecord_whenUserInserted_thenReturnUserId(){

        // given
        UserRequest users = new UserRequest();
        users.setFullName("Abishek");
        users.setEmail("abi@gmail.com");
        users.setPhoneNumber("9123456788");
        users.setPasswordHash("hash12344");
        users.setCreatedAt(LocalDateTime.now());
        users.setStatus("active");

        Users savedUser = new Users();
        savedUser.setUserId(1);
        savedUser.setFullName(users.getFullName());
        savedUser.setEmail(users.getEmail());
        savedUser.setPhoneNumber(users.getPhoneNumber());
        savedUser.setPasswordHash(users.getPasswordHash());
        savedUser.setCreatedAt(users.getCreatedAt());
        savedUser.setStatus(users.getStatus());

        when(usersRepository.save(any())).thenReturn(savedUser);

        try {
            Integer res = userServices.saveUser(users);
            assertEquals(1, res);
        } catch (UsersException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void saveUser_whenUserIdNotExist_thenThrowException(){
        UserRequest userRequest = new UserRequest();
        userRequest.setFullName("Abi");
        userRequest.setEmail("abi@gmail.com");
        userRequest.setPhoneNumber("9876543210");
        userRequest.setPasswordHash("test123");
        when(usersRepository.save(any()))
                .thenReturn(null);

        assertThrows(UsersException.class,
                () -> userServices.saveUser(userRequest)
        );
    }
    @Test
    public void updateUserRecord_whenUserFound_thenReturnUpdateUser() throws UsersException {

        // given
        UserRequestUpdate users = new UserRequestUpdate();
        users.setFullName("Abishek");
        users.setEmail("abi@gmail.com");
        users.setPhoneNumber("9123456788");
        users.setPasswordHash("hash12344");
        users.setCreatedAt(LocalDateTime.now());
        users.setStatus("active");

        Users user = new Users();
        user.setUserId(1);
        user.setFullName(users.getFullName());
        user.setEmail(users.getEmail());
        user.setPhoneNumber(users.getPhoneNumber());
        user.setPasswordHash(users.getPasswordHash());
        user.setCreatedAt(users.getCreatedAt());
        user.setStatus(users.getStatus());

        when(usersRepository.findById(anyInt()))
                .thenReturn(Optional.of(user));

        when(usersRepository.save(any())).thenReturn(user);

        // when
        int res = userServices.updateUser(users);

        // then
        assertEquals(1, res);
    }
    @Test
    public void updateUser_whenUserIdNotExist_thenThrowException(){
        UserRequestUpdate request = new UserRequestUpdate();

        request.setUserId(1);
        request.setFullName("Abi");
        request.setEmail("abi@gmail.com");
        request.setPhoneNumber("9876543210");
        request.setPasswordHash("test123");

        when(usersRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        try {
            int result = userServices.updateUser(request);
            assertEquals(0, result);
        } catch (UsersException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void deleteUser_whenUserIdExist_thenDeleteIt(){

        Users users = new Users();
        users.setUserId(1);

        when(usersRepository.findById(anyInt()))
                .thenReturn(Optional.of(users));

        try {
            String result = userServices.deleteUser(1);
            verify(usersRepository, times(1)).delete(users);
            assertEquals("This user details was deleted", result);
        } catch (UsersException e) {
            throw new RuntimeException(e);
        }



    }
    @Test
    public void deleteUser_whenUserIdNotExist_thenReturnException(){

        when(usersRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        try {
           String result = userServices.deleteUser(1);

            assertEquals("this user detail not founded", result);
        } catch (UsersException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void deleteUserByFullName_whenUserExists_thenDeleteUser() {

        Users user = new Users();
        user.setUserId(1);
        user.setFullName("Abi");

        when(usersRepository.findByFullName(anyString()))
                .thenReturn(user);

        String result = userServices.deleteUserByFullName(user.getFullName());

        assertEquals("User deleted successfully", result);
    }
    @Test
    public void deleteUserByFullName_whenUserNotFound_thenReturnMessage() {

        when(usersRepository.findByFullName(anyString()))
                .thenReturn(null);

        String result = userServices.deleteUserByFullName("Abi");

        assertEquals("User not found", result);
    }
}
