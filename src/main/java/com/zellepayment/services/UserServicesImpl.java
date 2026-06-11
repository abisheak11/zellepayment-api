package com.zellepayment.services;

import com.zellepayment.entity.Users;
import com.zellepayment.exception.UsersException;
import com.zellepayment.mapper.UserMapper;
import com.zellepayment.model.*;
import com.zellepayment.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserServicesImpl implements UserService {

    @Autowired
    public UsersRepository usersRepository;

    @Override
    public List<UsersTO> getUsers() throws UsersException {
        log.info("inside getUsers");

        List<Users> users = usersRepository.findAll();

        if (CollectionUtils.isEmpty(users)) {
            throw new UsersException("No users found");
        }

        return users.stream().map(user -> {

            UsersTO dto = new UsersTO();
            dto.setUserId(user.getUserId());
            dto.setFullName(user.getFullName());
            dto.setEmail(user.getEmail());
            dto.setPhoneNumber(user.getPhoneNumber());
            dto.setPasswordHash(user.getPasswordHash());
            dto.setCreatedAt(user.getCreatedAt());
            dto.setStatus(user.getStatus());

            // Bank Accounts
            if (!CollectionUtils.isEmpty(user.getBankAccounts())) {
                List<BankAccountsTO> bankList = user.getBankAccounts().stream().map(acc -> {
                    BankAccountsTO b = new BankAccountsTO();
                    b.setAccountId(acc.getAccountId());
                    b.setBankName(acc.getBankName());
                    b.setAccountNumber(acc.getAccountNumber());
                    b.setIfscCode(acc.getIfscCode());
                    b.setIsPrimary(acc.getIsPrimary());
                    b.setCreatedAt(acc.getCreatedAt());
                    return b;
                }).toList();
                dto.setBankAccountsTO(bankList);
            }

            // Sender Transactions
            if (!CollectionUtils.isEmpty(user.getSenderTransactions())) {
                List<TransactionsTO> senderTx = user.getSenderTransactions().stream().map(tx -> {
                    TransactionsTO t = new TransactionsTO();
                    t.setTransactionId(tx.getTransactionId());
                    t.setReceiverAccountId(tx.getReceiverAccountId());
                    t.setSenderAccountId(tx.getSenderAccountId());
                    t.setAmount(tx.getAmount());
                    t.setCurrency(tx.getCurrency());
                    t.setStatus(tx.getStatus());
                    t.setReferencesNote(tx.getReferenceNote());
                    t.setCreatedAt(tx.getCreatedAt());
                    t.setCompletedAt(tx.getCompletedAt());
                    return t;
                }).toList();
                dto.setSenderTransactionsTO(senderTx);
            }

            // Receiver Transactions
            if (!CollectionUtils.isEmpty(user.getReceiverTransactions())) {
                List<TransactionsTO> receiverTx = user.getReceiverTransactions().stream().map(tx -> {
                    TransactionsTO t = new TransactionsTO();
                    t.setTransactionId(tx.getTransactionId());
                    t.setReceiverAccountId(tx.getReceiverAccountId());
                    t.setSenderAccountId(tx.getSenderAccountId());
                    t.setAmount(tx.getAmount());
                    t.setCurrency(tx.getCurrency());
                    t.setStatus(tx.getStatus());
                    t.setReferencesNote(tx.getReferenceNote());
                    t.setCreatedAt(tx.getCreatedAt());
                    t.setCompletedAt(tx.getCompletedAt());
                    return t;
                }).toList();
                dto.setReceiverTransactionsTO(receiverTx);
            }

            // Zelle Alias
            if (!CollectionUtils.isEmpty(user.getZelleAlias())) {
                List<ZelleAliasTO> aliasList = user.getZelleAlias().stream().map(z -> {
                    ZelleAliasTO a = new ZelleAliasTO();
                    a.setAliasId(z.getAliasId());
                    a.setAliasType(z.getAliasType());
                    a.setAliasValue(z.getAliasValue());
                    return a;
                }).toList();
                dto.setZelleAliasTO(aliasList);
            }

            return dto;

        }).toList();
    }

    @Override
    public UsersTO getUserId(int id) throws UsersException {
        log.info("inside the userServicesImpl.getUserId");
        UsersTO usersTO = usersRepository.findById(id).map(UserMapper::getUserMapper).orElseThrow
                (() -> {
                    log.info("User Details not found for this userId:{}", id);
                    return new UsersException("UserDetail not found for this userId");
                });
        return usersTO;
    }

    @Override
    public UsersTO getUserByPhoneNumber(String phoneNumber) throws UsersException {
        log.info("inside the userServicesImpl.getUserByPhoneNumber");
        Users users = usersRepository.findByPhoneNumber(phoneNumber);
        UsersTO dto = new UsersTO();
        dto.setUserId(users.getUserId());
        dto.setFullName(users.getFullName());
        dto.setEmail(users.getEmail());
        dto.setPhoneNumber(users.getPhoneNumber());
        dto.setPasswordHash(users.getPasswordHash());
        dto.setCreatedAt(users.getCreatedAt());
        dto.setStatus(users.getStatus());
        return dto;
    }

    @Override
    public Integer saveUser(UserRequest userRequests) throws UsersException {
        log.info("Inside the UserServices.saveUser,userRequest:{}", userRequests);
        Users users = new Users();
        users.setFullName(userRequests.getFullName());
        users.setEmail(userRequests.getEmail());
        users.setPhoneNumber(userRequests.getPhoneNumber());
        users.setPasswordHash(userRequests.getPasswordHash());
        users.setCreatedAt(userRequests.getCreatedAt());
        users.setStatus(userRequests.getStatus());
        Users saveUser = usersRepository.save(users);

        log.info("User details has been saved :{}", saveUser);
        if (Objects.isNull(saveUser)) {
            log.info("User details has Been Saved:{}", saveUser);
            throw new UsersException("User details are not saved");
        }
        return saveUser.getUserId();
    }

    @Override
    public Integer updateUser(UserRequestUpdate userRequest) throws UsersException {

        log.info("Inside updateUser, userRequest:{}", userRequest);
        Optional<Users> optionalUser = usersRepository.findById(userRequest.getUserId());

        if (optionalUser.isEmpty()) {
            log.error("User not found");
            return 0;
        }

        // Get existing user from DB
        Users users = optionalUser.get();

        // Update fields (not create new object)
        users.setFullName(userRequest.getFullName());
        users.setEmail(userRequest.getEmail());
        users.setPhoneNumber(userRequest.getPhoneNumber());
        users.setPasswordHash(userRequest.getPasswordHash());
        users.setCreatedAt(userRequest.getCreatedAt());
        users.setStatus(userRequest.getStatus());

        // This will now perform UPDATE, not INSERT
        Users saveUser = usersRepository.save(users);

        log.info("User updated: {}", saveUser);
        return saveUser.getUserId();
    }

    public String deleteUser(int userId) throws UsersException {

        log.info("Inside userServiceImpl.userDelete");
        Optional<Users> users = usersRepository.findById(userId);

        if (users.isEmpty()) {
            log.info("user is not found for this user id:{}", userId);
            return "this user detail not founded";
        }
        usersRepository.delete(users.get());
        return "This user details was deleted";}

    @Override
    public String deleteUserByFullName(String fullName) {
        log.info("inside deleteUserByFullName userName:{}", fullName);
        Users user = usersRepository.findByFullName(fullName);
        if (user == null) {
            log.info("User not found: {}", fullName);
            return "User not found";}

        usersRepository.delete(user);
        return "User deleted successfully";}

//    @Override
//    public List<UsersTO> getRecords(String userid) throws UsersException {
//        log.info("inside the getRecords");
//        List<Users>users = usersRepository.findAllById();
//        if (CollectionUtils.isEmpty(users)){
//            log.info("user details Not founded for this id's :{}",userid);
//        }
//
//        return users.stream().map(user -> {
//
//            UsersTO dto = new UsersTO();
//            dto.setUserId(user.getUserId());
//            dto.setFullName(user.getFullName());
//            dto.setEmail(user.getEmail());
//            dto.setPhoneNumber(user.getPhoneNumber());
//            dto.setPasswordHash(user.getPasswordHash());
//            dto.setCreatedAt(user.getCreatedAt());
//            dto.setStatus(user.getStatus());
//            return dto;
//        }).toList();
//
//    }


}
