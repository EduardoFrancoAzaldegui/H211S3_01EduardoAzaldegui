
CREATE DATABASE PizzaHut;
GO

USE PizzaHut;


-- tables
-- Table: CARGO
CREATE TABLE CARGO (
    IDCAR int  NOT NULL IDENTITY,
    NOMCAR varchar(30)  NOT NULL,
    ESTCAR char(1)  NOT NULL DEFAULT 'A',
    CONSTRAINT CARGO_pk PRIMARY KEY  (IDCAR)
);

-- Table: CLIENTE
CREATE TABLE CLIENTE (
    IDCLI int  NOT NULL IDENTITY,
    NOMCLI varchar(20)  NOT NULL,
    CELCLI char(9)  NOT NULL,
    DIRCLI varchar(40)  NOT NULL,
    CORCLI varchar(30)  NOT NULL,
    ESTCLI char(1)  NOT NULL DEFAULT 'A',
    CONSTRAINT CLIENTE_pk PRIMARY KEY  (IDCLI)
);

-- Table: EMPLEADO
CREATE TABLE EMPLEADO (
    IDEMP int  NOT NULL IDENTITY,
    NOMEMP varchar(20)  NOT NULL,
    CELEMP char(9)  NOT NULL,
    COREMP varchar(30)  NOT NULL,
    ESTEMP char(1)  NOT NULL DEFAULT 'A',
    IDCAR int  NOT NULL,
    CONSTRAINT EMPLEADO_pk PRIMARY KEY  (IDEMP)
);

-- Table: EQUIPO
CREATE TABLE EQUIPO (
    IDEQUI int  NOT NULL IDENTITY,
    ESTQUI char(1)  NOT NULL DEFAULT 'A',
    IDEMP int  NOT NULL,
    IDSUC int  NOT NULL,
    CONSTRAINT EQUIPO_pk PRIMARY KEY  (IDEQUI)
);

-- Table: PRODUCTO
CREATE TABLE PRODUCTO (
    IDPRO int  NOT NULL IDENTITY,
    NOMPRO varchar(30)  NOT NULL,
    PREPRO decimal(5,2)  NOT NULL,
    CANPRO int  NOT NULL,
    ESTPRO char(1)  NOT NULL DEFAULT 'A',
    CONSTRAINT PRODUCTO_pk PRIMARY KEY  (IDPRO)
);

-- Table: SUCURSAL
CREATE TABLE SUCURSAL (
    IDSUC int  NOT NULL IDENTITY,
    ESTSUC char(1)  NOT NULL DEFAULT 'A',
    IDEMP int  NOT NULL , 
    CONSTRAINT SUCURSAL_pk PRIMARY KEY  (IDSUC)
);

-- Table: VENTA
CREATE TABLE VENTA (
    IDVEN int  NOT NULL IDENTITY,
    FECVENT datetime  NOT NULL,
    TIPVENT int  NOT NULL,
    IDCLI int  NOT NULL,
    IDEMP int  NOT NULL,
    CONSTRAINT VENTA_pk PRIMARY KEY  (IDVEN)
);

-- Table: VENTA_DETALLE
CREATE TABLE VENTA_DETALLE (
    IDVENDET int  NOT NULL IDENTITY,
    CANTVEDET int  NOT NULL,
    SUBVEDET decimal(5,2)  NOT NULL,
    IDPRO int  NOT NULL,
    IDVEN int  NOT NULL,
    CONSTRAINT VENTA_DETALLE_pk PRIMARY KEY  (IDVENDET)
);

-- foreign keys
-- Reference: EMPLEADO_CARGO (table: EMPLEADO)
ALTER TABLE EMPLEADO ADD CONSTRAINT EMPLEADO_CARGO
    FOREIGN KEY (IDCAR)
    REFERENCES CARGO (IDCAR);

-- Reference: EQUIPO_EMPLEADO (table: EQUIPO)
ALTER TABLE EQUIPO ADD CONSTRAINT EQUIPO_EMPLEADO
    FOREIGN KEY (IDEMP)
    REFERENCES EMPLEADO (IDEMP);

-- Reference: EQUIPO_SUCURSAL (table: EQUIPO)
ALTER TABLE EQUIPO ADD CONSTRAINT EQUIPO_SUCURSAL
    FOREIGN KEY (IDSUC)
    REFERENCES SUCURSAL (IDSUC);

-- Reference: SUCURSAL_EMPLEADO (table: SUCURSAL)
ALTER TABLE SUCURSAL ADD CONSTRAINT SUCURSAL_EMPLEADO
    FOREIGN KEY (IDEMP)
    REFERENCES EMPLEADO (IDEMP);

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

-- Reference: VENTA_EMPLEADO (table: VENTA)
ALTER TABLE VENTA ADD CONSTRAINT VENTA_EMPLEADO
    FOREIGN KEY (IDEMP)
    REFERENCES EMPLEADO (IDEMP);

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

-- Sequence: VENTA_DETALLE_seq
CREATE SEQUENCE VENTA_DETALLE_seq
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


------ INSERCION DE DATOS -----------


/* Insertar registros tabla CARGO */INSERT INTO CARGO (NOMCAR) VALUES('Jefe de Sucursal'),('Administrador'),('Vendedor'),('Despachador')SELECT * FROM CARGO;




/* Insertar registros tabla CLIENTE */INSERT INTO CLIENTE(NOMCLI, CELCLI, CORCLI,DIRCLI)VALUES('Sebastian', '987456321', 'sebastian122330@gmail.com','Los girasoles MzE'),('Pedro', '985203697', 'Pedro15@gmail.com','Plaza de Arma'),('Juan', '901254789', 'JuanLopes@gmail.com','Imperial'),('Eduardo', '974103695', 'Eduardo@gmail.com','Cerro azul '),('Julio', '974105996', 'JulioC@gmail.com','Villa vista'),('Victor', '933201596', 'Victor123@gmail.com','28 de julio'),('Jose', '901254006', 'JoseV@gmail.com','Plaza de Arma Mz E lt8'),('Maria','985476300', 'MariaP@gmail.com','San Vicente'),('Carmen', '999632014', 'CarmenP@gmail.com','Nuevo Imperial'),('Esperanza', '955214632', 'Esperanza1@gmail.com','Cementerio')SELECT * FROM CLIENTE/* Insertar registros tabla EMPLEADO */INSERT INTO EMPLEADO(NOMEMP,CELEMP,COREMP,IDCAR)VALUES('Lucas', '987456320', 'Lucas@gmail.com',1),
('Fabrizio', '974158996', 'Fabrizio@gmail.com',2),
('Anderson', '902356489', 'AndersonC@gmail.com',3),
('Sandra',  '912365409', 'SandraT@gmail.com',2),
('Kyara',  '987410236', 'KyaraH@gmail.com',3),
('Lucia', '900147852', 'LuciaP@gmail.com',2),
('Estrella',  '978456932', 'EstrellaA@gmail.com',1),
('Alberto', '941203256', 'Alberto@gmail.com',3),
('Jesus', '974120358', 'JesusC@gmail.com',4),
('Carlos', '989520365', 'Carlos@gmail.com',4)SELECT * FROM EMPLEADO/* Insertar registros tabla PRODUCTO */INSERT INTO PRODUCTO(NOMPRO,CANPRO,PREPRO)VALUES('Pizza margarita',50,16.00),('Pizza cuatro quesos',70,15.00),('Pizza de pepperoni',50,28.00),('Pizza cuatro estaciones',100,12.00),('Pizza con champiñones',60,50.00),('Pizza hawaiana',45,70.00),('Pizza marinara',9,90.00),('Pizza napolitana',5,80.00),('Pizza neoyorquina',20,76.00),('Pizza fugazza',10,98.00)SELECT * FROM PRODUCTO/* Insertar registros tabla SUCURSAL */INSERT INTO SUCURSAL(IDEMP)VALUES(1),(1),(7),(1),(7),(1),(7),(7),(1),(1),(7),(1)SELECT * FROM SUCURSAL;/* Insertar registros tabla EQUIPO */INSERT INTO EQUIPO(IDEMP,IDSUC)VALUES(3,1),(8,1),(8,2),(5,1),(8,3),(3,4),(5,9),(3,9),(8,2),(5,3),(5,4),(1,7)SELECT * FROM EQUIPO;

-----------    TRANSACCIONALES  ----------------






