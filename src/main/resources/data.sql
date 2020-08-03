INSERT INTO "EMPLOYEE"("EMPLOYEE_ID","CREATED_TIMESTAMP","UPDATED_TIMESTAMP","EMPLOYEE_NAME")VALUES(1,sysdate,sysdate,'Harish'),(2,sysdate,sysdate,'Vaddiraju');
INSERT INTO "CONFERENCE"("CONFERENCE_ID","NAME","COMPANY","LOCATION","CREATED_TIMESTAMP","UPDATED_TIMESTAMP")VALUES((SELECT NEXT VALUE FOR hibernate_sequence),'JAVATEST','ABC Corp','VERGINIA',sysdate,sysdate);
INSERT INTO "TALK"("TALK_ID","TALK_NAME","TALK_TITLE","CREATED_TIMESTAMP","UPDATED_TIMESTAMP","TALK_START_TIME","TALK_END_TIME")VALUES
(1,'DEFAULT','Normal Talk',sysdate,sysdate,parsedatetime('30-07-2020 08:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),parsedatetime('30-07-2020 09:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'));
INSERT INTO "TALK"("CONFERENCE_ID","TALK_ID","TALK_NAME","TALK_TITLE","CREATED_TIMESTAMP","UPDATED_TIMESTAMP","TALK_START_TIME","TALK_END_TIME")VALUES
(1,2,'COLLECTIONS','Collections',sysdate,sysdate,parsedatetime('30-07-2020 09:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),parsedatetime('30-07-2020 10:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS')),
(1,3,'THREADS','Threads',sysdate,sysdate,parsedatetime('30-07-2020 10:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),parsedatetime('30-07-2020 11:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS')),
(1,4,'EXCEPTION_HANDLING','Exception Handling',sysdate,sysdate,parsedatetime('30-07-2020 11:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'),parsedatetime('30-07-2020 12:00:00.000', 'dd-MM-yyyy hh:mm:ss.SS'));