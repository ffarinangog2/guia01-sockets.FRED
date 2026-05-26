package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {

        try {
            ServerSocket servidor = new ServerSocket(5000);

            System.out.println("Servidor iniciado...");
            System.out.println("Esperando clientes...");

            while (true) {

                Socket socket = servidor.accept();

                System.out.println("Cliente conectado");

                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                PrintWriter salida = new PrintWriter(
                        socket.getOutputStream(), true);

                String mensaje = entrada.readLine();

                System.out.println("Mensaje recibido: " + mensaje);

                System.out.println("Procesando petición...");

                // Simula procesamiento lento
                Thread.sleep(5000);

                salida.println("Respuesta del servidor");

                System.out.println("Respuesta enviada");

                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}