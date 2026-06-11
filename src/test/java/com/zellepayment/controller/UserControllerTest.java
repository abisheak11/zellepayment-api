package com.zellepayment.controller;

import com.zellepayment.exception.UsersException;
import com.zellepayment.model.*;
import com.zellepayment.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    public void getUsers_whenUserExist_returnsUserList(){
        UsersTO user = new UsersTO();
        user.setUserId(1);
        user.setFullName("abisheak");
        user.setEmail("abisheak@gmail.com");
        user.setPhoneNumber("9234567876");
        user.setPasswordHash("hash12334");
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus("Active");

        BankAccountsTO bankAccounts = new BankAccountsTO();
        bankAccounts.setAccountId(123);
        bankAccounts.setBankName("Sbi");
        bankAccounts.setAccountNumber("123456789");
        bankAccounts.setIfscCode("ifscSbi2334");
        bankAccounts.setIsPrimary(234);
        bankAccounts.setCreatedAt(LocalDateTime.now());

        List<BankAccountsTO>bankAcc= Arrays.asList(bankAccounts);
        user.setBankAccountsTO(bankAcc);

        TransactionsTO transactions = new TransactionsTO();
        transactions.setTransactionId(12);
        transactions.setReceiverAccountId(12);
        transactions.setSenderAccountId(3455);
        transactions.setAmount(1000);
        transactions.setCurrency("rupee");
        transactions.setStatus("cmpt");
        transactions.setReferencesNote("asd");
        transactions.setCreatedAt(LocalDateTime.now());
        transactions.setCompletedAt(LocalDateTime.now());
        List<TransactionsTO>sndTransaction= Arrays.asList(transactions);
        user.setSenderTransactionsTO(sndTransaction);

        TransactionsTO transaction = new TransactionsTO();
        transaction.setTransactionId(12);
        transaction.setReceiverAccountId(12);
        transaction.setSenderAccountId(3455);
        transaction.setAmount(1000);
        transaction.setCurrency("rupee");
        transaction.setStatus("cmpt");
        transaction.setReferencesNote("Abc");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setCompletedAt(LocalDateTime.now());
        List<TransactionsTO>recTransaction= Arrays.asList(transaction);
        user.setReceiverTransactionsTO(recTransaction);


        ZelleAliasTO zelleAlias = new ZelleAliasTO();
        zelleAlias.setAliasId(12);
        zelleAlias.setAliasType("phoneNumber");
        zelleAlias.setAliasValue("2345@gmail.com");

        List<ZelleAliasTO>zelleAliases=Arrays.asList(zelleAlias);
        user.setZelleAliasTO(zelleAliases);

        List<UsersTO>users = Arrays.asList(user);

        try {
            when(userService.getUsers()).thenReturn(users);
        } catch (UsersException e) {
            throw new RuntimeException(e);
        }
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        try {
            mockMvc.perform(requestBuilder).andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void getUserById_whenUserIdExist_returnUser(){
        UsersTO user = new UsersTO();
        user.setUserId(1);
        user.setFullName("abisheak");
        user.setEmail("abisheak@gmail.com");
        user.setPhoneNumber("9234567876");
        user.setPasswordHash("hash12334");
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus("Active");

        BankAccountsTO bankAccounts = new BankAccountsTO();
        bankAccounts.setAccountId(123);
        bankAccounts.setBankName("Sbi");
        bankAccounts.setAccountNumber("123456789");
        bankAccounts.setIfscCode("ifscSbi2334");
        bankAccounts.setIsPrimary(234);
        bankAccounts.setCreatedAt(LocalDateTime.now());

        List<BankAccountsTO>bankAcc= Arrays.asList(bankAccounts);
        user.setBankAccountsTO(bankAcc);

        TransactionsTO transactions = new TransactionsTO();
        transactions.setTransactionId(12);
        transactions.setReceiverAccountId(12);
        transactions.setSenderAccountId(3455);
        transactions.setAmount(1000);
        transactions.setCurrency("rupee");
        transactions.setStatus("cmpt");
        transactions.setReferencesNote("asd");
        transactions.setCreatedAt(LocalDateTime.now());
        transactions.setCompletedAt(LocalDateTime.now());
        List<TransactionsTO>sndTransaction= Arrays.asList(transactions);
        user.setSenderTransactionsTO(sndTransaction);

        TransactionsTO transaction = new TransactionsTO();
        transaction.setTransactionId(12);
        transaction.setReceiverAccountId(12);
        transaction.setSenderAccountId(3455);
        transaction.setAmount(1000);
        transaction.setCurrency("rupee");
        transaction.setStatus("cmpt");
        transaction.setReferencesNote("Abc");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setCompletedAt(LocalDateTime.now());
        List<TransactionsTO>recTransaction= Arrays.asList(transaction);
        user.setReceiverTransactionsTO(recTransaction);


        ZelleAliasTO zelleAlias = new ZelleAliasTO();
        zelleAlias.setAliasId(12);
        zelleAlias.setAliasType("phoneNumber");
        zelleAlias.setAliasValue("2345@gmail.com");

        List<ZelleAliasTO>zelleAliases=Arrays.asList(zelleAlias);
        user.setZelleAliasTO(zelleAliases);
        try {
            when(userService.getUserId(user.getUserId())).thenReturn(user);
        } catch (UsersException e) {
            throw new RuntimeException(e);
        }
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        try {
            mockMvc.perform(requestBuilder).andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    public void getUserByPhone_whenUserPhoneExist_returnUser(){
        UsersTO user = new UsersTO();
        user.setUserId(1);
        user.setFullName("abisheak");
        user.setEmail("abisheak@gmail.com");
        user.setPhoneNumber("9234567876");
        user.setPasswordHash("hash12334");
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus("Active");

        BankAccountsTO bankAccounts = new BankAccountsTO();
        bankAccounts.setAccountId(123);
        bankAccounts.setBankName("Sbi");
        bankAccounts.setAccountNumber("123456789");
        bankAccounts.setIfscCode("ifscSbi2334");
        bankAccounts.setIsPrimary(234);
        bankAccounts.setCreatedAt(LocalDateTime.now());

        List<BankAccountsTO>bankAcc= Arrays.asList(bankAccounts);
        user.setBankAccountsTO(bankAcc);

        TransactionsTO transactions = new TransactionsTO();
        transactions.setTransactionId(12);
        transactions.setReceiverAccountId(12);
        transactions.setSenderAccountId(3455);
        transactions.setAmount(1000);
        transactions.setCurrency("rupee");
        transactions.setStatus("cmpt");
        transactions.setReferencesNote("asd");
        transactions.setCreatedAt(LocalDateTime.now());
        transactions.setCompletedAt(LocalDateTime.now());
        List<TransactionsTO>sndTransaction= Arrays.asList(transactions);
        user.setSenderTransactionsTO(sndTransaction);

        TransactionsTO transaction = new TransactionsTO();
        transaction.setTransactionId(12);
        transaction.setReceiverAccountId(12);
        transaction.setSenderAccountId(3455);
        transaction.setAmount(1000);
        transaction.setCurrency("rupee");
        transaction.setStatus("cmpt");
        transaction.setReferencesNote("Abc");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setCompletedAt(LocalDateTime.now());
        List<TransactionsTO>recTransaction= Arrays.asList(transaction);
        user.setReceiverTransactionsTO(recTransaction);


        ZelleAliasTO zelleAlias = new ZelleAliasTO();
        zelleAlias.setAliasId(12);
        zelleAlias.setAliasType("phoneNumber");
        zelleAlias.setAliasValue("2345@gmail.com");

        List<ZelleAliasTO>zelleAliases=Arrays.asList(zelleAlias);
        user.setZelleAliasTO(zelleAliases);
        try {
            when(userService.getUserByPhoneNumber(anyString()))
                    .thenReturn(user);

        } catch (UsersException e) {
            throw new RuntimeException(e);
        }
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/phone?phone=9234567876")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        try {
            mockMvc.perform(requestBuilder).andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    public void save_whenValidRequest_theSaveUserDetails() throws Exception {
        when(userService.saveUser(any())).thenReturn(1);

        UserRequest users = new UserRequest();
        users.setFullName("Abishek");
        users.setEmail("abi@gmail.com");
        users.setPhoneNumber("9123456788");
        users.setPasswordHash("hash12344");
        users.setStatus("active");

        ObjectMapper objectMapper = new ObjectMapper();

            String req = objectMapper.writeValueAsString(users);
            RequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/users")
                    .content(req)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON);
            mockMvc.perform(builder).andExpect(status().isOk());
    }
    @Test
    public void update_whenValidRequest_thenUpdateUserDetails() throws Exception {

        UserRequestUpdate users = new UserRequestUpdate();
        users.setUserId(1);
        users.setFullName("Abishek");
        users.setEmail("abi@gmail.com");
        users.setPhoneNumber("9123456788");
        users.setPasswordHash("hash12344");
        users.setStatus("active");
        when(userService.updateUser(any())).thenReturn(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String req = objectMapper.writeValueAsString(users);

        RequestBuilder builder = MockMvcRequestBuilders.put("/api/v1/users")
                .content(req)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(builder).andExpect(status().isOk());
    }
    @Test
    public void deleteUser_whenUserIdExit_thenDeleteUser() throws Exception {
        when(userService.deleteUser(anyInt())).thenReturn("This user details was deleted");
        RequestBuilder builder = MockMvcRequestBuilders.delete("/api/v1/users/userId?userId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(builder).andExpect(status().isOk());
    }
    @Test
    public void deleteUser_whenUserFullNameExit_thenDeleteUser() throws Exception {
        when(userService.deleteUserByFullName(anyString())).thenReturn("This user details was deleted");
        RequestBuilder builder = MockMvcRequestBuilders.delete("/api/v1/users/name?name=abi")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(builder).andExpect(status().isOk());
    }
}
