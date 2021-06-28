ALTER DATABASE DrivingSchoolDB  
SET ALLOW_SNAPSHOT_ISOLATION ON

SET TRANSACTION ISOLATION LEVEL SNAPSHOT
BEGIN TRAN
	update examiner set eName = 'updateConflict' where eId = 1;
	WAITFOR DELAY '00:00:5';
COMMIT TRAN