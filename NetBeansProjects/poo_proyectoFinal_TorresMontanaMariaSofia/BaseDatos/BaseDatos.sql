create table departamentos(
   cod_departamento     int not null auto_increment,
   nombre_departamento  varchar(200) not null,
   dane_departamento    varchar(10) not null,
   primary key (cod_departamento)
);

create table ciudades
(
   cod_ciudad           int not null auto_increment,
   cod_departamento     int not null,
   nombre_ciudad        varchar(200) not null,
   dane_ciudad          varchar(10) not null,
   primary key (cod_ciudad)
);

CREATE TABLE revistas(
    cod_revista INTEGER auto_increment,
    nombre_revista VARCHAR(200) NOT NULL,
    categoria_revista VARCHAR(2) NOT NULL,
    issn_revista VARCHAR(20) NOT NULL,
	cod_ciudad INTEGER NOT NULL,
    PRIMARY KEY(cod_revista)
);

CREATE TABLE articulos(
    cod_articulo INTEGER auto_increment,
    nombre_articulo VARCHAR(200) NOT NULL,
    fecha_publicacion_articulo Date NOT NULL,
	tipo_articulo SMALLINT NOT NULL,
    cantidad_paginas_articulo SMALLINT NOT NULL,
    cod_revista INTEGER NOT NULL,
    PRIMARY KEY(cod_articulo)
);

ALTER TABLE articulos ADD CONSTRAINT FK_ARTICULOS 
    FOREIGN KEY(cod_revista) REFERENCES revistas(cod_revista) 
        ON DELETE RESTRICT ON UPDATE CASCADE;



alter table revistas add constraint fk_revista_ref_ciudad foreign key (cod_ciudad)
      references ciudades(cod_ciudad) on delete restrict on update cascade;

alter table ciudades add constraint fk_ciudad_ref_departamento foreign key (cod_departamento)
      references departamentos(cod_departamento) on delete restrict on update cascade;