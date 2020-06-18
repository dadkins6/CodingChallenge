package main.java;

public class TicTacToeAssessor {

    public static final char[][] board = {{'x', ' ', ' '},
                                          {'o', 'x', 'o'},
                                          {' ', 'o', 'x'},};


    public static void main(String[] args) {
        char winner = checkBoard(board);
        if(winner == ' ') {
            System.out.println("There is no winner. :(");
        } else {
            System.out.println("The winner is " + winner);
        }

    }


    private static char checkBoard(char[][] board) {
        int size = board.length;

        // check rows for winner
        for (char[] row : board) {
           char winner = isThereAWinner(row);
           if(winner != ' ') {
               return winner;
           }
        }

        // check columns for winner.
        for (int index = 0; index < size; index++) {
            char[] column = new char[size];

            for (int i = 0; i < size; i++) {
                column[i] = board[i][index];
            }
            char winner = isThereAWinner(column);
            if(winner != ' ') {
                return winner;
            }
        }

        // check diagonals for winner.
        char[] forwardDiag = new char[size];
        char[] reverseDiag = new char[size];
        for (int i = 0; i < size; i++) {
            forwardDiag[i] = board[i][i];
            reverseDiag[i] = board[i][size-i-1];
        }
        char winner = isThereAWinner(forwardDiag);

        if(winner != ' ') {
            return winner;
        }

        winner = isThereAWinner(reverseDiag);

        return winner;

    }


    /**
     * Checks the given path if there is a winner. Will return the winning character if there is a winner. blank otherwise.
     *
     * @param row The characters along the path being checked for a winner.
     * @return Winning character or blank if there is no winner.
     */
    private static char isThereAWinner(char[] row) {

        char winner = ' ';
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] != row[i + 1]) {
                return winner;
            }
        }

        // if reached here, we have a winner. return the character of the winner.
        return row[0];
    }

}
