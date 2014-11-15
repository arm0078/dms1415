package ubu.lsi.dms.agenda.modelo;

import java.io.Serializable;

/**
 * Clase de entidad con la información de TipodeContacto
 * 
 * @author Carlos López
 *
 */
@SuppressWarnings("serial")
public class TipoContacto implements Serializable {
	/**
	 * Id del tipo de contacto
	 */
	private int idTipoContacto;
	/**
	 * Tipo de contacto
	 */
	private String TipoContacto;

	/**
	 * Constructor por defecto de la clase TipoContacto
	 */
	public TipoContacto() {
	}

	/**
	 * Constructor de la clase TipoContacto
	 * 
	 * @param idTipoContacto
	 *            id del tipo de contacto
	 * @param tipoContacto
	 *            tipo de contacto
	 */
	public TipoContacto(int idTipoContacto, String tipoContacto) {
		super();
		this.setIdTipoContacto(idTipoContacto);
		setTipoContacto(tipoContacto);
	}

	/**
	 * Indica si otro tipo de contacto es igual a éste
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

		TipoContacto tC = (TipoContacto) obj;
		return idTipoContacto == tC.idTipoContacto
				&& (TipoContacto == tC.TipoContacto || (TipoContacto != null && TipoContacto
						.equals(tC.TipoContacto)));
	}

	/**
	 * Devuelve el id del tipo de contacto
	 * 
	 * @return id del tipo de contacto
	 */
	public int getIdTipoContacto() {
		return idTipoContacto;
	}

	/**
	 * Devuelve el tipo de contacto
	 * 
	 * @return tipo de contacto
	 */
	public String getTipoContacto() {
		return TipoContacto;
	}

	/**
	 * Establece el id del tipo de contacto
	 * 
	 * @param idTipoContacto
	 *            id del tipo de contacto
	 */
	public void setIdTipoContacto(int idTipoContacto) {
		this.idTipoContacto = idTipoContacto;
	}

	/**
	 * Establece el tipo de contacto
	 * 
	 * @param tipoContacto
	 *            tipo de contacto
	 */
	public void setTipoContacto(String tipoContacto) {
		TipoContacto = tipoContacto;
	}

	/**
	 * Devuelve una representación del tipo de contacto en una cadena de texto
	 *
	 * @see java.lang.Object#toString(java.lang.Object)
	 */
	@Override
	public String toString() {
		return "TipoContacto [idTipoContacto=" + getIdTipoContacto()
				+ ", TipoContacto=" + getTipoContacto() + "]";
	}

}
