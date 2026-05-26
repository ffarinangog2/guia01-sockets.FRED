package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteSincrono {

    public static void main(String[] args) {

        try {

            System.out.println("Conectando al servidor...");

            Socket socket = new Socket("localhost", 5000);

            PrintWriter salida = new PrintWriter(
                    socket.getOutputStream(), true);

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            System.out.println("Enviando mensaje...");

            salida.println("Hola servidor");

            System.out.println("Esperando respuesta del servidor...");

            // AQUÍ OCURRE EL BLOQUEO
            String respuesta = entrada.readLine();

            System.out.println("Respuesta recibida:");
            System.out.println(respuesta);

            System.out.println("Continuando ejecución...");

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}