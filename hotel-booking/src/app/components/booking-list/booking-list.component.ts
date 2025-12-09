import { Component, OnInit } from '@angular/core';
import { BookingService } from '../../services/booking.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  imports: [CommonModule, FormsModule],
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent implements OnInit {
  bookings: any[] = [];
  phoneFilter = '';
  dateFilter = '';

  constructor(private bookingService: BookingService) {}

  ngOnInit() {
    this.loadBookings();
  }

  loadBookings() {
    const filter: any = {};
    if (this.phoneFilter) filter.phone = this.phoneFilter;
    if (this.dateFilter) filter.date = this.dateFilter;

    this.bookingService.getBookings(filter).subscribe(
      data => {
        console.log('Réservations reçues du backend:', data);

        this.bookings = data.map(b => ({
          ...b,
          id: b.id || b.booking?.id,
          phone: b.customer?.phone || b.phone,
          fullName: b.customer?.fullName || b.phone,
          hotelName: b.hotel?.name || 'Hôtel non défini',
          startDate: b.booking?.startDate || b.startDate,
          endDate: b.booking?.endDate || b.endDate
        }));
      },
      err => console.error('Erreur chargement des réservations:', err)
    );
  }

  resetFilters() {
    this.phoneFilter = '';
    this.dateFilter = '';
    this.loadBookings();
  }

  // ✅ Correction ici
  getRoomByBookingNumber(rooms: any[], bookingRoomNumber: string): any {
    if (!rooms || rooms.length === 0 || !bookingRoomNumber) return null;
    return rooms.find(r => r.number === bookingRoomNumber) || null;
  }

  deleteBooking(bookingId: number) {
    if (!bookingId) {
      alert('ID de réservation invalide.');
      return;
    }

    if (!confirm('Voulez-vous vraiment supprimer cette réservation ?')) return;

    this.bookingService.deleteBooking(bookingId).subscribe(
      res => {
        alert('Réservation supprimée !');
        this.loadBookings();
      },
      err => {
        console.error('Erreur suppression réservation:', err);
        alert('Impossible de supprimer la réservation.');
      }
    );
  }
}
