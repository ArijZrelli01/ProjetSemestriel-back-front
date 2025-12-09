import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { BookingFormComponent } from './components/booking-form/booking-form.component';
import { BookingListComponent } from './components/booking-list/booking-list.component';
import { HotelComponent } from './components/hotel/hotel.component';
import { ClientComponent } from './components/client/client.component';
import { RouterOutlet } from "@angular/router";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    BookingFormComponent,
    BookingListComponent,
    HotelComponent,
    ClientComponent,
    RouterOutlet
],
  templateUrl: './app.component.html'
})
export class AppComponent {

  showBookings = false;
}
