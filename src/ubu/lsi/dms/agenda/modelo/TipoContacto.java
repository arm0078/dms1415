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
	private int idTipoContacto;
	private String TipoContacto;

	public TipoContacto(){
	}
	
	public TipoContacto(int idTipoContacto, String tipoContacto) {
		super();
		this.setIdTipoContacto(idTipoContacto);
		setTipoContacto(tipoContacto);
	}

	@Override
	public String toString() {
		return "TipoContacto [idTipoContacto=" + getIdTipoContacto()
				+ ", TipoContacto=" + getTipoContacto() + "]";
	}

	public int getIdTipoContacto() {
		return idTipoContacto;
	}

	public void setIdTipoContacto(int idTipoContacto) {
		this.idTipoContacto = idTipoContacto;
	}

	public String getTipoContacto() {
		return TipoContacto;
	}

	public void setTipoContacto(String tipoContacto) {
		TipoContacto = tipoContacto;
	}

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

}
