import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientService } from '../../services/client.service';
import { FormsModule } from '@angular/forms'; // si tu veux ajouter un filtre ou input plus tard

@Component({
  selector: 'app-client',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  clients: any[] = [];
  message = '';

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients() {
    this.clientService.getAllClients().subscribe({
      next: (data: any[]) => {
        this.clients = data;
        this.message = `Total clients : ${data.length}`;
      },
      error: (err: any) => {
        console.error('Erreur API clients:', err);
        this.message = 'Erreur lors de la récupération des clients';
      }
    });
  }
}
