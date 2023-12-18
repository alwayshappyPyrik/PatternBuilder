package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.OptionalInt;

@Getter
@EqualsAndHashCode
public class Person {

    private final String firstName;
    private final String lastName;
    private OptionalInt age;
    private String address;

    public Person(String firstName, String lastName, OptionalInt age, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    @SneakyThrows(IllegalAccessException.class)
    public void setAge(OptionalInt age, Person person) {
        OptionalInt oldAge = person.getAge();
        if (hasAge(age)) {
            int newAge = age.getAsInt();
            if (age.isPresent() && oldAge.isPresent() && oldAge.getAsInt() + 1 == newAge) {
                happyBirthday();
            } else {
                throw new IllegalAccessException("Вы хотите указать недопустимый возраст");
            }
        }
    }

    @SneakyThrows(IllegalAccessException.class)
    public void setAddress(String address) {
        if (address == null) {
            throw new IllegalAccessException("Вы хотите указать недопустимое место жительство");
        } else if (address.isBlank()) {
            throw new IllegalAccessException("Вы хотите указать недопустимое место жительство");
        } else {
            this.address = address;
        }
    }

    void happyBirthday() {
        age = OptionalInt.of(age.getAsInt() + 1);
        System.out.println("C " + age.getAsInt() + " Днем Рождения тебя!!!");
    }

    boolean hasAge(OptionalInt age) {
        return age != null ? age.isPresent() : false;
    }

    boolean hasAddress(String address) {
        return address != null ? true : false;
    }

    PersonBuilder newChildBuilder() {
        OptionalInt sonAge = OptionalInt.empty();
        if (age.isPresent() && !age.isEmpty()) {
            sonAge = OptionalInt.of(age.getAsInt() - 25);
            return new PersonBuilder(lastName, sonAge, address);
        } else {
            return new PersonBuilder(lastName, sonAge, address);
        }
    }

    @Override
    public String toString() {
        Person person = new Person(firstName, lastName, age, address);
        String ageToPrint;
        String addressToPrint;
        if (person.hasAge(age)) {
            ageToPrint = String.valueOf(age.getAsInt());
        } else {
            ageToPrint = "Возраст неизвестен";
        }
        if (person.hasAddress(address) && person.getAddress() == null) {
            addressToPrint = person.getAddress();
        } else {
            addressToPrint = "Адрес неизвестен";
        }
        StringBuilder personReady = new StringBuilder().append("Person{").append("firstName='").append(firstName).append('\'').append(", lastName='").append(lastName).append('\'').append(", age='").append(ageToPrint).append('\'')
                .append(", address='").append(addressToPrint).append('\'').append("}");
        return personReady.toString();
    }
}
