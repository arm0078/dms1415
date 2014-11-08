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
	private int idContacto;
	private String nombre;
	private String nomCorreoElectronico;
	private String notas;
	private String apellidos;
	private String estimado;
	private String direccion;
	private String ciudad;
	private String prov;
	private String codPostal;
	private String region;
	private String pais;
	private String nombreCompania;
	private String cargo;
	private String telefonoTrabajo;
	private String extensionTrabajo;
	private String telefonoMovil;
	private String numFax;

	private TipoContacto tipoContacto;

	public Contacto(int idContacto, String nombre, String apellidos,
			String estimado, String direccion, String ciudad, String prov,
			String codPostal, String region, String pais,
			String nombreCompania, String cargo, String telefonoTrabajo,
			String extensionTrabajo, String telefonoMovil, String numFax,
			String nomCorreoElectronico, String notas, TipoContacto tipoContacto) {
		super();
		this.setIdContacto(idContacto);
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

	@Override
	public String toString() {
		return "Contacto [idContacto=" + getIdContacto() + ", nombre=" + nombre
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

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNomCorreoElectronico() {
		return nomCorreoElectronico;
	}

	public void setNomCorreoElectronico(String nomCorreoElectronico) {
		this.nomCorreoElectronico = nomCorreoElectronico;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEstimado() {
		return estimado;
	}

	public void setEstimado(String estimado) {
		this.estimado = estimado;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getNombreCompania() {
		return nombreCompania;
	}

	public void setNombreCompania(String nombreCompania) {
		this.nombreCompania = nombreCompania;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}

	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}

	public String getExtensionTrabajo() {
		return extensionTrabajo;
	}

	public void setExtensionTrabajo(String extensionTrabajo) {
		this.extensionTrabajo = extensionTrabajo;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getNumFax() {
		return numFax;
	}

	public void setNumFax(String numFax) {
		this.numFax = numFax;
	}

	public TipoContacto getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(TipoContacto tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

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

}
