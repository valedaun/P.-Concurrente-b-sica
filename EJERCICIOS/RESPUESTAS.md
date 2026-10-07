Preguntas para pensar:

Ejercicio 1: Hola Hilo
start() crea un hilo nuevo y ahí ejecuta run(), así que el código corre en forma concurrente. Si llamás a run() directamente, es un método común que se ejecuta en el hilo actual (por ejemplo main), sin concurrencia. Además, start() solo se puede usar una vez por hilo; una segunda llamada lanza IllegalThreadStateException.

Ejercicio 2: Hilos con Runnable
Java no tiene herencia múltiple de clases: si extendés Thread, ya no podés extender otra. Con Runnable (una interfaz) la clase queda libre para heredar de otra. También separa la tarea del mecanismo que la ejecuta, y permite reutilizar el mismo Runnable en varios hilos o pasarlo a un ExecutorService.

Ejercicio 3: Múltiples hilos simples
No, el orden puede cambiar en cada ejecución. El sistema operativo reparte la CPU según la carga y los núcleos disponibles, y aunque todos usen Thread.sleep(100), el momento exacto en que despiertan varía un poco. start() solo asegura que el hilo va a correr, no en qué orden.

Ejercicio 4: Compartiendo datos y synchronized
La condición de carrera aparece porque valor++ no es atómico: son tres pasos (leer, sumar, escribir) que dos hilos pueden entrelazar y así perder un incremento. synchronized toma el monitor (lock) del objeto, que solo puede tener un hilo a la vez. Lo que se bloquea es el acceso simultáneo a los métodos sincronizados de esa misma instancia, no la variable ni otros objetos.

Ejercicio 5: Productor-Consumidor simple
Al salir de wait() no se garantiza que la condición siga cumpliéndose (despertares espurios, o varios hilos notificados con notifyAll() cuando solo uno debería actuar). Con while, cada hilo vuelve a verificar la condición y, si no se cumple, espera de nuevo. Con if, seguiría de largo y podría consumir un dato inválido o repetido.

Ejercicio 6: Executors y Future
ExecutorService reutiliza un pool de hilos, lo que evita el costo de crear y destruir uno por tarea, y permite limitar cuántos corren a la vez. Con Callable y Future las tareas devuelven resultados o excepciones que se recuperan con get(). Además tiene métodos para cerrar el pool ordenadamente (shutdown(), awaitTermination()).

Ejercicio 7: CountDownLatch
Conviene más que join() cuando no tenés referencias a los hilos (por ejemplo, tareas en un ExecutorService), cuando querés esperar eventos o fases y no solo que un hilo termine, o cuando necesitás largar varios hilos a la vez. También permite un único timeout para todo el conjunto. Es de un solo uso; para casos repetibles se usa CyclicBarrier.