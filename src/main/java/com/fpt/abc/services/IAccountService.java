package com.fpt.abc.services;

import com.fpt.abc.dtos.AuthenRequest;
import com.fpt.abc.pojos.AccountMember;

import java.util.List;

public interface IAccountService {
    public void createAccount(AccountMember accountMember);

    public void updateAccount(AccountMember accountMember, String id);

    public void deleteAccount(String accountID);

    public AccountMember getAccountByID(String accountID);

    public List<AccountMember> getAllAccounts();

    public AccountMember login(AuthenRequest authenRequest);
}
