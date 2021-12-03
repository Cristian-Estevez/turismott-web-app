PRAGMA foreign_keys = OFF;
DROP TABLE IF EXISTS "atraccion";
DROP TABLE IF EXISTS "promocion";
DROP TABLE IF EXISTS "atraccion_promocion";
DROP TABLE IF EXISTS "itinerario";
DROP TABLE IF EXISTS "usuario";
DROP TABLE IF EXISTS "tipo_atraccion";
DROP TABLE IF EXISTS "tipo_promocion";
PRAGMA foreign_keys = ON;

CREATE TABLE "tipo_atraccion" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "tipo_promocion" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE "usuario" (
	"id"	INTEGER,
	"tipo_atraccion_favorita"	INTEGER NOT NULL,
	"nombre"	TEXT NOT NULL,
	"tiempo"	REAL NOT NULL,
	"cantidad_monedas"	INTEGER NOT NULL,
	"es_admin"	INTEGER NOT NULL DEFAULT 0,
	FOREIGN KEY("tipo_atraccion_favorita") REFERENCES "tipo_atraccion"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE "atraccion" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL,
	"costo"	REAL NOT NULL,
	"cupo"	INTEGER NOT NULL,
	"duracion"	REAL NOT NULL,
	"tipo"	TEXT NOT NULL,
	"descripcion"	TEXT NOT NULL DEFAULT ' ',
	"url_imagen"	TEXT,
	FOREIGN KEY("tipo") REFERENCES "tipo_atraccion"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE "promocion" (
	"id"	INTEGER,
	"tipo_atraccion"	INTEGER NOT NULL,
	"nombre"	TEXT,
	"descuento_costo"	REAL,
	"tipo_promocion"	INTEGER NOT NULL,
	"atraccion_id"	INTEGER,
	"descripcion"	TEXT NOT NULL DEFAULT ' ',
	"url_imagen"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("tipo_atraccion") REFERENCES "tipo_atraccion"("id"),
	FOREIGN KEY("atraccion_id") REFERENCES "atraccion"("id"),
	FOREIGN KEY("tipo_promocion") REFERENCES "tipo_promocion"("id")
);
CREATE TABLE "atraccion_promocion" (
	"id"	INTEGER,
	"atraccion_id"	INTEGER NOT NULL,
	"promocion_id"	INTEGER NOT NULL,
	FOREIGN KEY("atraccion_id") REFERENCES "atraccion"("id"),
	FOREIGN KEY("promocion_id") REFERENCES "promocion"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "itinerario" (
	"id"	INTEGER,
	"usuario_id"	INTEGER NOT NULL,
	"atraccion_id"	INTEGER,
	"promocion_id"	INTEGER,
	FOREIGN KEY("usuario_id") REFERENCES "usuario"("id"),
	FOREIGN KEY("promocion_id") REFERENCES "promocion"("id"),
	FOREIGN KEY("atraccion_id") REFERENCES "atraccion"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);

INSERT INTO tipo_atraccion (nombre)
VALUES
("AVENTURA"),
("PAISAJE"),
("DEGUSTACION");

INSERT INTO tipo_promocion (nombre)
VALUES
("porcentual"),
("absoluta"),
("AxB");

INSERT INTO atraccion (nombre, costo, duracion, cupo, tipo) 
VALUES
("Moria", 10, 2, 6, 1),
("Minas de Tirith", 5, 2.5, 25, 2),
("La Comarca", 3, 6.5, 150, 3),
("Mordor", 25, 3, 4, 1),
("Abismo de Helm", 5, 2, 15, 2),
("Lothlórien", 35, 1,30, 3),
("Erebor", 12, 3, 32,2 ),
("Bosque Negro", 3, 4, 12, 1),
("Mar del Plata", 4, 5, 13, 3),
("Chascomus", 5, 3, 65, 2),
("Bolivia", 3, 2, 60, 1),
("Chile", 7, 30, 7, 2);

INSERT INTO usuario (nombre, cantidad_monedas, tiempo, tipo_atraccion_favorita, es_admin) 
VALUES
("Admin", 0, 0, 1, 1),
("Eowyn", 10, 8, 1, 0),
("Gandalf", 100, 5, 2, 0),
("Sam", 36, 8, 3, 0),
("Galadriel", 120, 4, 2, 0),
("Ivan", 85, 7, 3, 0),
("Nahuel", 30, 6, 2, 0),
("Cristian Joel", 95, 3, 1, 0),
("Juanjo", 200, 5, 3, 0),
("Cristian Rigoberto",100, 2, 2, 0),
("Cristian", 62, 60, 1, 0),
("Micaela", 98, 10, 2, 0),
("Federico", 68, 6, 3, 0),
("Lucas", 160, 9, 2, 0),
("Quique", 98, 8, 1, 0),
("Evita", 46, 9, 1, 0),
("Frida", 320, 9, 2, 0),
("Silvina", 200, 9, 3, 0),
("Chavela", 50, 10, 3, 0),
("Alfonsina", 93, 8, 1, 0);

INSERT INTO promocion (tipo_atraccion, nombre, descuento_costo, tipo_promocion, atraccion_id, descripcion, url_imagen)
VALUES
(1, "Promo Aventura", 20, 1, NULL, 
"Estas son las atracciones elegidas por los más exquisitos catadores de aventuras. 
El comité internacional de Aventureros Medievales ha hecho esta selección de aventuras 
Premium para deleitar al invitado más curioso y llevarlo al límite del asombro. ",
"https://www.ecured.cu/images/5/5b/Minas_Thirit.jpg"),
(1, "Todo Aventura", 30, 2, NULL,
"Este paquete te asegura las mejores historias para que puedas contar en los próximos asados familares.
Necesitás renovar el repertorio de anecdotas? Comprá este pack y olvidate de mentir para impresionar,
la verdad salvaje te está esperando en Todo Aventura!",
"https://1.bp.blogspot.com/-sG9b4OI7R7I/WnZbKxHv6iI/AAAAAAAACjo/kKZ5IwNWyQMjXGDTsp7vCDSP0B9ZOdpRACLcBGAs/s1600/helms5.png"),
(2, "Promo Paisajes", 20, 1, NULL,
"Para vos lo ideal es una reposera, un mate y pajaritos cantando? Estas en el lugar correcto! La promo paisajes 
te va a hacer olvidar de lo que significa el estrés. Con un recorrido histórico y geográfico vas a poder
aprender y relajarte... No incluye reposera.",
"https://i.pinimg.com/originals/22/16/6b/22166b1b7c3506049e25a7790f19aefe.jpg"),
(2, "Pack Paisajes", NULL, 3, 5,
"¿Pensaste que lo habías visto todo? Te falto el Pack Paisajes; la asamblea mundial
de viajeros catalogó a estas atracciones como las más intrigantes y misteriosas. Las criaturas fantásticas,
asombrosas plantas e increíbles tiendas de souvenirs te van a dejar con ganas de volver!",
"https://elvenwisdomblog.files.wordpress.com/2020/09/tim-catherall-lothlorien.jpg"),
(3, "Sabores de Tierra Media", 36, 2, NULL,
"¿Alguna vez soñaste con visitar un lugar donde puedas comer arepas hobbitianas y de postre helado
élfico? Nosotros sí! Y por eso te traemos este impresionante recorrido culinario por las mejores
capitales gastronómicas de nuestra agencia. (Opciones especiales para vegetarianos y celíacos)",
"https://cdn.unitycms.io/image/ocroped/2001,2000,1000,1000,0,0/zZWiwBO-UuU/0x7dVEFLawQB9Y8dIRheNG.jpg");

INSERT INTO atraccion_promocion (promocion_id, atraccion_id)
VALUES
(1, 8),
(1, 4),
(2, 8),
(2, 4),
(2, 1),
(3, 2),
(3, 5),
(4, 2),
(4, 5),
(4, 7),
(5, 6),
(5, 3);
