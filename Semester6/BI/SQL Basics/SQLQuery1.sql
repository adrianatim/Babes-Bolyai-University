DROP TABLE country_organization
DROP TABLE country
DROP TABLE organization

CREATE TABLE organization(
idOrganization INT PRIMARY KEY,
organizationName VARCHAR(100),
city VARCHAR(100),
foundedDate DATE
)

CREATE TABLE country(
idCountry INT PRIMARY KEY,
countryName VARCHAR(100),
capital VARCHAR(100),
area FLOAT,
countryPopulation INT,
continent VARCHAR(100),
)

CREATE TABLE country_organization(
idCountry INT REFERENCES country(idCountry),
idOrganization INT REFERENCES organization(idOrganization)
PRIMARY KEY(idCountry, idOrganization)
)

DELETE FROM country_organization
DELETE FROM country
DELETE FROM organization

--populate table organization
INSERT INTO organization (idOrganization, organizationName, city, foundedDate)
VALUES
(1, 'Fisher House Foundation', 'Orlando', '1990-01-01'),
(2, 'Hope For The Warriors', 'New York City', '2006-09-11'),
(3, 'NATO', 'Wahington D.C.' ,'1949-01-01'),
(4, 'Tragedy Assistance Program for Survivors', 'Alaska', '1994-01-01'),
(5, 'Vietnam Veterans of America', 'Vietnam', '1979-01-01')

--populate table country
INSERT INTO country(idCountry, countryName, capital, area, countryPopulation, continent)
VALUES
(1, 'Alaska', 'Juneau', 1718000, 731545, 'North American'),
(2, 'Vietnam', 'Hanoi', 331690, 97340000,'Asia'),
(3, 'Florida', 'Orlando', 3084, 280832, 'Europa'),
(4, 'America', 'Wahington D.C.', 9834000, 329500000 ,'North American')

--populate table country_organization
INSERT INTO country_organization(idCountry, idOrganization)
VALUES
(1, 4),
(2, 5),
(3, 1),
(4, 2),
(4, 3)

SELECT * FROM country
SELECT * FROM organization
SELECT * FROM country_organization

--1.List all the countries which are members of NATO.
SELECT countryName
FROM country
WHERE idCountry IN 
	(SELECT idCountry
	FROM country_organization
	WHERE idOrganization IN 
		(SELECT idOrganization
		FROM organization
		WHERE organizationName LIKE '%NATO'))

--2.List all the countries which are members of organizations founded before 1980
SELECT countryName
FROM country
WHERE idCountry IN 
	(SELECT idCountry
	FROM country_organization
	WHERE idOrganization IN 
		(SELECT idOrganization
		FROM organization
		WHERE foundedDate < '1980-01-01'))

--3.List all the countries which are members of only one organization
SELECT idCountry, COUNT(*)	
FROM country_organization
GROUP BY idCountry
HAVING COUNT(*) <= 1

--4.List all the capitals which are headquarter of no organization
SELECT DISTINCT capital
FROM country
WHERE capital NOT IN 
	(SELECT city
	FROM organization)

--5.List the population of each continent
SELECT continent, SUM(DISTINCT countryPopulation)
FROM country
GROUP BY continent

--6.Count the countries of each continent
SELECT continent, COUNT(DISTINCT countryName)
FROM country
GROUP BY continent
HAVING COUNT(*) >0
