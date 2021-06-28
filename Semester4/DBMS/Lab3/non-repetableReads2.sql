SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED

BEGIN TRAN
	update examiner set eName = 'newNonRepetableReads' where eId = 8
COMMIT TRAN

select @@TRANCOUNT