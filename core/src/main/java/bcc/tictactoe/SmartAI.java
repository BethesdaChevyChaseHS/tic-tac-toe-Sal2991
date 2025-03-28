package bcc.tictactoe;

public class SmartAI extends Player {
    @Override
    public Move makeMove(Board board, Mark mark) {
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.getGrid()[row][col].equals(Mark.EMPTY)) {
                    board.makeMove(row, col, mark);
                    int score = minimax(board, false, mark);
                    board.clearCell(row, col);
                    
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new Move(row, col);
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(Board board, boolean aiTurn, Mark aiMark) {
        Mark winner = board.checkWin();
        if (winner != null) {
            if (winner.equals(aiMark)) return 1;
            if (winner.equals(Mark.TIE)) return 0;
            return -1;
        }

        int bestScore = aiTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.getGrid()[row][col].equals(Mark.EMPTY)) {
                    board.makeMove(row, col, aiTurn ? aiMark : aiMark.opposite());
                    int score = minimax(board, !aiTurn, aiMark);
                    board.clearCell(row, col);

                    if (aiTurn) {
                        bestScore = Math.max(bestScore, score);
                    } else {
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
        }
        return bestScore;
    }

    @Override
    public String toString() {
        return "Smart AI";
    }
}