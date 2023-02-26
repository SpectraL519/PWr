package org.example;


import java.io.*;
import java.net.*;

class SocketClient  {


    public static void main(String[] args){

        try {
            Socket socket = new Socket("localhost", 4444);

            // Inicjalizacja wysylania do serwera
            OutputStream output = socket.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);

            // Inicjalizacja odbieranie z serwera
            InputStream input = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));

            // Wpisanie textu przez konsole
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

            String text;

            do {
                System.out.println("Enter text: ");
                text = bufferRead.readLine();

                // Wysylanie do serwera
                out.println(text);
                // Odbieranie z serwera
                System.out.println(in.readLine());

            } while (!text.equals("bye"));
            socket.close();

        }
        catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost"); System.exit(1);
        }
        catch  (IOException e) {
            System.out.println("No I/O"); System.exit(1);
        }
    }
}
