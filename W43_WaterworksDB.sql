-- This file is used to create the tables needed for the coding part.
-- In the second table, CustomerReading the fldID should be random generated, however we didn't know how to write it.

CREATE DATABASE W43_Waterworks11;

GO
use W43_Waterworks;

CREATE TABLE tblCustomerProfile  (fldCPRCVR      NCHAR(10) NOT NULL, 
                                 fldWaterMeter  INTEGER   NULL,
                                 fldFirstName   NCHAR(40) NULL,
							     fldLastName    NCHAR(40) NULL,
							     fldStreetName  NCHAR(40) NULL,
							     fldStreetNo    INTEGER   NULL,
							     fldZipCode     INTEGER   NULL,
							     fldEmail       NCHAR(40) NULL,
                                 fldPhoneNo     NCHAR(40) NULL)

CREATE TABLE tblCustomerReading  (fldID          INTEGER   NOT NULL, 
                                 fldCPRCVR      CHAR(10)  NULL,
							     fldReadingDate DATE      NULL,
                                 fldWaterConsumption INTEGER NULL)