create table cliente (
    ci integer primary key,
    nombre varchar(255),
    apellido_paterno varchar(255),
    apellido_materno varchar(255),
    direccion varchar(255),
    telefono_fijo integer,
    telefono_celular integer
);

create table urbanizacion(
    id serial primary key,
    nombre varchar(255),
    ancho double,
    largo double,
    cantidad_lotes integer
);

create table lote(
    id serial primary key,
    urbanizacion_id integer references urbanizacion(id),
    numero_lote integer,
    descripcion varchar(255),
    ancho double,
    largo double,
    precio double
);
create table venta(
    id serial primary key,
    lote_id integer references lote(id),
    cliente_id integer references cliente(ci),
    cantidad_cuotas integer,
    fecha_venta timestamp
);
create table pago(
    id serial primary key,
    venta_id integer references venta(id),
    fecha_pago timestamp,
    monto double
);


