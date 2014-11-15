package ubu.lsi.dms.agenda.persistencia;

import java.util.Collection;

import ubu.lsi.dms.agenda.modelo.Contacto;
import ubu.lsi.dms.agenda.modelo.Llamada;
import ubu.lsi.dms.agenda.modelo.TipoContacto;

/**
 * Interfaz que especifica las funciones que debe soportar el sistema de
 * persistencia de la agenda
 * 
 * Patrón de diseño: Fábrica Abstracta. Este participante es el Producto
 * Abstracto.
 * 
 * Patrón de diseño: Fachada.
 * 
 * @author Álvaro Ruiz
 * @author Jorge Alonso
 * @author Javier de la Fuente
 *
 */
public interface FachadaPersistente {

	/**
	 * Inserta una nueva llamada en el sistema de persistencia
	 *
	 * @param llamada
	 *            llamada a insertar
	 */
	void insertarLlamada(Llamada llamada);

	/**
	 * Actualiza una llamada del sistema de persistencia dada su identificador
	 *
	 * @param llamada
	 *            llamada a actualizar
	 */
	void actualizarLlamada(Llamada llamada);

	/**
	 * Devuelve una colección de llamadas del sistema de persistencia
	 * que tienen asocidadas el contacto especificado
	 *
	 * @param contacto
	 *            contacto para el que se quieren ver sus llamadas
	 * @return colección de llamadas asociadas al contacto
	 */
	Collection<Llamada> consultarLlamadas(Contacto contacto);

	/**
	 * Inserta un nuevo contacto en el sistema de persistencia
	 *
	 * @param contacto
	 *            contacto a insertar
	 */
	void insertarContacto(Contacto contacto);

	/**
	 * Actualiza un contacto del sistema de persistencia dado su identificador
	 * 
	 * @param contacto
	 *            contacto a actualizar
	 */
	void actualizarContacto(Contacto contacto);

	/**
	 * Devuelve una colección de contactos pertenecientes al sistema de
	 * persistencia para los cuales su apellido es el mismo que el proporcionado
	 *
	 * @param apellido
	 *            apellido de los contactos que queremos buscar
	 * @return colección de contactos con el mismo apellido que el proporcionado
	 */
	Collection<Contacto> consultarContactos(String apellido);

	/**
	 * Inserta un nuevo tipo de contacto en el sistema de persistencia
	 *
	 * @param tipoContacto
	 *            tipo de contacto a insertar
	 */
	void insertarTipoContacto(TipoContacto tipoContacto);

	/**
	 * Actualiza un tipo de contacto del sistema de persistencia dado su
	 * identificador
	 *
	 * @param tipoContacto
	 *            tipo de contacto a actualizar
	 */
	void actualizarTipoContacto(TipoContacto tipoContacto);

	/**
	 * Devuelve una colección de tipos de contactos que pertenecen al sistema de
	 * persistenca
	 *
	 * @return tipos de contactos que están en el sistema de persistencia
	 */
	Collection<TipoContacto> consultarTiposContacto();
}
