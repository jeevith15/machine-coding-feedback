package src;
import java.util.*;

public class Driver {
    public static void main(String[] args){
        Scanner sc  = new Scanner(System.in);
        Queue<Player> playerQueue = new LinkedList<>();
        System.out.println("Enter Grid Size");
        int size = sc.nextInt();
        Game game = new Game(size);

        System.out.println("Enter number of Players");
        int playersSize = sc.nextInt();
        System.out.println("Enter Player Details");
        List<Player> playersList= new ArrayList<>();
        for(int i=0;i<playersSize;i++){
            String name = sc.next();
            String piece = sc.next();
            Player player = new Player(name,piece);
            playersList.add(player);
            playerQueue.add(player);
        }
        game.setPlayersList(playersList);

        System.out.println("Enter Moves");
        while (true){
            int row = sc.nextInt();
            int col = sc.nextInt();
            Player player = playerQueue.peek();
            boolean validMove = game.placePiece(row,col,player.getPiece());
            if(!validMove){
                continue;
            }
            playerQueue.add(playerQueue.poll());

            boolean gameWon = game.hasPlayerWonGame(row,col, player);
            if(gameWon) break;
            boolean gameDrawn = game.hasPlayerDrawnGame();
            if(gameDrawn) break;
        }
    }
}
