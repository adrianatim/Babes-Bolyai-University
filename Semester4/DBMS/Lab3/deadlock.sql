SET TRANSACTION ISOLATION LEVEL READ COMMITTED

BEGIN TRAN
	update examiner set eName='newDeadlock' where eId=2;
	WAITFOR DELAY '00:00:10';
	update examiner set eName='newDeadlock' where eId=3;
COMMIT TRAN

select * from examiner

update examiner set eName='deadlock' where eId=2;
update examiner set eName='deadlock' where eId=3;