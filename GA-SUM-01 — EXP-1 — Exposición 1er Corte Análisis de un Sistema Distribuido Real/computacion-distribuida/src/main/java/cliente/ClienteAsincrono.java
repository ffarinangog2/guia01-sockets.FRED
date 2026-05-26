package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteAsincrono {

    public static void main(String[] args) {

        try {

            System.out.println("Conectando al servidor...");

            Socket socket = new Socket("localhost", 5000);

            PrintWriter salida = new PrintWriter(
                    socket.getOutputStream(), true);

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            System.out.println("Enviando mensaje al servidor...");

            salida.println("Hola desde cliente asincrono");

            // HILO ASÍNCRONO
            Thread recibirRespuesta = new Thread(() -> {

                try {

                    String respuesta = entrada.readLine();

                    System.out.println("\nRespuesta recibida del servidor:");
                    System.out.println(respuesta);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

            recibirRespuesta.start();

            // EL CLIENTE SIGUE TRABAJANDO
            for (int i = 1; i <= 5; i++) {

                System.out.println("Cliente trabajando... " + i);

                Thread.sleep(1000);
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
