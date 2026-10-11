package library;

import java.io.*;

public class FileManager {
    public void saveText(String path, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            writer.write(text);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String loadText(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return  content.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
//FileManager fileManager= new FileManager();
//fileManager.saveText("test.txt", "Hello world");
//System.out.println(fileManager.loadText("test.txt"));