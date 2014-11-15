package ubu.lsi.dms.agenda.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import ubu.lsi.dms.agenda.modelo.Contacto;
import ubu.lsi.dms.agenda.modelo.Llamada;
import ubu.lsi.dms.agenda.modelo.TipoContacto;

/**
 * Fachada para persistencia de datos en base de datos.
 * 
 * Patrón de diseño: Fábrica Abstracta. Este participante es el Producto
 * Concreto.
 * 
 * Patrón de diseño: Fachada.
 * 
 * Patrón de diseño: Singleton.
 * 
 * @author Jorge Alonso
 * @author Javier de la Fuente
 *
 */
public class FachadaBD implements FachadaPersistente {

	/**
	 * Conexión a la base de datos.
	 */
	Connection con1 = null;

	/**
	 * Instancia única de la clase.
	 */
	private static FachadaBD instance;

	/**
	 * Servidor donde está funcionado el SGBD.
	 */
	private static final String servidor = "localhost";

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

	/**
	 * Obtiene la instancia de la clase.
	 * 
	 * @return la instancia de la clase
	 */
	public static FachadaBD getInstance() {
		if (instance == null)
			instance = new FachadaBD();
		return instance;
	}

	/**
	 * Inserta una nueva llamada en la base de datos
	 *
	 * @param llamada
	 *            llamada a insertar
	 */
	@Override
	public void insertarLlamada(Llamada llamada) {
		Statement stmt = null;
		try {
			stmt = con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			ResultSet uprs = stmt.executeQuery("SELECT * FROM  LLAMADAS");

			// Posicionamiento en buffer especial para esta operación
			uprs.moveToInsertRow();

			uprs.updateInt("IdLlamada", llamada.getIdLlamada());
			uprs.updateInt("IdContacto", llamada.getContacto().getIdContacto());
			uprs.updateTimestamp("FechaLlamada",
					Timestamp.valueOf(llamada.getFechaLlamada()));
			uprs.updateString("Asunto", llamada.getAsunto());
			uprs.updateString("Notas", llamada.getNotas());

			// Añadir la fila
			uprs.insertRow();
			// Posicionamos el cursor
			uprs.moveToCurrentRow();
			uprs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			;
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

	/**
	 * Actualiza una llamada de la base de datos dada su identificador
	 *
	 * @param llamada
	 *            llamada a actualizar
	 */
	@Override
	public void actualizarLlamada(Llamada llamada) {

		try {
			PreparedStatement stmt = null;
			stmt = con1
					.prepareStatement("UPDATE Llamadas "
							+ "SET IdLLamada=?, IdContacto=?, FechaLlamada=?, Asunto=?, Notas=? WHERE "
							+ "IdLlamada=?");
			stmt.setInt(1, llamada.getIdLlamada());
			stmt.setInt(2, llamada.getContacto().getIdContacto());
			stmt.setTimestamp(3, Timestamp.valueOf(llamada.getFechaLlamada()));
			stmt.setString(4, llamada.getAsunto());
			stmt.setString(5, llamada.getNotas());
			stmt.setInt(6, llamada.getIdLlamada());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	/**
	 * Devuelve una colección de llamadas de la base de datos
	 * que tienen asocidadas el contacto especificado
	 *
	 * @param contacto
	 *            contacto para el que se quieren ver sus llamadas
	 * @return colección de llamadas asociadas al contacto
	 */
	@Override
	public Collection<Llamada> consultarLlamadas(Contacto contacto) {
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM LLAMADAS" + " WHERE idContacto = ?";
		Collection<Llamada> listaLlamadas = new ArrayList<Llamada>();

		try {
			stmt = con1.prepareStatement(sql); // Obtenemos el objeto
												// PreparedStatement

			stmt.setInt(1, contacto.getIdContacto()); // Establecemos el
														// parámetro de la
														// consulta
			ResultSet rs = stmt.executeQuery(); // Ejecutamos la consulta

			while (rs.next()) {
				listaLlamadas.add(new Llamada(rs.getInt("IdLlamada"), contacto,
						rs.getString("FechaLlamada"), rs.getString("Asunto"),
						rs.getString("Notas")));
			}

			rs.close(); // Cierre del result set

		} catch (SQLException e) {
			e.printStackTrace();
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
	
	/**
	 * Inserta un nuevo contacto en la base de datos
	 *
	 * @param contacto
	 *            contacto a insertar
	 */
	@Override
	public void insertarContacto(Contacto contacto) {
		try {
			PreparedStatement stmt = null;
			stmt = con1
					.prepareStatement("INSERT INTO CONTACTOS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, contacto.getIdContacto());
			stmt.setString(2, contacto.getNombre());
			stmt.setString(3, contacto.getApellidos());
			stmt.setString(4, contacto.getEstimado());
			stmt.setString(5, contacto.getDireccion());
			stmt.setString(6, contacto.getCiudad());
			stmt.setString(7, contacto.getProv());
			stmt.setString(8, contacto.getCodPostal());
			stmt.setString(9, contacto.getRegion());
			stmt.setString(10, contacto.getPais());
			stmt.setString(11, contacto.getNombreCompania());
			stmt.setString(12, contacto.getCargo());
			stmt.setString(13, contacto.getTelefonoTrabajo());
			stmt.setString(14, contacto.getExtensionTrabajo());
			stmt.setString(15, contacto.getTelefonoMovil());
			stmt.setString(16, contacto.getNumFax());
			stmt.setString(17, contacto.getNomCorreoElectronico());
			stmt.setInt(18, contacto.getTipoContacto().getIdTipoContacto());
			stmt.setString(19, contacto.getNotas());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Actualiza un contacto de la base de datos dado su identificador
	 * 
	 * @param contacto
	 *            contacto a actualizar
	 */
	@Override
	public void actualizarContacto(Contacto contacto) {
		try {
			PreparedStatement stmt = null;
			stmt = con1
					.prepareStatement("UPDATE Contactos "
							+ "SET IdContacto=?, Nombre=?, Apellidos=?, Estimado=?, Direccion=?, "
							+ "Ciudad=?, Prov=?, CodPostal=?, Region=?, Pais=?, NombreCompania=?, Cargo=?, "
							+ "TelefonoTrabajo=?, ExtensionTrabajo=?, TelefonoMovil=?, NumFax=?, "
							+ "NomCorreoElectronico=?, IdTipoContacto=?, Notas=? WHERE IdContacto=?");
			stmt.setInt(1, contacto.getIdContacto());
			stmt.setString(2, contacto.getNombre());
			stmt.setString(3, contacto.getApellidos());
			stmt.setString(4, contacto.getEstimado());
			stmt.setString(5, contacto.getDireccion());
			stmt.setString(6, contacto.getCiudad());
			stmt.setString(7, contacto.getProv());
			stmt.setString(8, contacto.getCodPostal());
			stmt.setString(9, contacto.getRegion());
			stmt.setString(10, contacto.getPais());
			stmt.setString(11, contacto.getNombreCompania());
			stmt.setString(12, contacto.getCargo());
			stmt.setString(13, contacto.getTelefonoTrabajo());
			stmt.setString(14, contacto.getExtensionTrabajo());
			stmt.setString(15, contacto.getTelefonoMovil());
			stmt.setString(16, contacto.getNumFax());
			stmt.setString(17, contacto.getNomCorreoElectronico());
			stmt.setInt(18, contacto.getTipoContacto().getIdTipoContacto());
			stmt.setString(19, contacto.getNotas());
			stmt.setInt(20, contacto.getIdContacto());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve una colección de contactos pertenecientes a la base de 
	 * datos para los cuales su apellido es el mismo que el proporcionado
	 *
	 * @param apellido
	 *            apellido de los contactos que queremos buscar
	 * @return colección de contactos con el mismo apellido que el proporcionado
	 */
	@Override
	public Collection<Contacto> consultarContactos(String apellido) {
		PreparedStatement stmtContactos = null;
		String sqlContactos = "SELECT * FROM CONTACTOS WHERE Apellidos = ?";
		Collection<Contacto> listaContactos = new ArrayList<Contacto>();

		try {
			stmtContactos = con1.prepareStatement(sqlContactos); // Obtenemos el
																	// objeto
																	// PreparedStatement
			stmtContactos.setString(1, apellido); // Establecemos el parámetro
													// de la consulta
			ResultSet rsContactos = stmtContactos.executeQuery(); // Ejecutamos
																	// la
																	// consulta

			while (rsContactos.next()) {
				// Hallar y reconstruir el TipoContacto asociado
				int idTipoContacto = rsContactos.getInt("IdTipoContacto");

				String sqlTipoContacto = "SELECT * FROM TIPOSDECONTACTO"
						+ " WHERE IdTipoContacto = ?";
				PreparedStatement stmtTipoContacto = con1
						.prepareStatement(sqlTipoContacto);
				stmtTipoContacto.setInt(1, idTipoContacto);
				ResultSet rsTipoContacto = stmtTipoContacto.executeQuery();
				rsTipoContacto.next();
				TipoContacto tipoContacto = new TipoContacto(idTipoContacto,
						rsTipoContacto.getString("TipoContacto"));

				// Añadir el Contacto a la lista
				listaContactos.add(new Contacto(rsContactos
						.getInt("IdContacto"), rsContactos.getString("Nombre"),
						rsContactos.getString("Apellidos"), rsContactos
								.getString("Estimado"), rsContactos
								.getString("Direccion"), rsContactos
								.getString("Ciudad"), rsContactos
								.getString("Prov"), rsContactos
								.getString("CodPostal"), rsContactos
								.getString("Region"), rsContactos
								.getString("Pais"), rsContactos
								.getString("NombreCompania"), rsContactos
								.getString("Cargo"), rsContactos
								.getString("TelefonoTrabajo"), rsContactos
								.getString("ExtensionTrabajo"), rsContactos
								.getString("TelefonoMovil"), rsContactos
								.getString("NumFax"), rsContactos
								.getString("NomCorreoElectronico"), rsContactos
								.getString("Notas"), tipoContacto));
			}

			rsContactos.close(); // Cierre del result set

		} catch (SQLException e) {
			e.printStackTrace();
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

	/**
	 * Inserta un nuevo tipo de contacto en la base de datos
	 * dado su identificador
	 * 
	 * @param tipoContacto
	 *            tipo de contacto a insertar
	 */
	@Override
	public void insertarTipoContacto(TipoContacto tipoContacto) {
		Statement stmt = null;
		try {
			stmt = con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			ResultSet uprs = stmt
					.executeQuery("SELECT * FROM  TIPOSDECONTACTO");

			// Posicionamiento en buffer especial para esta operación
			uprs.moveToInsertRow();

			uprs.updateInt("IdTipoContacto", tipoContacto.getIdTipoContacto());
			uprs.updateString("TipoContacto", tipoContacto.getTipoContacto());

			// Añadir la fila
			uprs.insertRow();
			// Posicionamos el cursor
			uprs.moveToCurrentRow();

		} catch (SQLException e) {
			e.printStackTrace();
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

	/**
	 * Actualiza un tipo de contacto de la base de dataos
	 * identificador
	 *
	 * @param tipoContacto
	 *            tipo de contacto a actualizar
	 */
	@Override
	public void actualizarTipoContacto(TipoContacto tipoContacto) {
		try {
			PreparedStatement stmt = null;
			stmt = con1
					.prepareStatement("UPDATE Tiposdecontacto "
							+ "SET IdTipoContacto=?, TipoContacto=? WHERE IdTipoContacto=?");
			stmt.setInt(1, tipoContacto.getIdTipoContacto());
			stmt.setString(2, tipoContacto.getTipoContacto());
			stmt.setInt(3, tipoContacto.getIdTipoContacto());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve una colección de tipos de contactos que pertenecen a
	 * la base de datos
	 *
	 * @return tipos de contactos que están en el sistema de persistencia
	 */
	@Override
	public Collection<TipoContacto> consultarTiposContacto() {
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM TIPOSDECONTACTO";
		Collection<TipoContacto> listaTiposDeContacto = new ArrayList<TipoContacto>();

		try {
			stmt = con1.prepareStatement(sql); // Obtenemos el objeto
												// PreparedStatement
			ResultSet rs = stmt.executeQuery(); // Ejecutamos la consulta

			while (rs.next()) {
				listaTiposDeContacto
						.add(new TipoContacto(rs.getInt("IdTipoContacto"), rs
								.getString("TipoContacto")));
			}

			rs.close(); // Cierre del result set

		} catch (SQLException e) {
			e.printStackTrace();
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