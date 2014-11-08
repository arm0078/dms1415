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
	 * Inserta contactos en el archivo de persistencia y después los recupera.
	 */
	@Test
	public void test1insertarContactos() {
		try {
			// Insertar 75 conctactos
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
			Collection<Contacto> contactosRuiz = fachada
					.consultarContactos("Ruiz");
			Collection<Contacto> contactosPerez = fachada
					.consultarContactos("Pérez");
			Collection<Contacto> contactosAguirre = fachada
					.consultarContactos("Aguirre");
			// Tiene que haber 25 conctacto de cada tipo de apellido
			Assert.assertEquals(contactosRuiz.size(), 25);
			Assert.assertEquals(contactosPerez.size(), 25);
			Assert.assertEquals(contactosAguirre.size(), 25);
		} catch (AgendaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza contactos.
	 */
	@Test
	public void test3actualizarContactos() {
		try {
			// Actualizar 25 contactos
			for (int i = 0; i < 25; i++) {
				Contacto con = new Contacto();
				con.setApellidos("Fernández");

				con.setIdContacto(i);
				fachada.actualizarContacto(con);
			}
			Collection<Contacto> contactosRuiz = fachada
					.consultarContactos("Ruiz");
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
			
		} catch (AgendaException e) {
			e.printStackTrace();
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
