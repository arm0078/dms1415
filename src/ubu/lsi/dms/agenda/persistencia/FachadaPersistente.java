/**
 * 
 */
package ubu.lsi.dms.agenda.persistencia;

import java.util.Collection;

import ubu.lsi.dms.agenda.modelo.Contacto;
import ubu.lsi.dms.agenda.modelo.Llamada;
import ubu.lsi.dms.agenda.modelo.TipoContacto;

/**
 * @author Álvaro Ruiz
 *
 */
public interface FachadaPersistente {
	void insertarLlamada(Llamada llamada);
	void actualizarLlamada(Llamada llamada);
	Collection<Llamada> consultarLlamadas();
	
	void insertarContacto(Contacto contacto);
	void actualizarContacto(Contacto contacto);
	Collection<Contacto> consultarContactos();
	
	void insertarTipoContacto(TipoContacto tipoContacto);
	void actualizarTipoContacto(TipoContacto tipoContacto);
	Collection<TipoContacto> consultarTiposContacto();
}
