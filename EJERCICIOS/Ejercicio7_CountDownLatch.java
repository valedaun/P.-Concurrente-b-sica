import java.util.concurrent.CountDownLatch;

class TareaInicial extends Thread {
    private CountDownLatch latch;
    private String nombre;

    public TareaInicial(CountDownLatch latch, String nombre) {
        this.latch = latch;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            System.out.println("Tarea " + nombre + " iniciandose...");
            Thread.sleep((long) (Math.random() * 2000));
            System.out.println("La tarea " + nombre + " ha terminado.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            latch.countDown();
        }
    }
}

public class Ejercicio7_CountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        int numeroDeTareas = 3;
        CountDownLatch latch = new CountDownLatch(numeroDeTareas);

        for (int i = 1; i <= numeroDeTareas; i++) {
            new TareaInicial(latch, "TAREA-" + i).start();
        }

        System.out.println("El hilo principal esta esperando a que las tareas terminen...");
        latch.await();
        System.out.println("Las tareas han concluido. El hilo principal continua.");
    }
}
