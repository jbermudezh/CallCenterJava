/**
 *  Clase Call, representa una llamada en el sistema del Call Center.
 *  Tiene por atributos un código identificador, un empleado asignado a atender la llamada, 
 *  una duración, y un estado (que puede ser NUEVA, EN ATENCION, EN ESPERA, FINALIZADA).
 */
package com.AlMundo.CallCenterDomains;

/**
 * @author Jonatan Bermudez Herrera
 *
 */
public class Call 
{
	private String callId;		//Identificador de las llamadas, se deja de tipo String para incluir todo tipo de caracteres.
	private Employee agent;		//Objeto de tipo Empleado, que representa quien atenderá la llamada.
	private int duration;		//Número entero que representa la duración (en segundos) de la llamada.	
	private String status;		//Estado de la llamada, valores posibles descritos en el encabezado de este archivo.

	/**
	 * Constructor de la clase por defecto.
	 */
	public Call() {

	}

	/**
	 * Constructor de la clase con parámetros de entrada
	 */
	public Call(String callId, int duration, String status) {
		this.callId = callId;
		this.duration = duration;
		this.status = status;
	}

	/**
	 * Devuelve el identificador de la llamada.
	 */
	public String getCallId() {
		return callId;
	}

	/**
	 * Permite modificar el valor del identificador de la llamada.
	 */
	public void setCallId(String callId) {
		this.callId = callId;
	}

	/**
	 * Devuelve el objeto Empleado asignado a la llamada.
	 */
	public Employee getAgent() {
		return agent;
	}

	/**
	 * Permite asignar la variable Empleado de la clase.
	 */
	public void setAgent(Employee agent) {
		this.agent = agent;
	}

	/**
	 * Devuelve la duración de la llamada.
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Permite modificar o asignar la duración de la llamada, dado un tiempo entero en segundos.
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Devuelve el estado de la llamada.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Permite modificar el estado de la llamada.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
