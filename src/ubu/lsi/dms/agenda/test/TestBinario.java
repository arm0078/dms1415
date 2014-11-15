package ubu.lsi.dms.agenda.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ubu.lsi.dms.agenda.modelo.Contacto;
import ubu.lsi.dms.agenda.modelo.Llamada;
import ubu.lsi.dms.agenda.modelo.TipoContacto;
import ubu.lsi.dms.agenda.persistencia.FabricaBin;
import ubu.lsi.dms.agenda.persistencia.FachadaPersistente;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
// Test ejecutados por orden de nombre
public class TestBinario {

	private FachadaPersistente fachada;

	/**
	 * Crear fachada binaria.
	 */
	@Before
	public void obtenerFabrica() {
		fachada = FabricaBin.getInstance().crearFachadaPersistente();
	}

	/**
	 * Inserta objetos en los archivos persistencia y después los recupera.
	 */
	@Test
	public void test1InsertarContactos() {
		// Insertar 75 contactos
		for (int i = 0; i < 75; i++) {
			Contacto con = new Contacto();
			if (i < 25)
				con.setApellidos("Ruiz");
			else if (i < 50)
				con.setApellidos("Pérez");
			else
				con.setApellidos("Aguirre");

			con.setIdContacto(i);
			fachada.insertarContacto(con);
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
	 * Actualiza contactos.
	 */
	@Test
	public void test2ActualizarContactos() {

		// Actualizar 25 contactos
		for (int i = 0; i < 25; i++) {
			Contacto con = new Contacto();
			con.setApellidos("Fernández");

			con.setIdContacto(i);
			fachada.actualizarContacto(con);
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

	/**
	 * Test que usa los tres modelos.
	 */
	@Test
	public void test3Modelos() {
		vaciarArchivoPesistencia();
		// Crear tipos
		TipoContacto tipo1 = new TipoContacto(1, "Jefe");
		tipo1.setIdTipoContacto(1);
		TipoContacto tipo2 = new TipoContacto(1, "Amigo");
		tipo1.setIdTipoContacto(2);
		fachada.insertarTipoContacto(tipo1);
		fachada.insertarTipoContacto(tipo2);
		// Crear Contactos
		Contacto con1 = new Contacto();
		con1.setIdContacto(1);
		con1.setNombre("Álvaro");
		con1.setApellidos("Ruiz");
		con1.setTipoContacto(tipo1);
		Contacto con2 = new Contacto();
		con2.setIdContacto(2);
		con2.setNombre("Pepe");
		con2.setApellidos("Shánchez");
		con2.setTipoContacto(tipo2);
		fachada.insertarContacto(con1);
		fachada.insertarContacto(con2);
		// Crear Llamadas
		Llamada llam1 = new Llamada();
		llam1.setIdLlamada(1);
		llam1.setContacto(con1);
		Llamada llam2 = new Llamada();
		llam2.setIdLlamada(2);
		llam2.setContacto(con1);
		Llamada llam3 = new Llamada();
		llam3.setIdLlamada(3);
		llam3.setContacto(con2);
		fachada.insertarLlamada(llam1);
		fachada.insertarLlamada(llam2);
		fachada.insertarLlamada(llam3);

		Collection<TipoContacto> tipos = fachada.consultarTiposContacto();
		Collection<Contacto> contactos = fachada.consultarContactos("Ruiz");
		Collection<Llamada> llamadasRuiz = fachada.consultarLlamadas(con1);
		Collection<Llamada> llamadasSanchez = fachada.consultarLlamadas(con2);
		Assert.assertEquals(tipos.size(), 2);
		Assert.assertEquals(contactos.size(), 1);
		Assert.assertEquals(llamadasRuiz.size(), 2);
		Assert.assertEquals(llamadasSanchez.size(), 1);
	}

	/**
	 * Vaciar los archivos de persistencia.
	 */
	@After
	public void vaciarArchivoPesistencia() {
		FileOutputStream writer;
		try {
			writer = new FileOutputStream("res\\contactos.dat");
			writer.write(new String().getBytes());
			writer.close();
			writer = new FileOutputStream("res\\tipos.dat");
			writer.write(new String().getBytes());
			writer.close();
			writer = new FileOutputStream("res\\llamadas.dat");
			writer.write(new String().getBytes());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
