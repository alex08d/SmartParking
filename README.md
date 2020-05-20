# SmartParking
Smart Parking Simulator

Within the project there are 5 classes, namely: Entry Barrier, Exit Barrier, Parking, Car and Main.

Within the classes we have constructors, setters and getters for each parameter and method.

Barrier lift method () sends the message that the barrier has been lifted. (This method is common to the Entry Barrier and Exit Barrier classes).
The Barrier () descent method sends the message that the barrier has been raised. (This method is common to the Entry Barrier and Exit Barrier classes).
The stator1 () interrupt method sends the message that the power supply for the stator 1 winding is interrupted. (This method is common to the Entry Barrier and Exit Barrier classes).
The stator2 () interrupt method sends the message that the power supply for the stator 2 winding is interrupted. (This method is common to the Entry Barrier and Exit Barrier classes).
Power methodStator1 () sends the message that stator 1 is powered. (This method is common to the Entry Barrier and Exit Barrier classes).
Power method Stator2 () sends the message that stator 2 is powered. (This method is common to the Entry Barrier and Exit Barrier classes).
The angleTeta90 () method sends the message that the theta angle becomes 90. (This method is common to the Entry Barrier and Exit Barrier classes).
AngleTeta0 () method sends the message that the theta angle becomes 0. (This method is common to the Entry Barrier and Exit Barrier classes).

Within the Main class we have:

A CarsInParking hashmap that represents the cars that are parked next to the phone number of the car owner.
A LoculMasinii hashmap that contains the phone number of the car owner next to the parking place where his car is stored.
A queue Waiting queue that contains cars waiting to enter the parking lot.
A list of free spaces that contains free parking spaces in real time.
An entry time list that contains entry times for each car entering the parking lot.

The idea of the main program is the following:

The entry data is read from the file: the phone number of the car owner and the time he stays in the parking lot.
The cars are added to the queue.
The following condition is validated in the main while loop: the number of cars in the parking lot must be less than the maximum parking capacity.
- check if the queue is not empty and process the cars that are in it.
- cars enter the parking lot one by one depending on the availability of parking spaces.
- there are cars that are less than 5s (maximum parking time).
- there are cars that last more than 5s, and the owner of the car is announced to leave the parking lot.
- the cars leave the parking lot depending on the things mentioned above.
This process is repeated until the queue empties.
Finally, while checking that the queue is empty, cars that exceed the preset time of 5 seconds and cars that have a parking time less than 5 seconds leave the parking lot.
