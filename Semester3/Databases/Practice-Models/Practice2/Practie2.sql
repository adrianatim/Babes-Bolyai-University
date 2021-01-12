--a)
DROP TABLE StationsRouters
DROP TABLE Stations
DROP TABLE Routers
DROP TABLE Trains
DROP TABLE TrainTypes
DROP PROCEDURE updateRoute

CREATE TABLE TrainTypes
(TrainTypeId INT PRIMARY KEY,
 TTName VARCHAR(50),
 TTDescription VARCHAR(400))

 CREATE TABLE Trains
 (TrainId INT PRIMARY KEY,
  TName VARCHAR(50),
  TrainTypeId INT REFERENCES TrainTypes(TrainTypeId))

CREATE TABLE Routes 
(RouteId INT PRIMARY KEY,
RName VARCHAR(50) UNIQUE,
TrainId INT REFERENCES Trains(TrainId))

CREATE TABLE Stations 
(StationId INT PRIMARY KEY,
SName VARCHAR(50) UNIQUE)

CREATE TABLE StationsRoters
(StationId INT REFERENCES Stations(StationId),
 RouteId INT REFERENCES Routes(RouteId),
 Arrival TIME,
 Departure TIME,
 PRIMARY KEY(StationId, RouteId))


 --b)
 CREATE OR ALTER PROC updateRoute(@RName VARCHAR(50), @SName VARCHAR(50), @Arrival TIME, @Departure TIME)
 AS
	DECLARE @RID INT, @SID INT
   IF NOT EXISTS(SELECT* FROM Routes WHERE RName = @RName)
   BEGIN
		RAISERROR('Route name not valid', 16, 1)
		RETURN
   END
   IF NOT EXISTS(SELECT* FROM Stations WHERE SName = @SName)
   BEGIN
		RAISERROR('Station name not valid', 16, 1)
		RETURN
   END

   SELECT @RID = (SELECT RouteId FROM Routes WHERE RName = @RName),
   @SID = (SELECT StationId FROM Stations WHERE SName = @SName)

   IF EXISTS (SELECT *
		   FROM StationsRoters
		   WHERE StationId = @SID AND RouteId = @RID)
		  UPDATE StationsRoters
		  SET Arrival = @Arrival, Departure = @Departure
		  WHERE StationId = @SID AND RouteId = @RID
   ELSE
		INSERT StationsRoters(StationId, RouteId, Arrival, Departure)
		VALUES (@SID, @RID, @Arrival, @Departure)
GO

INSERT TrainTypes VALUES(1, 'type1', 'description1')
INSERT Trains VALUES(1, 't1', 1), (2, 't2', 1), (3, 't3', 1)
INSERT Routes VALUES(1, 'r1', 1), (2, 'r2', 2), (3, 'r3', 3)
INSERT Stations VALUES(1, 's1'), (2, 's2'), (3, 's4')

SELECT * FROM TrainTypes
SELECT * FROM Trains
SELECT * FROM Routes
SELECT * FROM Stations
SELECT * FROM StationsRoters
ORDER BY RouteId

EXEC updateRoute @RName = 'r1', @SName = 's1', @Arrival = '5:20', @Departure = '5:30'
EXEC updateRoute @RName = 'r1', @SName = 's2', @Arrival = '6:20', @Departure = '6:30'
EXEC updateRoute @RName = 'r1', @SName = 's4', @Arrival = '7:20', @Departure = '7:30'

EXEC updateRoute @RName = 'r2', @SName = 's1', @Arrival = '5:20', @Departure = '5:30'
EXEC updateRoute @RName = 'r2', @SName = 's2', @Arrival = '6:20', @Departure = '6:30'
EXEC updateRoute @RName = 'r2', @SName = 's4', @Arrival = '7:20', @Departure = '7:30'

EXEC updateRoute @RName = 'r3', @SName = 's1', @Arrival = '5:20', @Departure = '5:30'

--c)
SELECT StationId
FROM Stations
EXCEPT
SELECT StationId
FROM StationsRoters
WHERE RouteId = 1

CREATE OR ALTER VIEW vRoutersWithAllTheStations
AS
SELECT r.RName
FROM Routes r
WHERE NOT EXISTS
	(SELECT StationId
	FROM Stations
	EXCEPT
	SELECT StationId
	FROM StationsRoters
	WHERE RouteId = r.RouteId)
GO

SELECT *
FROM vRoutersWithAllTheStations

--d)
CREATE OR ALTER FUNCTION filterByNumOfRoutes(@R INT)
RETURNS TABLE
RETURN SELECT S.SName
FROM STATIONS S
WHERE S.StationId IN
	(SELECT SR.StationId
	FROM StationsRoters SR
	GROUP BY SR.StationId
	HAVING COUNT(*) > @R)
GO

SELECT * 
FROM filterByNumOfRoutes(2)






