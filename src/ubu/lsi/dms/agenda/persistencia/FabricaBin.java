package ubu.lsi.dms.agenda.persistencia;

/**
 * Fábrica de fachadas de persistencia de datos en ficheros binarios.
 * 
 * Patrón de diseño: Fábrica Abstracta. Este participante es la Fábrica
 * Concreta.
 * 
 * Patrón de diseño: Singleton.
 * 
 * @author Álvaro Ruiz
 *
 */
public class FabricaBin implements FabricaPersistencia {

	/**
	 * Instancia única de la clase.
	 */
	private static FabricaBin instance;

	private FabricaBin() {
	}

	/**
	 * Obtiene la instancia de la clase.
	 * 
	 * @return la instancia de la clase
	 */
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
