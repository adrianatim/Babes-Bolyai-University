create table Manufacture
(Mid int primary key,
Mname varchar(30))

create table Model
(DMid int primary key,
DMname varchar(30),
life int,
speed int,
Mid int references Manufacture(Mid))

create table Drone
(Did int primary key,
serialNo int,
DMid int references Model(DMid))

create table pizzaShop
(PSid int primary key,
PSname varchar(30) UNIQUE,
PSaddress varchar(20))

create table Customer
(Cid int primary key,
Cname varchar(30) UNIQUE,
score int)

create table Deliveries
(Cid int references Customer(Cid),
PSid int references pizzaShop(PSid),
primary key(Cid, PSid),
Did int references Drone(Did),
Ddate date,
Dtime time)

---b)
CREATE OR ALTER PROCEDURE addDilivery(@customer VARCHAR(30), @pizzaShop VARCHAR(30), @drone INT, @date DATE, @time TIME)
AS
	DECLARE @Cid INT, @PSid INT, @Did INT
	SET @Cid = (SELECT Cid FROM Customer WHERE Cname = @customer)
	SET @PSid = (SELECT PSid FROM pizzaShop WHERE PSname = @pizzaShop)
	SET @Did = (SELECT Did FROM Drone WHERE serialNo = @drone)

	INSERT INTO Deliveries 
	VALUES (@Cid, @PSid, @Did, @date, @time)
GO

INSERT INTO Manufacture VALUES (1, 'm1'), (2, 'm2')
INSERT INTO Model VALUES (1, 'model1', 2, 100, 1), (2, 'model2', 3, 120, 1), (3, 'model3', 4, 150, 2)
INSERT INTO Drone VALUES (1, 8889, 1), (2, 9910, 2), (3, 1002, 1)
INSERT INTO pizzaShop VALUES (1, 'ps1', 'a1'), (2, 'ps2', 'a2'), (3, 'ps3', 'a3')
INSERT INTO Customer VALUES (1, 'c1', 10), (2, 'c2', 9), (3, 'c3', 10)

SELECT * FROM Manufacture
SELECT * FROM Model
SELECT * FROM Drone
SELECT * FROM pizzaShop
SELECT * FROM Customer
SELECT * FROM Deliveries

EXEC addDilivery @customer = 'c1', @pizzaShop = 'ps1', @drone = 1002, @date = '2000-01-26', @time = '5:30'
EXEC addDilivery @customer = 'c2', @pizzaShop = 'ps1', @drone = 8889, @date = '2002-07-20', @time = '6:30'
EXEC addDilivery @customer = 'c1', @pizzaShop = 'ps3', @drone = 1002, @date = '2004-12-21', @time = '7:30'

--c)
CREATE OR ALTER VIEW showStartup
AS
	SELECT m.Mname
	FROM Manufacture m
	WHERE m.Mid IN
		(SELECT nr.Mid
		 FROM (SELECT Mid, COUNT(Mid) nrDrones
			FROM Model 
			GROUP BY Mid) AS nr
		WHERE nr.Mid = m.Mid AND nr.nrDrones = (SELECT MAX(n.nrDrones)
											    FROM (SELECT Mid, COUNT(Mid) nrDrones
													  FROM Model 
												       GROUP BY Mid) AS n))
GO

SELECT * FROM showStartup

--d)
CREATE OR ALTER FUNCTION showCustomers(@D INT)
RETURNS TABLE
	RETURN SELECT Cname
	FROM Customer
	WHERE Cid IN
			(SELECT d.Cid
			FROM Deliveries d
			GROUP BY d.Cid
			HAVING COUNT(*) > @D)

GO

SELECT * FROM showCustomers(0)

