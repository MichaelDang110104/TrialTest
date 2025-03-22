package com.fpt.abc.controller;

import com.fpt.abc.dtos.AuthenRequest;
import com.fpt.abc.pojos.AccountMember;
import com.fpt.abc.services.IAccountService;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
@CrossOrigin("*")
public class AccountMemberController {
    private String jwtSecretKey = "0c01d01bf34158899ed1808c36e14cfb5e811f36f948b223a5e011ca9fbf6c64";

    private String issuer = "SE183660";

    private IAccountService iAccountService;

    private AuthenticationManager authenticationManager;

    @Autowired
    public AccountMemberController(IAccountService iAccountService, AuthenticationManager authenticationManager) {
        this.iAccountService = iAccountService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/get-all-accounts")
    public ResponseEntity<Object> getAllAccounts() {
        List<AccountMember> allAccounts = iAccountService.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }

    @GetMapping("/get-account")
    public ResponseEntity<Object> getAccount(String accountId) {
        AccountMember accountMember = iAccountService.getAccountByID(accountId);
        return ResponseEntity.ok(accountMember);
    }

    @PutMapping("/update-account")
    public ResponseEntity<Object> updateAccount(@RequestBody AccountMember accountMember, @RequestParam String accountId) {
        iAccountService.updateAccount(accountMember, accountId);
        return ResponseEntity.ok("Updated account successfully !");
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<Object> deleteAccount(@RequestParam String accountId) {
        iAccountService.deleteAccount(accountId);
        return ResponseEntity.ok("Deleted account successfully !");
    }


    @PostMapping("/create-account")
    public ResponseEntity<Object> createAccount(@RequestBody AccountMember accountMember) {
        iAccountService.createAccount(accountMember);
        return ResponseEntity.ok("Created account successfully !");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginAccount(@RequestBody AuthenRequest authenRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenRequest.getAccountEmail(),
                        authenRequest.getAccountPassword()
                )
        );
        AccountMember account = iAccountService.login(authenRequest);
        Map returnObj = new HashMap();
        returnObj.put("account", account);
        returnObj.put("token", createJwtToken(account));
        return ResponseEntity.ok(returnObj);
    }

    private String createJwtToken(AccountMember user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(24 * 3600))
                .subject(user.getEmailAddress())
                .claim("role", user.getMemberRole() == 1 ? "ADMIN" : "USER")
                .build();
        var encoder = new NimbusJwtEncoder(
                new ImmutableSecret<>(jwtSecretKey.getBytes()));
        var params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return encoder.encode(params).getTokenValue();
    }
}
