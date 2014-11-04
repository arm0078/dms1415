/**
 * 
 */
package ubu.lsi.dms.agenda.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

import ubu.lsi.dms.agenda.modelo.Contacto;
import ubu.lsi.dms.agenda.modelo.Llamada;
import ubu.lsi.dms.agenda.modelo.TipoContacto;

/**
 * @author
 *
 */
public class FachadaBin implements FachadaPersistente {

	private static FachadaBin instance;

	private Collection<Contacto> contactos;
	private Collection<TipoContacto> tipos;
	private Collection<Llamada> llamadas;

	@SuppressWarnings("unchecked")
	private FachadaBin() {
		
		// Cargar objetos persistentes
		try (ObjectInputStream inContactos = new ObjectInputStream(
				new FileInputStream("res\\contactos.dat"));
				ObjectInputStream inLlamadas = new ObjectInputStream(
						new FileInputStream("res\\llamadas.dat"));
				ObjectInputStream inTipos = new ObjectInputStream(
						new FileInputStream("res\\tipos.dat"))) {
			if (!isEmptyFile("res\\contactos.dat"))
				contactos = (Collection<Contacto>) inContactos.readObject();
			else
				contactos = new ArrayList<>();
			if (!isEmptyFile("res\\llamadas.dat"))
				llamadas = (Collection<Llamada>) inLlamadas.readObject();
			else
				llamadas = new ArrayList<>();
			if (!isEmptyFile("res\\tipos.dat")) 
				tipos = (Collection<TipoContacto>) inTipos.readObject();
			else
				tipos = new ArrayList<>();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static FachadaBin getInstance() {
		if (instance == null)
			instance = new FachadaBin();
		return instance;
	}

	@Override
	public void insertarLlamada(Llamada llamada) {
		llamadas.add(llamada);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("res\\llamadas.dat"))) {
			out.writeObject(llamadas);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarLlamada(Llamada llamada) {
		// TODO Auto-generated method stub

	}

	private boolean isEmptyFile(String nameFile) {
		File file = new File(nameFile);
		if (file.length() == 0)
			return true;
		return false;
	}

	@Override
	public void insertarContacto(Contacto contacto) {
		contactos.add(contacto);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("res\\contactos.dat"))) {
			out.writeObject(contactos);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actualizarContacto(Contacto contacto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertarTipoContacto(TipoContacto tipoContacto) {
		tipos.add(tipoContacto);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("res\\tipos.dat"))) {
			out.writeObject(tipos);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarTipoContacto(TipoContacto tipoContacto) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<TipoContacto> consultarTiposContacto() {
		return tipos;
	}

	@Override
	public Collection<Llamada> consultarLlamadas(Contacto contacto) {
		Collection<Llamada> llamadasContacto = new ArrayList<>();
		for (Llamada llamada : llamadas){
			if(llamada.getContacto().getIdContacto() == contacto.getIdContacto())
				llamadasContacto.add(llamada);
		}
		return llamadasContacto;
	}

	@Override
	public Collection<Contacto> consultarContactos(String apellidos) {
		Collection<Contacto> contactosApellido = new ArrayList<>();
		for (Contacto contacto : contactos){
			if(contacto.getApellidos() == apellidos)
				contactosApellido .add(contacto);
		}
		return contactosApellido ;
	}

}
