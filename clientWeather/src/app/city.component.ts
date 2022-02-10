import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styleUrls: ['./city.component.css']
})
export class CityComponent implements OnInit {

  cityList = [ "Singapore", "London", "Tokyo", "Taipei", "Kuala Lumpur", "San Francisco" ]

  constructor(private router: Router) { }

  ngOnInit(): void { }

  go(city: string) {
    const params = { fields: 'imperial' }
    this.router.navigate(['/weather', city], { queryParams: params})
  }

}
