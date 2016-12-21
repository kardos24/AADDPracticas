package jsf;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.jms.JMSException;
import javax.naming.NamingException;
import jms.EmisorCola;
import jms.ReceptorCola;

public class BeanMensajes {
	private String texto;
	private int tiempoEspera = 10000;
	private Map<String, String> mensajesRecibidos = new LinkedHashMap<String, String>();
	private String[] mensajesRecibidosSeleccionados = null;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void enviarTexto(ActionEvent event) {
		String resultado = "";
		if (texto == null || texto.equals("")) {
			resultado = "No se puede enviar un mensaje vacio.";
			FacesContext.getCurrentInstance().addMessage("ShippingForm:texto",
					new FacesMessage(resultado));
		}
		try {
			EmisorCola.enviar(texto);
		} catch (NamingException e) {
			resultado = "Error durante el envio.";
			e.printStackTrace();
		} catch (JMSException e) {
			resultado = "Error durante el envio.";
			e.printStackTrace();
		}
		resultado = "Envio realizado correctamente.";
		FacesContext.getCurrentInstance().addMessage("ShippingForm:texto",
				new FacesMessage(resultado));
		texto = "";
	}

	public void recibirTexto(ActionEvent event) {
		String resultado = "";
		String recibido = "";
		try {
			recibido = ReceptorCola.recibir(tiempoEspera);
		} catch (NamingException e) {
			resultado = "Error durante el envio.";
			e.printStackTrace();
		} catch (JMSException e) {
			resultado = "Error durante el envio.";
			e.printStackTrace();
		}
		if (recibido != null && !recibido.equals("")) {
			resultado = "Mensaje recibido correctamente.";
			FacesContext.getCurrentInstance().addMessage("ReceptionForm:texto",
					new FacesMessage(resultado));
			texto = recibido;
		} else {
			if (resultado.equals(""))
				resultado = "No se recibio ningún mensaje.";
			texto = "";
			FacesContext.getCurrentInstance().addMessage("ReceptionForm:texto",
					new FacesMessage(resultado));
		}
	}

	public void recibirTodosTexto(ActionEvent event) {
		String recibido = "";
		while (recibido != null) {
			try {
				recibido = ReceptorCola.recibir(tiempoEspera);
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			if (recibido != null) {
				mensajesRecibidos.put(recibido, recibido);
			}
		}
	}

	public String[] getMensajesRecibidosSeleccionados() {
		return mensajesRecibidosSeleccionados;
	}

	public void setMensajesRecibidosSeleccionados(
			String[] mensajesRecibidosSeleccionados) {
		this.mensajesRecibidosSeleccionados = mensajesRecibidosSeleccionados;
	}

	public Map<String, String> getMensajesRecibidos() {
		return mensajesRecibidos;
	}

	public void setMensajesRecibidos(Map<String, String> mensajesRecibidos) {
		this.mensajesRecibidos = mensajesRecibidos;
	}

	public int getTiempoEspera() {
		return tiempoEspera;
	}

	public void setTiempoEspera(int tiempoEspera) {
		this.tiempoEspera = tiempoEspera;
	}
}