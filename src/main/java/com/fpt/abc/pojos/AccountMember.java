package com.fpt.abc.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AccountMember")
public class AccountMember {
    @Id
    @Column(name = "MemberID")
    private String MemberID;

    @Column(name = "MemberPassword")
    private String memberPassword;

    @Column(name = "EmailAddress")
    private String emailAddress;

    @Column(name = "MemberRole")
    private int memberRole;

    public AccountMember(String memberID, String memberPassword, String emailAddress, int memberRole) {
        MemberID = memberID;
        this.memberPassword = memberPassword;
        this.emailAddress = emailAddress;
        this.memberRole = memberRole;
    }

    public AccountMember() {
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(int memberRole) {
        this.memberRole = memberRole;
    }
}
