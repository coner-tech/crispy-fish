package org.crispy_fish.domain.payload;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DriverTest {

    @Test
    public void whenEqualsBothDriverClassingNumberEqualsItShouldTrue() {
        Driver a = new Driver();
        Driver b = new Driver();
        String classing = "class";
        String number = "number";
        a.classing = classing;
        a.number = number;
        a.name = "irrelevant";
        a.car = "acceptable different";
        b.classing = classing;
        b.number = number;
        b.name = "different is ok";
        b.car = "different car";

        boolean actual = a.equals(b);

        assertThat(actual).isTrue();
    }

    @Test
    public void whenEqualsBothDriverClassingNumberPartiallyDifferentItShouldFalse() {
        Driver a = new Driver();
        Driver b = new Driver();
        a.classing = "STR";
        a.number = "8";
        b.classing = "STR";
        b.number = "08";

        boolean actual = a.equals(b);

        assertThat(actual).isFalse();
    }

    @Test
    public void whenHashCodeItShouldNotChangeAfterAssigningName() {
        Driver driver = new Driver();
        driver.classing = "classing";
        driver.number = "01";

        int priorHashCode = driver.hashCode();
        driver.name = "name";
        int afterHashCode = driver.hashCode();

        assertThat(priorHashCode).isEqualTo(afterHashCode);
    }

    @Test
    public void whenHashCodeItShouldNotChangeAfterAssigningCar() {
        Driver driver = new Driver();
        driver.classing = "classing";
        driver.number = "02";

        int priorHashCode = driver.hashCode();
        driver.car = "car";
        int afterHashCode = driver.hashCode();

        assertThat(priorHashCode).isEqualTo(afterHashCode);
    }

    @Test
    public void whenHashCodeItShouldChangeAfterNumberChanges() {
        Driver driver = new Driver();
        driver.classing = "classing";
        driver.number = "02";

        int priorHashCode = driver.hashCode();
        driver.number = "2";
        int afterHashCode = driver.hashCode();

        assertThat(priorHashCode).isNotEqualTo(afterHashCode);
    }

    @Test
    public void whenHashCodeItShouldChangeAfterClassingChanges() {
        Driver driver = new Driver();
        driver.classing = "class";
        driver.number = "000";

        int priorHashCode = driver.hashCode();
        driver.classing = "classing";
        int afterHashCode = driver.hashCode();

        assertThat(priorHashCode).isNotEqualTo(afterHashCode);
    }

}