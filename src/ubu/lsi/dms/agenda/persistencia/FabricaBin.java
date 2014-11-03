/**
 * 
 */
package ubu.lsi.dms.agenda.persistencia;

/**
 * @author alumno
 *
 */
public class FabricaBin implements FabricaPersistencia {

	private static FabricaBin instance;
	
	private FabricaBin() {
	}

	public static FabricaBin getInstance() {
		if (instance == null)
			instance = new FabricaBin();
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ubu.lsi.dms.agenda.persistencia.FabricaPersistencia#crearFachadaPersistente
	 * ()
	 */
	@Override
	public FachadaPersistente crearFachadaPersistente() {
		return FachadaBin.getInstance();
	}

}
