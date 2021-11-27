/**
*  Las dos siguientes instrucciones sólo se ejecutan una vez
*/
SET GLOBAL time_zone = '-6:00';

DROP SCHEMA IF EXISTS vinyl;
DROP USER IF exists 'vinyl_user'@'%';

CREATE SCHEMA vinyl;
create user 'vinyl_user'@'%' identified by 'Dp123456';
grant all privileges on vinyl.* to 'vinyl_user'@'%';
flush privileges;

USE vinyl;

/*
* luego de crear el usuario y asignarlo no debiera de ejecutarse nuevamente
*/


CREATE TABLE rol (
  id int unsigned NOT NULL AUTO_INCREMENT,
  idRol VARCHAR(15) NOT NULL,
  descripcionRol VARCHAR(30) NOT NULL,
  activo Boolean,
  PRIMARY KEY (id),
  unique key  `idRol` (idRol)
);

CREATE TABLE usuario (
  idUsuario int NOT NULL AUTO_INCREMENT,
  emailUsuario VARCHAR(50) NOT NULL,
  pwUsuario varchar(255) not null,
  nombreUsuario VARCHAR(50) NOT NULL,
  apellidosUsuario VARCHAR(50) NOT NULL,
  provinciaUsuario varchar(30) not null,
  cantonUsuario varchar(30) not null,
  activo Boolean,
  idRol VARCHAR(15) NOT NULL,
  PRIMARY KEY (idUsuario),  
  CONSTRAINT FK_Usuario_Rol
    FOREIGN KEY (idRol)
    REFERENCES rol(idRol)
);

CREATE TABLE categoriaProd (
  idCategoria int NOT NULL AUTO_INCREMENT,
  nombreCategoria varchar(50) NOT NULL,
  PRIMARY KEY (idCategoria)
);


create table producto(
   id int unsigned NOT NULL AUTO_INCREMENT,
   idProd varchar(20) not null,
   nombreProd varchar(150) not null,
   descripcionProd varchar(300) not null,
   precioProd float not null,
   imgProd varchar(100),
   cantidadProd int(10),
   idCategoria int(11) not null,
   PRIMARY KEY (id),
   unique key  `idProd` (idProd),
   FOREIGN KEY (idCategoria)
    REFERENCES categoriaProd(idCategoria)
);

create table orden (
  idOrden int unsigned NOT NULL AUTO_INCREMENT,
  idUsuario int(10) not null,
  descOrden varchar(100) not null,
  montoOrden int(15) not null,
  PRIMARY KEY (idOrden),
  FOREIGN KEY (idUsuario)
    REFERENCES usuario(idUsuario)
);



insert into rol (idRol,descripcionRol,activo) values ('admin','Admninistrador del sistema',true),
  ('reportes','Solo emite reportes',true),
  ('respaldo','Solo hace respaldos de BD',false),
  ('cliente','Cliente regular',true);

insert into categoriaProd (nombreCategoria) values ('Acetato'),
  ('CD'),
  ('DVD'),
  ('Casette'),
  ('Digipak'),
  ('T-Shirt'),
  ('Accesorios'),
  ('Memorabilia');

insert into usuario (emailUsuario, pwUsuario, nombreUsuario, apellidosUsuario, provinciaUsuario, cantonUsuario, activo,idRol) 
             values ('diego.chavarria@vinyl.com', MD5('123'),'Diego','Chavarria','San José', 'Desamparados', true,'admin'),
			 ('andres.chavarria@vinyl.com', MD5('123'),'Andres','Chavarria','San José', 'Desamparados', true,'admin'),
			 ('mrhankey@crappy.com', MD5('123'),'Hank','Tomm','Heredia', 'Barva', true,'cliente'),
			 ('abbath@immortal.no', MD5('123'),'Abbath','Doom Occulta','Cartago', 'Turrialba', true,'cliente');
                           

insert into producto (idProd,nombreProd,descripcionProd, precioProd, imgProd, cantidadProd, idCategoria) values 
('CS-910','Morbid Angel - Altars of Madness', 'Album debut de la banda Morbid Angel.', 35000, './resources/images/image1.jpg',15,2),
('CR-821','Metallica - Black Album', 'El album mas vendido de la banda americana Metallica.', 40000, './resources/images/image2.jpg',12,1),
('KZ-550','Immortal - Sons of Northern Darkness', 'Disco lanzado en 2002, album de culto en el black metal.', 24500, './resources/images/image3.jpg',8,1),
('WQ-543','Nirvana - Nevermind', 'De lo mejor de la epoca grunge', 12000, './resources/images/image4.jpg',4,6),
('RT-734','Mayhem - Live in Marseille', 'Concierto en vivo de los noruegos Mayhem.', 20000, './resources/images/image5.jpg',9,3),
('CS-999','Hypocrisy - Worship', 'Nuevo album de Hypocrisy lanzado en 2021', 35000, './resources/images/image6.jpg',26,5);
 
