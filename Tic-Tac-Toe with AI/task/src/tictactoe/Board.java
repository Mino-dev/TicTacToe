package tictactoe;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

    private List<List<Character>> field;
    private List<String> available;
    final static int SIDE = 3;

    public Board() {
        resetBoard();
    }

    final void resetBoard() {
        field = IntStream.range(0, SIDE)
                .mapToObj(i -> Stream.of(' ', ' ', ' ').collect(Collectors.toList()))
                .collect(Collectors.toList());
        available = Stream.of("0 0", "0 1", "0 2", "1 0", "1 1", "1 2", "2 0", "2 1", "2 2").collect(Collectors.toCollection(LinkedList::new));
    }
    int[] getIndices(int index) {
        return Arrays.stream(this.available.get(index).split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }
    int getTotalAIMoves() {
        return this.available.size();
    }
    void removeAIMove(int index) {
        this.available.remove(index);
    }
    void removeAIMove(String index) {
        this.available.remove(index);
    }
    char getSymbol(int i, int j) {
        return this.field.get(i).get(j);
    }
    void setSymbol(int i, int j, char symbol) {
        this.field.get(i).set(j,symbol);
    }
    int getRemainingMoves() {
        return (int) field.stream().flatMapToInt(c -> IntStream.range(0, SIDE).filter(i -> c.get(i) == ' ')).count();
    }
    public void showBoard() {
        System.out.println("---------");
        for (List<Character> l : field) {
            System.out.print("| ");
            for (char c : l) {
                System.out.print(c + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public byte checkBoard() {
        for (int i = 0; i < SIDE; i++) {
            if(field.get(i).get(0) == field.get(i).get(1) && field.get(i).get(1)  == field.get(i).get(2)) {
                if (field.get(i).get(0) != ' ') {
                    if (field.get(i).get(0) == 'X') {
                        return 10;
                    } else {
                        return -10;
                    }
                }
            }
        }
        for (int i = 0; i < SIDE; i++) {
            if(field.get(0).get(i) == field.get(1).get(i) && field.get(1).get(i)  == field.get(2).get(i)) {
                if (field.get(0).get(i) != ' ') {
                    if (field.get(0).get(i) == 'X') {
                        return 10;
                    } else {
                        return -10;
                    }
                }
            }
        }
        if (field.get(0).get(0) == field.get(1).get(1) && field.get(1).get(1) == field.get(2).get(2)) {
            if (field.get(0).get(0) != ' ') {
                if (field.get(0).get(0) == 'X') {
                    return 10;
                } else {
                    return -10;
                }
            }
        } else if (field.get(0).get(2) == field.get(1).get(1) && field.get(1).get(1) == field.get(2).get(0)) {
            if (field.get(0).get(2) != ' ') {
                if (field.get(0).get(2) == 'X') {
                    return 10;
                } else {
                    return -10;
                }
            }
        }
        return 0;
    }
}
