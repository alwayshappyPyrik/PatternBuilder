package org.example;

import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {
        Person mom = new PersonBuilder()
                .setFirstName("Марина")
                .setLastName("Пырикова")
                .setAge(OptionalInt.of(30))
                .setAddress("Москва")
                .build();
        System.out.println(mom);

        Person son = mom.newChildBuilder()
                .setFirstName("Ярослав")
                .build();
        System.out.println("У " + mom + " есть сын, " + son);

        mom.setAge(OptionalInt.of(31), mom);
        mom.setAddress(null);

    }
}