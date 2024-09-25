-- Roles por defecto
/* INSERT INTO rol ( nombre) VALUES ( 'Administrador');
INSERT INTO rol ( nombre) VALUES ( 'Desarrollador');
INSERT INTO rol ( nombre) VALUES ( 'Tech Lead');
INSERT INTO rol ( nombre) VALUES ( 'QA');
INSERT INTO rol ( nombre) VALUES ( 'Project Manager'); */
/* 
-- Users por defecto
INSERT INTO user (id, nombre, cedula, correo, foto_perfil, password, username, id_rol) VALUES (6, 'Christian Celis', '1234567890', 'christian.celis@example.com', 'foto1.jpg', '$2a$12$5tAA5yFIOV6Q3HoS9B2udeVyzr.Dp2IUn9BZ086PLW2HCxT9BbMPi', 'ccelis', 3);


-- Estados por defecto
INSERT INTO estado (id, nombre) VALUES (1, 'En Progreso');
INSERT INTO estado (id, nombre) VALUES (2, 'Finalizado');
INSERT INTO estado (id, nombre) VALUES (3, 'En Revisión');
INSERT INTO estado (id, nombre) VALUES (4, 'En Progreso');
INSERT INTO estado (id, nombre) VALUES (5, 'Finalizado');

-- Asistencia por defecto
INSERT INTO asistencia (fecha_entrada, fecha_salida, user_id) VALUES ('2024-01-01', '2024-01-01', 6);
INSERT INTO asistencia (fecha_entrada, fecha_salida, user_id) VALUES ('2024-01-02', '2024-01-02', 2);
INSERT INTO asistencia (fecha_entrada, fecha_salida, user_id) VALUES ('2024-01-03', '2024-01-03', 3);
INSERT INTO asistencia (fecha_entrada, fecha_salida, user_id) VALUES ('2024-01-04', '2024-01-04', 4);
INSERT INTO asistencia (fecha_entrada, fecha_salida, user_id) VALUES ('2024-01-05', '2024-01-05', 5);

-- Usuario-Proyecto por defecto
INSERT INTO usuario_proyecto (usuario_id, proyecto_id) VALUES (2, 3);
INSERT INTO usuario_proyecto (usuario_id, proyecto_id) VALUES (3, 4);
INSERT INTO usuario_proyecto (usuario_id, proyecto_id) VALUES (4, 5);
INSERT INTO usuario_proyecto (usuario_id, proyecto_id) VALUES (5, 6);
INSERT INTO usuario_proyecto (usuario_id, proyecto_id) VALUES (2, 7);


-- Usuario-Proyecto por defecto
INSERT INTO actividad (id, descripcion, fecha_inicio, fecha_fin, horas_usadas, nombre, estado_id, proyecto_id, desarrollador_id) VALUES (1, 'Diseño de UI', '2024-01-10', '2024-01-20', 1, 'UI', 1, 3, 2);
INSERT INTO actividad (id, descripcion, fecha_inicio, fecha_fin, horas_usadas, nombre, estado_id, proyecto_id, desarrollador_id) VALUES (2, 'Desarrollo backend', '2024-02-01', '2024-03-15', 2, 'Backend', 1, 4, 3);
INSERT INTO actividad (id, descripcion, fecha_inicio, fecha_fin, horas_usadas, nombre, estado_id, proyecto_id, desarrollador_id) VALUES (3, 'Pruebas unitarias', '2024-05-01', '2024-05-15', 3, 'Pruebas', 2, 5, 4);
INSERT INTO actividad (id, descripcion, fecha_inicio, fecha_fin, horas_usadas, nombre, estado_id, proyecto_id, desarrollador_id) VALUES (4, 'Implementación de APIs', '2024-06-01', '2024-07-01', 4, 'APIs', 1, 5, 5);
INSERT INTO actividad (id, descripcion, fecha_inicio, fecha_fin, horas_usadas, nombre, estado_id, proyecto_id, desarrollador_id) VALUES (5, 'Integración con terceros', '2024-07-01', '2024-07-15', 5, 'Integración', 1, 6, 6); */