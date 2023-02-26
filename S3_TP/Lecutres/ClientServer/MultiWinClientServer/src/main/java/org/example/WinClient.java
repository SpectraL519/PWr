package org.example;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class WinClient extends Frame implements ActionListener {
    Label msg;
    Label output;
    Button send;
    Button receive;
    TextField input;
    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    private static int turn=1;

    private int player;

    WinClient() {
        setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
        msg = new Label("Status");
        input = new TextField(20);
        output = new Label();
        output.setBackground(Color.white);
        send = new Button("Send");
        receive = new Button("Receive");
        send.addActionListener(this);
        receive.addActionListener(this);

        setLayout(new GridLayout(5,1));
        add(msg);
        add(input);
        add(send);
        add(receive);
        add(output);
    }

    public void actionPerformed(ActionEvent event) {

        if(event.getSource() == send) {
            // Wysylanie do serwera
            out.println(input.getText());
            msg.setText("Opposite turn");
            send.setEnabled(false);
            receive.setEnabled(true);
          }
        if(event.getSource() == receive) {
            try {
                // Odbieranie z serwera
                String str = in.readLine();
                output.setText(str);
                msg.setText("My turn");
                send.setEnabled(true);
                receive.setEnabled(false);
                input.setText("");
                input.requestFocus();
            }
            catch (IOException e) {
                System.out.println("Read failed"); System.exit(1);
            }
        }
    }

    /*
    // Połaczenie z socketem
     */
    public void listenSocket(){
        try {
            socket = new Socket("localhost", 4444);
            // Inicjalizacja wysylania do serwera
            out = new PrintWriter(socket.getOutputStream(), true);
            // Inicjalizacja odbierania z serwera
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost"); System.exit(1);
        }
        catch  (IOException e) {
            System.out.println("No I/O"); System.exit(1);
        }
    }

    /*
    Poczatkowe ustawienia klienta. Ustalenie ktory socket jest ktorym kliente
     */
    private void receiveInitFromServer(){
        try {
            player = Integer.parseInt(in.readLine());
            System.out.println(player);
            if (player==turn){
                msg.setText("My Turn");
                receive.setEnabled(false);
            }
            else{
                msg.setText("OppositeTurn");
                send.setEnabled(false);
            }
        }
        catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
    }

    public static void main(String[] args){
        WinClient frame = new WinClient();
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        } );
        frame.pack();
        frame.setVisible(true);
        frame.listenSocket();
        frame.receiveInitFromServer();

    }
}

