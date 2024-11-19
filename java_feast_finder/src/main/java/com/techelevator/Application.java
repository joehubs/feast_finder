package com.techelevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // try (Scanner sc = new Scanner(System.in)) {
        //     System.out.print("Would you like to reset database? (y/n): ");
        //     String response = sc.nextLine();
        //     if (response.equalsIgnoreCase("y")) {
        //         runShellScript("create.sh");
        //     }
        // }
        SpringApplication.run(Application.class, args);
    }

    public static void runShellScript(String scriptPath) {
        File directory = new File("C:\\Users\\Student\\workspace\\java-blue-finalcapstone-team3\\java\\database");
        System.out.println("Current directory: " + directory.getAbsolutePath());  // Debug line

        ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe", scriptPath);
        processBuilder.directory(directory);

        try {
            Process process = processBuilder.start();

            // Capture the output from the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Print output to console
            }

            // Capture any errors from the script
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);  // Print errors to the error stream
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Script executed successfully!");
            } else {
                System.out.println("Script execution failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
