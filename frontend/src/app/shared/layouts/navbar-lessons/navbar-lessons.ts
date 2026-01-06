import { Component } from '@angular/core';
import { AuthService } from '../../../core/auth/auth-service/auth-service';
import { Observable } from 'rxjs/internal/Observable';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Principal } from '../../../core/models/principal';

@Component({
  selector: 'app-navbar-lessons',
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar-lessons.html',
  styleUrl: './navbar-lessons.css'
})
export class NavbarLessons {
  isOpen = false;
  
  principal$: Observable<Principal | null>;

  constructor(private authService: AuthService){
    this.principal$ = this.authService.principal$;
  }

  onLogout() : void {
    this.authService.logout();
  }
}
