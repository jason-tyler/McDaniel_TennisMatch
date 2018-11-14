/**
 * Represents an instance of a match.
 */
public class Match {

    private final static byte NONE = 0;
    private final static byte PLAYER_1 = 1;
    private final static byte PLAYER_2 = 2;

    private boolean matchComplete;
    private byte currentSet;
    private int scoreSet1Player1;
    private int scoreSet1Player2;
    private int scoreSet2Player1;
    private int scoreSet2Player2;
    private int scoreSet3Player1;
    private int scoreSet3Player2;
    private String currentScorePlayer1;
    private String currentScorePlayer2;

    /**
     * Gets the current set number.
     * @return Set number.
     */
    public byte getCurrentSet() {
        return currentSet;
    }

    /**
     * Default constructor.
     */
    public Match(){
        currentSet = 1;
        this.resetCurrentScore();
    }

    /**
     * Resets the current score to LOVE for both players.
     */
    private void resetCurrentScore(){
        currentScorePlayer1 = MatchPoint.LOVE.toString();
        currentScorePlayer2 = MatchPoint.LOVE.toString();
    }

    /**
     * Calculcates whether the game is over and returns a value that indicates which player won the game.
     * @param player1Score Player 1 current score.
     * @param player2Score Player 2 current score.
     * @return Value indicating whether play 1 or player 2 won the set. 0 is returned if the set is still on.
     */
    private byte isSetOver(int player1Score, int player2Score){
        // Did player 1 win?
        if ((player1Score == 4 && player2Score < 3) ||
                (player2Score == 3 && player1Score == 5) )
            return PLAYER_1;
        // Did player 2 win?
        else if ((player2Score == 4 && player1Score < 3) ||
                (player1Score == 3 && player2Score == 5) )
            return PLAYER_2;
        // Else, the game is still on.
        else
            return NONE;
    }

    /**
     * Determines if the game is over using best of 3 rules.
     * @return Value of the player that won the game or 0 if the game is still on.
     */
    private byte isGameOver(){
        byte player1GameCount = 0;
        byte player2GameCount = 0;

        // Set 1
        if (this.scoreSet1Player1 > this.scoreSet1Player2)
            player1GameCount++;
        else if (this.scoreSet1Player1 < this.scoreSet1Player2)
            player2GameCount++;

        // Set 2
        if (this.scoreSet2Player1 > this.scoreSet2Player2)
            player1GameCount++;
        else if (this.scoreSet2Player1 < this.scoreSet2Player2)
            player2GameCount++;

        // Set 3
        if (this.scoreSet3Player1 > this.scoreSet3Player2)
            player1GameCount++;
        else if (this.scoreSet3Player1 < this.scoreSet3Player2)
            player2GameCount++;

        if (player1GameCount == 2)
            return PLAYER_1;
        else if (player2GameCount == 2)
            return PLAYER_2;
        else
            return NONE;
    }

    /**
     * Updates the scores for the current set.
     * @param set Current set.
     * @param scorePlayer1 Player 1 score
     * @param scorePlayer2 Player 2 score
     */
    private void updateSetScores(byte set, int scorePlayer1, int scorePlayer2){
        if (set == 1){
            this.scoreSet1Player1 = scorePlayer1;
            this.scoreSet1Player2 = scorePlayer2;
        }
        else if (set == 2){
            this.scoreSet2Player1 = scorePlayer1;
            this.scoreSet2Player2 = scorePlayer2;
        }
        else {
            this.scoreSet3Player1 = scorePlayer1;
            this.scoreSet3Player2 = scorePlayer2;
        }
    }

    /**
     * Represents that a player scored a point.
     * @param isPlayer1 Boolean value to indicate whether player 1 or 2 scored the point.
     */
    public void scorePoint(boolean isPlayer1){

        if (this.matchComplete == true){
            System.err.println("The match is best of 3 and is complete.");
            return;
        }

        // Get the numeric value of the current match score.
        int player1score = MatchPoint.valueOf(currentScorePlayer1).ordinal();
        int player2score = MatchPoint.valueOf(currentScorePlayer2).ordinal();

        // Determine which player scored a point.
        if (isPlayer1 == true) {
            if (player2score == 4)
                player2score = 3;
            else
                player1score++;
        }
        else{
            if (player1score == 4)
                player1score = 3;
            else
                player2score++;
        }

        this.updateSetScores(this.currentSet, player1score, player2score);

        int winningPlayer = this.isSetOver(player1score, player2score);

        if (winningPlayer > 0){

            System.out.println("Player " + winningPlayer + " won the match.");

            // Determine if the game is over based upon best of 3 rules.
            // If the game is not over, then increment the set.
            if (this.isGameOver() == 0) {
                this.currentSet++;
                this.resetCurrentScore();
            }
            else{
                this.matchComplete = true;
                System.out.println("The best of 3 match is complete.");
            }
        }
        else {
            // Set the current scores.
            this.currentScorePlayer1 = MatchPoint.values()[player1score].toString();
            this.currentScorePlayer2 = MatchPoint.values()[player2score].toString();
        }
    }

    /**
     * Returns a message that indicates the current set and score.
     * @return Set message.
     */
    public String toString(){
        String message;

        message = "Current Set: " + this.getCurrentSet() + "\n";
        message +="Current Score:\n";
        message += "\tPlayer 1: " + currentScorePlayer1 + "\n";
        message += "\tPlayer 2: " + currentScorePlayer2 + "\n";

        return message;
    }

    /**
     * Returns a message that indicates the current score for all sets in the game.
     * @return
     */
    public String getGameScore(){
        String message;

        message = "Set\t\t\t1\t2\t3\n";
        message += "--------------------------------------\n";
        message += "Player 1\t" + this.scoreSet1Player1 + "\t" + this.scoreSet2Player1 + "\t" + this.scoreSet3Player1 + "\n";
        message += "Player 2\t" + this.scoreSet1Player2 + "\t" + this.scoreSet2Player2 + "\t" + this.scoreSet3Player2 + "\n";

        return message;
    }
}
