package sample.Client;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.util.ArrayList;

public class ClientController {

    @FXML
    private Button sendButton;

    @FXML
    private TextField addText;

    @FXML
    private Button addButton;

    @FXML
    private Label fileList;

    private ArrayList<String> list = new ArrayList<>();

    @FXML
    void initialize() throws IOException {

        addButton.setOnAction(event -> {
            String str = addText.getText();
            String name_file = "";
            list.add(str);

            for(String s : list) {
                name_file += s + "\n";
            }
            fileList.setText(name_file);
        });

        sendButton.setOnAction(event -> {

            if(list.isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Вы должны выбрать хотя бы один файл!");
                alert.showAndWait();

            } else {

                Socket socket = new Socket();

                try {
                    socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8800), 2000);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                PrintWriter writer = null;

                try {
                    writer = new PrintWriter(socket.getOutputStream(), true);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                boolean check = true;

                for(String s : list) {
                    s = "csv/" + s;

                    if ((new File(s)).exists()) {
                        check = true;

                    } else {
                        check = false;
                    }
                }

                if(check) {

                    ArrayList<Thread> threads = new ArrayList<>();

                    for (String s : list) {
                        System.out.println(s);
                        threads.add(new Thread(new ReadThread("csv/" + s, writer)));
                    }

                    for (Thread t : threads) {
                        t.start();
                        try {
                            t.join();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Таких файлов нет!");
                    alert.showAndWait();
                }

                System.exit(0);
            }
        });
    }
}