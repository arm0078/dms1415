/**
 * 
 */
package ubu.lsi.dms.agenda.persistencia;

import java.util.Collection;

import ubu.lsi.dms.agenda.modelo.Contacto;
import ubu.lsi.dms.agenda.modelo.Llamada;
import ubu.lsi.dms.agenda.modelo.TipoContacto;

/**
 * @author alumno
 *
 */
public class FachadaBD implements FachadaPersistente {
	
private static FachadaBD instance;
	
	private FachadaBD() {
	}
	
	public static FachadaBD getInstance() {
		if (instance == null)
			instance = new FachadaBD();
		return instance;
	}

	@Override
	public void insertarLlamada(Llamada llamada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarLlamada(Llamada llamada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Llamada> consultarLlamadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarContacto(Contacto contacto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarContacto(Contacto contacto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Contacto> consultarContactos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarTipoContacto(TipoContacto tipoContacto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarTipoContacto(TipoContacto tipoContacto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<TipoContacto> consultarTiposContacto() {
		// TODO Auto-generated method stub
		return null;
	}

}
