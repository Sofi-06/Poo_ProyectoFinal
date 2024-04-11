/**
 * Author:  luzeg
 * Created: 8/10/2023
 */
INSERT INTO revistas (nombre_revista, categoria_revista, issn_revista) VALUES ("Ingeniare","C","5878");
INSERT INTO revistas (nombre_revista, categoria_revista, issn_revista) VALUES ("Mathematics","A1","7895");
INSERT INTO revistas (nombre_revista, categoria_revista, issn_revista) VALUES ("Ingenio Magno","B","4589");


-- Inserción de datos en la tabla revistas
INSERT INTO revistas (nombre_revista, categoria_revista, issn_revista, cod_ciudad)
VALUES ('Revista A', 'T1', '1234-5678', 1),
       ('Revista B', 'T2', '5678-1234', 2),
       ('Revista C', 'T1', '9012-3456', 1);

-- Inserción de datos en la tabla articulos
INSERT INTO articulos (nombre_articulo, fecha_publicacion_articulo, tipo_articulo, cantidad_paginas_articulo, cod_revista)
VALUES ('Artículo 1', '2023-01-15', 1, 10, 1),
       ('Artículo 2', '2023-02-20', 2, 8, 2),
       ('Artículo 3', '2023-03-10', 1, 12, 1),
       ('Artículo 4', '2023-04-05', 2, 6, 3);
