-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-07-22 01:29:53.659

CREATE DATABASE PizzaHut;
GO

USE PizzaHut;


-- tables
-- Table: CARGO
CREATE TABLE CARGO (
    IDCAR int  NOT NULL IDENTITY(1,1),
    NOMCAR varchar(30)  NOT NULL,
    CONSTRAINT CARGO_pk PRIMARY KEY  (IDCAR)
);

-- Table: CATEGORIA
CREATE TABLE CATEGORIA (
    IDCAT int  NOT NULL IDENTITY(1,1),
    NOMCAT varchar(20)  NOT NULL,
    CONSTRAINT CATEGORIA_pk PRIMARY KEY  (IDCAT)
);

-- Table: CLIENTE
CREATE TABLE CLIENTE (
    IDCLI int  NOT NULL IDENTITY(1,1),
    NOMCLI varchar(20)  NOT NULL,
    CELCLI char(9)  NOT NULL,
    DIRCLI varchar(40)  NOT NULL,
    CORCLI varchar(30)  NOT NULL,
    ESTCLI char(1)  NOT NULL DEFAULT 'A',
    IDUBI int  NOT NULL,
    CONSTRAINT CLIENTE_pk PRIMARY KEY  (IDCLI)
);

-- Table: EMPLEADO
CREATE TABLE EMPLEADO (
    IDEMP int  NOT NULL IDENTITY(1,1),
    NOMEMP varchar(20)  NOT NULL,
    CELEMP char(9)  NOT NULL,
    ESTEMP char(1)  NOT NULL DEFAULT 'A',
    COREMP varchar(30)  NOT NULL,
    IDCAR int  NOT NULL,
    IDUBI int  NOT NULL,
    CONSTRAINT EMPLEADO_pk PRIMARY KEY  (IDEMP)
);

-- Table: EQUIPO
CREATE TABLE EQUIPO (
    IDEQUI int  NOT NULL IDENTITY(1,1),
    ESTQUI char(1)  NOT NULL DEFAULT 'A',
    IDEMP int  NOT NULL,
    IDSUC int  NOT NULL,
    CONSTRAINT EQUIPO_pk PRIMARY KEY  (IDEQUI)
);

-- Table: PRODUCTO
CREATE TABLE PRODUCTO (
    IDPRO int  NOT NULL IDENTITY(1,1),
    NOMPRO varchar(30)  NOT NULL,
    PREPRO decimal(5,2)  NOT NULL,
    CANPRO int  NOT NULL,
    ESTPRO char(1)  NOT NULL DEFAULT 'A',
    IDCAT int  NOT NULL,
    IDSUC int  NOT NULL,
    CONSTRAINT PRODUCTO_pk PRIMARY KEY  (IDPRO)
);

-- Table: SUCURSAL
CREATE TABLE SUCURSAL (
    IDSUC int  NOT NULL IDENTITY(1,1),
    ESTSUC char(1)  NOT NULL DEFAULT 'A',
    IDEMP int  NOT NULL,
    IDUBI int  NOT NULL,
    CONSTRAINT SUCURSAL_pk PRIMARY KEY  (IDSUC)
);

-- Table: UBIGEO
CREATE TABLE UBIGEO (
    IDUBI int  NOT NULL IDENTITY(1,1),
    DISUBI varchar(50)  NOT NULL,
    PROUBI varchar(50)  NOT NULL,
    DEPUBI varchar(50)  NOT NULL,
    ESTUBI char(1)  NOT NULL DEFAULT 'A',
    CONSTRAINT UBIGEO_pk PRIMARY KEY  (IDUBI)
);

-- Table: VENTA
CREATE TABLE VENTA (
    IDVEN int  NOT NULL IDENTITY(1,1),
    FECVENT datetime  NOT NULL,
    TIPVENT varchar(12)  NOT NULL,
    IDCLI int  NOT NULL,
    CONSTRAINT VENTA_pk PRIMARY KEY  (IDVEN)
);

-- Table: VENTA_DETALLE
CREATE TABLE VENTA_DETALLE (
    IDVENDET int  NOT NULL IDENTITY(1,1),
    CANTVEDET int  NOT NULL,
    SUBVEDET decimal(5,2)  NOT NULL,
    IDPRO int  NOT NULL,
    IDVEN int  NOT NULL,
    CONSTRAINT VENTA_DETALLE_pk PRIMARY KEY  (IDVENDET)
);

-- Table: VENTA_EMPLEADO
CREATE TABLE VENTA_EMPLEADO (
    IDVEEM int  NOT NULL IDENTITY(1,1),
    IDEMP int  NOT NULL,
    IDVEN int  NOT NULL,
    CONSTRAINT VENTA_EMPLEADO_pk PRIMARY KEY  (IDVEEM)
);

-- foreign keys
-- Reference: CLIENTE_UBIGEO (table: CLIENTE)
ALTER TABLE CLIENTE ADD CONSTRAINT CLIENTE_UBIGEO
    FOREIGN KEY (IDUBI)
    REFERENCES UBIGEO (IDUBI);

-- Reference: EMPLEADO_CARGO (table: EMPLEADO)
ALTER TABLE EMPLEADO ADD CONSTRAINT EMPLEADO_CARGO
    FOREIGN KEY (IDCAR)
    REFERENCES CARGO (IDCAR);

-- Reference: EMPLEADO_UBIGEO (table: EMPLEADO)
ALTER TABLE EMPLEADO ADD CONSTRAINT EMPLEADO_UBIGEO
    FOREIGN KEY (IDUBI)
    REFERENCES UBIGEO (IDUBI);

-- Reference: EQUIPO_EMPLEADO (table: EQUIPO)
ALTER TABLE EQUIPO ADD CONSTRAINT EQUIPO_EMPLEADO
    FOREIGN KEY (IDEMP)
    REFERENCES EMPLEADO (IDEMP);

-- Reference: EQUIPO_SUCURSAL (table: EQUIPO)
ALTER TABLE EQUIPO ADD CONSTRAINT EQUIPO_SUCURSAL
    FOREIGN KEY (IDSUC)
    REFERENCES SUCURSAL (IDSUC);

-- Reference: PRODUCTO_CATEGORIA (table: PRODUCTO)
ALTER TABLE PRODUCTO ADD CONSTRAINT PRODUCTO_CATEGORIA
    FOREIGN KEY (IDCAT)
    REFERENCES CATEGORIA (IDCAT);

-- Reference: PRODUCTO_SUCURSAL (table: PRODUCTO)
ALTER TABLE PRODUCTO ADD CONSTRAINT PRODUCTO_SUCURSAL
    FOREIGN KEY (IDSUC)
    REFERENCES SUCURSAL (IDSUC);

-- Reference: SUCURSAL_EMPLEADO (table: SUCURSAL)
ALTER TABLE SUCURSAL ADD CONSTRAINT SUCURSAL_EMPLEADO
    FOREIGN KEY (IDEMP)
    REFERENCES EMPLEADO (IDEMP);

-- Reference: SUCURSAL_UBIGEO (table: SUCURSAL)
ALTER TABLE SUCURSAL ADD CONSTRAINT SUCURSAL_UBIGEO
    FOREIGN KEY (IDUBI)
    REFERENCES UBIGEO (IDUBI);

-- Reference: VENTA_CLIENTE (table: VENTA)
ALTER TABLE VENTA ADD CONSTRAINT VENTA_CLIENTE
    FOREIGN KEY (IDCLI)
    REFERENCES CLIENTE (IDCLI);

-- Reference: VENTA_DETALLE_PRODUCTO (table: VENTA_DETALLE)
ALTER TABLE VENTA_DETALLE ADD CONSTRAINT VENTA_DETALLE_PRODUCTO
    FOREIGN KEY (IDPRO)
    REFERENCES PRODUCTO (IDPRO);

-- Reference: VENTA_DETALLE_VENTA (table: VENTA_DETALLE)
ALTER TABLE VENTA_DETALLE ADD CONSTRAINT VENTA_DETALLE_VENTA
    FOREIGN KEY (IDVEN)
    REFERENCES VENTA (IDVEN);

-- Reference: VENTA_EMPLEADO_EMPLEADO (table: VENTA_EMPLEADO)
ALTER TABLE VENTA_EMPLEADO ADD CONSTRAINT VENTA_EMPLEADO_EMPLEADO
    FOREIGN KEY (IDEMP)
    REFERENCES EMPLEADO (IDEMP);

-- Reference: VENTA_EMPLEADO_VENTA (table: VENTA_EMPLEADO)
ALTER TABLE VENTA_EMPLEADO ADD CONSTRAINT VENTA_EMPLEADO_VENTA
    FOREIGN KEY (IDVEN)
    REFERENCES VENTA (IDVEN);

-- sequences
-- Sequence: CARGO_seq
CREATE SEQUENCE CARGO_seq
    START WITH 1 
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    NO CYCLE
    NO CACHE;

-- Sequence: CLIENTE_seq
CREATE SEQUENCE CLIENTE_seq
    START WITH 1 
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    NO CYCLE
    NO CACHE;

-- Sequence: EMPLEADO_seq
CREATE SEQUENCE EMPLEADO_seq
    START WITH 1 
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    NO CYCLE
    NO CACHE;

-- Sequence: EQUIPO_seq
CREATE SEQUENCE EQUIPO_seq
    START WITH 1 
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    NO CYCLE
    NO CACHE;

-- Sequence: PRODUCTO_seq
CREATE SEQUENCE PRODUCTO_seq
    START WITH 1 
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    NO CYCLE
    NO CACHE;

-- Sequence: SUCURSAL_seq
CREATE SEQUENCE SUCURSAL_seq
    START WITH 1 
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    NO CYCLE
    NO CACHE;

-- Sequence: UBIGEO_seq
CREATE SEQUENCE UBIGEO_seq
    START WITH 1 
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    NO CYCLE
    NO CACHE;

-- Sequence: VENTA_DETALLE_seq
CREATE SEQUENCE VENTA_DETALLE_seq
    START WITH 1 
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    NO CYCLE
    NO CACHE;

-- Sequence: VENTA_EMPLEADO_seq
CREATE SEQUENCE VENTA_EMPLEADO_seq
    START WITH 1 
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    NO CYCLE
    NO CACHE;

-- Sequence: VENTA_seq
CREATE SEQUENCE VENTA_seq
    START WITH 1 
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    NO CYCLE
    NO CACHE;

-- End of file.


--------- TABLAS TEMPORALES ------------------
CREATE TABLE CARRITO_VENTA(
         IDCARVEN int NOT NULL IDENTITY(1,1),
         CANTVEDET int NOT NULL,
		 IDSUC int NOT NULL,
		 IDPRO int NOT NULL,
		 IDCLI int NOT NULL,
		 IDEMVEN int NOT NULL,
		 IDEMDES int NOT NULL,
)


CREATE TABLE REPORTE(
         IDREP int NOT NULL IDENTITY(1,1),
		 IDVEN int
)
GO


----- Vistas -----

CREATE VIEW VistaCarritoVenta AS
   SELECT 
        PD.NOMPRO,PD.PREPRO,
		CARV.CANTVEDET,'Subtotal'=CARV.CANTVEDET*PD.PREPRO
		      FROM CARRITO_VENTA AS CARV
		      INNER JOIN PRODUCTO AS PD
		      ON PD.IDPRO=CARV.IDPRO
GO


CREATE VIEW VistaVenta AS
       SELECT V.IDVEN,V.FECVENT,'TOTAL'=SUM(SUBVEDET),
	     CL.NOMCLI,'Vendedor'=EM.NOMEMP
	     FROM VENTA AS V
		 INNER JOIN VENTA_DETALLE AS VDT
		 ON V.IDVEN=VDT.IDVEN
		 INNER JOIN CLIENTE AS CL
		 ON CL.IDCLI=V.IDCLI
		 INNER JOIN VENTA_EMPLEADO AS VEM
		 ON VEM.IDVEN=V.IDVEN 
		 INNER JOIN EMPLEADO AS EM
		 ON EM.IDEMP=VEM.IDEMP
		 WHERE EM.IDCAR=3
         GROUP BY V.IDVEN,V.FECVENT,CL.NOMCLI,EM.NOMEMP
GO




CREATE VIEW VistaVentaDetalle AS
       SELECT VDT.IDVENDET,PD.NOMPRO,
	    PD.PREPRO,VDT.CANTVEDET,V.IDVEN,
	    'Subtotal'=VDT.CANTVEDET*PD.PREPRO,'Despachador'=EM.NOMEMP
	     FROM VENTA AS V
		 INNER JOIN VENTA_DETALLE AS VDT
		 ON V.IDVEN=VDT.IDVEN
		 INNER JOIN CLIENTE AS CL
		 ON CL.IDCLI=V.IDCLI
		 INNER JOIN PRODUCTO AS PD
		 ON PD.IDPRO=VDT.IDPRO
		 INNER JOIN VENTA_EMPLEADO AS VEM
		 ON VEM.IDVEN=V.IDVEN
		 INNER JOIN EMPLEADO AS EM
		 ON EM.IDEMP=VEM.IDEMP 
		 WHERE EM.IDCAR=4
GO


CREATE VIEW Despachador AS
  SELECT EM.IDEMP,EM.NOMEMP,S.IDSUC FROM SUCURSAL AS S
   INNER JOIN EQUIPO AS E
   ON E.IDSUC=S.IDSUC
   INNER JOIN EMPLEADO AS EM
   ON EM.IDEMP=E.IDEMP
   WHERE IDCAR=4
GO


CREATE VIEW Vendedores AS
  SELECT EM.IDEMP,EM.NOMEMP,S.IDSUC FROM SUCURSAL AS S
   INNER JOIN EQUIPO AS E
   ON E.IDSUC=S.IDSUC
   INNER JOIN EMPLEADO AS EM
   ON EM.IDEMP=E.IDEMP
   WHERE IDCAR=3
GO


----- STOP PROCEDURE ----
CREATE PROCEDURE spUpdateVenta
      (
	      --Venta variables 
		  @IDCLI int
      )
AS
   BEGIN 
	      SET NOCOUNT ON
		  BEGIN TRAN /* TRANSACCIONES */
	      BEGIN TRY

		  	 --Obteniendo la fecha de hoy
			   DECLARE @FECVENT date;
			   SELECT  @FECVENT=CAST( GETDATE() AS Date ) ;

		       DECLARE @TIPVENT varchar(12);
		       SET @TIPVENT='Delivery';

			   --Insertando datos(Factura-Detalle) y actualizando el producto
			   INSERT INTO VENTA(FECVENT,TIPVENT,IDCLI) VALUES(@FECVENT,@TIPVENT,@IDCLI)
			   COMMIT TRAN
           END TRY

		   BEGIN CATCH
		         SELECT 'Fallo la transaccion de Venta' AS ERROR
			    IF @@TRANCOUNT > 0 ROLLBACK TRAN;
		   END CATCH
	   END
GO




CREATE PROCEDURE spUpdateVentaDetalle
      (
	      --Venta variables 
		  @CANTVEDET int,
		  @SUBVEDET decimal(5,2),
		  @IDPRO int,
		  @IDVEN int
      )
AS
   BEGIN 
	      SET NOCOUNT ON
		  BEGIN TRAN /* TRANSACCIONES */
	      BEGIN TRY

		  	   --calculando la nueva cantidad
		       DECLARE @CANTACTUAL int,
		       @NEWCANT int;
			   SELECT @CANTACTUAL=CANPRO FROM PRODUCTO WHERE IDPRO=@IDPRO ;
			   SET @NEWCANT= @CANTACTUAL-@CANTVEDET;

			   --calculando el Subtotal
			   DECLARE @TOTBOL decimal(10,2),
			   @PRECUNIT decimal(10,2);
			   SELECT @PRECUNIT=PREPRO FROM PRODUCTO WHERE IDPRO=@IDPRO;
			   SET @TOTBOL=(@PRECUNIT*@CANTVEDET)

			   --obteniendo y insertando Factura
			   INSERT INTO VENTA_DETALLE VALUES(@CANTVEDET,@SUBVEDET,@IDPRO,@IDVEN);

			   --Actualizando el stop del Producto vendido
			   UPDATE PRODUCTO SET CANPRO=@NEWCANT WHERE IDPRO=@IDPRO;

			   COMMIT TRAN
           END TRY

		   BEGIN CATCH
		         SELECT 'Fallo la transaccion de Venta_Detalle' AS ERROR
			    IF @@TRANCOUNT > 0 ROLLBACK TRAN;
		   END CATCH
	   END
GO



CREATE PROCEDURE spUpdateVentaEmpleado
      (
	      --Venta variables 
		  @IDEMP int,
		  @IDVEN int
      )
AS
   BEGIN 
	      SET NOCOUNT ON
		  BEGIN TRAN /* TRANSACCIONES */
	      BEGIN TRY
			   --Insertando datos(Factura-Detalle) y actualizando el producto
			   INSERT INTO VENTA_EMPLEADO VALUES(@IDEMP,@IDVEN)
			   COMMIT TRAN
           END TRY

		   BEGIN CATCH
		         SELECT 'Fallo la transaccion de Venta_empleado' AS ERROR
			    IF @@TRANCOUNT > 0 ROLLBACK TRAN;
		   END CATCH
	   END
GO


------ INSERCION DE DATOS -----------


/* Insertar registros tabla CARGO */INSERT INTO CARGO (NOMCAR) VALUES('Jefe de Sucursal'),('Administrador'),('Vendedor'),('Despachador')
INSERT INTO UBIGEO(DISUBI,PROUBI,DEPUBI)VALUES('San Vicente','Ca�ete','Lima'),('San Luis','Ca�ete','Lima'),('Cerro Azul','Ca�ete','Lima'),('Mala','Ca�ete','Lima'),('Pacar�n','Ca�ete','Lima'),('Zu�iga','Ca�ete','Lima'),('Lunahuan�','Ca�ete','Lima'),('Santa Cruz de Flores','Ca�ete','Lima'),('Imperial','Ca�ete','Lima'),('Quilman�','Ca�ete','Lima'),('Chilca','Ca�ete','Lima'),('Asia','Ca�ete','Lima'),('Nuevo Imperial','Ca�ete','Lima')
/* Insertar registros tabla CLIENTE */INSERT INTO CLIENTE(NOMCLI, CELCLI, CORCLI,DIRCLI,IDUBI)VALUES('Sebastian', '987456321', 'sebastian122330@gmail.com','Los girasoles MzE',1),('Pedro', '985203697', 'Pedro15@gmail.com','Plaza de Arma',1),('Juan', '901254789', 'JuanLopes@gmail.com','Imperial',2),('Eduardo', '974103695', 'Eduardo@gmail.com','Cerro azul ',3),('Julio', '974105996', 'JulioC@gmail.com','Villa vista',4),('Victor', '933201596', 'Victor123@gmail.com','28 de julio',3),('Jose', '901254006', 'JoseV@gmail.com','Plaza de Arma Mz E lt8',6),('Maria','985476300', 'MariaP@gmail.com','San Vicente',5),('Carmen', '999632014', 'CarmenP@gmail.com','Nuevo Imperial',7),('Esperanza', '955214632', 'Esperanza1@gmail.com','Cementerio',8)/* Insertar registros tabla EMPLEADO */INSERT INTO EMPLEADO(NOMEMP,CELEMP,COREMP,IDCAR,IDUBI)VALUES('Lucas', '987456320', 'Lucas@gmail.com',1,1),
('Fabrizio', '974158996', 'Fabrizio@gmail.com',3,2),
('Anderson', '902356489', 'AndersonC@gmail.com',4,1),
('Sandra',  '912365409', 'SandraT@gmail.com',1,7),
('Kyara',  '987410236', 'KyaraH@gmail.com',3,5),
('Lucia', '900147852', 'LuciaP@gmail.com',4,2),
('Estrella',  '978456932', 'EstrellaA@gmail.com',1,4),
('Alberto', '941203256', 'Alberto@gmail.com',3,5),
('Jesus', '974120358', 'JesusC@gmail.com',4,7),
('Carlos', '989520365', 'Carlos@gmail.com',2,9)/* Insertar registros tabla SUCURSAL */INSERT INTO SUCURSAL(IDEMP,IDUBI)VALUES(1,1),(4,1),(7,1),(1,2),(7,2),(4,2),(7,3),(7,3),(1,3),(1,3),(7,4),(4,4)INSERT INTO CATEGORIA(NOMCAT)VALUES('Bebidas'),('Postres'),('Pizzas')/* Insertar registros tabla EQUIPO */INSERT INTO EQUIPO(IDEMP,IDSUC)VALUES(2,1),(3,1),(5,2),(6,2),(8,3),(9,3)/* Insertar registros tabla PRODUCTO */INSERT INTO PRODUCTO(NOMPRO,CANPRO,PREPRO,IDCAT,IDSUC)VALUES('Pizza margarita',50,16.00,3,1),('Pizza cuatro quesos',70,15.00,3,1),('Pizza de pepperoni',50,28.00,3,1),('Pizza cuatro estaciones',100,12.00,3,1),('Pizza con champi�ones',60,50.00,3,2),('Pizza hawaiana',45,70.00,3,2),('Pizza marinara',9,90.00,3,2),('Pizza napolitana',5,80.00,3,3),('Pizza neoyorquina',20,76.00,3,3),('Pizza fugazza',10,98.00,3,3)/*Insertar Transaccional */INSERT INTO VENTA  (FECVENT,TIPVENT,IDCLI)VALUES('01-15-2022',1,1),('02-20-2022',1,2)INSERT INTO VENTA_DETALLE   (CANTVEDET,SUBVEDET,IDPRO,IDVEN)VALUES (4,64,1,1), (1,15,2,1), (2,56,3,2), (2,32,1,2)INSERT INTO VENTA_EMPLEADO       (IDEMP,IDVEN)VALUES(2,1),(3,1),(5,2),(6,2)INSERT INTO REPORTE VALUES(1);