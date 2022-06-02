package src;

public class Board{
    private final int SIZE;
    private final int MAX_MOVES;
    private final String[][] board;
    int moveCount;

    public Board(int size){
        this.SIZE = size;
        this.board = new String[size][size];
        this.MAX_MOVES = size*size;
        this.moveCount=0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                this.board[i][j] = "-";
            }
        }

        printBoard();
    }

    public int getSize(){
        return this.SIZE;
    }

    public void setPieceOnBoard(int i,int j, String piece){
        this.board[i][j] = piece;
        moveCount++;
        printBoard();
    }

    public String[][] getBoard(){
        return board;
    }

    public void printBoard(){
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }


    public boolean rowCheck(int row, String piece){
        for(int i=0;i<SIZE;i++){
            if(!board[row][i].equals(piece)) return false;
        }
        return true;
    }

    public boolean columnCheck(int col, String piece){
        for(int i=0;i<SIZE;i++){
            if(!board[i][col].equals(piece)) return false;
        }
        return true;
    }

    public boolean posDiagCheck(int row, int col, String piece){
        if(row!=col) return false;
        for(int i=0;i<SIZE;i++){
            if(!board[i][i].equals(piece)) return false;
        }
        return true;
    }

    public boolean negDiagCheck(int row, int col, String piece){
        if(row+col!=SIZE-1) return false;
        for(int i=0,j=SIZE-1;i<SIZE && j>=0;i++,j--){
            if(!board[i][j].equals(piece)) return false;
        }
        return true;
    }

    public boolean hasPlayerWon(int row,int col,String piece){
        return rowCheck(row,piece) || columnCheck(col,piece) ||
                posDiagCheck(row,col,piece) || negDiagCheck(row,col,piece);
    }

    public boolean isGameDraw(){
        return moveCount==MAX_MOVES;
    }
}