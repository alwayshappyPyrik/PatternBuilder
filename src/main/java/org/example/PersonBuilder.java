package org.example;

import java.util.OptionalInt;

public class PersonBuilder {

    private String firstName;
    private String lastName;
    private OptionalInt age;
    private String address;

    public PersonBuilder() {
    }

    public PersonBuilder(String lastName, OptionalInt age, String address) {
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    public PersonBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder setAge(OptionalInt age) {
        if (age.getAsInt() < 0) {
            throw new IllegalStateException("Недопустимый возраст");
        }
        this.age = age;
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    Person build() {
        if (firstName.isBlank()) {
            throw new IllegalStateException("Нужно указать имя");
        } else if (lastName.isBlank()) {
            throw new IllegalStateException("Нужно указать фамилию");
        }
        return new Person(firstName, lastName, age, address);
    }
}
