-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-09-2019 a las 02:18:20
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.2.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `uytube`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `canal`
--

CREATE TABLE `canal` (
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `privacidad` bit(1) DEFAULT NULL,
  `usuario_nickname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `canal`
--

INSERT INTO `canal` (`nombre`, `descripcion`, `privacidad`, `usuario_nickname`) VALUES
(' ', 'Canal HG', b'1', 'hectorg'),
('Canal Horacio', 'El canal Horacio es para publicar contenido divertido', b'1', 'hrubino'),
('Chino Recoba', 'Canal de goles con Nacional', b'0', 'chino'),
('Con la gente', 'Preparando las elecciones', b'1', 'novick'),
('Desde Genexus', 'Canal informacin C y T', b'1', 'nicoJ'),
('diegop', 'Canal de DP', b'1', 'diegop'),
('El Bocha', 'Canal HG', b'1', 'mbusca'),
('El Cachila', 'Para juntar cosas', b'0', 'cachilas'),
('juliob', 'Canal de JB', b'1', 'juliob'),
('Kairo msica', 'Videos de grandes canciones de hoy y siempre', b'1', 'kairoh'),
('Puglia invita', 'Programas del ciclo y videos de cocina masterchef', b'1', 'sergiop'),
('robinh', 'Henderson', b'1', 'robinh'),
('Tabar', 'Mi msica e ainda mais', b'1', 'tabarec'),
('Tinelli total', 'Todo lo que queras y ms !', b'1', 'marcelot'),
('Tony Pacheco', 'Todos los goles con Pearol', b'0', 'tonyp');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`) VALUES
(1, 'Musica'),
(2, 'Deporte'),
(3, 'Carnaval'),
(4, 'Noticias'),
(5, 'Entretenimiento'),
(6, 'Comida'),
(7, 'Videojuego'),
(8, 'Ciencia y tecnologia'),
(9, 'ONG y activismo'),
(10, 'Gente y blogs'),
(11, 'Mascotas y animales'),
(12, 'Viajes y eventos'),
(13, 'Sin Categoria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE `comentario` (
  `id` bigint(20) NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `usuario_nickname` varchar(255) DEFAULT NULL,
  `vid_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `comentario`
--

INSERT INTO `comentario` (`id`, `comentario`, `fecha`, `usuario_nickname`, `vid_id`) VALUES
(32, 'Fue un gran evento', '2017-12-05 00:00:00', 'nicoJ', 7),
(33, 'Para el próximo aniversario ofrezco  vamo’ con los Momo', '2017-12-08 00:00:00', 'hrubino', NULL),
(34, 'Felicitaciones FING!!!', '3870-02-01 00:00:00', 'nicoJ', 6),
(35, 'Un gusto cubrir eventos como este.', '2017-10-23 00:00:00', 'kairoh', 8),
(36, 'Peñarol peñarol!!!', '2016-11-14 00:00:00', 'kairoh', 13),
(37, 'Rock and Rolllll', '2017-10-30 00:00:00', 'marcelot', 3),
(38, 'Anoche exploto!!!', '2018-08-25 00:00:00', 'marcelot', 4),
(39, 'Me encanta este tema', '2017-09-11 00:00:00', 'tabarec', 5),
(40, 'Gracias Marce ;)', '2017-09-15 00:00:00', 'marcelot', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario_comentario`
--

CREATE TABLE `comentario_comentario` (
  `Comentario_id` bigint(20) NOT NULL,
  `respuestas_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `comentario_comentario`
--

INSERT INTO `comentario_comentario` (`Comentario_id`, `respuestas_id`) VALUES
(32, 33),
(39, 40);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(41),
(41),
(41),
(41);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lista`
--

CREATE TABLE `lista` (
  `id` int(11) NOT NULL,
  `nombre_lista` varchar(255) DEFAULT NULL,
  `privado` bit(1) DEFAULT NULL,
  `tipo` bit(1) DEFAULT NULL,
  `canal_nombre` varchar(255) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `lista`
--

INSERT INTO `lista` (`id`, `nombre_lista`, `privado`, `tipo`, `canal_nombre`, `categoria_id`) VALUES
(17, 'Nostalgia', b'0', b'0', 'Kairo msica', 1),
(18, 'De fiesta', b'1', b'0', 'Tabar', 1),
(19, 'Novedades FING', b'0', b'0', ' ', 4),
(20, 'De todo un poco', b'1', b'0', 'El Cachila', 1),
(21, 'Noticias y CYT', b'0', b'0', 'Desde Genexus', 4),
(22, 'Solo deportes', b'0', b'0', 'juliob', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lista_video`
--

CREATE TABLE `lista_video` (
  `Lista_id` int(11) NOT NULL,
  `videos_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `lista_video`
--

INSERT INTO `lista_video` (`Lista_id`, `videos_id`) VALUES
(17, 3),
(17, 4),
(17, 5),
(18, 1),
(18, 2),
(18, 10),
(19, 6),
(19, 7),
(19, 8),
(20, 1),
(20, 2),
(20, 9),
(20, 10),
(20, 13),
(21, 8),
(21, 16),
(22, 14),
(22, 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `nickname` varchar(255) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `fnacimiento` datetime DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`nickname`, `apellido`, `correo`, `fnacimiento`, `img`, `nombre`) VALUES
('cachilas', 'Silva', 'Cachila.sil@c1080.org.uy', '3847-02-01 00:00:00', '', 'Waldemar Cachila'),
('chino', 'Recoba', 'chino@trico.org.uy', '3876-04-07 00:00:00', '', 'Alvaro'),
('diegop', 'Parodi', 'diego@efectocine.com', '3875-02-01 00:00:00', '', 'Diego'),
('hectorg', 'Guido', 'hector.gui@elgalpon.org.uy', '3854-02-07 00:00:00', '', 'Hctor'),
('hrubino', 'Rubino', 'horacio.rubino@guambia.com.uy', '3862-03-05 00:00:00', '', 'Horacio'),
('juliob', 'Bocca', 'juliobocca@sodre.com.uy', '3867-04-06 00:00:00', '', 'Julio'),
('kairoh', 'Herrera', 'kairoher@pilsenrock.com.uy', '3740-05-05 00:00:00', '', 'Kairo'),
('marcelot', 'Tinelli', 'marcelot@ideasdelsur.com.ar', '3860-05-01 00:00:00', '', 'Marcelo'),
('mbusca', 'Buscaglia', 'Martin.bus@agadu.org.uy', '3872-07-04 00:00:00', '', 'Martn'),
('nicoJ', 'Jodal', 'jodal@artech.com.uy', '3860-09-09 00:00:00', '', 'Nicols'),
('novick', 'Novick', 'edgardo@novick.com.uy', '3852-08-07 00:00:00', '', 'Edgardo'),
('robinh', 'Henderson', 'Robin.h@tinglesa.com.uy', '3840-09-03 00:00:00', '', 'Robin'),
('sergiop', 'Puglia', 'puglia@alpanpan.com.uy', '3850-02-08 00:00:00', '', 'Sergio'),
('tabarec', 'Cardozo', 'tabare.car@agadu.org.uy', '3871-08-04 00:00:00', '', 'Tabar'),
('tonyp', 'Pacheco', 'eltony@manya.org.uy', '3855-03-04 00:00:00', '', 'Antonio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_canal`
--

CREATE TABLE `usuario_canal` (
  `Usuario_nickname` varchar(255) NOT NULL,
  `canalesSeguidos_nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario_canal`
--

INSERT INTO `usuario_canal` (`Usuario_nickname`, `canalesSeguidos_nombre`) VALUES
('hrubino', ' '),
('hrubino', 'diegop'),
('mbusca', 'Tabar'),
('mbusca', 'El Cachila'),
('mbusca', 'Kairo msica'),
('hectorg', 'El Bocha'),
('hectorg', 'juliob'),
('tabarec', 'El Cachila'),
('tabarec', 'Canal Horacio'),
('cachilas', 'Canal Horacio'),
('juliob', 'El Bocha'),
('juliob', 'diegop'),
('diegop', ' '),
('kairoh', 'Puglia invita'),
('robinh', ' '),
('robinh', 'juliob'),
('robinh', 'diegop'),
('marcelot', 'El Cachila'),
('marcelot', 'juliob'),
('marcelot', 'Kairo msica'),
('novick', ' '),
('novick', 'Tabar'),
('novick', 'El Cachila'),
('sergiop', 'El Bocha'),
('sergiop', 'juliob'),
('sergiop', 'diegop'),
('chino', 'Tony Pacheco'),
('tonyp', 'Chino Recoba'),
('nicoJ', 'diegop');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoracionvideo`
--

CREATE TABLE `valoracionvideo` (
  `id` int(11) NOT NULL,
  `valoracion` int(11) DEFAULT NULL,
  `usuario_nickname` varchar(255) DEFAULT NULL,
  `video_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `valoracionvideo`
--

INSERT INTO `valoracionvideo` (`id`, `valoracion`, `usuario_nickname`, `video_id`) VALUES
(23, -1, 'sergiop', 7),
(24, 1, 'sergiop', 8),
(25, 1, 'sergiop', 11),
(26, -1, 'nicoJ', 1),
(27, 1, 'nicoJ', 7),
(28, 1, 'kairoh', 7),
(29, 1, 'kairoh', 13),
(30, 1, 'marcelot', 1),
(31, 1, 'marcelot', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `video`
--

CREATE TABLE `video` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `duracion` varchar(255) DEFAULT NULL,
  `es_publico` bit(1) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `canal_nombre` varchar(255) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `video`
--

INSERT INTO `video` (`id`, `descripcion`, `duracion`, `es_publico`, `fecha`, `nombre`, `url`, `canal_nombre`, `categoria_id`) VALUES
(1, '', '', b'0', '3870-02-01 00:00:00', 'Locura celeste', 'https://youtu.be/PAfbzKcePx0', 'Tabar', 1),
(2, '', '', b'0', '3870-02-01 00:00:00', 'Nio payaso', 'https://youtu.be/K-uEIUnyZPg', 'Tabar', 1),
(3, '', '', b'1', '3870-02-01 00:00:00', 'Sweet child\'o mine', 'https://youtu.be/1w7OgIMMRc4', 'Kairo msica', 1),
(4, '', '', b'1', '3870-02-01 00:00:00', 'Dancing in the Dark', 'https://youtu.be/129kuDCQtHs', 'Kairo msica', 1),
(5, '', '', b'1', '3870-02-01 00:00:00', 'Thriller', 'https://youtu.be/sOnqjkJTMaA', 'Kairo msica', 1),
(6, 'Noticias', '', b'1', '3870-02-01 00:00:00', '100 aos de FING', 'https://youtu.be/peGS4TBxSaI', ' ', 4),
(7, 'Noticias', '', b'1', '3870-02-01 00:00:00', '50 aos del InCo', 'https://youtu.be/GzOJSk4urlM', ' ', 4),
(8, 'Carnaval', '', b'1', '3870-02-01 00:00:00', 'Ingeniera de Muestra 2017', 'https://youtu.be/RnaYRA1k5j4', ' ', 3),
(9, '', '', b'0', '3870-02-01 00:00:00', 'Etapa A contramano Liguilla', 'https://youtu.be/Es6GRMHXeCQ', 'El Cachila', 3),
(10, '', '', b'0', '3870-02-01 00:00:00', 'Etapa Don Timoteo Liguilla', 'https://youtu.be/I_spHBU9ZsI', 'El Cachila', 3),
(11, '', '', b'1', '3870-02-01 00:00:00', 'Show de goles', 'https://youtu.be/g46w4_kD_lA', 'juliob', 2),
(12, '', '', b'0', '3870-02-01 00:00:00', 'Pacheco goles ms recordados', 'https://youtu.be/wlEd6-HsIxI', 'Tony Pacheco', 2),
(13, '', '', b'1', '3870-02-01 00:00:00', 'Inauguracin Estadio Pearol', 'https://youtu.be/U6XPJ8Vz72A', 'Tony Pacheco', 2),
(14, '', '', b'0', '3870-02-01 00:00:00', 'Recoba 20 mejores goles', 'https://youtu.be/Gy3fZhWdLEQ', 'Chino Recoba', 2),
(15, 'Ciencia y tecnologia', '', b'1', '3870-02-01 00:00:00', 'Entrevista a director CUTI', 'https://youtu.be/Eq5uBEzI6qs', 'Desde Genexus', 8),
(16, 'Ciencia y tecnologia', '', b'1', '3870-02-01 00:00:00', 'Ventana al futuro Uruguay y dficit de ingenieros', 'https://youtu.be/zBR2pnASlQE', 'Desde Genexus', 8);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `canal`
--
ALTER TABLE `canal`
  ADD PRIMARY KEY (`nombre`),
  ADD KEY `FKr68vuatjahcrq0hjqp2ie9u33` (`usuario_nickname`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8ml0o19yklqbplnjt0wqac1vf` (`usuario_nickname`),
  ADD KEY `FK4cw21rqxcjbhuh4dxiiee0u1k` (`vid_id`);

--
-- Indices de la tabla `comentario_comentario`
--
ALTER TABLE `comentario_comentario`
  ADD KEY `FKqack4f55a31h9nkw7yavih10k` (`respuestas_id`),
  ADD KEY `FK97aahv147tveifpc2e5tdta9` (`Comentario_id`);

--
-- Indices de la tabla `lista`
--
ALTER TABLE `lista`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1vkjxkbwg0ikfaymekf6t2w1i` (`canal_nombre`),
  ADD KEY `FK7p34u32gug0h5ng6o0hhxj3y9` (`categoria_id`);

--
-- Indices de la tabla `lista_video`
--
ALTER TABLE `lista_video`
  ADD KEY `FKp9kb937apcemdo9lhu3yinuer` (`videos_id`),
  ADD KEY `FKfe7l5t5grpslv3yumk6qbafk` (`Lista_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`nickname`);

--
-- Indices de la tabla `usuario_canal`
--
ALTER TABLE `usuario_canal`
  ADD KEY `FK3cs35ibflevggvwyfrmhqq0dg` (`canalesSeguidos_nombre`),
  ADD KEY `FKlewtfkx26qsk69p7ab0iys2cy` (`Usuario_nickname`);

--
-- Indices de la tabla `valoracionvideo`
--
ALTER TABLE `valoracionvideo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKe8ytcbq5wpa2o26ew2dro5j8s` (`usuario_nickname`),
  ADD KEY `FKsjqovecqbh6biueh04wwof0rh` (`video_id`);

--
-- Indices de la tabla `video`
--
ALTER TABLE `video`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnt538fwr3rvtxfdxmt927dlom` (`canal_nombre`),
  ADD KEY `FK7549ix767xjq3y58d12i3p2e7` (`categoria_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `canal`
--
ALTER TABLE `canal`
  ADD CONSTRAINT `FKr68vuatjahcrq0hjqp2ie9u33` FOREIGN KEY (`usuario_nickname`) REFERENCES `usuario` (`nickname`);

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `FK4cw21rqxcjbhuh4dxiiee0u1k` FOREIGN KEY (`vid_id`) REFERENCES `video` (`id`),
  ADD CONSTRAINT `FK8ml0o19yklqbplnjt0wqac1vf` FOREIGN KEY (`usuario_nickname`) REFERENCES `usuario` (`nickname`);

--
-- Filtros para la tabla `comentario_comentario`
--
ALTER TABLE `comentario_comentario`
  ADD CONSTRAINT `FK97aahv147tveifpc2e5tdta9` FOREIGN KEY (`Comentario_id`) REFERENCES `comentario` (`id`),
  ADD CONSTRAINT `FKqack4f55a31h9nkw7yavih10k` FOREIGN KEY (`respuestas_id`) REFERENCES `comentario` (`id`);

--
-- Filtros para la tabla `lista`
--
ALTER TABLE `lista`
  ADD CONSTRAINT `FK1vkjxkbwg0ikfaymekf6t2w1i` FOREIGN KEY (`canal_nombre`) REFERENCES `canal` (`nombre`),
  ADD CONSTRAINT `FK7p34u32gug0h5ng6o0hhxj3y9` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);

--
-- Filtros para la tabla `lista_video`
--
ALTER TABLE `lista_video`
  ADD CONSTRAINT `FKfe7l5t5grpslv3yumk6qbafk` FOREIGN KEY (`Lista_id`) REFERENCES `lista` (`id`),
  ADD CONSTRAINT `FKp9kb937apcemdo9lhu3yinuer` FOREIGN KEY (`videos_id`) REFERENCES `video` (`id`);

--
-- Filtros para la tabla `usuario_canal`
--
ALTER TABLE `usuario_canal`
  ADD CONSTRAINT `FK3cs35ibflevggvwyfrmhqq0dg` FOREIGN KEY (`canalesSeguidos_nombre`) REFERENCES `canal` (`nombre`),
  ADD CONSTRAINT `FKlewtfkx26qsk69p7ab0iys2cy` FOREIGN KEY (`Usuario_nickname`) REFERENCES `usuario` (`nickname`);

--
-- Filtros para la tabla `valoracionvideo`
--
ALTER TABLE `valoracionvideo`
  ADD CONSTRAINT `FKe8ytcbq5wpa2o26ew2dro5j8s` FOREIGN KEY (`usuario_nickname`) REFERENCES `usuario` (`nickname`),
  ADD CONSTRAINT `FKsjqovecqbh6biueh04wwof0rh` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`);

--
-- Filtros para la tabla `video`
--
ALTER TABLE `video`
  ADD CONSTRAINT `FK7549ix767xjq3y58d12i3p2e7` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `FKnt538fwr3rvtxfdxmt927dlom` FOREIGN KEY (`canal_nombre`) REFERENCES `canal` (`nombre`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
