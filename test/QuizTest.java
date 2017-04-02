
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;


public class QuizTest  {

    /* test player class behaviors */
    // test constructor and get name and password methods
    @Test
    public void testQuiz1() {
        Player player = new Player("name", "password", null); // intialize player object

        // check name and password
        assertEquals("name", player.getName());
        assertEquals("password", player.getPassword());
    }

    // test set name and password methods
    @Test
    public void testQuiz2() {
        Player player = new Player("name", "password", null); // intialize player object

        // check name and password
        assertEquals("name", player.getName());
        assertEquals("password", player.getPassword());

        // change name and password
        player.setName("otherName");
        player.setPassword("otherPassword");

        // check name and password change
        assertEquals("otherName", player.getName());
        assertEquals("otherPassword", player.getPassword());
    }

    // test addScore and getScores methods
    @Test
    public void testQuiz3() {
        Player player = new Player("name", "password", null); // intialize player object
        ArrayList<Integer> expected = new ArrayList<>(); // intialize array of integers

        // add scores to player
        player.addScore(1);
        player.addScore(2);
        player.addScore(3);
        player.addScore(4);

        // add scores to array
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);

        // check getScores
        assertEquals(expected, player.getScores());
    }

    // test addScore and getScores methods if return scores in order rank
    @Test
    public void testQuiz4() {
        Player player = new Player("name", "password", null); // intialize player object
        ArrayList<Integer> expected = new ArrayList<>(); // intialize array of integers  

        // add scores to player unsorted
        player.addScore(4);
        player.addScore(1);
        player.addScore(3);
        player.addScore(2);

        // add scores sorted
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);

        // check getScores 
        assertEquals(expected, player.getScores());
    }

    /* test players class behaviors */
    
    // test constructor, add and found methods
    @Test
    public void testQuiz5() {
        Players player = new Players(); // intialize players object

        // add player name to players
        player.add("name", "password");
        
        // check name if found in players
        assertTrue(player.found("name"));
    }

    // test get password
    @Test
    public void testQuiz6() {
        Players player = new Players(); // intialize players object

        // add player name and password to players
        player.add("name", "password");
        
        // check password
        assertEquals("password",player.getPassword("name"));
    }

    // test addScore and score methods (run this method just one time because file is updated)
    @Test
    public void testQuiz7() {
        Players player = new Players(); // intialize players object

        // add player name and password to players
        player.add("name", "password");
        
        ArrayList<Integer> expected = new ArrayList<>(); // intialize array of integers

        // add scores to player
        player.addScore("name",1);
        player.addScore("name",2);
        player.addScore("name",3);
        player.addScore("name",4);

        // add scores to array
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);

        // check score
        assertEquals(expected, player.score("name"));
    }
    
    /* test questions class behaviors */
    
    // test constructor and getQuestion method
    @Test
    public void testQuiz8() {
        Questions q = new Questions(); // declare question object to get different questions

        // print question, choices and answer
        for (String s : q.getQuestion().split(",")) {
            System.out.println(s);
        }
    }
    
    // test difference of questions
    @Test
    public void testQuiz9() {
        Questions q = new Questions(); // declare question object to get different questions
        
        String question1 = q.getQuestion() ; // get question1
        String question2 = q.getQuestion() ; // get question2

        // check if two questions is not equals
        assertTrue(!question1.equals(question2));
    }}

 
