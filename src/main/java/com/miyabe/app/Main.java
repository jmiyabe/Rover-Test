package com.miyabe.app;

import com.miyabe.app.adapters.ReadServiceImpl;
import com.miyabe.app.core.RoverServiceImpl;
import com.miyabe.app.ports.ReadService;
import com.miyabe.app.ports.RoverService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        ReadService readCommands = new ReadServiceImpl();
        Scanner scanner = new Scanner(System.in);
        boolean fileExists = false;
        while (!fileExists) {
            System.out.println("Enter with an exist file name");
            String fileName = scanner.nextLine();

            readCommands = new ReadServiceImpl(fileName);
            fileExists = readCommands.checkInput();
            if(!fileExists){
                System.out.println("File does not exist");
            }
        }

        List<String> commands = readCommands.readInput();
        int size = commands.size();
        String[] plataformPosition = commands.get(0).replaceAll("\\s", "").split("");
        if(plataformPosition.length == 2) {
            int plataformX = Integer.parseInt(plataformPosition[0]);
            int plataformY = Integer.parseInt(plataformPosition[1]);
            for (int i = 1; i < size; i += 2) {
                String[] positions = commands.get(i).replaceAll("\\s", "").split("");
                if (positions.length == 3 && plataformPosition.length == 2) {
                    RoverService rover = new RoverServiceImpl(plataformX, plataformY, Integer.parseInt(positions[0]), Integer.parseInt(positions[1]), positions[2]);
                    rover.commandRover(commands.get(i + 1));
                    System.out.println(rover.getPosition());
                }
            }
        }
    }
}
