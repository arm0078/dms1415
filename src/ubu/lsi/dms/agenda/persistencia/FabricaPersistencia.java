package ubu.lsi.dms.agenda.persistencia;

/**
 * Fábrica para crear fachadas de persitencia de datos.
 * 
 * Patrón de diseño: Fábrica Abstracta. Este participante es la Fábrica
 * Abstracta.
 * 
 * @author Álvaro Ruiz
 *
 */
public interface FabricaPersistencia {
	/**
	 * Crea una fachada para operaciones de persistencia de datos.
	 * 
	 * @return fachada de persistencia
	 */
	public FachadaPersistente crearFachadaPersistente();

}
