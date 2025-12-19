Premesso che si consiglia di far girare il progetto BE su IntelliJ Idea (Community o Ultimate Edition), 
  al fine di avviare il progetto basterà andare su com.tesi.sustReportBE.SustReportBeApplication e premere sul triangolino verde in alto a destra.

Di seguito, lo script SQL da far girare sul db MySql root@127.0.0.1:3306 (Versione 8.4.3):

CREATE TABLE report (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
  file_name VARCHAR(255),
  year INT,
  file_data LONGBLOB
);
