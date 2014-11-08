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

	private int idLlamada;
	private Contacto contacto;
	private String fechaLlamada;
	private String asunto;
	private String notas;

	public Llamada(int idLlamada, Contacto contacto, String fechaLlamada,
			String asunto, String notas) {
		super();
		this.setIdLlamada(idLlamada);
		this.setContacto(contacto);
		this.setFechaLlamada(fechaLlamada);
		this.setAsunto(asunto);
		this.setNotas(notas);
	}

	@Override
	public String toString() {
		return "Llamada [idLlamada=" + getIdLlamada() + ", contacto="
				+ getContacto() + ", fechaLlamada=" + getFechaLlamada()
				+ ", asunto=" + getAsunto() + ", notas=" + getNotas() + "]";
	}

	public int getIdLlamada() {
		return idLlamada;
	}

	public void setIdLlamada(int idLlamada) {
		this.idLlamada = idLlamada;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public String getFechaLlamada() {
		return fechaLlamada;
	}

	public void setFechaLlamada(String fechaLlamada) {
		this.fechaLlamada = fechaLlamada;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

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

}
