class HiloContador extends Thread {
    private String nombre;

    public HiloContador(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hilo " + nombre + ": " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class Ejercicio3_MultiplesHilos {
    public static void main(String[] args) {
        new HiloContador("1").start();
        new HiloContador("2").start();
        new HiloContador("3").start();
    }
}
