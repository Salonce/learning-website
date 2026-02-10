package salonce.dev.website.account.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import salonce.dev.website.account.domain.Account;
import salonce.dev.website.account.domain.Role;
import salonce.dev.website.account.infrastructure.AccountRepository;
import salonce.dev.website.account.infrastructure.security.AccountPrincipal;
import salonce.dev.website.account.presentation.AccountMapper;
import salonce.dev.website.account.presentation.dtos.AccountResponse;
import salonce.dev.website.account.presentation.dtos.PatchProfileRequest;
import salonce.dev.website.account.presentation.AccountNotFound;
import salonce.dev.website.account.presentation.dtos.UserResponse;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public Account findAccount(Long id){
        return accountRepository.findById(id).orElseThrow(AccountNotFound::new);
    }

    @Transactional
    public AccountResponse updateProfile(Long id, PatchProfileRequest request){
        Account account = accountRepository.findById(id).orElseThrow(AccountNotFound::new);
        account.setName(request.name());
        return AccountMapper.toAccountResponse(accountRepository.save(account));
    }

    public List<UserResponse> getAllUsers(){
        return accountRepository.findAll().stream().map(AccountMapper::toUserResponse).toList();
    }

    public UserResponse getUser(Long id){
        Account account = accountRepository.findById(id).orElseThrow(AccountNotFound::new);
        return AccountMapper.toUserResponse(account);
    }

    @Transactional
    public UserResponse addRole(Long id, Role role){
        Account account = accountRepository.findById(id).orElseThrow(AccountNotFound::new);
        account.addRole(role);
        return AccountMapper.toUserResponse(account);
    }

    @Transactional
    public UserResponse removeRole(Long id, Role role){
        Account account = accountRepository.findById(id).orElseThrow(AccountNotFound::new);
        account.removeRole(role);
        return AccountMapper.toUserResponse(account);
    }


    // util

    private Account addAccount(AccountDto accountDto){
        Account newAccount = new Account(accountDto.email(), accountDto.name(), accountDto.subject(), accountDto.provider());
        if (accountRepository.count() == 0) newAccount.addRole(Role.ADMIN);
        return accountRepository.save(newAccount);
    }

    public Account loadOrCreateAccount(AccountDto accountDto){
        return accountRepository.findByIdentity(accountDto.subject(), accountDto.provider())
                .orElseGet(() -> addAccount(accountDto));
    }
}
