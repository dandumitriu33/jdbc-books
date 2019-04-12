package com.codecool.books.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner;
    PrintStream out;

    public UserInterface(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    public void printTitle(String title) {
        out.println(title);
    }

    public void printOption(char option, String description) {
        out.println("(" + option + ")" + " " + description);
    }

    public char choice(String options) {
        String line;
        do {
            out.print("Choice [" + options + "]: ");
            line = scanner.nextLine();
        } while (!(line.length() == 1 && options.contains(line)));
        return line.charAt(0);
    }
}
