import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Weather } from './model';
import { weatherService } from './weather.service';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  weather!: Weather
  city = ''
  fields = ''

  constructor(private activatedRoute: ActivatedRoute, private weatherServ: weatherService) { }

  ngOnInit(): void {
    this.city = this.activatedRoute.snapshot.params['city']
    this.fields = this.activatedRoute.snapshot.queryParams['fields']
    console.info('City: ', this.city)
    console.info('Query Fields: ', this.fields)

    this.weatherServ.getWeather(this.city)
      .then(w => this.weather = w)
  }

}
