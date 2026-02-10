package salonce.dev.website.account.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salonce.dev.website.account.application.AccountService;
import salonce.dev.website.account.infrastructure.security.AccountPrincipal;
import salonce.dev.website.account.presentation.dtos.AccountResponse;
import salonce.dev.website.account.presentation.dtos.PatchProfileRequest;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @PreAuthorize("permitAll()")
    @GetMapping("/api/auth")
    public ResponseEntity<AccountPrincipal> getAuth(@AuthenticationPrincipal AccountPrincipal principal){
        return ResponseEntity.ok(principal);
    }

    @PreAuthorize("hasAuthority('user:read:own')")
    @GetMapping("/api/account")
    public ResponseEntity<AccountResponse> getOwnAccount(@AuthenticationPrincipal AccountPrincipal principal){
        return ResponseEntity.ok(AccountMapper.toAccountResponse(accountService.findAccount(principal.id())));
    }

    @PreAuthorize("hasAuthority('user:update:own')")
    @PatchMapping("/api/profile")
    public ResponseEntity<AccountResponse> patchProfile(@AuthenticationPrincipal AccountPrincipal principal, @RequestBody PatchProfileRequest patchProfileRequest){
        return ResponseEntity.ok(accountService.updateProfile(principal.id(), patchProfileRequest));
    }
}
