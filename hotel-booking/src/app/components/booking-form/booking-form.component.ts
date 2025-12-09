import { Component, Input, OnInit } from '@angular/core';
import { BookingService } from '../../services/booking.service';
import { ClientService } from '../../services/client.service';
import { RoomService } from '../../services/room.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-booking-form',
  templateUrl: './booking-form.component.html',
  imports: [CommonModule, FormsModule],
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent implements OnInit {
  @Input() hotelId!: number;

  booking = { phoneClient: '', startDate: '', endDate: '', roomNumber: '' };
  rooms: any[] = [];

  showModal = false;
  newClient: any = { fullName: '', email: '', phone: '' };
  clientId!: number;

  constructor(
    private bookingService: BookingService,
    private clientService: ClientService,
    private roomService: RoomService
  ) {}

  ngOnInit(): void {
    this.loadRooms();
  }

  // Charger les chambres disponibles pour l'hôtel
  loadRooms() {
    if (!this.hotelId) return;

    this.roomService.getRoomsByHotel(this.hotelId).subscribe(
      rooms => {
        this.rooms = rooms.map(r => ({
          ...r,
          available: r.available === true || r.available === 'true'
        }));
        console.log('Rooms loaded:', this.rooms);
      },
      err => console.error('Erreur chargement des chambres:', err)
    );
  }

  // Vérifier le client et réserver
  checkClient() {
    if (!this.booking.phoneClient) {
      alert('Veuillez saisir un numéro de téléphone.');
      return;
    }
    if (!this.booking.roomNumber) {
      alert('Veuillez sélectionner une chambre.');
      return;
    }

    // Client déjà identifié
    if (this.clientId) {
      this.saveBooking(this.clientId);
      return;
    }

    // Vérifier si le client existe
    this.clientService.findByPhone(this.booking.phoneClient).subscribe(
      client => {
        if (client) {
          this.clientId = client.id;
          this.saveBooking(this.clientId);
        } else {
          this.newClient.phone = this.booking.phoneClient;
          this.showModal = true;
        }
      },
      err => {
        console.error(err);
        alert('Erreur lors de la recherche du client.');
      }
    );
  }

  closeModal() {
    this.showModal = false;
  }

  addNewClient() {
    if (!this.newClient.fullName || !this.newClient.email || !this.newClient.phone) {
      alert('Veuillez remplir tous les champs.');
      return;
    }

    this.clientService.addClient(this.newClient).subscribe(
      client => {
        this.showModal = false;
        alert('Client ajouté avec succès ! Vous pouvez maintenant cliquer sur Réserver.');
        this.clientId = client.id;
      },
      err => {
        console.error(err);
        alert('Erreur lors de l’ajout du client.');
      }
    );
  }

  saveBooking(clientId: number) {
    const bookingData = {
      hotelId: this.hotelId,
      phone: this.booking.phoneClient,
      startDate: this.booking.startDate,
      endDate: this.booking.endDate,
      roomNumber: this.booking.roomNumber
    };

    console.log('Booking data sent to backend:', bookingData);

    this.bookingService.createBooking(bookingData).subscribe(
      res => {
        alert('Réservation effectuée avec succès !');
        this.booking = { phoneClient: '', startDate: '', endDate: '', roomNumber: '' };
        //this.clientId = undefined!;
        this.loadRooms();  
      },
      err => {
        console.error('Erreur lors de la réservation:', err);
        alert('Erreur lors de la réservation.');
      }
    );
  }
}
