package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

    private final Board board = new Board();

    private boolean endGame() {
        byte res = board.checkBoard();
        if (res != 0) {
            if (board.checkBoard() == 10) {
                System.out.println("X wins");
            } else {
                System.out.println("O wins");
            }
            return true;
        } else if (board.getRemainingMoves() == 0) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }
    public void play() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("Input command: ");
            try {
                String temp = br.readLine().trim();
                String[] raw = temp.split("\\s+");
                if (temp.equalsIgnoreCase("exit")) {
                    break;
                }
                if (raw.length != 3) {
                    throw new IllegalArgumentException("Bad parameters");
                }

                Player p1;
                if (raw[1].equalsIgnoreCase("user")) {
                    p1 = new User('X');
                } else if (raw[1].equalsIgnoreCase("easy") ||
                        raw[1].equalsIgnoreCase("medium") ||
                        raw[1].equalsIgnoreCase("hard")) {
                    p1 = new AI(AI.Difficulty.valueOf(raw[1].toUpperCase()), 'X');
                } else {
                    throw new IllegalArgumentException("Bad parameter");
                }

                Player p2;
                if (raw[2].equalsIgnoreCase("user")) {
                    p2 = new User('O');
                } else if (raw[2].equalsIgnoreCase("easy") ||
                        raw[2].equalsIgnoreCase("medium") ||
                        raw[2].equalsIgnoreCase("hard")) {
                    p2 = new AI(AI.Difficulty.valueOf(raw[2].toUpperCase()), 'O');
                } else {
                    throw new IllegalArgumentException("Bad parameter");
                }

                boolean flag = true;
                board.showBoard();
                while (!endGame()) {
                    if (flag) {
                        p1.move(this.board);
                    } else {
                        p2.move(this.board);
                    }
                    flag = !flag;
                    this.board.showBoard();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            board.resetBoard();
        } while (true);
        br.close();
    }
}
