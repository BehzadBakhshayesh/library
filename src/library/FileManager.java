package library;

import java.io.*;

public class FileManager {

    private File file;

    public FileManager(String pathname) {
        this.file = new File(pathname);
    }

    public void save(String text, boolean append) {
        try (FileWriter writer = new FileWriter(file, append)) {
            writer.write(text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String load() {
        try (
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            StringBuilder stringBuilder = new StringBuilder();

            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }

            return stringBuilder.toString();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}