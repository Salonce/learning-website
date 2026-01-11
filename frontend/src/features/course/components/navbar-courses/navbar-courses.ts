import { Component } from '@angular/core';
import { AuthService } from '../../../../core/auth-service/auth-service';
import { Observable } from 'rxjs/internal/Observable';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Principal } from '../../../../core/models/principal';

@Component({
  selector: 'app-navbar-courses',
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar-courses.html',
  styleUrl: './navbar-courses.css'
})
export class NavbarCourses {
  isOpen = false;
  
  principal$: Observable<Principal | null>;

  constructor(private authService: AuthService){
    this.principal$ = this.authService.principal$;
  }

  onLogout() : void {
    this.authService.logout();
  }
}
