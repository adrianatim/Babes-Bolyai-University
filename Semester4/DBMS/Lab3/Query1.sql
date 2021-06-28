USE DrivingSchoolDB

CREATE OR ALTER PROCEDURE addExaminer(@name VARCHAR(50))
AS
	IF(@name is null)
	BEGIN
		RAISERROR('Name can not be null!', 12, 1)
	END
	ELSE
	BEGIN
		DECLARE @id INT
		SELECT @id=max(eId)+1 FROM examiner
		INSERT INTO examiner VALUES (@id, @name)
	END
GO

CREATE OR ALTER PROCEDURE addHuman(@hName VARCHAR(100), @DOB Date, @statusFile VARCHAR(50), @medicalFile VARCHAR(50))
AS
	IF(@hName is null)
	BEGIN
		RAISERROR('Name can not be null!',12,1)
	END
	ELSE IF(@DOB is null)
	BEGIN
		RAISERROR('Date of birth can not be null!', 12, 1)
	END
	ELSE IF(@statusFile is null)
	BEGIN
		RAISERROR('Status file can not be null!', 12, 1)
	END
	ELSE IF(@medicalFile is null)
	BEGIN
		RAISERROR('Medical file can not be null!', 12, 1)
	END
	ELSE
	BEGIN
		DECLARE @id INT
		SELECT @id=max(hId)+1 FROM human
		INSERT INTO human VALUES (@id, @hName, @DOB, @statusFile, @medicalFile)
	END
GO

CREATE OR ALTER PROCEDURE addPDSE(@points INT, @statusExam VARCHAR(50), @hName VARCHAR(50), @eName VARCHAR(50))
AS
	IF(@points is null)
	BEGIN
		RAISERROR('Points can not be null!',12,1)
	END
	ELSE IF(@statusExam is null)
	BEGIN
		RAISERROR('Status exam can not be null!', 12, 1)
	END
	ELSE
	BEGIN

	DECLARE @id INT
	SELECT @id = hId FROM human WHERE hName = @hName

	DECLARE @idE INT
	SELECT @idE = eId FROM examiner WHERE eName = @eName

	INSERT INTO practical_driving_school_exam VALUES(@idE, @id, @points, @statusExam)
	END
GO

--ROLLBACK when an insertion fails
--test case: no fail
CREATE OR ALTER PROC runRollBackNoFail
AS
	BEGIN TRAN
	BEGIN TRY
		exec addExaminer 'test runRollBackNoFail';
		exec addHuman 'test runRollBackNoFail','2020-02-02', 'available', 'expired';
		exec addPDSE 20,'fail','test runRollBackNoFail','test runRollBackNoFail';
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
		RETURN
	END CATCH
	COMMIT TRAN
GO

--test case: fail
CREATE OR ALTER PROCEDURE runRollBackFail
AS
	BEGIN TRAN
	BEGIN TRY
		exec addExaminer 'test runRollBackFail';
		exec addHuman 'test runRollBackFail','2020-02-02', 'available', NULL;
		exec addPDSE 20,'fail','test runRollBackFail','test runRollBackNoFail';
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
		RETURN
	END CATCH
	COMMIT TRAN
GO

--Add with keeping correct queries
--test case:no fail
CREATE OR ALTER PROCEDURE runKeepingQueries
AS
	DECLARE @failed int;
	SET @failed = 0;

	BEGIN TRY
		exec addExaminer 'test runKeepingQueries';
	END TRY
	BEGIN CATCH
		SET @failed = 1;
	END CATCH

	BEGIN TRY
		exec addHuman 'test runKeepingQueries', '2020-02-02', 'available', 'expired';
	END TRY
	BEGIN CATCH
		SET @failed = 1;
	END CATCH

	if (@failed =0)
		BEGIN TRY
			exec addPDSE 10, 'loat', 'test runKeepingQueries', 'test runKeepingQueries';
		END TRY
		BEGIN CATCH
		END CATCH
GO

--test case:fail
CREATE OR ALTER PROCEDURE runKeepingQueriesFail
AS
	DECLARE @failed int;
	SET @failed = 0;

	BEGIN TRY
		exec addExaminer 'test runKeepingQueriesFail';
	END TRY
	BEGIN CATCH
		SET @failed = 1;
	END CATCH

	BEGIN TRY
		exec addHuman 'test runKeepingQueriesFail', '2020-02-02', 'available', NULL;
	END TRY
	BEGIN CATCH
		SET @failed = 1;
	END CATCH

	if (@failed =0)
		BEGIN TRY
			exec addPDSE 11, 'loat', 'test runKeepingQueriesFail', 'test runKeepingQueriesFail';
		END TRY
		BEGIN CATCH
		END CATCH
GO

exec addExaminer NULL
exec addHuman 'test addHuman', '2020-02-02', 'available', 'expired'
exec addPDSE 10, 'loat', 'test addHuman', 'test addExaminer'

exec runRollBackNoFail
exec runRollBackFail
exec runKeepingQueries
exec runKeepingQueriesFail

SELECT * FROM examiner
SELECT * FROM practical_driving_school_exam
SELECT * FROM human