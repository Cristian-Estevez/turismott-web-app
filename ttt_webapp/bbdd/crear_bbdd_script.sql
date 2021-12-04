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

INSERT INTO atraccion (nombre, costo, duracion, cupo, tipo, descripcion, url_imagen) 
VALUES
("Moria", 10, 2, 6, 1, 
"Visitá este reino subterráneo 
debajo de las Misty Mountains. 
Este es el reino más antiguo y 
famoso de los Enanos de Durin.",
"https://i.pinimg.com/originals/22/16/6b/22166b1b7c3506049e25a7790f19aefe.jpg"),
("Minas de Tirith", 5, 2.5, 25, 2, 
"Vení a conocer la capital de Gondor, esta maravilla
está encerrada por el Rammas Echor, una gran pared
anillada que rodea los Campos del Pelennor. 
Esta pared servía como defensa, pero ahora los
restos nos dejan ver un pintoresco paisaje con una 
larga historia.",
"https://www.ecured.cu/images/5/5b/Minas_Thirit.jpg"),
("La Comarca", 3, 6.5, 150, 3,
"Este excelente barrio privado de hobbits te hará sentir como en casa
Tiene más de 5 millones de hectareas donde vas
a encontrar fiestas, alojamiento, spa y deliciosos bocados
de la comida local.",
"https://cdn.unitycms.io/image/ocroped/2001,2000,1000,1000,0,0/zZWiwBO-UuU/0x7dVEFLawQB9Y8dIRheNG.jpg"),
("Mordor", 25, 3, 4, 1,
"No te olvides del traje de baño para visitar Mordor!
Esta región volcánica fundada por Sauron se caracterizaba
por ser una fortaleza del mal. Ahora está lista para 
recibirte con las mejores aventuras y las mas ricas exquisiteces
de la zona.",
"https://storge.pic2.me/cm/2560x1440/180/605e74fa825653.60114200.jpg"),
("Abismo de Helm", 5, 2, 15, 2,
"¿Que pasa cuando se abre una ventana? Aparece este
excelente destino turistico! Abismo de Helm tiene para
ofrecer una gran variedad de actividades familiares.
Este paisaje pet-friendly tiene secretos que 
podrás descubirir en las visitas guiadas...¡las 24 horas del dia!",
"https://1.bp.blogspot.com/-sG9b4OI7R7I/WnZbKxHv6iI/AAAAAAAACjo/kKZ5IwNWyQMjXGDTsp7vCDSP0B9ZOdpRACLcBGAs/s1600/helms5.png"),
("Lothlórien", 35, 1,30, 3, 
"¿Estas buscando relajarte? Este reino de elfos te da la posibilidad
de relajarte, curar tu alma y rejuvenecerte
a un precio de locos!! ",
"https://elvenwisdomblog.files.wordpress.com/2020/09/tim-catherall-lothlorien.jpg"),
("Erebor", 12, 3, 32,2,
"Recomendado por Smaug, esta montaña solitaria tiene
siglos de historia que podrás apreciar en las visitas
turísticas.",
"https://ihan.signotic.com/wp-content/uploads/2017/09/cropped-Erebor_gate.jpg"),
("Bosque Negro", 3, 4, 12, 1,
"El Bosque Negro (Mirkwood en inglés), 
también llamado Taur-nu-Fuin o Taur-e-Ndadelos, 
fue un enorme bosque situado en 
Rhovanion, al nordeste de la Tierra Media. 
Conocido anteriormente como el Gran Bosque Verde, 
desde la construcción de la fortaleza de Dol Guldur 
por parte de Sauron, una gran sombra se cernió sobre el bosque, 
pasando a llamarse Bosque Negro.",
"https://static.wikia.nocookie.net/bibliotecadelatierramedia/images/7/7a/Bosque_Negro.jpg/revision/latest?cb=20140730114538&path-prefix=es"),
("Mar del Plata", 4, 5, 13, 3, 
"Mar del Plata es una ciudad balnearia argentina en la costa del Atlántico. 
Su larga franja de playas incluye la amplia Punta Mogotes y Playa Grande, con sus rompientes para el surf. 
Detrás de Playa Grande, las calles rodeadas de árboles del barrio Los Troncos 
tienen elegantes casas de comienzos del siglo XX, que ahora son museos.",
"https://radiomitre-la100-prod.cdn.arcpublishing.com/resizer/HyLlAvgOCZy7LRzbX8n8o0O_kJ4=/1200x0/smart/cloudfront-us-east-1.images.arcpublishing.com/radiomitre/YUK2ZHXEOJCYVCTBDGZYEBC4BI.jpg"),
("Chascomus", 5, 3, 65, 2, 
"Si uno piensa en Chascomús automáticamente piensa en su laguna. Podría pensar en la pesca, en 
Alfonsín o en el turismo rural. Pero no, la relación es con la laguna. 
Es que la laguna se lleva todas las miradas, sobre todo al atardecer. 
Caminar por la costanera es la actividad preferida de locales y visitantes",
"https://www.todoprovincial.com/wp-content/uploads/2021/11/chascomuus.jpg"),
("Bolivia", 3, 2, 60, 1,
"Bolivia es un país con una amplia variedad de paisajes, 
rica historia, importante cultura, tradiciones ancestrales y cálidas personas.",
"https://lh3.googleusercontent.com/proxy/0gvE9j2FaaLrLL2CNkpEQDhz1EjHd-GvQ-4pAGZl0ECF3uWnDXa-8Hn8DH4zzeBOmLMByli0s1295INOAXR1pz1IT6G4r6IhFB8ARdqefvS9dvX57z3uMtXg3cgru8fcXUSOwMHt5Sl_FOhEhGM4vg8bbvX5xrO7DTE6OZjanXEIgkdEU-XVA3Q-HYQwao7FhDEgnA9uJgK_2Q_iMa4CDpn5weFNAtV5FZxQLjQ-0Q"),
("Chile", 7, 30, 7, 2, 
"Desde el extremo norte con el desierto más árido del mundo, 
hasta el sur austral con hielos eternos y cascadas invertidas, 
Chile es una invitación difícil de rechazar. 
Conoce el turismo en Chile, y asómbrate con las experiencias en el extremo sur del mundo.", 
"https://www.zicasso.com/static/e647a850cfd012261cf6d0a70ffb3968/5b569/e647a850cfd012261cf6d0a70ffb3968.jpg");

INSERT INTO usuario (nombre, cantidad_monedas, tiempo, tipo_atraccion_favorita, es_admin) 
VALUES
("admin", 0, 0, 1, 1),
("eowyn", 10, 8, 1, 0),
("gandalf", 100, 5, 2, 0),
("sam", 36, 8, 3, 0),
("galadriel", 120, 4, 2, 0),
("ivan", 85, 7, 3, 0),
("nahuel", 30, 6, 2, 0),
("cristian joel", 95, 3, 1, 0),
("juanjo", 200, 5, 3, 0),
("cristian rigoberto",100, 2, 2, 0),
("cristian", 62, 60, 1, 0),
("micaela", 98, 10, 2, 0),
("federico", 68, 6, 3, 0),
("lucas", 160, 9, 2, 0),
("quique", 98, 8, 1, 0),
("evita", 46, 9, 1, 0),
("frida", 320, 9, 2, 0),
("silvina", 200, 9, 3, 0),
("chavela", 50, 10, 3, 0),
("alfonsina", 93, 8, 1, 0);

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
