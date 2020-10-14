import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Frame extends JFrame {
    private static final File key_file = new File("key.txt");

    public Frame() {
        super("Szyfrowanie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new FlowLayout());

        setSize(1000, 1000);
        setLocation(50, 50);

        JButton encryptButton = new JButton("Szyfruj - vigenere");
        JButton decryptButton = new JButton("Deszyfruj - vigenere");
        JButton applyKeyButton = new JButton("Zapisz klucz");
        add(encryptButton);
        add(decryptButton);
        add(applyKeyButton);

        JTextArea key = new JTextArea("Change key");
        JTextArea text = new JTextArea("Put text to encrypt");
        JTextArea result = new JTextArea("Result");
        result.setBounds(10, 110, 200, 100);
        add(text);
        add(result);
        add(key);

        SzyfrowaniePodstawieniowePoliVigenere szyfrowaniePodstawieniowePoliVigenere = new SzyfrowaniePodstawieniowePoliVigenere();

        encryptButton.addActionListener(arg0 -> {
            String theKey = readKey();
            result.setText(szyfrowaniePodstawieniowePoliVigenere.encrypt(text.getText().toLowerCase(), theKey));
        });
        decryptButton.addActionListener(arg0 -> {
            String theKey = readKey();
            result.setText(szyfrowaniePodstawieniowePoliVigenere.decrypt(text.getText().toLowerCase(), theKey));
        });

        applyKeyButton.addActionListener(ev -> writeKey(key.getText()));
        pack();
    }

    private String readKey(){
        String key = "test";
        try {
            Scanner odczyt = new Scanner(key_file);
            key = odczyt.nextLine();
            return key;
        } catch (FileNotFoundException e) {
            writeKey(key);
            return key;
        }
    }

    private void writeKey(String key){
        try {
            PrintWriter zapis = new PrintWriter(key_file);
            zapis.println(key);
            zapis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

