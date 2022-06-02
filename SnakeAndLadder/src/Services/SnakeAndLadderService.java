package Services;

import Models.*;

import java.util.*;


public class SnakeAndLadderService {
    private SnakeAndLadderBoard snakeAndLadderBoard;
    private int initailNumberOfPlayers;
    private Queue<Player> players;
    private boolean isGameCompleted;

    private int noOfDices;
    private boolean shouldGameContinueTillLastPlayer;
    private boolean shouldAllowMultipleDiceRollOnSix;

    private static final int DEFAULT_BOARD_SIZE = 100;
    private static final int DEFAULT_NO_OF_DICES = 1;

    public SnakeAndLadderService(int size){
        this.snakeAndLadderBoard = new SnakeAndLadderBoard(size);
        this.players = new LinkedList<Player>();
        this.noOfDices= SnakeAndLadderService.DEFAULT_NO_OF_DICES;
    }

    public SnakeAndLadderService(){
        this(SnakeAndLadderService.DEFAULT_BOARD_SIZE);
    }

    /**
     * ====Setters for making the game more extensible====
     */

    public void setNoOfDices(int noOfDices) {
        this.noOfDices = noOfDices;
    }

    public void setShouldAllowMultipleDiceRollOnSix(boolean shouldAllowMultipleDiceRollOnSix) {
        this.shouldAllowMultipleDiceRollOnSix = shouldAllowMultipleDiceRollOnSix;
    }

    public void setShouldGameContinueTillLastPlayer(boolean shouldGameContinueTillLastPlayer) {
        this.shouldGameContinueTillLastPlayer = shouldGameContinueTillLastPlayer;
    }

    /**
     * ==================Initialize board==================
     */

    public void setPlayers(List<Player> players) {
        this.players = new LinkedList<>();
        this.initailNumberOfPlayers = players.size();

        Map<String,Integer> playerPieces = new HashMap<>();

        for(Player player: players){
            this.players.add(player);
            playerPieces.put(player.getId(),0);
        }
        this.snakeAndLadderBoard.setPlayerPieces(playerPieces);
    }

    public void setSnakes(List<Snake> snakes){
        this.snakeAndLadderBoard.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders){
        this.snakeAndLadderBoard.setLadders(ladders);
    }

    /**
     * ==========Core business logic for the game==========
     */

    private int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition){
        int previousPosition;

        do{
            previousPosition=newPosition;
            for(Snake snake: this.snakeAndLadderBoard.getSnakes()){
                if(snake.getStart() == newPosition){
                    newPosition = snake.getEnd();
                }
            }

            for(Ladder ladder: this.snakeAndLadderBoard.getLadders()){
                if(ladder.getStart() == newPosition){
                    newPosition = ladder.getEnd();
                }
            }

        }while(previousPosition!=newPosition);

        return newPosition;
    }

    private void movePlayer(Player player,int positions){
        int oldPosition = this.snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        int newPosition = oldPosition + positions;

        int boardSize = snakeAndLadderBoard.getSize();

        if(newPosition>boardSize){
            newPosition = oldPosition;
        }else{
            newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
        }

        snakeAndLadderBoard.getPlayerPieces().put(player.getId(),newPosition);

        System.out.println(player.getName() +" rolled a "+ positions +" and moved from "+ oldPosition +" to "+newPosition );
    }

    private int getTotalValueAfterDiceRollls(){
        // Can use noOfDices and setShouldAllowMultipleDiceRollOnSix here to get total value (Optional requirements)
        return DiceService.roll();
    }

    private boolean hasPlayerWon(Player player){
        // Can change the logic a bit to handle special cases when there are more than one dice (Optional requirements)
        int playerPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        int winningPosition = snakeAndLadderBoard.getSize();

        return playerPosition==winningPosition;
    }

    private boolean isGameCompleted(){
        // Can use shouldGameContinueTillLastPlayer to change the logic of determining if game is completed (Optional requirements)
        int currentNoOfPlayers = players.size();
        return currentNoOfPlayers<initailNumberOfPlayers;
    }

    public void startGame(){
        while(!isGameCompleted()){
            int totalDiceValue = getTotalValueAfterDiceRollls();
            Player currentPlayer = players.poll();
            movePlayer(currentPlayer,totalDiceValue);
            if(hasPlayerWon(currentPlayer)){
                System.out.println(currentPlayer.getName()+" wins the game");
                this.snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer.getId());
            }else{
                players.add(currentPlayer);
            }

        }
    }

}