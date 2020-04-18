# CS4350_Lab4 **Pomona Transit System**
## SQL Tables:
**Trip** (TripNumber, StartLocationName, DestinationName)\
**TripOffering** (Trip Number, Date, ScheduledStartTime, ScheduledArrivalTime, DriverName, BusID)\
**Bus** (BusID, Model, Year)\
**Driver** (DriverName, DriverTelephoneNumber)\
**Stop** (StopNumber, StopAddress)\
**ActualTripStopInfo** (TripNumber, Data, ScheduledStartTime, StopNumber, ScheduledArrivalTime, ActualStartTime, ActualArrivalTime, NumberOfPassengerIn, NumberOfPassengerOut)\
**TripStopInfo** (TripNumber, StopNumber, SequenceNumber, DrivingTime)\
 
