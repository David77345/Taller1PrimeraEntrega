INSERT INTO proyectos (id, nombre, id_cliente, fecha) VALUES (1, 'Remodelacion sala principal', 101, DATE '2026-03-01');
INSERT INTO proyectos (id, nombre, id_cliente, fecha) VALUES (2, 'Adecuacion oficina administrativa', 102, DATE '2026-03-02');
INSERT INTO proyectos (id, nombre, id_cliente, fecha) VALUES (3, 'Construccion terraza exterior', 103, DATE '2026-03-03');
INSERT INTO proyectos (id, nombre, id_cliente, fecha) VALUES (4, 'Reparacion techo bodega', 104, DATE '2026-03-04');
INSERT INTO proyectos (id, nombre, id_cliente, fecha) VALUES (5, 'Instalacion piso ceramico', 105, DATE '2026-03-05');
INSERT INTO proyectos (id, nombre, id_cliente, fecha) VALUES (6, 'Pintura general apartamento', 106, DATE '2026-03-06');
INSERT INTO proyectos (id, nombre, id_cliente, fecha) VALUES (7, 'Ampliacion cocina integral', 107, DATE '2026-03-07');
INSERT INTO proyectos (id, nombre, id_cliente, fecha) VALUES (8, 'Cambio de puertas internas', 108, DATE '2026-03-08');
INSERT INTO proyectos (id, nombre, id_cliente, fecha) VALUES (9, 'Reforzamiento muro lateral', 109, DATE '2026-03-09');
INSERT INTO proyectos (id, nombre, id_cliente, fecha) VALUES (10, 'Mantenimiento local comercial', 110, DATE '2026-03-10');

INSERT INTO presupuestos (id, id_proyecto, total) VALUES (1, 1, 1850000);
INSERT INTO presupuestos (id, id_proyecto, total) VALUES (2, 2, 1320000);
INSERT INTO presupuestos (id, id_proyecto, total) VALUES (3, 3, 2640000);
INSERT INTO presupuestos (id, id_proyecto, total) VALUES (4, 4, 980000);
INSERT INTO presupuestos (id, id_proyecto, total) VALUES (5, 5, 1560000);
INSERT INTO presupuestos (id, id_proyecto, total) VALUES (6, 6, 890000);
INSERT INTO presupuestos (id, id_proyecto, total) VALUES (7, 7, 2140000);
INSERT INTO presupuestos (id, id_proyecto, total) VALUES (8, 8, 760000);
INSERT INTO presupuestos (id, id_proyecto, total) VALUES (9, 9, 1730000);
INSERT INTO presupuestos (id, id_proyecto, total) VALUES (10, 10, 1190000);

INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (1, 'Cemento gris', 'Bolsa de cemento gris 50kg', 38000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (2, 'Arena lavada', 'Metro cubico de arena lavada', 95000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (3, 'Gravilla', 'Metro cubico de gravilla seleccionada', 110000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (4, 'Varilla 3/8', 'Varilla corrugada de 3/8 pulgada', 29000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (5, 'Ladrillo estructural', 'Ladrillo estructural rojo', 1800);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (6, 'Ceramica blanca', 'Caja de ceramica blanca 60x60', 72000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (7, 'Pintura vinilo', 'Galon de pintura vinilo blanco', 48000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (8, 'Yeso en polvo', 'Bolsa de yeso para acabado', 26000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (9, 'Madera triplex', 'Lamina triplex 15mm', 87000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (10, 'Tornillo drywall', 'Caja de tornillos drywall', 22000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (11, 'Puerta madera', 'Puerta entamborada interior', 210000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (12, 'Bisagra', 'Bisagra metalica reforzada', 8500);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (13, 'Teja PVC', 'Teja PVC trapezoidal', 68000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (14, 'Impermeabilizante', 'Galon impermeabilizante elastico', 56000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (15, 'Pegante ceramico', 'Bulto pegante ceramico', 34000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (16, 'Boquilla', 'Boquilla para juntas ceramicas', 14000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (17, 'Tubo PVC 2 pulgadas', 'Tubo PVC sanitario 2 pulgadas', 31000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (18, 'Codo PVC', 'Codo PVC sanitario', 7000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (19, 'Brocha 4 pulgadas', 'Brocha profesional 4 pulgadas', 12000);
INSERT INTO materiales (id, nombre, descripcion, valor_unitario) VALUES (20, 'Rodillo pintura', 'Rodillo para pintura de muro', 18000);

INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (1, 1, 1, 10, 380000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (2, 1, 2, 4, 380000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (3, 1, 5, 120, 216000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (4, 1, 7, 6, 288000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (5, 1, 8, 6, 156000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (6, 1, 20, 4, 72000);

INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (7, 2, 9, 6, 522000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (8, 2, 10, 5, 110000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (9, 2, 7, 8, 384000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (10, 2, 19, 4, 48000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (11, 2, 20, 4, 72000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (12, 2, 8, 7, 182000);

INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (13, 3, 1, 15, 570000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (14, 3, 2, 6, 570000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (15, 3, 3, 3, 330000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (16, 3, 4, 12, 348000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (17, 3, 5, 180, 324000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (18, 3, 14, 9, 504000);

INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (19, 4, 13, 8, 544000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (20, 4, 14, 5, 280000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (21, 4, 10, 3, 66000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (22, 4, 4, 2, 58000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (23, 4, 19, 2, 24000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (24, 4, 20, 1, 18000);

INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (25, 5, 6, 10, 720000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (26, 5, 15, 8, 272000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (27, 5, 16, 6, 84000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (28, 5, 1, 6, 228000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (29, 5, 2, 2, 190000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (30, 5, 19, 3, 36000);

INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (31, 6, 7, 9, 432000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (32, 6, 19, 5, 60000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (33, 6, 20, 5, 90000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (34, 6, 8, 6, 156000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (35, 6, 10, 2, 44000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (36, 6, 9, 1, 87000);

INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (37, 7, 9, 8, 696000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (38, 7, 10, 8, 176000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (39, 7, 11, 4, 840000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (40, 7, 12, 10, 85000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (41, 7, 7, 5, 240000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (42, 7, 20, 3, 54000);

INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (43, 8, 11, 3, 630000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (44, 8, 12, 8, 68000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (45, 8, 10, 2, 44000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (46, 8, 19, 1, 12000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (47, 8, 20, 1, 18000);

INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (48, 9, 1, 9, 342000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (49, 9, 2, 4, 380000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (50, 9, 4, 14, 406000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (51, 9, 5, 140, 252000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (52, 9, 14, 5, 280000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (53, 9, 8, 3, 78000);

INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (54, 10, 7, 7, 336000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (55, 10, 19, 4, 48000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (56, 10, 20, 4, 72000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (57, 10, 17, 8, 248000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (58, 10, 18, 6, 42000);
INSERT INTO detalle_presupuesto (id, id_presupuesto, id_material, stock, subtotal) VALUES (59, 10, 8, 4, 104000);