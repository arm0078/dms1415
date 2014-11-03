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
	void insertarLlamada(Llamada llamada) throws AgendaException;
	void actualizarLlamada(Llamada llamada) throws AgendaException;
	Collection<Llamada> consultarLlamadas() throws AgendaException;
	
	void insertarContacto(Contacto contacto) throws AgendaException;
	void actualizarContacto(Contacto contacto) throws AgendaException;
	Collection<Contacto> consultarContactos() throws AgendaException;
	
	void insertarTipoContacto(TipoContacto tipoContacto) throws AgendaException;
	void actualizarTipoContacto(TipoContacto tipoContacto) throws AgendaException;
	Collection<TipoContacto> consultarTiposContacto() throws AgendaException;
}
