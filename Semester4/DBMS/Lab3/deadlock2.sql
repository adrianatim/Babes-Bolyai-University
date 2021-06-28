SET TRANSACTION ISOLATION LEVEL READ COMMITTED

--select * from examiner

BEGIN TRAN
	update examiner set eName='2Deadlock' where eId=3;
	update examiner set eName='2Deadlock' where eId=2;
COMMIT TRAN