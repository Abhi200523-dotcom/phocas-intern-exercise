package app.entity;

/**
 * Represents a person with personal details including first name, last name,
 * country of residence, and age.
 *
 * This record is an immutable data structure.
 *
 * Fields:
 * - firstName: The first name of the person.
 * - lastName: The last name of the person.
 * - country: The country where the person is from.
 * - age: The age of the person.
 */
public record Person(String firstName, String lastName,String country,Integer age){
}
