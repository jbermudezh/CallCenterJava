/**
 *  Clase Dispatcher, representa el proceso automático del conmutador del Call Center.
 *  Se encarga de asignar una llamada entrante a uno de los empleados de la empresa.
 *  Tiene la lógica para asignar por prioridad del cargo del empleado:
 *  Primero a los operadores, luego a supervisores, luego a directores.
 *  Genera un log de la operación del call center para su seguimiento.
 */

package com.AlMundo.CallCenterSimulator;

/**
 * @author Jonatan Bermudez Herrera
 *
 */

import java.util.ArrayList;				//Se importa para el manejo de arreglos y listas.
import org.slf4j.Logger;				//Se requiere para la implementación del log.
import org.slf4j.LoggerFactory;			//Se requiere para la implementación del log.

import com.AlMundo.CallCenterDomains.*;		//Permite utilizar objetos de las clases Call y Employee.

//Esta clase contiene el manejo de hilos, por esta razón implementa la interface Runnable.
public class Dispatcher implements Runnable {

	//Se inicializa la variable que permite escribir en el log todos los eventos.
	private static final Logger log = LoggerFactory.getLogger(Dispatcher.class);

	private Call incomingCall;				//Objeto que representa la llamada entrante.
	private Employee employee;				//Objeto de tipo Empleado que se asignará finalmente.
	private long initialTime;				//Tiempo en segundos de la llamada, puede tener decimal.
	private ArrayList<Employee> operators;		//Listado de todos los operadores disponibles.
	private ArrayList<Employee> supervisors;	//Listado de todos los supervisores disponibles.
	private ArrayList<Employee> directors;		//Listado de todos los directores disponibles.
	
	/**
	 * Constructor de la clase con parámetros de entrada.
	 */
	public Dispatcher(Call call, long initialTime, ArrayList<Employee> operators, ArrayList<Employee> supervisors, ArrayList<Employee> directors) {
		this.incomingCall = call;				
		this.initialTime = initialTime;
		this.operators = operators;
		this.supervisors = supervisors;
		this.directors = directors;
	}

	/**
	 * Método principal que se ejecuta en la clase. 
	 * Utiliza los demás métodos para definir el empleado asignado a la llamada.
	 */
	public void run() {
		
		if(!operators.isEmpty()) {
    		this.dispatchCall(operators);		//Se asigna un operador disponible.	
    	}else if(!supervisors.isEmpty()) {
    		this.dispatchCall(supervisors);		//Se asigna un supervisor disponible.
    	}else if(!directors.isEmpty()) {
    		this.dispatchCall(directors);		//Se asigna un director disponible.
    	}else {
    		//Se deja la llamada EN ESPERA mientras un empleado se libera.
    		this.incomingCall.setStatus("EN ESPERA");
    		log.info("La llamada " + this.incomingCall.getCallId() + " se encuentra " + incomingCall.getStatus() + " en el tiempo: "
    				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
    		this.waitTime(1);
    		run();
    		
    	}
	}
	
	/**
	 * Permite asignar la duración o de espera de una llamada, dado un tiempo en segundos.
	 */
	private void waitTime(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * Método que contiene la lógica de asignación de una llamada para un tipo de empleado,
	 * después de validada la disponibilidad de alguno de ellos.
	 */
	private void dispatchCall(ArrayList<Employee> employees) {
		int position = employees.size()-1;					//Define que se tomará el último de la cola.
		Employee employee = employees.get(position);
		employees.remove(position);							//Retira al empleado de la cola de disponibles.
		this.incomingCall.setAgent(employee);
		this.incomingCall.setStatus("EN ATENCION");
		log.info("La llamada " + this.incomingCall.getCallId() + " se encuentra " + incomingCall.getStatus() + " por el " + employee.getCharge() + " " + employee.getNumId() + " en el tiempo: "
				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
		this.waitTime(this.incomingCall.getDuration());		//Duración de la llamada en ejecución.
		this.incomingCall.setStatus("FINALIZADA");
		log.info("La llamada " + this.incomingCall.getCallId() + " se encuentra " + incomingCall.getStatus() + " por el " + employee.getCharge() + " " + employee.getNumId() + " en el tiempo: "
				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
		employees.add(employee);							//Se le asigna la disponibilidad nuevamente al empleado.
	}

	/**
	 * Obtiene el objeto que representa la llamada entrante.
	 */
	public Call getIncomingCall() {
		return incomingCall;
	}

	/**
	 * Permite asignar la llamada entrante al conmutador o dispatcher.
	 */
	public void setIncomingCall(Call incomingCall) {
		this.incomingCall = incomingCall;
	}

	/**
	 * Devuelve el tiempo inicial de la llamada, en segundos con decimales.
	 */
	public long getInitialTime() {
		return initialTime;
	}

	/**
	 * Permite definir o ajustar el tiempo inicial de la llamada.
	 */
	public void setInitialTime(long initialTime) {
		this.initialTime = initialTime;
	}

	/**
	 * Permite retornar el log de la clase.
	 */
	public static Logger getLog() {
		return log;
	}

	/**
	 * Retorna el empleado asignado a la llamada.
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * Permite definir el empleado asignado en la clase.
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
