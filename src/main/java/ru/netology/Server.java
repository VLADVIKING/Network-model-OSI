package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        String nameCity = null;
        char nameCityLastChar;
        int connectionCount = 0;

        try (ServerSocket server = new ServerSocket(ServerConfig.PORT)) {

            while (true) {
                connectionCount ++;
                try (Socket client = server.accept();
                     PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                ) {
                    System.out.println("Клиент " + connectionCount + " подключен: " + client.getPort());
                    if (connectionCount == 1) {
                        writer.println("???");
                        nameCity = reader.readLine();
                        System.out.println("Название города: " + nameCity + "  OK");
                        writer.println("OK");
                    } else {
                        writer.println(nameCity);
                        nameCityLastChar = nameCity.charAt(nameCity.length() - 1);
                        nameCity = reader.readLine();
                        if (nameCityLastChar == nameCity.charAt(0)) {
                            System.out.println("Следующий город: " + nameCity + "  OK");
                            writer.println("OK");
                        } else {
                            System.out.println("Следующий город: " + nameCity + "  NOT OK");
                            writer.println("NOT OK");
                        }
                    } client.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}