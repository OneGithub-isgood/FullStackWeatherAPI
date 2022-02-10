import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { Weather } from "./model";

@Injectable()
export class weatherService {

  constructor(private http: HttpClient) {}

  getWeather(city: string): Promise<Weather> { //CSF Day 6 Notes Page 25 shows example on Query String
    return lastValueFrom(
      //this.http.get<Weather>(`http://localhost:8080/api/weather/${city}`)
      this.http.get<Weather>(`/api/weather/${city}`)
      //Symbol `(Backtick) delimit Template Literals which allows pass in variables and functions in JavaScript
      //Symbol '"(Single or Double quote) delimit String Literals will turn ${city} variable into literal string
    )
  }
}
