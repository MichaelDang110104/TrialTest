package com.fpt.abc.services;

import com.fpt.abc.dtos.AuthenRequest;
import com.fpt.abc.pojos.AccountMember;
import com.fpt.abc.repos.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepo iAccountRepo;

    @Override
    public void createAccount(AccountMember accountMember) {
        iAccountRepo.save(accountMember);
    }

    @Override
    public void updateAccount(AccountMember accountMember, String id) {
        AccountMember account = iAccountRepo.findById(id).get();
        account.setMemberPassword(accountMember.getMemberPassword());
        account.setEmailAddress(accountMember.getEmailAddress());
        account.setMemberRole(accountMember.getMemberRole());
        account.setMemberID(accountMember.getMemberID());
        iAccountRepo.save(account);
    }

    @Override
    public void deleteAccount(String accountID) {
        AccountMember account = iAccountRepo.findById(accountID).get();
        iAccountRepo.delete(account);
    }

    @Override
    public AccountMember getAccountByID(String accountID) {
        return iAccountRepo.findById(accountID).get();
    }

    @Override
    public List<AccountMember> getAllAccounts() {
        List<AccountMember> accounts = iAccountRepo.findAll();
        return accounts;
    }

    @Override
    public AccountMember login(AuthenRequest authenRequest) {
        String accountEmail = authenRequest.getAccountEmail();
        String accountPassword = authenRequest.getAccountPassword();
        AccountMember accountMember = iAccountRepo
                .getAccountMemberByEmailAddressAndMemberPassword(accountEmail, accountPassword);
        return accountMember;
    }
}
