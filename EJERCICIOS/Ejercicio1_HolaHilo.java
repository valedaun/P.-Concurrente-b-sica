class MiHilo extends Thread {
    @Override
    public void run() {
        System.out.println("¡Buenas!");
    }
}

public class Ejercicio1_HolaHilo {
    public static void main(String[] args) {
        MiHilo hilo = new MiHilo();
        hilo.start();
    }
}
