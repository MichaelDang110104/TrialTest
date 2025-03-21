package com.fpt.abc.repos;

import com.fpt.abc.pojos.AccountMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepo extends JpaRepository<AccountMember, String> {
    AccountMember getAccountMemberByEmailAddress(String emailAddress);

    AccountMember getAccountMemberByEmailAddressAndMemberPassword(String emailAddress, String memberPassword);
}
