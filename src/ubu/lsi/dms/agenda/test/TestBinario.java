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
import ubu.lsi.dms.agenda.persistencia.AgendaException;
import ubu.lsi.dms.agenda.persistencia.FabricaBin;
import ubu.lsi.dms.agenda.persistencia.FachadaPersistente;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Test ejecutados por orden de nombre
public class TestBinario {

	private FachadaPersistente fachada;

	/**
	 * Crear fachada binaria.
	 */
	@Before
	public void setUp() {
		fachada = FabricaBin.getInstance().crearFachadaPersistente();
	}

	/**
	 * Inserta contactos en el archivo de persistencia y después los recupera
	 * para ver si coinciden.
	 */
	@Test
	public void test1insertarContactos() {
		Contacto contacto1 = new Contacto();
		contacto1.setIdContacto(0);
		contacto1.setNombre("Álvaro");
		contacto1.setApellidos("Ruiz");
		contacto1.setCiudad("Burgos");
		Contacto contacto2 = new Contacto();
		contacto2.setIdContacto(1);
		contacto2.setNombre("Juan");
		contacto2.setCiudad("Burgos");
		contacto2.setApellidos("Ruiz");
		try {
			fachada.insertarContacto(contacto1);
			fachada.insertarContacto(contacto2);
			Collection<Contacto> contactos = fachada.consultarContactos("Ruiz");
			Assert.assertEquals(contactos.size(), 2);
			Assert.assertTrue(contactos.contains(contacto1));
			Assert.assertTrue(contactos.contains(contacto2));
		} catch (AgendaException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void test2insertarMasContactos() {
		Contacto contacto3 = new Contacto();
		contacto3.setIdContacto(2);
		contacto3.setNombre("Lucas");
		contacto3.setApellidos("Ruiz");
		contacto3.setCiudad("Burgos");
		Contacto contacto4 = new Contacto();
		contacto4.setIdContacto(3);
		contacto4.setNombre("Rodrigo");
		contacto4.setCiudad("Burgos");
		contacto4.setApellidos("Pérez");
		try {
			fachada.insertarContacto(contacto3);
			fachada.insertarContacto(contacto4);
			Collection<Contacto> contactos = fachada.consultarContactos("Ruiz");
			// Ahora hay tres contactos
			Assert.assertEquals(contactos.size(), 3);
			Assert.assertTrue(contactos.contains(contacto3));	
		} catch (AgendaException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void test3actualizarContactos() {
		Contacto contacto3 = new Contacto();
		contacto3.setIdContacto(2);
		contacto3.setNombre("Cristiano");
		contacto3.setApellidos("Ronaldo");
		contacto3.setCiudad("Burgos");
		try {
			fachada.actualizarContacto(contacto3);
			Collection<Contacto> contactosRonaldo = fachada.consultarContactos("Ronaldo");
			Assert.assertEquals(contactosRonaldo.size(), 1);
			Collection<Contacto> contactosRuiz = fachada.consultarContactos("Ruiz");
			Assert.assertEquals(contactosRuiz.size(), 2);
		} catch (AgendaException e) {
			Assert.fail();
		}
	}
	

	/**
	 * Vaciar el archivo de persistencia.
	 */
	@After
	public void vaciarAchivoPesistencia() {
		FileOutputStream writer;
		try {
			writer = new FileOutputStream("res\\contactos.dat");
			writer.write(new String().getBytes());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
