package com.example;

import java.io.*;

public class CsvTableLoader {

    public static TabulatedFunction load(String file, String name) throws IOException {

        TabulatedFunction func = new TabulatedFunction(name);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank() || line.startsWith("#"))
                    continue;

                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);

                func.addPoint(x, y);
            }
        }

        return func;
    }
}