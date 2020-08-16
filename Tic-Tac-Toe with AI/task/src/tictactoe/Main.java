package tictactoe;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new Game().play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}