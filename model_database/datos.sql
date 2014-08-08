INSERT INTO urbanizacion VALUES 
    (1000,"Sanja",1222,2222,10),
    (1001,"CaraCota",23232,23232,20),
    (1002,"Latitud",12222,2121,30);
INSERT INTO manzano values
    (2000,1, 1001),
    (2001,5, 1001),
    (2002,6, 1002),
    (2003,2, 1002),
    (2004,3, 1002),
    (2005,4, 1000);

INSERT INTO lote VALUES
    (3001,10,10000,"Lote de la esquina",2000,200,100),
    (3002,11,20000,"lote del medio",2000,13123,8000),
    (3003,12,30000,"lote de a lado de la esquina",2000,121,7878),
    (3004,13,40000,"el primer lote",2001,121,3434),
    (3005,2,5000,"el segundo lote",2002,222,1214),
    (3006,3,6000,"el tecer loter",2003,544,2333),
    (3007,4,7000,"lotecito al lado del rio",2004,32432,32344),
    (3008,5,8000,"lleno de arboles",2005,2132,21321);

INSERT INTO cliente VALUES
    (1,"Juan","Perez","Fernandes",
    "AV Santa Cruz",7891222,7373737),
    (2,"Pepito","Paredes","Rojas","Calle Tarija",4897867,78978978),
    (3,"Fernando","Zurita",NULL,"Innominada",2111,2222),
    (4,"Juan","Ortiz",NULL,"Innominada",21212,123231),
    (5,"Rodrigo","Ramallo",NULL,"Innominada",54654,546456);

INSERT INTO venta VALUES
    (1,3001,1,12,"2014-03-03"),
    (2,3002,2,24,"2013-08-09"),
    (3,3003,3,12,"2013-03-12"),
    (4,3004,4,12,"2013-05-06"),
    (5,3005,5,36,"2014-05-06"),
    (6,3006,1,24,"2013-06-12"),
    (7,3007,2,12,"2013-07-09"),
    (8,3008,3,24,"2013-09-09");

-- INSERT INTO pago VALUES 
--     (1,1,"2014-04-04",500),
--     (2,1,"2014-05-05",400),
--     (3,2,"2013-09-10",300),
--     (4,2,"2013-10-11",400),
--     (5,2,"2013-11-10",500),
--     (6,2,"2013-12-09",1000);
