package ubu.lsi.dms.agenda.persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import ubu.lsi.dms.agenda.modelo.Contacto;

public class Ciente {

	public static void main(String[] args) {
		FabricaPersistencia fabrica = obtenerFabrica();
		// Obtenemos la fachada
		FachadaPersistente fachada = fabrica.crearFachadaPersistente();
		// Creamos contactos
		Contacto contacto1 = new Contacto();
		contacto1.setNombre("Álvaro");
		contacto1.setApellidos("Ruiz");
		Contacto contacto2 = new Contacto();
		contacto2.setNombre("Rodrigo");
		contacto2.setApellidos("Ruiz");
		Contacto contacto3 = new Contacto();
		contacto3.setNombre("Pepe");
		contacto3.setApellidos("Sánchez");
		// Insertamos los contactos
		fachada.insertarContacto(contacto1);
		fachada.insertarContacto(contacto2);
		fachada.insertarContacto(contacto3);
		// Obtenemos los contactos apellidados Ruiz
		Collection<Contacto> contactos = fachada.consultarContactos("Ruiz");
		for (Contacto c : contactos)
			System.out.println(c);
	}

	public static FabricaPersistencia obtenerFabrica() {
		Properties prop = new Properties();
		String tipoFab = null;
		try (InputStream input = new FileInputStream(
				"res\\configuration.properties");) {
			prop.load(input);
			// Obtenemos la propiedad tipo
			tipoFab = prop.getProperty("tipo", "bin");
			// Devolvemos la fábrica
			if(tipoFab.equals("bin"))
				return FabricaBin.getInstance();
			else
				return FabricaBD.getInstance();
		} catch (IOException io) {
			io.printStackTrace();
		}
		return null;
	}

}
