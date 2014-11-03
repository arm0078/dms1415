/**
 * 
 */
package ubu.lsi.dms.agenda.persistencia;

/**
 * @author alumno
 *
 */
public class FabricaBD implements FabricaPersistencia {

	private static FabricaBD instance;

	private FabricaBD() {
	}

	public static FabricaBD getInstance() {
		if (instance == null)
			instance = new FabricaBD();
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
		return FachadaBD.getInstance();
	}

}
