import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EachPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		String score;
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		
		//Act
		// This statement should cause an exception according to teacher. Why? Should't the program have ended already by this time?
		game.player1Scored();	
		score=game.getScore();
		System.out.println(score);
				
		//Assert
		assertEquals("failure when game has already ended", "player1 wins", score);
		
	}	
	
	@Test // tests 3-3 deuce
	public void deuce_testi() throws TennisGameException
	{
	//Arrange
	TennisGame game = new TennisGame();
	String score;
	int i;
	for (i=0; i<3; i++)
		{
		game.player2Scored();
		game.player1Scored();
		} 
	//Act
	
	score = game.getScore() ;
	

	// Assert 
	assertEquals("Deuce fails", "deuce", score);
	
	}
	
	@Test // tests 1-0 15-love - apparently score was reported in the wrong fashion
	public void fifteen_love_test() throws TennisGameException
	{
	//Arrange
	TennisGame game = new TennisGame();
	String score;
	//Act
	game.player1Scored();
	score = game.getScore() ;
	

	// Assert 
	assertEquals("fails", "15 - love", score);
	
	}	
	
	@Test // tests 1-0 15-love - apparently score was reported in the wrong fashion
	public void love_fifteen_test() throws TennisGameException
	{
	//Arrange
	TennisGame game = new TennisGame();
	String score;
	//Act
	game.player2Scored();
	score = game.getScore() ;
	

	// Assert 
	assertEquals("fails", "love - 15", score);
	}
	
	// There would normally be different tests for 30-15, 15-30, 30-30 etc, but they hardly seem necessary. 
	// could be tested in a loop that prints each score as the game progresses.
	
	@Test 
	public void player1_wins_test() throws TennisGameException
	{
	//Arrange
	TennisGame game = new TennisGame();
	String score;
	int i;
	//Act
	for (i=0;i<3;i++)
		{
		game.player1Scored();
		
		}
	game.player2Scored();
	game.player1Scored();
	score = game.getScore() ;
	

	// Assert 
	assertEquals("fails", "player1 wins", score);
	}
	

	
	@Test 
	public void player2_advantage() throws TennisGameException
	{
	//Arrange
	TennisGame game = new TennisGame();
	String score;
	int i;
	//Act
	for (i=0;i<3;i++)
		{
		game.player1Scored();
		game.player2Scored();
		}
	// score is now 40-40 (i.e. 3-3)
	game.player2Scored();
	score = game.getScore();
	
	// Assert 
	assertEquals("fails", "player2 has advantage", score);
	
	}	
	
	@Test 
	public void player1_advantage() throws TennisGameException
	{
	//Arrange
	TennisGame game = new TennisGame();
	String score;
	int i;
	//Act
	for (i=0;i<3;i++)
		{
		game.player1Scored();
		game.player2Scored();
		}
	// score is now 40-40 (i.e. 3-3)
	game.player1Scored();
	score = game.getScore();
	
	// Assert 
	assertEquals("fails", "player1 has advantage", score);
	
	}
	
	@Test 
	public void player2_wins_test() throws TennisGameException
	{
	//Arrange
	TennisGame game = new TennisGame();
	String score;
	int i;
	//Act
	for (i=0;i<3;i++)
		{
		game.player2Scored();
		}
	game.player1Scored();
	game.player2Scored();
	score = game.getScore() ;
	
	// Assert 
	assertEquals("fails", "player2 wins", score);
	
	}	
	
	
}
