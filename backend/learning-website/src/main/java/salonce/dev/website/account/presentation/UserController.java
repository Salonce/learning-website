package salonce.dev.website.account.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import salonce.dev.website.account.application.AccountService;
import salonce.dev.website.account.domain.Role;
import salonce.dev.website.account.presentation.dtos.UserResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final AccountService accountService;

    @PreAuthorize("hasAuthority('user:read:all')")
    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.ok(accountService.getAllUsers());
    }

    @PreAuthorize("hasAuthority('user:read:own')")
    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> getMyUser(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getUser(id));
    }

    @PreAuthorize("hasAuthority('user:update:any')")
    @PostMapping("/api/users/{id}/roles/{role}")
    public ResponseEntity<UserResponse> addRole(@PathVariable Long id,@PathVariable Role role) {
        return ResponseEntity.ok(accountService.addRole(id, role));
    }

    @PreAuthorize("hasAuthority('user:update:any')")
    @DeleteMapping("/api/users/{id}/roles/{role}")
    public ResponseEntity<UserResponse> removeRole(@PathVariable Long id, @PathVariable Role role) {
        return ResponseEntity.ok(accountService.removeRole(id, role));
    }
}
