import exceptions.RecipeException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {
    @BeforeAll
    public static void setupClass() {

    }

    @BeforeEach
    public void setup() {

    }

    @Test
    public void equalsWorksWithExactSameRecipes() {
        Recipe r = new Recipe();
        assertTrue(r.equals(r));
    }

    @Test
    public void ingredientsEqualsWorksWithSimilarRecipes() {
        Recipe r = new Recipe();
        r.setName("Ham");

        Recipe r2 = new Recipe();
        r2.setName("Ham");
        try {
            r.setPrice("1");
            r.setAmtMilk("1");
            r.setAmtSugar("1");
            r.setAmtCoffee("2");
            r.setAmtChocolate("11");

            r2.setPrice("1");
            r2.setAmtMilk("1");
            r2.setAmtSugar("1");
            r2.setAmtCoffee("2");
            r2.setAmtChocolate("11");
        } catch (RecipeException e) {
            e.printStackTrace();
        }

        assertTrue(r.ingredientsEquals(r2));
    }

    @Test
    public void ingredientsEqualsAreNotEqual() {
        Recipe r = new Recipe();
        Recipe r2 = new Recipe();
        r.setName("Ham");
        r2.setName("Ham2");
        try {
            r2.setAmtSugar("2");
        } catch (RecipeException e) {
            e.printStackTrace();
        }
        assertFalse(r.ingredientsEquals(r2));
    }

    @Test
    public void equalsWhenNamesAreSimilar(){
        Recipe r = new Recipe();
        Recipe r2 = new Recipe();
        r.setName("Ham");
        r2.setName("Ham");

        try {
            r.setAmtCoffee("1");
        } catch (RecipeException e) {
            e.printStackTrace();
        }

        assertTrue(r.equals(r2));
    }

    @Test
    public void equalsWhenNamesAreDiffernet(){
        Recipe r = new Recipe();
        Recipe r2 = new Recipe();
        r.setName("Ham");
        r2.setName("Ham2");

        assertFalse(r.equals(r2));
    }


    @AfterEach
    public void tearDown() {

    }

    @AfterAll
    public static void tearDownClass() {

    }
}
