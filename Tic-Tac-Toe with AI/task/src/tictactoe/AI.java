package tictactoe;

import java.util.Random;

public class AI implements Player {

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    final Difficulty level;

    private final char symbol;

    public AI (Difficulty level, char symbol) {
        this.level = level;
        this.symbol = symbol;
    }

    private static class BestMove {
        static int x;
        static int y;
    }
    private void randomMove(Board board) {
        Random random = new Random();
        int index = random.nextInt(board.getTotalAIMoves());
        int[] indices = board.getIndices(index);
        board.removeAIMove(index);
        board.setSymbol(indices[0], indices[1], this.symbol);
    }

    void getNextMove(boolean isPlayerOne, Board board) {
        BestMove.x = -1;
        BestMove.y = -1;
        for (int i = 0; i < Board.SIDE; ++i) {
            for (int j = 0; j < Board.SIDE; ++j) {
                if (board.getSymbol(i, j) == ' ') {
                    if (isPlayerOne) {
                        board.setSymbol(i, j, 'O');
                    } else {
                        board.setSymbol(i, j, 'X');
                    }
                    int retValue = board.checkBoard();
                    board.setSymbol(i, j, ' ');
                    if (retValue != 0) {
                        BestMove.x = i;
                        BestMove.y = j;
                        return;
                    }
                }
            }
        }
    }
    int minimax(Board board, int depth, boolean isPlayerOne) {
        int score = board.checkBoard();
        if (score != 0) {
            return score < 0 ? score + depth : score - depth;
        }
        if (board.getRemainingMoves() == 0) {
            return score;
        }
        int best;
        if (isPlayerOne) {
            best = Integer.MIN_VALUE;
            for (int i = 0; i < Board.SIDE; ++i) {
                for (int j = 0; j < Board.SIDE; ++j) {
                    if (board.getSymbol(i, j) == ' ') {
                        board.setSymbol(i, j, 'X');
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board.setSymbol(i, j, ' ');
                    }
                }
            }

        } else {
            best = Integer.MAX_VALUE;
            for (int i = 0; i < Board.SIDE; ++i) {
                for (int j = 0; j < Board.SIDE; ++j) {
                    if (board.getSymbol(i, j) == ' ') {
                        board.setSymbol(i, j, 'O');
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board.setSymbol(i, j, ' ');
                    }
                }
            }
        }
        return best;
    }
    void getOptimalMove(boolean isPlayerOne, Board board) {
        BestMove.x = -1;
        BestMove.y = -1;
        int best = isPlayerOne ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int prev = best;
        for (int i = 0; i < Board.SIDE; ++i) {
            for (int j = 0; j < Board.SIDE; ++j) {
                if (board.getSymbol(i, j) == ' ') {
                    board.setSymbol(i, j, this.symbol);
                    if (isPlayerOne) {
                        best = Math.max(best, minimax(board, 0, false));
                        if (best > prev) {
                            prev = best;
                            BestMove.x = i;
                            BestMove.y = j;
                        }
                    } else {
                        best = Math.min(best, minimax(board, 0, true));
                        if (best < prev) {
                            prev = best;
                            BestMove.x = i;
                            BestMove.y = j;
                        }
                    }

                    board.setSymbol(i, j, ' ');
                }
            }
        }
    }
    @Override
    public void move(Board board) {
        if (level.equals(Difficulty.EASY)) {
            System.out.println("Making move level \"easy\"");
            randomMove(board);
        } else if (level.equals(Difficulty.MEDIUM)) {
            System.out.println("Making move level \"medium\"");
            getNextMove(this.symbol == 'X', board);
            if (BestMove.x != -1){
                board.setSymbol(BestMove.x, BestMove.y, this.symbol);
                board.removeAIMove(BestMove.x + " " + BestMove.y);
            } else {
                randomMove(board);
            }
        } else if (level.equals(Difficulty.HARD)) {
            System.out.println("Making move level \"hard\"");
            getOptimalMove(this.symbol == 'X', board);
            board.setSymbol(BestMove.x, BestMove.y, this.symbol);
            board.removeAIMove(BestMove.x + " " + BestMove.y);
        }
    }
}
