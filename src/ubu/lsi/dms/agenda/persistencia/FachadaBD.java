/**
 * 
 */
package ubu.lsi.dms.agenda.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Properties;

import ubu.lsi.dms.agenda.modelo.Contacto;
import ubu.lsi.dms.agenda.modelo.Llamada;
import ubu.lsi.dms.agenda.modelo.TipoContacto;

/**
 * @author alumno
 *
 */
public class FachadaBD implements FachadaPersistente {
	
	Connection con1 = null;
	
	private static FachadaBD instance;
	
	/**
	 * Servidor donde está funcionado el SGBD.
	 */
	private static final String servidor = "localhost";

	/**
	 * Puerto de conexión.
	 */
	private static final String puerto = "";

	/**
	 * Usuario de la base de datos.
	 */
	private static final String usuario = "SA";

	/**
	 * Contraseña.
	 */
	private static final String contraseña = "";

	/**
	 * Sistema gestor de base de datos.
	 */
	private static final String SGBD = "hsqldb:hsql";

	/**
	 * Base de datos sobre la que trabajamos.
	 */
	private static final String baseDeDatos = "Agenda";


	private FachadaBD() {
		try {
			con1 = getConnectionWithDriverManager();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static FachadaBD getInstance() {
		if (instance == null)
			instance = new FachadaBD();
		return instance;
	}

	@Override
	public void insertarLlamada(Llamada llamada) throws AgendaException {
		Statement stmt = null;
		try {
			stmt = con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			ResultSet uprs = stmt.executeQuery("SELECT * FROM  tiposdecontacto");

			// Posicionamiento en buffer especial para esta operación
			uprs.moveToInsertRow();

			uprs.updateInt("IdLlamada", llamada.getIdLlamada());
			uprs.updateInt("IdContacto", llamada.getContacto().getIdContacto());
			uprs.updateTimestamp("FechaLlamada", Timestamp.valueOf(llamada.getFechaLlamada()));
			uprs.updateString("Asunto", llamada.getAsunto());
			uprs.updateString("Notas", llamada.getNotas());
			
			// Añadir la fila
			uprs.insertRow();
			// Posicionamos el cursor
		    uprs.moveToCurrentRow();

		} catch (SQLException e) {
			throw new AgendaException();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void actualizarLlamada(Llamada llamada) throws AgendaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Llamada> consultarLlamadas() throws AgendaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarContacto(Contacto contacto) throws AgendaException {
		Statement stmt = null;
		try {
			stmt = con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			ResultSet uprs = stmt.executeQuery("SELECT * FROM  tiposdecontacto");

			// Posicionamiento en buffer especial para esta operación
			uprs.moveToInsertRow();

			uprs.updateInt("IdContacto", contacto.getIdContacto());
			uprs.updateString("Nombre", contacto.getNombre());
			uprs.updateString("Apellidos", contacto.getApellidos());
			uprs.updateString("Estimado", contacto.getEstimado());
			uprs.updateString("Direccion", contacto.getDireccion());
			uprs.updateString("Ciudad", contacto.getCiudad());
			uprs.updateString("Prov", contacto.getProv());
			uprs.updateString("CodPostal", contacto.getCodPostal());
			uprs.updateString("Region", contacto.getRegion());
			uprs.updateString("Pais", contacto.getPais());
			uprs.updateString("Cargo", contacto.getCargo());
			uprs.updateString("TelefonoTrabajo", contacto.getTelefonoTrabajo());
			uprs.updateString("ExtensionTrabajo", contacto.getExtensionTrabajo());
			uprs.updateString("TelefonoMovil", contacto.getTelefonoMovil());
			uprs.updateString("NumFax", contacto.getNumFax());
			uprs.updateString("NomCorreoElectronico", contacto.getNomCorreoElectronico());
			uprs.updateInt("IdTipoContacto", contacto.getTipoContacto().getIdTipoContacto());
			uprs.updateString("Notas", contacto.getNotas());
			
			
			// Añadir la fila
			uprs.insertRow();
			// Posicionamos el cursor
		    uprs.moveToCurrentRow();

		} catch (SQLException e) {
			throw new AgendaException();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void actualizarContacto(Contacto contacto) throws AgendaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Contacto> consultarContactos() throws AgendaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarTipoContacto(TipoContacto tipoContacto) throws AgendaException {
		Statement stmt = null;
		try {
			stmt = con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			ResultSet uprs = stmt.executeQuery("SELECT * FROM  tiposdecontacto");

			// Posicionamiento en buffer especial para esta operación
			uprs.moveToInsertRow();

			uprs.updateInt("IdTipoContacto", tipoContacto.getIdTipoContacto());
			uprs.updateString("TipoContacto", tipoContacto.getTipoContacto());
			
			// Añadir la fila
			uprs.insertRow();
			// Posicionamos el cursor
		    uprs.moveToCurrentRow();

		} catch (SQLException e) {
			throw new AgendaException();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void actualizarTipoContacto(TipoContacto tipoContacto) throws AgendaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<TipoContacto> consultarTiposContacto() throws AgendaException {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * Obtiene una conexión a la base de datos utilizando un DriverManager.
	 * 
	 * @return conexión
	 * @throws SQLException
	 *             error en acceso a la base de datos
	 */
	private static Connection getConnectionWithDriverManager()
			throws SQLException {
		Connection conn = null;
		Properties propiedadesDeConexión = new Properties();
		propiedadesDeConexión.put("user", usuario);
		propiedadesDeConexión.put("password", contraseña);

		conn = DriverManager.getConnection("jdbc:" + SGBD + "://" + servidor
				+ "/" + baseDeDatos, propiedadesDeConexión);
		return conn;
	}

}
