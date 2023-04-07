package ru.netology;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static ru.netology.ServerConfig.HOST;
import static ru.netology.ServerConfig.PORT;

public class ClientTwo {

    static String input = null;

    public static void main(String[] args) {

        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String serverQuery = reader.readLine();
            if (serverQuery.equals("???")) {
                System.out.println("Введи любое название города:");
                writer.println(ClientOne.scannerImpl());
            } else {
                System.out.println("Введи название города, начинающееся на букву: " + serverQuery.charAt(serverQuery.length() - 1));
                writer.println(ClientOne.scannerImpl());
            }
            System.out.println(reader.readLine());
            writer.flush();
            writer.close();
            reader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String scannerImpl() {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input;
    }
}
