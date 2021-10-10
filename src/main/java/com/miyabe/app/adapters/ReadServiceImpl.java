package com.miyabe.app.adapters;

import com.miyabe.app.ports.ReadService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadServiceImpl implements ReadService {
    private String fileName;

    public ReadServiceImpl() {
    }

    public ReadServiceImpl(String fileName){
        this.fileName = fileName;
    }

    @Override
    public boolean checkInput() {
        File file = new File(this.fileName);
        return file.exists();
    }

    @Override
    public List<String> readInput(){
        List<String> fileLines = new ArrayList<>();
        try {
            if(this.fileName != null) {
                File file = new File(this.fileName);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    fileLines.add(scanner.nextLine());
                }
                scanner.close();
            }
        } catch (FileNotFoundException e){
            System.out.println("It was not possible to read the file");
        }
        return fileLines;
    }
}
