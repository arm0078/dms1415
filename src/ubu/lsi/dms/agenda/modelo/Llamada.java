package ubu.lsi.dms.agenda.modelo;

import java.io.Serializable;

/**
 * Clase de entidad con la información de Llamada
 * 
 * @author Carlos López
 *
 */
@SuppressWarnings("serial")
public class Llamada implements Serializable {

	/**
	 * Id de esta llamada
	 */
	private int idLlamada;
	/**
	 * Contacto de esta llamada
	 */
	private Contacto contacto;
	/**
	 * Fecha de esta llamada
	 */
	private String fechaLlamada;
	/**
	 * Asunto de esta llamada
	 */
	private String asunto;
	/**
	 * Notas de esta llamada
	 */
	private String notas;

	/**
	 * Constructor por defecto de la clase llamada
	 */
	public Llamada() {
	}

	/**
	 * Constructor de la clase llamada
	 * 
	 * @param idLlamada
	 *            id de esta llamada
	 * @param contacto
	 *            contacto asociado a esta llamada
	 * @param fechaLlamada
	 *            fecha de esta llamada
	 * @param asunto
	 *            asunto de esta llamada
	 * @param notas
	 *            notas de esta llamada
	 */
	public Llamada(int idLlamada, Contacto contacto, String fechaLlamada,
			String asunto, String notas) {
		super();
		this.setIdLlamada(idLlamada);
		this.setContacto(contacto);
		this.setFechaLlamada(fechaLlamada);
		this.setAsunto(asunto);
		this.setNotas(notas);
	}

	/**
	 * Indica si otra llamada es igual a éste
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

		Llamada ll = (Llamada) obj;
		return idLlamada == ll.idLlamada
				&& (contacto == ll.contacto || (contacto != null && contacto
						.equals(ll.contacto)))
				&& (fechaLlamada == ll.fechaLlamada || (fechaLlamada != null && fechaLlamada
						.equals(ll.fechaLlamada)))
				&& (asunto == ll.fechaLlamada || (fechaLlamada != null && fechaLlamada
						.equals(ll.fechaLlamada)))
				&& (notas == ll.notas || (notas != null && notas
						.equals(ll.notas)));
	}

	/**
	 * Devuelve el asunto de esta llamada
	 * 
	 * @return asunto de esta llamada
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * Devuelve el contacto asociado a esta llamada
	 * 
	 * @return contacto de esta llamada
	 */
	public Contacto getContacto() {
		return contacto;
	}

	/**
	 * Devuelve la fecha de esta llamada
	 * 
	 * @return fecha de esta llamada
	 */
	public String getFechaLlamada() {
		return fechaLlamada;
	}

	/**
	 * Devuelve el id de esta llamada
	 * 
	 * @return id de esta llamada
	 */
	public int getIdLlamada() {
		return idLlamada;
	}

	/**
	 * Devuelve las notas de esta llamada
	 * 
	 * @return notas de esta llamada
	 */
	public String getNotas() {
		return notas;
	}

	/**
	 * Establece el asunto de esta llamada
	 * 
	 * @param asunt
	 *            oasunto de esta llamada
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * Establece el contacto asociado a esta llamada
	 * 
	 * @param contacto
	 *            contacto asociado a esta llamada
	 */
	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	/**
	 * Establece la fecha de esta llamada
	 * 
	 * @param fechaLlamada
	 *            fecha de esta llamada
	 */
	public void setFechaLlamada(String fechaLlamada) {
		this.fechaLlamada = fechaLlamada;
	}

	/**
	 * Establece el id de esta llamada
	 * 
	 * @param idLlamada
	 *            id de esta llamada
	 */
	public void setIdLlamada(int idLlamada) {
		this.idLlamada = idLlamada;
	}

	/**
	 * Establece las notas de esta llamada
	 * 
	 * @param notas
	 *            notas de esta llamada
	 */
	public void setNotas(String notas) {
		this.notas = notas;
	}

	/**
	 * Devuelve una representación de la llamada en una cadena de texto
	 *
	 * @see java.lang.Object#toString(java.lang.Object)
	 */
	@Override
	public String toString() {
		return "Llamada [idLlamada=" + getIdLlamada() + ", contacto="
				+ getContacto() + ", fechaLlamada=" + getFechaLlamada()
				+ ", asunto=" + getAsunto() + ", notas=" + getNotas() + "]";
	}

}
