package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote{
    
    private Map<String,CauseDetails> person = new HashMap<>();
    private String lastPerson = null;
    private long time = 0;

    @Override
    public String getRule(int ruleNumber) {
       if(ruleNumber < 1 || ruleNumber > RULES.size()){
        throw new IllegalArgumentException("Your rule number doesn't exist -> must be [ 0-" + RULES.size() + " ]");
       } else {
            return RULES.get(ruleNumber-1);
       }
    }

    @Override
    public void writeName(String name) {
        this.time = System.currentTimeMillis();
        this.lastPerson = name;
        if(name == null){
            throw new NullPointerException("you must write the name of the person");
        } else if (person.containsKey(name)) {
            throw new ClassCastException("this name is already present in the Death Note");
            } else {
                person.put(name, new CauseDetails("heart attack",""));
            }
    }

    @Override
    public boolean writeDeathCause(String cause) {

        if(lastPerson == null){
            throw new IllegalStateException("first you have to write a name for the person");
        } else if(cause == null){
            throw new IllegalStateException("the cause can't be null");
        }

        if(System.currentTimeMillis() - this.time <= 40){
            person.get(lastPerson).setCause(cause);
            return true;  
        }
        
        return false;
    }

    @Override
    public boolean writeDetails(String details) {

        if(lastPerson == null){
            throw new IllegalStateException("first you have to write a name for the person");
        } else if(details == null){
            throw new IllegalStateException("the cause can't be null");
        }

        if(System.currentTimeMillis() - this.time <= 6040){
            person.get(lastPerson).setDetails(details);
            return true;  
        }
        
        return false;

    }

    @Override
    public String getDeathCause(String name) {
       return person.get(name).getCause();
    }

    @Override
    public String getDeathDetails(String name) {
        return person.get(name).getDetails();
    }

    @Override
    public boolean isNameWritten(String name) {
       return person.containsKey(name);
    }

    public static class CauseDetails{
        
        private String cause;
        private String details;

        CauseDetails(String cause, String details){
            this.cause = cause;
            this.details = details;

        }

        CauseDetails(){

        }

        //getter

        public String getCause() {
            return this.cause;
        }

        public String getDetails() {
            return this.details;
        }

        //setter 

        public void setCause(String cause){
            this.cause = cause;
        }

        public void setDetails(String details){
            this.details = details;
        }
    }

}
