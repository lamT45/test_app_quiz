import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LeaderboardService, Player } from 'services/leaderboard.service';

@Component({
  selector: 'app-leaderboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.scss']
})
export class LeaderboardComponent implements OnInit {

  topPlayers: Player[] = [];
  others: Player[] = [];
  loading = true;
  errorMessage = '';

  constructor(private leaderboardService: LeaderboardService) {}

  ngOnInit(): void {
    this.leaderboardService.getAll().subscribe({
      next: (data: { top: Player[]; others: Player[] }) => {
        this.topPlayers = data.top;
        this.others = data.others;
        this.loading = false;
      },
      error: (err: unknown) => {
        console.error('Erreur de chargement du classement :', err);
        this.errorMessage = 'Erreur de chargement du classement';
        this.loading = false;
      }
    });
  }
}
