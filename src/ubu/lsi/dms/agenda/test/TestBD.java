package ubu.lsi.dms.agenda.test;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ubu.lsi.dms.agenda.modelo.Contacto;
import ubu.lsi.dms.agenda.modelo.Llamada;
import ubu.lsi.dms.agenda.modelo.TipoContacto;
import ubu.lsi.dms.agenda.persistencia.FabricaBD;
import ubu.lsi.dms.agenda.persistencia.FachadaPersistente;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
// Test ejecutados por orden de nombre
public class TestBD {

	private FachadaPersistente fachada;

	/**
	 * Crear fachada binaria.
	 */
	@Before
	public void obtenerFabrica() {
		fachada = FabricaBD.getInstance().crearFachadaPersistente();
	}

	/**
	 * Test para comprobar el funcionamiento del manejo de tipos de contacto
	 */
	@Test
	public void test1TipoContactos() {

		TipoContacto tipoContacto1 = new TipoContacto(1, "alumno");
		fachada.insertarTipoContacto(tipoContacto1);
		Collection<TipoContacto> tiposDeContacto1 = fachada
				.consultarTiposContacto();
		Assert.assertEquals(tiposDeContacto1.size(), 1);

		TipoContacto tipoContacto2 = new TipoContacto(2, "profesor");
		TipoContacto tipoContacto3 = new TipoContacto(3, "director");
		fachada.insertarTipoContacto(tipoContacto2);
		fachada.insertarTipoContacto(tipoContacto3);
		Collection<TipoContacto> tiposDeContacto2 = fachada
				.consultarTiposContacto();
		Assert.assertEquals(tiposDeContacto2.size(), 3);

		// actualizamos el tipo 1 y comprobamos que ya no
		// existe tras actualizarle
		TipoContacto tipoContacto4 = new TipoContacto(1, "alumnomod");
		fachada.actualizarTipoContacto(tipoContacto4);
		Collection<TipoContacto> tiposDeContacto3 = fachada
				.consultarTiposContacto();
		Assert.assertFalse(tiposDeContacto3.contains(tipoContacto1));

	}

	/**
	 * Test para comprobar el funcionamiento del manejo de contactos
	 */
	@Test
	public void test2Contactos() {

		TipoContacto tipoContacto1 = new TipoContacto(4, "obrero");
		fachada.insertarTipoContacto(tipoContacto1);
		// insertamos un contacto y compromabos que si buscamos por
		// apellido, le encuentra
		Contacto contacto = new Contacto(102, "pepe", "calabaza", "sr",
				"calle 1", "madrid", "madrid", "00901", "madrid", "spain",
				"asdfa", "adsfa", "992341", "123", "1325123", "123123", "adsf",
				"notas", tipoContacto1);

		fachada.insertarContacto(contacto);
		Collection<Contacto> contactos1 = fachada
				.consultarContactos("calabaza");
		Assert.assertEquals(contactos1.size(), 1);

		// cambiamos el apellido al contacto, por lo que si buscamos por el
		// apellido
		// modificado, no deberia dar resultados
		contacto.setApellidos("ramos");
		fachada.actualizarContacto(contacto);
		Collection<Contacto> contactos2 = fachada
				.consultarContactos("calabaza");
		Assert.assertEquals(contactos2.size(), 0);

	}

	/**
	 * Test para comprobar el funcionamiento del manejo de llamadas
	 */
	@Test
	public void test3Llamadas() {

		// insertamos una nueva llamaa y comprobamos que si buscamos
		// por su contacto, la encuentra
		Contacto contacto = new Contacto();
		contacto.setIdContacto(103);
		Llamada llamada = new Llamada(1, contacto, "2014-10-18 01:00:00",
				"AsuntoLlamada001", "NotaLlamada001");
		fachada.insertarLlamada(llamada);
		Collection<Llamada> llamadas1 = fachada.consultarLlamadas(contacto);
		Assert.assertEquals(llamadas1.size(), 1);

		// le cambiamos el id del contacto de la llamada
		// y comprobamos que no hay llamadas para el contacto anterior
		Contacto contactoNuevo = new Contacto();
		contactoNuevo.setIdContacto(105);
		llamada.setContacto(contactoNuevo);
		fachada.actualizarLlamada(llamada);
		Collection<Llamada> llmadadas2 = fachada.consultarLlamadas(contacto);
		Assert.assertEquals(llmadadas2.size(), 0);

	}

	/**
	 * Test para probar con los datos cargados en scriptcargadatos
	 */
	@Ignore
	@Test
	public void test4bd() {

		Collection<TipoContacto> tipoDeContactos;
		tipoDeContactos = fachada.consultarTiposContacto();
		Assert.assertEquals(tipoDeContactos.size(), 10);

		Contacto contacto1 = new Contacto();
		contacto1.setApellidos("Apellidos010");
		Collection<Contacto> cc = fachada.consultarContactos("Apellidos010");
		Assert.assertEquals(cc.size(), 1);
		Contacto contacto2 = new Contacto();
		contacto2.setIdContacto(1);
		Collection<Llamada> llamadas = fachada.consultarLlamadas(contacto2);
		Assert.assertEquals(llamadas.size(), 5);

	}

	/**
	 * Test reutilizado del test binario
	 */
	@Test
	public void test5insertarContactos() {

		// Insertar 75 conctactos
		TipoContacto tipoDeContacto = new TipoContacto(5, "alumna");
		fachada.insertarTipoContacto(tipoDeContacto);
		for (int i = 0; i < 75; i++) {
			Contacto contacto = new Contacto();
			if (i < 25)
				contacto.setApellidos("Ruiz");
			else if (i < 50)
				contacto.setApellidos("Pérez");
			else
				contacto.setApellidos("Aguirre");
			contacto.setTipoContacto(tipoDeContacto);

			contacto.setIdContacto(i);
			fachada.insertarContacto(contacto);
		}
		Collection<Contacto> contactosRuiz = fachada.consultarContactos("Ruiz");
		Collection<Contacto> contactosPerez = fachada
				.consultarContactos("Pérez");
		Collection<Contacto> contactosAguirre = fachada
				.consultarContactos("Aguirre");
		// Tiene que haber 25 conctacto de cada tipo de apellido
		Assert.assertEquals(contactosRuiz.size(), 25);
		Assert.assertEquals(contactosPerez.size(), 25);
		Assert.assertEquals(contactosAguirre.size(), 25);

	}

	/**
	 * Test reutilizado del test binario
	 */
	@Test
	public void test6actualizarContactos() {

		// Actualizar 25 contactos
		TipoContacto tipoContacto = new TipoContacto(7, "amigo");
		fachada.insertarTipoContacto(tipoContacto);
		for (int i = 0; i < 25; i++) {
			Contacto contacto = new Contacto();
			contacto.setTipoContacto(tipoContacto);
			contacto.setApellidos("Fernández");

			contacto.setIdContacto(i);
			fachada.actualizarContacto(contacto);
		}
		Collection<Contacto> contactosRuiz = fachada.consultarContactos("Ruiz");
		Collection<Contacto> contactosPerez = fachada
				.consultarContactos("Pérez");
		Collection<Contacto> contactosAguirre = fachada
				.consultarContactos("Aguirre");
		Collection<Contacto> contactosFernandez = fachada
				.consultarContactos("Fernández");
		// Tiene que haber 25 conctacto de cada tipo de apellido
		Assert.assertEquals(contactosFernandez.size(), 25);
		Assert.assertEquals(contactosPerez.size(), 25);
		Assert.assertEquals(contactosAguirre.size(), 25);

		// Ya no hay contactos que se apelliden Ruiz
		Assert.assertEquals(contactosRuiz.size(), 0);
	}

}