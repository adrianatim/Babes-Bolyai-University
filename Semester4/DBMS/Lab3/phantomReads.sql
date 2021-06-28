-- when two same queries executed by two users show different output

SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED

BEGIN TRAN 
	select * from examiner;
	WAITFOR DELAY '00:00:05';
	select * from examiner;
COMMIT TRAN

INSERT INTO examiner VALUES(100, 'phantomReads');

