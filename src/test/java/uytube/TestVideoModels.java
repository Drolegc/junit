package uytube;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import uytube.models.Canal;
import uytube.models.Categoria;
import uytube.models.Video;

public class TestVideoModels {
	Video video = new Video();
	Canal can = new Canal();
	Categoria cat = new Categoria();
	Date fecha = new Date();

	@Test
	public void testGetEs_publico() {
		video.setEs_publico(true);
		assertTrue(video.getEs_publico());
	}

	@Test
	public void testSetEs_publico() {
		video.setEs_publico(false);
		assertFalse(video.getEs_publico());
	}

	@Test
	public void testGetCanal() {
		video.setCanal(can);
		assertNotNull(video.getCanal());
	}

	@Test
	public void testSetCanal() {
		video.setCanal(can);
		assertNotNull(video.getCanal());
	}

	@Test
	public void testGetCategoria() {
		video.setCategoria(cat);
		assertNotNull(video.getCategoria());
	}

	@Test
	public void testSetCategoria() {
		video.setCategoria(cat);
		assertNotNull(video.getCategoria());
	}

	@Test
	public void testVideo() {
		assertNotNull(new Video());
	}

	@Test
	public void testGetNombre() {
		video.setNombre("Nombre video");
		assertNotNull(video.getNombre());
	}

	@Test
	public void testSetNombre() {
		video.setNombre("Nombre video");
		assertEquals(video.getNombre(),"Nombre video");
	}

	@Test
	public void testGetDescripcion() {
		video.setDescripcion("Aqui tengo tu nuevo video");
		assertNotNull(video.getDescripcion());
	}

	@Test
	public void testSetDescripcion() {
		video.setDescripcion("Aqui tengo tu nuevo video");
		assertEquals(video.getDescripcion(),"Aqui tengo tu nuevo video");
	}

	@Test
	public void testGetDuracion() {
		video.setDuracion("10:00:00");
		assertNotNull(video.getDuracion());
	}

	@Test
	public void testSetDuracion() {
		video.setDuracion("10:00:00");
		assertEquals("10:00:00",video.getDuracion());
	}

	@Test
	public void testGetFecha() {
		video.setFecha(fecha);
		assertNotNull(video.getFecha());
	}

	@Test
	public void testSetFecha() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUrl() {
		video.setUrl("http://coso.com");
		assertNotNull(video.getUrl());
	}

	@Test
	public void testSetUrl() {
		video.setUrl("http://coso.com");
		assertEquals("http://coso.com", video.getUrl());
	}

	@Test
	public void testGetId() {
		video.setId(101010);
		assertNotNull(video.getId());
	}

	@Test
	public void testSetId() {
		video.setId(101010);
		assertEquals(101010, video.getId());
	}

}
