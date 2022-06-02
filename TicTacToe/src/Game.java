package src;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> playersList;
    private final Board board;

    public Game(){
        this.playersList = new ArrayList<>();
        this.board = new Board(3);
    }

    public Game(int size){
        this.playersList = new ArrayList<>();
        this.board = new Board(size);
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList){
        this.playersList = playersList;
    }

    public Board getBoard(){
        return this.board;
    }


    public boolean placePiece(int row, int col, String piece){
        if(row<0 || row>=board.getSize() || col<0 || col>= board.getSize() || !board.getBoard()[row][col].equals("-")){
            System.out.println("Invalid Move");
            return false;
        }
        board.setPieceOnBoard(row,col,piece);
        return true;
    }

    public boolean hasPlayerWonGame(int row, int col, Player player){
        boolean playerWon = board.hasPlayerWon(row,col,player.getPiece());
        if(playerWon) System.out.println( player.getName()+" wins");
        return playerWon;
    }

    public boolean hasPlayerDrawnGame(){
        boolean draw = board.isGameDraw();
        if(draw) System.out.println("Game Draw");
        return draw;
    }

}