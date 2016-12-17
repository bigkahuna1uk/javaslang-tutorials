package com.usul.training.javaslang;

import com.google.common.base.MoreObjects;
import javaslang.collection.CharSeq;
import javaslang.collection.List;
import javaslang.control.Validation;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * ValidationTest
 *
 * @author bigkahuna
 * @since 12/11/2016
 */
public class ValidationTest {

    private PersonValidator personValidator;

    @BeforeEach
    public void setup()
    {
        personValidator = new PersonValidator();
    }

    @Test
    public void invalidPerson()
    {
        Validation<List<String>, Person> invalid = personValidator.validatePerson(null, -1);

        then(invalid.isInvalid()).as("Should be invalid as Person has invalid parameters").isTrue();
        then(invalid.isEmpty()).as("No person as invalid parameters").isTrue();
        then(invalid.getOption()).isEmpty();
        then(invalid.getError()).isNotEmpty();
        then(invalid.getError()).as("an error each for invalid name and age").hasSize(1 + 1);
    }

    @Test
    public void validPerson()
    {
        Validation<List<String>, Person> valid = personValidator.validatePerson("Chris", 19);

        then(valid.isInvalid()).as("Should be valid as Person has valid parameters").isFalse();
        then(valid.isEmpty()).as("A person as all valid parameters").isFalse();
        then(valid.getOption()).isNotEmpty();
        then(valid.get()).isNotNull().isInstanceOf(Person.class);
    }


    private static class PersonValidator {

        private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
        private static final int MIN_AGE = 0;

        public Validation<List<String>, Person> validatePerson(String name, int age) {
            return Validation.combine(validateName(name), validateAge(age)).ap(Person::new);
        }

        private Validation<String, String> validateName(String name) {
            if(StringUtils.isBlank(name))
                return Validation.invalid("Name is unspecified");

            return CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "").transform(seq -> seq.isEmpty()
                    ? Validation.valid(name)
                    : Validation.invalid("Name contains invalid characters: '"
                    + seq.distinct().sorted() + "'"));
        }

        private Validation<String, Integer> validateAge(int age) {
            return age < MIN_AGE
                    ? Validation.invalid("Age must be greater than " + MIN_AGE)
                    : Validation.valid(age);
        }

    }

    private static class Person
    {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("name", name)
                    .add("age", age)
                    .toString();
        }
    }

}
