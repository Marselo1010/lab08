package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote{
    
    private Map<String,CauseDetails> person = new HashMap<>();

    @Override
    public String getRule(int ruleNumber) {
       if(ruleNumber < 0 && ruleNumber > RULES.size()){
        throw new IllegalArgumentException("Your rule number doesn't exist -> must be [ 0-" + RULES.size() + " ]");
       } else {
            return RULES.get(ruleNumber);
       }
    }

    @Override
    public void writeName(String name) {
        if(name == null){
            throw new NullPointerException("you must write the name of the person");
        } else if (person.containsKey(name)) {
            throw new ClassCastException("this name is already present in the Death Note");
             } else {
                person.put(name, null);
             }

        
        
    }

    @Override
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }

    public static class CauseDetails{
        
        private String cause;
        private String details;

        CauseDetails(String cause, String details){
            this.cause = cause;
            this.details = details;

        }

        //getter

        public String getCause() {
            return this.cause;
        }

        public String getDetails() {
            return this.details;
        }
    }

}
