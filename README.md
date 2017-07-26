# AirlineReservationExcerise
An airline reservation system I made to practice my programming while at University.

# Description

An airline reservation system.  Create a system to track flights between different airports, including the departure time and arrival time. Keep track of the base price of the flight.  The actual flight price is figured by the capacity of the plane and the number of days before the flight leaves when a reservation is booked.   To keep things simple, the price is always the base price if the flight is more than 2 weeks away.  However, if there is less than 2 weeks to the flight, the following formula is used to figure out the price:

Over 75% capacity? Double price.  The flight is popular, and we can make more money.
Between 50% and 75%? Normal price.
Less than 50%. Half price.  We need to fill the seats, drop the price.
 

The database must know the type of aircraft and the number of seats on the plane.  The database must track the each passenger.  This includes there contact information, their meal option, price paid, and reservation.  A reservation can include more than one flight (think round trip) and the seat assignment on each flight.

 

Some questions the database can answer:

What flights go from one city to another on a given day? Show all times and current prices.
Who is in each seat on a given flight?
What flights has a customer taken in the last year?
What flights are the most popular?
Who is flying only one way and not a US citizen? The DHS is interested in this.