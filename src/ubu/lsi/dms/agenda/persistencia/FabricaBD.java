package ubu.lsi.dms.agenda.persistencia;

/**
 * Fábrica de fachadas de persistencia de datos en bases de datos.
 * 
 * Patrón de diseño: Fábrica Abstracta. Este participante es la Fábrica
 * Concreta.
 * 
 * Patrón de diseño: Singleton.
 * 
 * @author Álvaro Ruiz
 *
 */
public class FabricaBD implements FabricaPersistencia {

	/**
	 * Instancia única de la clase.
	 */
	private static FabricaBD instance;

	private FabricaBD() {
	}

	/**
	 * Obtiene la instancia de la clase.
	 * 
	 * @return la instancia de la clase
	 */
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