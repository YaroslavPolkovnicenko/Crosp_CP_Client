package sample.Client;

import java.io.*;

public class ReadThread implements Runnable{

    private String fileName;
    private PrintWriter writer;

    public ReadThread(String fileName, PrintWriter writer) {
        this.fileName = fileName;
        this.writer = writer;
    }

    public void read(BufferedReader reader) throws IOException {

        String line;

        while((line = reader.readLine()) != null) {

            System.out.println("Клиент " + Thread.currentThread().getName() + " передал строку: " + line);
            writer.println(line);
        }
    }

    @Override
    public void run() {

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            read(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}