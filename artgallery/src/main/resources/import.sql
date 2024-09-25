-- Roles por defecto
-- INSERT INTO rol ( nombre) VALUES ( 'Project Manager'); 
-- INSERT INTO rol ( nombre) VALUES ( 'Tech Leader');
-- INSERT INTO rol ( nombre) VALUES ( 'Desarrollador');


-- Users por defecto
INSERT INTO user (id , nombre, cedula, correo, foto_perfil, password, username, id_rol) VALUES ( 1 , 'Daniel', '12345', 'datc@gmail.com', 'foto1.jpg', '$2a$12$4c0UFBS0kccjtWx.PRTXoOFVqNGQcGY.qxTJIEtyRefsPjNblxUAm', 'datc', 3);

INSERT INTO user (id, nombre, cedula, correo, foto_perfil, password, username, id_rol) 
VALUES (2, 'Alejandra Gómez', '5432123456', 'alejandra.gomez@example.com', 'foto2.jpg', '$2a$12$EA0GplLYsAVhpP7gW5HnN.cljpsnivr01EH.YdxgI/KGd273vfuIe', 'agomez', 2);

INSERT INTO user (id, nombre, cedula, correo, foto_perfil, password, username, id_rol) 
VALUES (3, 'Roberto García', '1001001001', 'roberto.garcia@example.com', 'foto3.jpg', '$2a$12$hTMge4t8CdBLNTWEXNbb9e5zQPzxUEiy1JBwxFXdUciXjIAm5E47O', 'rgarcia', 3);

INSERT INTO user (id, nombre, cedula, correo, foto_perfil, password, username, id_rol) 
VALUES (4, 'Carlos Jhoan Aguilar', '1095822445', 'carlosjhoanguilar@gmail.com', 'perfil.jpeg', '$2a$12$u0qqLXrY7mHMKc463ICduusv83bNdAWty9uLH/R/l2lAKtI50CML6', 'carlosjhoan', 1);


-- Estados por defecto
/* INSERT INTO estado (id, nombre) VALUES (1, 'En Progreso');
INSERT INTO estado (id, nombre) VALUES (2, 'Finalizado');
INSERT INTO estado (id, nombre) VALUES (3, 'En Revisión');
INSERT INTO estado (id, nombre) VALUES (4, 'Pendiente'); */

INSERT INTO proyecto (id, nombre, descripcion,  fecha_inicio, fecha_fin, horas_usadas, estado_id, id_techlead) 
VALUES (1, 'kktech', 'Crear la bitacora', '2024-09-01', '2024-09-05', 150, 1, 2);

INSERT INTO proyecto (id, nombre, descripcion, fecha_inicio, fecha_fin, horas_usadas, estado_id, id_techlead) 
VALUES (2, 'InnovaApp', 'Desarrollo de plataforma de innovación', '2024-08-15' , '2024-08-20', 200, 2, 2);

INSERT INTO proyecto (id, nombre, descripcion, fecha_inicio, fecha_fin, horas_usadas, estado_id, id_techlead) 
VALUES (3, 'EcoEnergy', 'Implementación de soluciones energéticas', '2024-07-20', '2024-07-25', 180, 1, 2);




-- Asistencia por defecto
/* INSERT INTO asistencia (fecha_entrada, fecha_salida, user_id) VALUES ('2024-01-01', '2024-01-01', 1);
INSERT INTO asistencia (fecha_entrada, fecha_salida, user_id) VALUES ('2024-01-02', '2024-01-02', 2);
INSERT INTO asistencia (fecha_entrada, fecha_salida, user_id) VALUES ('2024-01-03', '2024-01-03', 3);
INSERT INTO asistencia (fecha_entrada, fecha_salida, user_id) VALUES ('2024-01-04', '2024-01-04', 4);
INSERT INTO asistencia (fecha_entrada, fecha_salida, user_id) VALUES ('2024-01-05', '2024-01-05', 1); */

-- Usuario-Proyecto por defecto
INSERT INTO usuario_proyecto (usuario_id, proyecto_id) VALUES (1, 1);
INSERT INTO usuario_proyecto (usuario_id, proyecto_id) VALUES (1, 2);
INSERT INTO usuario_proyecto (usuario_id, proyecto_id) VALUES (3, 3);



-- Usuario-Proyecto por defecto
INSERT INTO actividad (id, descripcion, fecha_inicio, fecha_fin, horas_usadas, nombre, estado_id, proyecto_id, desarrollador_id) VALUES (1, 'Diseño de UI', '2024-01-10', '2024-01-20', 1, 'UI', 1, 3, 1);
INSERT INTO actividad (id, descripcion, fecha_inicio, fecha_fin, horas_usadas, nombre, estado_id, proyecto_id, desarrollador_id) VALUES (2, 'Desarrollo backend', '2024-02-01', '2024-03-15', 2, 'Backend', 1, 2, 1);
INSERT INTO actividad (id, descripcion, fecha_inicio, fecha_fin, horas_usadas, nombre, estado_id, proyecto_id, desarrollador_id) VALUES (3, 'Pruebas unitarias', '2024-05-01', '2024-05-15', 3, 'Pruebas', 2, 1, 3);

/* 
delete from user where id > 0;
delete from rol where id > 0;
 */