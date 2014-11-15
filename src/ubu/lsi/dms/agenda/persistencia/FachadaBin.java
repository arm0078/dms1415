package ubu.lsi.dms.agenda.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ubu.lsi.dms.agenda.modelo.Contacto;
import ubu.lsi.dms.agenda.modelo.Llamada;
import ubu.lsi.dms.agenda.modelo.TipoContacto;

/**
 * Fachada para persistencia de datos en ficheros binarios.
 * 
 * Patrón de diseño: Fábrica Abstracta. Este participante es el Producto
 * Concreto.
 * 
 * Patrón de diseño: Fachada.
 * 
 * Patrón de diseño: Singleton.
 * 
 * @author Álvaro Ruiz
 *
 */
public class FachadaBin implements FachadaPersistente {

	/**
	 * Instancia única de la clase.
	 */
	private static FachadaBin instance;

	/**
	 * Colección de contactos.
	 */
	private Collection<Contacto> contactos;

	/**
	 * Colección de tipos de contacto.
	 */
	private Collection<TipoContacto> tipos;

	/**
	 * Colección de llamadas.
	 */
	private Collection<Llamada> llamadas;

	@SuppressWarnings("unchecked")
	private FachadaBin() {
		// Cargar objetos persistentes
		ObjectInputStream inContactos = null;
		ObjectInputStream inLlamadas = null;
		ObjectInputStream inTipos = null;

		try {
			if (!isEmptyFile("res\\contactos.dat")) {
				inContactos = new ObjectInputStream(new FileInputStream(
						"res\\contactos.dat"));
				contactos = (Collection<Contacto>) inContactos.readObject();
			} else {
				contactos = new ArrayList<>();
			}
			if (!isEmptyFile("res\\llamadas.dat")) {
				inLlamadas = new ObjectInputStream(new FileInputStream(
						"res\\llamadas.dat"));
				llamadas = (Collection<Llamada>) inLlamadas.readObject();
			} else {
				llamadas = new ArrayList<>();
			}
			if (!isEmptyFile("res\\tipos.dat")) {
				inTipos = new ObjectInputStream(new FileInputStream(
						"res\\tipos.dat"));
				tipos = (Collection<TipoContacto>) inTipos.readObject();
			} else {
				tipos = new ArrayList<>();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene la instancia de la clase.
	 * 
	 * @return la instancia de la clase
	 */
	public static FachadaBin getInstance() {
		if (instance == null)
			instance = new FachadaBin();
		return instance;
	}

	/**
	 * Inserta una nueva llamada en el fichero binario
	 *
	 * @param llamada
	 *            llamada a insertar
	 */
	@Override
	public void insertarLlamada(Llamada llamada) {
		llamadas.add(llamada);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("res\\llamadas.dat"))) {
			out.writeObject(llamadas);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza una llamada en el fichero binario dada su identificador
	 *
	 * @param llamada
	 *            llamada a actualizar
	 */
	@Override
	public void actualizarLlamada(Llamada llamada) {
		Iterator<Llamada> iterator = llamadas.iterator();
		while (iterator.hasNext()) {
			Llamada llam = iterator.next();
			if (llam.getIdLlamada() == llamada.getIdLlamada()) {
				iterator.remove();
				break;
			}
		}
		llamadas.add(llamada);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("res\\llamadas.dat"))) {
			out.writeObject(llamadas);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Consulta si un fichero está vacío.
	 * 
	 * @param nameFile
	 *            nombre del fichero
	 * @return true si el fichero está vacío, false en caso contrario.
	 */
	private boolean isEmptyFile(String nameFile) {
		File file = new File(nameFile);
		if (file.length() == 0)
			return true;
		return false;
	}

	/**
	 * Inserta un nuevo contacto en el fichero binario
	 *
	 * @param contacto
	 *            contacto a insertar
	 */
	@Override
	public void insertarContacto(Contacto contacto) {
		contactos.add(contacto);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("res\\contactos.dat"))) {
			out.writeObject(contactos);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Actualiza un contacto en el fichero binario dado su identificador
	 * 
	 * @param contacto
	 *            contacto a actualizar
	 */
	@Override
	public void actualizarContacto(Contacto contacto) {
		Iterator<Contacto> iterator = contactos.iterator();
		while (iterator.hasNext()) {
			Contacto c = iterator.next();
			if (contacto.getIdContacto() == c.getIdContacto()) {
				iterator.remove();
				break;
			}
		}
		contactos.add(contacto);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("res\\contactos.dat"))) {
			out.writeObject(contactos);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserta un nuevo tipo de contacto en el fichero binario
	 *
	 * @param tipoContacto
	 *            tipo de contacto a insertar
	 */
	@Override
	public void insertarTipoContacto(TipoContacto tipoContacto) {
		tipos.add(tipoContacto);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("res\\tipos.dat"))) {
			out.writeObject(tipos);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza un tipo de contacto en el fichero binario dado su
	 * identificador
	 *
	 * @param tipoContacto
	 *            tipo de contacto a actualizar
	 */
	@Override
	public void actualizarTipoContacto(TipoContacto tipoContacto) {
		Iterator<TipoContacto> iterator = tipos.iterator();
		while (iterator.hasNext()) {
			TipoContacto tipo = iterator.next();
			if (tipo.getIdTipoContacto() == tipoContacto.getIdTipoContacto()) {
				iterator.remove();
				break;
			}
		}
		tipos.add(tipoContacto);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("res\\tipos.dat"))) {
			out.writeObject(tipos);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve una colección de tipos de contactos que pertenecen al 
	 * fichero binario
	 *
	 * @return tipos de contactos que están en el sistema de persistencia
	 */
	@Override
	public Collection<TipoContacto> consultarTiposContacto() {
		return tipos;
	}

	/**
	 * Devuelve una colección de llamadas del fichero binario
	 * que tienen asocidadas el contacto especificado
	 *
	 * @param contacto
	 *            contacto para el que se quieren ver sus llamadas
	 * @return colección de llamadas asociadas al contacto
	 */
	@Override
	public Collection<Llamada> consultarLlamadas(Contacto contacto) {
		Collection<Llamada> llamadasContacto = new ArrayList<>();
		for (Llamada llamada : llamadas) {
			if (llamada.getContacto().getIdContacto() == contacto
					.getIdContacto())
				llamadasContacto.add(llamada);
		}
		return llamadasContacto;
	}

	/**
	 * Devuelve una colección de contactos pertenecientes al fichero binario
	 * para los cuales su apellido es el mismo que el proporcionado
	 *
	 * @param apellido
	 *            apellido de los contactos que queremos buscar
	 * @return colección de contactos con el mismo apellido que el proporcionado
	 */
	@Override
	public Collection<Contacto> consultarContactos(String apellidos) {
		Collection<Contacto> contactosApellido = new ArrayList<>();
		for (Contacto contacto : contactos) {
			if (contacto.getApellidos().equals(apellidos))
				contactosApellido.add(contacto);
		}
		return contactosApellido;
	}

}
