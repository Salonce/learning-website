import { Component } from '@angular/core';
import { AccountService } from '../../services/account-service/account-service';
import { Account } from '../../models/account';

@Component({
  selector: 'app-settings-page',
  imports: [],
  templateUrl: './settings-page.html',
  styleUrl: './settings-page.css'
})
export class SettingsPageComponent {

  account: Account | null = null;

  constructor(private accountService: AccountService) {}

  ngOnInit(): void {
    this.accountService.getAccount().subscribe({
      next: (data) => this.account = data,
      error: (err) => console.error('Failed to fetch accounts', err)
    });
  }
}
