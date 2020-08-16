package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class User implements Player {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final char symbol;

    public User(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void move(Board board) {
        boolean flag = false;
        while (!flag) {
            try {
                System.out.print("Enter the coordinates: ");
                String[] raw = br.readLine().split("\\s+");
                int y = Integer.parseInt(raw[0]);
                int x = Integer.parseInt(raw[1]);

                if (x > Board.SIDE || y > Board.SIDE || x == 0 || y == 0) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    x = (x - 3 < 0) ? 3 - x : x - 3;
                    y = y - 1;
                    if (board.getSymbol(x, y) == ' ') {
                        board.setSymbol(x, y, this.symbol);
                        board.removeAIMove(x + " " + y);
                        flag = true;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }

            } catch (IOException e) {
                System.out.println("Something went wrong there");
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }
}
