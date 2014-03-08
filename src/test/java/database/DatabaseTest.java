package database;

import model.Coach;
import model.RightsLevel;
import org.junit.Before;
import org.junit.Test;
import utils.Utility;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Tomo.
 */
public class DatabaseTest {

    private Database db;
    private boolean success;
    private Coach coach;
    private Coach coachInDatabase;

    @Before
    public void setUp() throws DatabaseException {

        db = new Database(Utility.getLocalConnection());
        db.dropTable("coaches");
        db.createCoachesTable();
    }

    @Test
    public void verifyExistingCoach() throws DatabaseException {

        givenCoachInDatabase(new Coach("coach", "123", RightsLevel.DIAMOND));
        givenCoach(new Coach("coach", "123", RightsLevel.SILVER));

        whenAuthenticate();

        thenAuthenticationSucceeded();
        thenCoachIsAsInDatabase();
    }

    @Test
    public void verifyUnExistingCoach() throws DatabaseException {

        givenCoachInDatabase(new Coach("coach", "123", RightsLevel.DIAMOND));
        givenCoach(new Coach("coach", "1234", RightsLevel.SILVER));

        whenAuthenticate();

        thenAuthenticationFailed();
    }


    public void givenCoachInDatabase(Coach coach) throws DatabaseException {
        coachInDatabase = coach;
        db.insertCoach(coach);
    }

    public void givenCoach(Coach coach){
        this.coach = coach;
    }

    public void whenAuthenticate() throws DatabaseException {
        success = db.authenticateCoach(coach);
    }

    public void thenAuthenticationFailed(){
        assertEquals(false, success);
    }

    public void thenAuthenticationSucceeded(){
        assertEquals(true, success);
    }

    public void thenCoachIsAsInDatabase(){
        assertEquals(coachInDatabase.getRightsLevel(), coach.getRightsLevel());
    }

}
