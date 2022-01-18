package vea.home.entities;

import javax.persistence.Entity;

@Entity
public class Cat extends Animal{
    private Boolean fluffy=false;
    @Override
    public String makeNoise() {
        return "meow meow";
    }

    public void setFluffy(Boolean fluffy) {
        this.fluffy = fluffy;
    }
}
