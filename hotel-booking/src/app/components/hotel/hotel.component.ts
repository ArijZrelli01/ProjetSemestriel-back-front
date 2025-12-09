import { Component, OnInit } from '@angular/core';
import { HotelService } from '../../services/hotel.service';
import { BookingService } from '../../services/booking.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BookingFormComponent } from "../booking-form/booking-form.component";

@Component({
  selector: 'app-hotel',
    standalone: true,
      imports: [CommonModule, FormsModule, BookingFormComponent],

  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {
  hotels: any[] = [];
  selectedHotelId: number | null = null; // pour savoir quel hôtel on réserve

  constructor(private hotelService: HotelService) {}

  ngOnInit(): void {
    this.loadHotels();
  }

  loadHotels() {
    this.hotelService.getAllHotels().subscribe(data => {
      this.hotels = data;
    });
  }

  selectHotel(hotelId: number) {
    this.selectedHotelId = hotelId;
  }
}