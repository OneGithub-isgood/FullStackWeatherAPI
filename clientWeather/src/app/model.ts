export interface Weather { //Variable name must be same as incoming JSON data if planning to directly pass in variable to HTML
  city: string
  main: string
  description: string
  icon: string
  temp: number
}
