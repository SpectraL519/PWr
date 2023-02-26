package org.example;

import java.io.*;
import java.net.Socket;

public class Game implements Runnable{

    private Socket firstPlayer;
    private Socket secondPlayer;

    private final static int FIRST=1;
    private final static int SECOND=2;
    private static int turn=FIRST;

    public Game(Socket firstPlayer, Socket secondPlayer){
        this.firstPlayer = firstPlayer;
        this.secondPlayer= secondPlayer;

    }
    @Override
    public void run() {

        try{
            //Inicjalizacja pobierania od socketa
            InputStream inputF = firstPlayer.getInputStream();
            BufferedReader inF = new BufferedReader(new InputStreamReader(inputF));

            InputStream inputS = secondPlayer.getInputStream();
            BufferedReader inS = new BufferedReader(new InputStreamReader(inputS));

            //Inicjalizacja wysylania do socketa
            OutputStream outputF = firstPlayer.getOutputStream();
            PrintWriter outF = new PrintWriter(outputF, true);

            OutputStream outputS = secondPlayer.getOutputStream();
            PrintWriter outS = new PrintWriter(outputS, true);

            // Ustalenie kto jest pierwszym a kto drugim klientem.
            outF.println("1");
            outS.println("2");

            String line;
            do {
                if (turn==SECOND) {
                    // Odbieranie od socketa
                    line = inS.readLine();
                    // Wypisywanie na serwerze
                    System.out.println(line);
                    // Wysylanie do socketa
                    outF.println("-> (" + line + ")");
                    turn=FIRST;
                }

                if (turn==FIRST) {
                    // Odbieranie od socketa
                    line = inF.readLine();
                    // Wypisywanie na serwerze
                    System.out.println(line);
                    // Wysylanie do socketa
                    outS.println("-> (" + line + ")");
                    turn=SECOND;
                }
            } while (true);

        } catch (IOException ex) {
            System.err.println("ex");
        }
    }


}
