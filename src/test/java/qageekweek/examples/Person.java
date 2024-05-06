package qageekweek.examples;

import lombok.Data;

@Data
public class Person {

    private String firstName;
    private String lastName;
    private int id;


    public static void main(String[] args) {
        Person itai = new Person();
        itai.setFirstName("Itai");
        itai.setLastName("Agmon");
        itai.setId(121212);
    }

}
