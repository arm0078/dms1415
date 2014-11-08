/**
 * 
 */
package ubu.lsi.dms.agenda.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
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
		Statement stmt = null;
		try {
			stmt = con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSetActualizable = stmt.executeQuery("SELECT * FROM LLAMADAS");
			
			int idLlamada = resultSetActualizable.getInt("IdLlamada");
			boolean encontrado = false;
			
			while (resultSetActualizable.next() && encontrado == false) {
				if (llamada.getIdLlamada() == idLlamada) {
					encontrado = true;
				}
			}
			
			if(encontrado){
				//Actualizar los campos de la Llamada
				resultSetActualizable.updateInt("IdLlamada", llamada.getIdLlamada());
				resultSetActualizable.updateInt("IdContacto", llamada.getContacto().getIdContacto());
				resultSetActualizable.updateTimestamp("FechaLlamada", Timestamp.valueOf(llamada.getFechaLlamada()));
				resultSetActualizable.updateString("Asunto", llamada.getAsunto());
				resultSetActualizable.updateString("Notas", llamada.getNotas());
				
				// Enviamos la actualización a la base de datos
				 resultSetActualizable.updateRow();
			} else {
				//Insertar la Llamada en la Base de Datos
				insertarLlamada(llamada);
			}

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
	public Collection<Llamada> consultarLlamadas(Contacto contacto) throws AgendaException {
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM LLAMADAS" + " WHERE idContacto = ?";
		Collection<Llamada> listaLlamadas = new ArrayList<Llamada>();
		
		try {
			stmt = con1.prepareStatement(sql); // Obtenemos el objeto PreparedStatement
			
			stmt.setInt(1, contacto.getIdContacto()); // Establecemos el parámetro de la consulta
			ResultSet rs = stmt.executeQuery(); // Ejecutamos la consulta
			
			while (rs.next()) {
				listaLlamadas.add(new Llamada(rs.getInt("IdLlamada"), contacto, rs.getString("FechaLlamada"), rs.getString("Asunto"), rs.getString("Notas")));
			}
			
			rs.close(); // Cierre del result set

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
		
		return listaLlamadas;
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
		Statement stmt = null;
		try {
			stmt = con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSetActualizable = stmt.executeQuery("SELECT * FROM CONTACTOS");
			
			int idContacto = resultSetActualizable.getInt("IdContacto");
			boolean encontrado = false;
			
			while (resultSetActualizable.next() && encontrado == false) {
				if (contacto.getIdContacto() == idContacto) {
					encontrado = true;
				}
			}
			
			if(encontrado){
				//Actualizar los campos de la Llamada
				resultSetActualizable.updateInt("IdContacto", contacto.getIdContacto());
				resultSetActualizable.updateString("Nombre", contacto.getNombre());
				resultSetActualizable.updateString("Apellidos", contacto.getApellidos());
				resultSetActualizable.updateString("Estimado", contacto.getEstimado());
				resultSetActualizable.updateString("Direccion", contacto.getDireccion());
				resultSetActualizable.updateString("Ciudad", contacto.getCiudad());
				resultSetActualizable.updateString("Prov", contacto.getProv());
				resultSetActualizable.updateString("CodPostal", contacto.getCodPostal());
				resultSetActualizable.updateString("Region", contacto.getRegion());
				resultSetActualizable.updateString("Pais", contacto.getPais());
				resultSetActualizable.updateString("Cargo", contacto.getCargo());
				resultSetActualizable.updateString("TelefonoTrabajo", contacto.getTelefonoTrabajo());
				resultSetActualizable.updateString("ExtensionTrabajo", contacto.getExtensionTrabajo());
				resultSetActualizable.updateString("TelefonoMovil", contacto.getTelefonoMovil());
				resultSetActualizable.updateString("NumFax", contacto.getNumFax());
				resultSetActualizable.updateString("NomCorreoElectronico", contacto.getNomCorreoElectronico());
				resultSetActualizable.updateInt("IdTipoContacto", contacto.getTipoContacto().getIdTipoContacto());
				resultSetActualizable.updateString("Notas", contacto.getNotas());
				
				// Enviamos la actualización a la base de datos
				 resultSetActualizable.updateRow();
			} else {
				//Insertar la Llamada en la Base de Datos
				insertarContacto(contacto);
			}

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
	public Collection<Contacto> consultarContactos(String apellido) throws AgendaException {
		PreparedStatement stmtContactos = null;
		String sqlContactos = "SELECT * FROM CONTACTOS" + " WHERE Apellidos = ?";
		Collection<Contacto> listaContactos = new ArrayList<Contacto>();
		
		try {
			stmtContactos = con1.prepareStatement(sqlContactos); // Obtenemos el objeto PreparedStatement
			
			stmtContactos.setString(1, apellido); // Establecemos el parámetro de la consulta
			ResultSet rsContactos = stmtContactos.executeQuery(); // Ejecutamos la consulta
			
			while (rsContactos.next()) {
				//Hallar y reconstruir el TipoContacto asociado
				int idTipoContacto = rsContactos.getInt("IdTipoContacto");
				
				String sqlTipoContacto = "SELECT * FROM TIPOSDECONTACTO" + " WHERE IdTipoContacto = ?";
				PreparedStatement stmtTipoContacto = con1.prepareStatement(sqlTipoContacto);
				stmtTipoContacto.setInt(1, idTipoContacto);
				ResultSet rsTipoContacto = stmtTipoContacto.executeQuery();
				rsTipoContacto.next();
				TipoContacto tipoContacto = new TipoContacto(idTipoContacto, rsTipoContacto.getString("TipoContacto"));
				
				//Añadir el Contacto a la lista
				listaContactos.add(new Contacto(rsContactos.getInt("IdContacto"), rsContactos.getString("Nombre"), rsContactos.getString("Apellidos"),
						rsContactos.getString("Estimado"), rsContactos.getString("Direccion"), rsContactos.getString("Ciudad"), rsContactos.getString("Prov"),
						rsContactos.getString("CodPostal"), rsContactos.getString("Region"), rsContactos.getString("Pais"),
						rsContactos.getString("NombreCompania"), rsContactos.getString("Cargo"), rsContactos.getString("TelefonoTrabajo"),
						rsContactos.getString("ExtensionTrabajo"), rsContactos.getString("TelefonoMovil"), rsContactos.getString("NumFax"),
						rsContactos.getString("NomCorreoElectronico"), rsContactos.getString("Notas"), tipoContacto));
			}
			
			rsContactos.close(); // Cierre del result set

		} catch (SQLException e) {
			throw new AgendaException();
		} finally {
			if (stmtContactos != null) {
				try {
					stmtContactos.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return listaContactos;
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
		Statement stmt = null;
		try {
			stmt = con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSetActualizable = stmt.executeQuery("SELECT * FROM TIPOSDECONTACTO");
			
			int idTipoContacto = resultSetActualizable.getInt("IdTipoContacto");
			boolean encontrado = false;
			
			while (resultSetActualizable.next() && encontrado == false) {
				if (tipoContacto.getIdTipoContacto() == idTipoContacto) {
					encontrado = true;
				}
			}
			
			if(encontrado){
				//Actualizar los campos de la Llamada
				resultSetActualizable.updateInt("IdTipoContacto", tipoContacto.getIdTipoContacto());
				resultSetActualizable.updateString("TipoContacto", tipoContacto.getTipoContacto());
				
				// Enviamos la actualización a la base de datos
				 resultSetActualizable.updateRow();
			} else {
				//Insertar la Llamada en la Base de Datos
				insertarTipoContacto(tipoContacto);
			}

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
	public Collection<TipoContacto> consultarTiposContacto() throws AgendaException {
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM TIPOSDECONTACTO";
		Collection<TipoContacto> listaTiposDeContacto = new ArrayList<TipoContacto>();
		
		try {
			stmt = con1.prepareStatement(sql); // Obtenemos el objeto PreparedStatement
			ResultSet rs = stmt.executeQuery(); // Ejecutamos la consulta
			
			while (rs.next()) {
				listaTiposDeContacto.add(new TipoContacto(rs.getInt("IdTipoContacto"), rs.getString("TipoContacto")));
			}
			
			rs.close(); // Cierre del result set

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
		
		return listaTiposDeContacto;
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
