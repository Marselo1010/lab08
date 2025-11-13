package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static it.unibo.deathnote.api.DeathNote.RULES;

import java.util.List;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private DeathNote deathNoteTest;
    final String person = "marco";
    final String person2 = "giulia"; 
    final String notWrittenPerson = "different person";

    @BeforeEach
    void setUp(){
        deathNoteTest = new DeathNoteImpl();
        
    }

    /*
     * 1. Rule number 0 and negative rules do not exist in the DeathNote rules.
     * check that the exceptions are thrown correctly, that their type is the 
     * expected one, and that the message is not null, empty, or blank.
     */

    @Test
    void testRuleNumber(){
        try {
            deathNoteTest.getRule(0);
            Assertions.fail("rule number 0 doesn't exist");    
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertFalse(e.getMessage().isEmpty()); 
        }
        try {
            deathNoteTest.getRule(-1);
            Assertions.fail("the rule number must be a positive number");    
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertFalse(e.getMessage().isEmpty()); 
        }
    }
    /*
     * 2. No rule is empty or null in the DeathNote rules.
     * for all the valid rules, check that none is null or blank
     */
    @Test
    void testNoEmptyOrNullRule(){

        for(int i = 1; i <= RULES.size(); i++){
            assertFalse(deathNoteTest.getRule(i).isEmpty());
            assertFalse(deathNoteTest.getRule(i).isBlank());
            assertNotNull(deathNoteTest.getRule(i));
        }
    }
    /*  
     * 3. The human whose name is written in the DeathNote will eventually die.
      * verify that the human has not been written in the notebook yet
      * write the human in the notebook
      * verify that the human has been written in the notebook
      * verify that another human has not been written in the notebook
      * verify that the empty string has not been written in the notebook
      */
    @Test
    void testWrittenPerson(){

        assertFalse(deathNoteTest.isNameWritten(person));
        deathNoteTest.writeName(person);
        assertTrue(deathNoteTest.isNameWritten(person));
        assertFalse(deathNoteTest.isNameWritten(notWrittenPerson));
        assertFalse(deathNoteTest.isNameWritten(""));
    }

    /*
      * 4. If the cause of death is written within the next 40 milliseconds of writing the person's name, it will happen.
      * If the cause of death is not specified, the person will simply die of a heart attack.
      * check that writing a cause of death before writing a name throws the correct exception
      * write the name of a human in the notebook
      * verify that the cause of death is a heart attack
      * write the name of another human in the notebook
      * set the cause of death to "karting accident"
      * verify that the cause of death has been set correctly (returned true, and the cause is indeed "karting accident")
      * sleep for 100ms
      * try to change the cause of death 
      * verify that the cause of death has not been changed
      */

      @Test
      void testCauseOfDeath() throws InterruptedException{

            try {
                deathNoteTest.writeDeathCause("suicide");
                Assertions.fail("you can't write the cause before writing the name of the person");
            } catch (final IllegalStateException e) {
                
            }   
        
        deathNoteTest.writeName(person);
        assertEquals("heart attack", deathNoteTest.getDeathCause(person));
        deathNoteTest.writeName(person2);
        deathNoteTest.writeDeathCause("karting accident");
        Thread.sleep(100);
        deathNoteTest.writeDeathCause("car incident");
        assertEquals("karting accident",deathNoteTest.getDeathCause(person2));
      }

    /*
      * 5. After writing the cause of death, details of the death should be written in the next 6 seconds and 40 milliseconds
      * of writing the death's cause.
      * check that writing the death details before writing a name throws the correct exception
      * write the name of a human in the notebook
      * verify that the details of the death are currently empty
      * set the details of the death to "ran for too long"
      * verify that death details have been set correctly (returned true, and the details are indeed "ran for too long")
      * write the name of another human in the notebook
      * sleep for 6100ms
      * try to change the details
      * verify that the details have not been changed
      */

      @Test
      void testDeathDetails() throws InterruptedException{
            try {
                deathNoteTest.writeDetails("death will come at 21:00 ");
                Assertions.fail("you can't write the details of the death before writing the name of the person");
            } catch (final IllegalStateException e) {
                
            }
            deathNoteTest.writeName(person);
            assertEquals("", deathNoteTest.getDeathDetails(person));
            deathNoteTest.writeDetails("ran for too long");
            assertEquals("ran for too long", deathNoteTest.getDeathDetails(person));
            deathNoteTest.writeName(person2);
            Thread.sleep(6100);
            deathNoteTest.writeDetails("death will come at 21:00 ");
            assertEquals("", deathNoteTest.getDeathDetails(person2));

      }
}