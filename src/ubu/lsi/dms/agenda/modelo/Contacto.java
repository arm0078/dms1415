package ubu.lsi.dms.agenda.modelo;

import java.io.Serializable;

/**
 * Clase de entidad con la información de Contactos
 * 
 * @author Carlos López
 *
 */

@SuppressWarnings("serial")
public class Contacto implements Serializable {
	/**
	 * Id de este contacto
	 */
	private int idContacto;
	/**
	 * Nombre de este contacto
	 */
	private String nombre;
	/**
	 * Correo electrónico de este contacto
	 */
	private String nomCorreoElectronico;
	/**
	 * Notas de este contacto
	 */
	private String notas;
	/**
	 * Apellidos de este contacto
	 */
	private String apellidos;
	/**
	 * Estimado de este contacto
	 */
	private String estimado;
	/**
	 * Dirección de este contacto
	 */
	private String direccion;
	/**
	 * Ciudad de este contacto
	 */
	private String ciudad;
	/**
	 * Provincia de este contacto
	 */
	private String prov;
	/**
	 * Código postal de este contacto
	 */
	private String codPostal;
	/**
	 * Región de este contacto
	 */
	private String region;
	/**
	 * País de este contacto
	 */
	private String pais;
	/**
	 * Nombre de la compañía de este contacto
	 */
	private String nombreCompania;
	/**
	 * Cargo de este contacto
	 */
	private String cargo;
	/**
	 * Número de teléfono de trabajo de este contacto
	 */
	private String telefonoTrabajo;
	/**
	 * Extensión del teléfono de trabajo de este contacto
	 */
	private String extensionTrabajo;
	/**
	 * Teléfono móvil de este contacto
	 */
	private String telefonoMovil;
	/**
	 * Número de fax de este contacto
	 */
	private String numFax;
	/**
	 * Tipo de este contacto
	 */
	private TipoContacto tipoContacto;

	/**
	 * Constructor por defecto de la clase contacto.
	 */
	public Contacto() {
	}

	/**
	 * Constructor de la clase contacto.
	 * 
	 * @param idContacto
	 *            Id de este contacto
	 * @param nombre
	 *            Nombre de este contacto
	 * @param apellidos
	 *            Apellidos de este contacto
	 * @param estimado
	 *            Estimado de este contacto
	 * @param direccion
	 *            Dirección de este contacto
	 * @param ciudad
	 *            Ciudad de este contacto
	 * @param prov
	 *            Provincia de este contacto
	 * @param codPostal
	 *            Código postal de este contacto
	 * @param region
	 *            Región de este contacto
	 * @param pais
	 *            País de este contacto
	 * @param nombreCompania
	 *            Nombre de la compañía de este contacto
	 * @param cargo
	 *            Cargo de este contacto
	 * @param telefonoTrabajo
	 *            Número de teléfono de trabajo de este contacto
	 * @param extensionTrabajo
	 *            Extensión del teléfono de trabajo de este contacto
	 * @param telefonoMovil
	 *            Teléfono móvil de este contacto
	 * @param numFax
	 *            Número de fax de este contacto
	 * @param nomCorreoElectronico
	 *            Correo electrónico de este contacto
	 * @param notas
	 *            Notas de este contacto
	 * @param tipoContacto
	 *            Tipo de contacto
	 */
	public Contacto(int idContacto, String nombre, String apellidos,
			String estimado, String direccion, String ciudad, String prov,
			String codPostal, String region, String pais,
			String nombreCompania, String cargo, String telefonoTrabajo,
			String extensionTrabajo, String telefonoMovil, String numFax,
			String nomCorreoElectronico, String notas, TipoContacto tipoContacto) {
		super();
		this.idContacto = idContacto;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.estimado = estimado;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.prov = prov;
		this.codPostal = codPostal;
		this.region = region;
		this.pais = pais;
		this.nombreCompania = nombreCompania;
		this.cargo = cargo;
		this.telefonoTrabajo = telefonoTrabajo;
		this.extensionTrabajo = extensionTrabajo;
		this.telefonoMovil = telefonoMovil;
		this.numFax = numFax;
		this.nomCorreoElectronico = nomCorreoElectronico;
		this.notas = notas;
		this.tipoContacto = tipoContacto;
	}

	/**
	 * Indica si otro contacto es igual a éste
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Contacto c = (Contacto) obj;
		return idContacto == c.idContacto
				&& (nombre == c.nombre || (nombre != null && nombre
						.equals(c.nombre)))
				&& (nomCorreoElectronico == c.nomCorreoElectronico || (nomCorreoElectronico != null && nomCorreoElectronico
						.equals(c.nomCorreoElectronico)))
				&& (notas == c.notas || (notas != null && notas.equals(c.notas)))
				&& (apellidos == c.apellidos || (apellidos != null && apellidos
						.equals(c.apellidos)))
				&& (estimado == c.estimado || (estimado != null && estimado
						.equals(c.estimado)))
				&& (direccion == c.direccion || (direccion != null && direccion
						.equals(c.direccion)))
				&& (ciudad == c.ciudad || (ciudad != null && ciudad
						.equals(c.ciudad)))
				&& (prov == c.prov || (prov != null && prov.equals(c.prov)))
				&& (codPostal == c.codPostal || (codPostal != null && codPostal
						.equals(c.codPostal)))
				&& (region == c.region || (region != null && region
						.equals(c.region)))
				&& (pais == c.pais || (pais != null && pais.equals(c.pais)))
				&& (nombreCompania == c.nombreCompania || (nombreCompania != null && nombreCompania
						.equals(c.nombreCompania)))
				&& (cargo == c.cargo || (cargo != null && cargo.equals(c.cargo)))
				&& (telefonoTrabajo == c.telefonoTrabajo || (telefonoTrabajo != null && telefonoTrabajo
						.equals(c.telefonoTrabajo)))
				&& (extensionTrabajo == c.extensionTrabajo || (extensionTrabajo != null && extensionTrabajo
						.equals(c.extensionTrabajo)))
				&& (telefonoMovil == c.telefonoMovil || (telefonoMovil != null && telefonoMovil
						.equals(c.telefonoMovil)))
				&& (numFax == c.numFax || (numFax != null && numFax
						.equals(c.numFax)))
				&& (tipoContacto == c.tipoContacto || (tipoContacto != null && tipoContacto
						.equals(c.tipoContacto)));
	}

	/**
	 * Devuelve los apellidos de este contacto
	 * 
	 * @return apellidos de este contacto
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Devuelve el cargo de este contacto
	 * 
	 * @return cargo de este contacto
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * Devuelve la ciudad de este contacto
	 *
	 * @return ciudad de este contacto
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Devuelve el código postal de este contacto
	 * 
	 * @return código postal de este contacto
	 */
	public String getCodPostal() {
		return codPostal;
	}

	/**
	 * Devuelve la dirección de este contacto
	 * 
	 * @return dirección de este contacto
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Devuelve el estimado de este contacto
	 * 
	 * @return estimado de este contacto
	 */
	public String getEstimado() {
		return estimado;
	}

	/**
	 * Devuelve la extensión del teléfono de trabajo de este contacto
	 * 
	 * @return extensión del teléfono de trabajo de este contacto
	 */
	public String getExtensionTrabajo() {
		return extensionTrabajo;
	}

	/**
	 * Devuelve el id de este contacto
	 * 
	 * @return id de este contacto
	 */
	public int getIdContacto() {
		return idContacto;
	}

	/**
	 * Devuelve el nombre de este contacto
	 * 
	 * @return nombre de este contacto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Devuelve el nombre de la compañía de este contacto
	 * 
	 * @return nombre de la compañía de este contacto
	 */
	public String getNombreCompania() {
		return nombreCompania;
	}

	/**
	 * Devuelve el correo electrónico de este contacto
	 * 
	 * @return correo electrónico de este contacto
	 */
	public String getNomCorreoElectronico() {
		return nomCorreoElectronico;
	}

	/**
	 * Devuelve las notas de este contacto
	 * 
	 * @return notas de este contacto
	 */
	public String getNotas() {
		return notas;
	}

	/**
	 * Devuelve el número de fax de este contacto
	 * 
	 * @return número de fax de este contacto
	 */
	public String getNumFax() {
		return numFax;
	}

	/**
	 * Devuelve el país de este contacto
	 * 
	 * @return país de este contacto
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Devuelve la provincia de este contacto
	 * 
	 * @return provincia de este contacto
	 */
	public String getProv() {
		return prov;
	}

	/**
	 * Devuelve la región de este contacto
	 * 
	 * @return región de este contacto
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Devuelve el teléfono móvil de este contacto
	 * 
	 * @return teléfono móvil de este contacto
	 */
	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	/**
	 * Devuelve el téléfono de trabajo de este contacto
	 * 
	 * @return téléfono de trabajo de este contacto
	 */
	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}

	/**
	 * Devuelve el tipo de contacto
	 * 
	 * @return tipo de contacto
	 */
	public TipoContacto getTipoContacto() {
		return tipoContacto;
	}

	/**
	 * Establece los apellidos de este contacto
	 *
	 * @param apellidos
	 *            apellidosd el contacto
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Establece el cargo de este contacto
	 *
	 * @param cargo
	 *            cargo de este contacto
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * Establece la ciudad de este contacto
	 *
	 * @param ciudad
	 *            ciudad de este contacto
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Establece el código postal de este contacto
	 *
	 * @param codPostal
	 *            código postal de este contacto
	 */
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	/**
	 * Establece la dirección de este contacto
	 *
	 * @param direccion
	 *            dirección de este contacto
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Establece el estimado de este contacto
	 *
	 * @param estimado
	 *            estimado de este contacto
	 */
	public void setEstimado(String estimado) {
		this.estimado = estimado;
	}

	/**
	 * Establece la extensión del teléfono de trabajo de este contacto
	 *
	 * @param extensionTrabajo
	 *            extensión del teléfono de trabajo de este contacto
	 */
	public void setExtensionTrabajo(String extensionTrabajo) {
		this.extensionTrabajo = extensionTrabajo;
	}

	/**
	 * Establece el id de este contacto
	 *
	 * @param idContacto
	 *            id de este contacto
	 */
	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	/**
	 * Establece el nombre de este contacto
	 *
	 * @param nombre
	 *            nombre de este contacto
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Establece el nombre de la compañía de este contacto
	 *
	 * @param nombreCompania
	 *            nombre de la compañía de este contacto
	 */
	public void setNombreCompania(String nombreCompania) {
		this.nombreCompania = nombreCompania;
	}

	/**
	 * Establece el correo electrónico de este contacto
	 *
	 * @param nomCorreoElectronico
	 *            correo electrónico de este contacto
	 */
	public void setNomCorreoElectronico(String nomCorreoElectronico) {
		this.nomCorreoElectronico = nomCorreoElectronico;
	}

	/**
	 * Establece las notas de este contacto
	 *
	 * @param notas
	 *            notas de este contacto
	 */
	public void setNotas(String notas) {
		this.notas = notas;
	}

	/**
	 * Establece el número de fax de este contacto
	 *
	 * @param numFax
	 *            número de fax de este contacto
	 */
	public void setNumFax(String numFax) {
		this.numFax = numFax;
	}

	/**
	 * Establece el país de este contacto
	 *
	 * @param pais
	 *            país de este contacto
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Establece la provincia de este contacto
	 *
	 * @param prov
	 *            provincia de este contacto
	 */
	public void setProv(String prov) {
		this.prov = prov;
	}

	/**
	 * Establece la región de este contacto
	 *
	 * @param region
	 *            región de este contacto
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Establece el teléfono móvil de este contacto
	 *
	 * @param telefonoMovil
	 *            teléfono móvil de este contacto
	 */
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	/**
	 * Establece el teléfono de trabajo de este contacto
	 *
	 * @param telefonoTrabajo
	 *            teléfono de trabajo de este contacto
	 */
	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}

	/**
	 * Establece el tipo de este contacto
	 *
	 * @param tipoContacto
	 *            tipo de este contacto
	 */
	public void setTipoContacto(TipoContacto tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	/**
	 * Devuelve una representación del contacto en una cadena de texto
	 *
	 * @see java.lang.Object#toString(java.lang.Object)
	 */
	@Override
	public String toString() {
		return "Contacto [idContacto=" + idContacto + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", estimado=" + estimado
				+ ", direccion=" + direccion + ", ciudad=" + ciudad + ", prov="
				+ prov + ", codPostal=" + codPostal + ", region=" + region
				+ ", pais=" + pais + ", nombreCompania=" + nombreCompania
				+ ", cargo=" + cargo + ", telefonoTrabajo=" + telefonoTrabajo
				+ ", extensionTrabajo=" + extensionTrabajo + ", telefonoMovil="
				+ telefonoMovil + ", numFax=" + numFax
				+ ", nomCorreoElectronico=" + nomCorreoElectronico + ", notas="
				+ notas + ", tipoContacto=" + tipoContacto + "]";
	}

}
