/**
 *  Clase CallCenter, representa el simulador del Call Center.
 *  Contiene el método principal de la aplicación.
 *  Tiene por atributos un log de eventos, una lista de llamadas entrantes, un número de empleados
 *  y una lista de cada uno de los empleados por cargo.
 */

package com.AlMundo.CallCenterSimulator;

/**
 * @author Jonatan Bermudez Herrera
 *
 */

import java.util.ArrayList;						//Permite manejar objetos de tipo arreglos y listas.
import java.util.concurrent.ExecutorService;	//Se requiere para ejecutar procesos concurrentes con hilos.
import java.util.concurrent.Executors;			//Se requiere para ejecutar procesos concurrentes con hilos.

import org.slf4j.Logger;						//Permite registrar eventos en el log.
import org.slf4j.LoggerFactory;					//Permite registrar eventos en el log.

import com.AlMundo.CallCenterDomains.*;			//Permite utilizar objetos de las clases Call y Employee.
import com.AlMundo.CallCenterSimulator.Dispatcher;		//Utiliza la clase Dispatcher para cada llamada entrante.

public class CallCenterApp {

	//Se inicializa la variable que permite escribir en el log todos los eventos.
	private static final Logger log = LoggerFactory.getLogger(Dispatcher.class);
	
	private ArrayList<Call> incomingCalls;		//Listado de llamadas entrantes.
	private ArrayList<Employee> operators;		//Listado de operadores disponibles.
	private ArrayList<Employee> supervisors;	//Listado de supervisores disponibles.
	private ArrayList<Employee> directors;		//Listado de directores disponibles.
	private int numEmployees;					//Número total de empleados del call center.
	
	/**
	 * Método Main del aplicativo.
	 */
	public static void main(String[] args) {
		
		int numOperators = 5;
		int numSupervisors = 2;
		int numDirectors = 3;
		int numberCalls = 15;
		new CallCenterApp().calling(numOperators, numSupervisors, numDirectors, numberCalls);

	}

	/**
	 * Método que inicializa las llamadas y empleados para simular la concurrencia.
	 */
	public void calling(int numOperators, int numSupervisors, int numDirectors, int numberCalls) {

		this.numEmployees = numOperators + numSupervisors + numDirectors;
        this.incomingCalls = this.createCalls(numberCalls);			//Crea las llamadas.
        this.createEmployees(numOperators, numSupervisors, numDirectors);	//Crea los empleados.
        long init = System.currentTimeMillis();			//Obtiene los milisegundos de la hora actual del sistema para cálculos posteriores.
        
        ExecutorService executorService = Executors.newFixedThreadPool(this.numEmployees+2);  //Inicia la ejecución de llamadas concurrentes.
        
        //Se toma cada llamada y se le pasa al Dispatcher para que asigne el empleado. 
        for (Call call: incomingCalls) {
        	Runnable dispatcher = new Dispatcher(call, init, operators, supervisors, directors);
        	executorService.execute(dispatcher);
        }
        executorService.shutdown();					//Se apaga el controlador de hilos para liberar memoria.
        while (!executorService.isTerminated()) {	//Se valida que no hayan hilos aún pendientes de finalizar.
        }
        
        long fin = System.currentTimeMillis();		// Calcula el tiempo total del programa en atender todas las llamadas.
        log.info("Tiempo total de procesamiento: "+(fin-init)/1000+" Segundos");
    }
	
	/**
	 * Método que crea el listado de llamadas entrantes.
	 */
	private ArrayList<Call> createCalls(int numberCalls){
		ArrayList<Call> calls = new ArrayList<Call>();

        for(int i=0; i<numberCalls; i++){
        	int duration = (int) (Math.random()*(10-5)) + 5;		//Les asigna una duración entre 5 y 10 segundos.
            Call call = new Call(Integer.toString(i), duration, "NUEVA");
            System.out.println(duration);
            calls.add(call);
        }
        return calls;
    }

	/**
	 * Método que crea las 3 colas o listas de empleados disponibles (operadores, supervisores, directores).
	 */
	private void createEmployees(int operators, int supervisors, int directors){
		
		this.operators = new ArrayList<Employee>();
		this.supervisors = new ArrayList<Employee>();
		this.directors = new ArrayList<Employee>();

		for(int i=0; i<operators; i++){			//Les asigna el cargo e identificación secuencial incremental desde 0.
			Employee operator = new Employee(""+i, "Operator");
			this.operators.add(operator);
		}
		for(int i=0; i<supervisors; i++){
			Employee supervisor = new Employee(""+i, "Supervisor");
			this.supervisors.add(supervisor);
		}
		for(int i=0; i<directors; i++){
			Employee director = new Employee(""+i, "Director");
			this.directors.add(director);
		}
	}
}
