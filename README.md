# CS4350_Lab4
## **Pomona Transit System**
SQL Tables:
**Trip** (<u>TripNumber</u>, StartLocationName, DestinationName)
**TripOffering** (<u>Trip Number, Date, ScheduledStartTime</u>, ScheduledArrivalTime, DriverName, BusID)
**Bus** (<u>BusID</u>, Model, Year)
**Driver** (<u>DriverName</u>, DriverTelephoneNumber)
**Stop** (<u>StopNumber</u>, StopAddress)
**ActualTripStopInfo** (<u>TripNumber, Data, ScheduledStartTime, StopNumber</u>, ScheduledArrivalTime, ActualStartTime, ActualArrivalTime, NumberOfPassengerIn, NumberOfPassengerOut)
**TripStopInfo** (<u>TripNumber, StopNumber</u>, SequenceNumber, DrivingTime)
 
