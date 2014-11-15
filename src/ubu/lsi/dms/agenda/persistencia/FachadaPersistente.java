package ubu.lsi.dms.agenda.persistencia;

import java.util.Collection;

import ubu.lsi.dms.agenda.modelo.Contacto;
import ubu.lsi.dms.agenda.modelo.Llamada;
import ubu.lsi.dms.agenda.modelo.TipoContacto;

/**
 * Fachada para persistencia de datos.
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
	 * Hace una llamada persistente.
	 * 
	 * @param llamada
	 *            llamada que se quiere hacer persistente
	 */
	void insertarLlamada(Llamada llamada);

	/**
	 * Actualiza una llamada.
	 * 
	 * @param llamada
	 *            llamada que se quiere actualizar
	 */
	void actualizarLlamada(Llamada llamada);

	/**
	 * Consulta las llamadas filtradas por contacto.
	 * 
	 * @param contacto
	 *            contacto del que se quieren consultar las llamadas
	 * @return colección de llamadas del contacto
	 */
	Collection<Llamada> consultarLlamadas(Contacto contacto);

	/**
	 * Hace un contacto persistente.
	 * 
	 * @param contacto
	 *            contacto que se quiere hacer persistente
	 */
	void insertarContacto(Contacto contacto);

	/**
	 * Actualiza un contacto.
	 * 
	 * @param contacto
	 *            contacto que se quiere actualizar
	 */
	void actualizarContacto(Contacto contacto);

	/**
	 * Consulta los contactos filtrados por apellido.
	 * 
	 * @param apellido
	 *            apellido de los contactos que se quiere obtener
	 * @return colección de contactos filtrados por apellido
	 */
	Collection<Contacto> consultarContactos(String apellido);

	/**
	 * Hace un tipo de contacto persistente.
	 * 
	 * @param tipoContacto
	 *            tipo de contacto que se quiere hacer persistente
	 */
	void insertarTipoContacto(TipoContacto tipoContacto);

	/**
	 * Actualiza un tipo de contacto.
	 * 
	 * @param tipoContacto
	 *            tipo de contactoque se quiere actualizar
	 */
	void actualizarTipoContacto(TipoContacto tipoContacto);

	/**
	 * Consulta los contactos.
	 *
	 * @return colección de tipos de contacto
	 */
	Collection<TipoContacto> consultarTiposContacto();
}
