--when a transaction is allowed to read a row that has been modified by an another transaction which is not committed yet
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED

BEGIN TRAN
	update examiner set eName = 'dirtyRead1' where eId = 1;
	WAITFOR DELAY '00:00:10';
ROLLBACK

select * from examiner