package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static it.unibo.deathnote.api.DeathNote.RULES;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private DeathNote deathNoteTest;
    private static final String PERSON = "marco";
    private static final String PERSON_2 = "giulia"; 
    private static final String NOT_WRITTEN_PERSON = "different person";

    @BeforeEach
    void setUp() {
        deathNoteTest = new DeathNoteImpl();
    }

    @Test
    void testRuleNumber() {
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

    @Test
    void testNoEmptyOrNullRule() {

        for (int i = 1; i < RULES.size(); i++) {
            assertFalse(deathNoteTest.getRule(i).isEmpty());
            assertFalse(deathNoteTest.getRule(i).isBlank());
            assertNotNull(deathNoteTest.getRule(i));
        }
    }

    @Test
    void testWrittenPerson() {

        assertFalse(deathNoteTest.isNameWritten(PERSON));
        deathNoteTest.writeName(PERSON);
        assertTrue(deathNoteTest.isNameWritten(PERSON));
        assertFalse(deathNoteTest.isNameWritten(NOT_WRITTEN_PERSON));
        assertFalse(deathNoteTest.isNameWritten(""));
    }

      @Test
      void testCauseOfDeath() throws InterruptedException {

            try {
                deathNoteTest.writeDeathCause("suicide");
                Assertions.fail("you can't write the cause before writing the name of the person");
            } catch (final IllegalStateException e) {

            }

        deathNoteTest.writeName(PERSON);
        assertEquals("heart attack", deathNoteTest.getDeathCause(PERSON));
        deathNoteTest.writeName(PERSON_2);
        deathNoteTest.writeDeathCause("karting accident");
        Thread.sleep(100);
        deathNoteTest.writeDeathCause("car incident");
        assertEquals("karting accident", deathNoteTest.getDeathCause(PERSON_2));
      }

      @Test
      void testDeathDetails() throws InterruptedException {
            try {
                deathNoteTest.writeDetails("death will come at 21:00 ");
                Assertions.fail("you can't write the details of the death before writing the name of the person");
            } catch (final IllegalStateException e) {
                
            }
            deathNoteTest.writeName(PERSON);
            assertEquals("", deathNoteTest.getDeathDetails(PERSON));
            deathNoteTest.writeDetails("ran for too long");
            assertEquals("ran for too long", deathNoteTest.getDeathDetails(PERSON));
            deathNoteTest.writeName(PERSON_2);
            Thread.sleep(6100);
            deathNoteTest.writeDetails("death will come at 21:00 ");
            assertEquals("", deathNoteTest.getDeathDetails(PERSON_2));

      }
}
