/**
 *  Clase Employee, representa un empleado del Call Center.
 *  Tiene por atributos un código identificador y un cargo (OPERADOR, SUPERVISOR, DIRECTOR).
 */
package com.AlMundo.CallCenterDomains;

/**
 * @author Jonatan Bermudez Herrera
 *
 */
public class Employee 
{
	private String numId;		//Código identificador del empleado.
	private String charge;		//Cargo del empleado, valores posibles descritos en el encabezado de este archivo.

	/**
	 * Constructor de la clase con parámetros de entrada.
	 */
	public Employee(String numId, String charge) {
		this.numId = numId;
		this.charge = charge;
	}

	/**
	 * Constructor de la clase por defecto.
	 */
	public Employee() {
	}

	/**
	 * Obtiene el identificador del empleado.
	 */
	public String getNumId() {
		return numId;
	}

	/**
	 * Permite ajustar el identificador del empleado.
	 */
	public void setNumId(String numId) {
		this.numId = numId;
	}

	/**
	 * Devuelve el cargo del empleado.
	 */
	public String getCharge() {
		return charge;
	}

	/**
	 * Permite definir el cargo del empleado.
	 */
	public void setCharge(String charge) {
		this.charge = charge;
	}
}