##CallCenterJava

La idea es implementar un call center donde hay 3 tipos de empleados: operador, supervisor y director. El proceso de la atención de una llamada telefónica en primera instancia debe ser atendida por un operador, si no hay ninguno libre debe ser atendida por un supervisor, y de no haber tampoco supervisores libres debe ser atendida por un director.

---

## Requerimientos


1. Debe existir una clase Dispatcher encargada de manejar las llamadas, y debe contener el método dispatchCall para que las asigne a los empleados disponibles.
2. El método dispatchCall puede invocarse por varios hilos al mismo tiempo.
3. La clase Dispatcher debe tener la capacidad de poder procesar 10 llamadas al mismo tiempo (de modo concurrente).
4. Cada llamada puede durar un tiempo aleatorio entre 5 y 10 segundos.
5. Debe tener un test unitario donde lleguen 10 llamadas.

---

## Extras/Plus

1. Dar alguna solución sobre qué pasa con una llamada cuando no hay ningún empleado libre.
2. Dar alguna solución sobre qué pasa con una llamada cuando entran más de 10 llamadas concurrentes.
3. Agregar los tests unitarios que se crean convenientes.
4. Agregar documentación de código.

---

## Tener en Cuenta

1. El proyecto debe ser creado con Maven.
2. De ser necesario, anexar un documento con la explicación del cómo y porqué resolvió los puntos extras, o comentarlo en las clases donde se encuentran sus tests unitarios respectivos.

---

##Solución

1. Si ante una llamada entrante no existen empleados libres en alguna de las colas por cargo, se pone en espera el hilo correspondiente a la llamada y vuelve a validar las colas hasta que la llamada se atienda por algún empleado.
2. Al entrar más de 10 llamadas concurrentes, la aplicación asigna las 10 primeras a los empleados y las demás las deja en espera y se atienden en la medida que se liberan los empleados. No se definió un tope o bloqueo de llamadas entrantes para simular un escenario realista de un call center.
3. Se basó la solución en el uso de concurrencia manejada por arreglos e hilos, que representaban a su vez una colas FIFO para las llamadas entrantes y para cada tipo de empleado. 
4. La asignación de empleados por disponibilidad y prioridad de cargo, se manejó validando cada cola de empleados creadas por cada cargo, que adicional a las bondades de los métodos de la libreria de concurrencia de JAVA y el FOR EACH para recorrer las diferentes llamadas entrantes, facilita la ejecución de la aplicación ante los requerimientos indicados y soporta mayor cantidad de llamadas. 
