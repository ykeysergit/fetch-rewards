package fetch.rewards.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @InjectMocks
    EmailService objectUnderTest;

    /**
     * Given:
     * No emails provided
     *
     * When:
     * Call unique
     *
     * Then:
     * Return 0
     */
    @Test
    public void testUnique1(){
        // setup
        final Optional<Set<String>> emails1 = Optional.empty();
        final Optional<Set<String>> emails2 = Optional.of(Collections.emptySet());

        // act
        final long result1 = objectUnderTest.unique(emails1);
        final long result2 = objectUnderTest.unique(emails2);

        // assert
        assertThat(result1, is(0L));
        assertThat(result2, is(0L));
    }

    /**
     * Given:
     * test.email@gmail.com, test.email+spam@gmail.com and testemail@gmail.com
     *
     * When:
     * Call unique
     *
     * Then:
     * Return 1
     */
    @Test
    public void testUnique2(){
        // setup
        final Set<String> emails = new HashSet<>(Arrays.asList("test.email@gmail.com", "test.email+spam@gmail.com",
                "testemail@gmail.com"));

        // act
        final long result = objectUnderTest.unique(Optional.of(emails));

        // assert
        assertThat(result, is(1L));
    }

    /**
     * Given:
     * test.email@gmail.com and test.email@fetchrewards.com
     *
     * When:
     * Call unique
     *
     * Then:
     * Return 2
     */
    @Test
    public void testUnique3(){
        // setup
        final Set<String> emails = new HashSet<>(Arrays.asList("test.email@gmail.com", "test.email@fetchrewards.com"));

        // act
        final long result = objectUnderTest.unique(Optional.of(emails));

        // assert
        assertThat(result, is(2L));
    }
}
