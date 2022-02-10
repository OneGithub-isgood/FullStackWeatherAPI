import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule} from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CityComponent } from './city.component';
import { WeatherComponent } from './weather.component';
import { weatherService } from './weather.service';

const appRoutes: Routes = [
  { path: '', component: CityComponent },
  { path: 'weather/:city', component: WeatherComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    CityComponent,
    WeatherComponent
  ],
  imports: [
    BrowserModule,
    //AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [ weatherService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
