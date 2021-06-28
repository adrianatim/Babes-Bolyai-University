--the first process reading the value might get two different values, as the changed data is read a second time because the second process changes the data

SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED

BEGIN TRAN
	select * from examiner;
	WAITFOR DELAY '00:00:10';
	select * from examiner;
COMMIT TRAN

update examiner set eName = 'nonRepeatblereads' where eId = 8
