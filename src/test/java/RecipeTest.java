import exceptions.RecipeException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {
    Recipe recp;

    @BeforeAll
    public static void setupClass() {

    }

    @BeforeEach
    public void setup() {
        this.recp = new Recipe();
    }

    @Test
    public void testConstructorChocolate() {
        assertEquals(recp.getAmtChocolate(), 0, "amtChocolate initialized incorrectly");
    }

    @Test
    public void testConstructorMilk() {
        assertEquals(recp.getAmtMilk(), 0, "amtMilk initialized incorrectly");
    }

    @Test
    public void testConstructorSugar() {
        assertEquals(recp.getAmtSugar(), 0, "amtSugar initialized incorrectly");
    }

    @Test
    public void testConstructorCoffee() {
        assertEquals(recp.getAmtCoffee(), 0, "amtCoffee initialized incorrectly");
    }

    @Test
    public void testConstructorName() {
        assertEquals(recp.getName(), "", "amtChocolate initialized incorrectly");
    }

    @Test
    public void testConctructorPrice() {
        assertEquals(recp.getPrice(), 0, "amtChocolate initialized incorrectly");
    }

    /*
    Chocolate
     */
    @Test
    public void testSetAmtChocolatecCatchInt() {
        assertThrows(RecipeException.class, () -> {
            recp.setAmtChocolate("5a");
        });
    }

    @Test
    public void testSetAmtChocolateCatchnNegativeInt() {
        assertThrows(RecipeException.class, () -> {
            recp.setAmtChocolate("-5");
        });
    }

    @Test
    public void testSetAmtChocolateValid() {
        try {
            recp.setAmtChocolate("5");
        } catch (Exception e) {
        }
        assertEquals(5, recp.getAmtChocolate());
    }

    @Test
    public void testGetAmtChocolateValid() {
        try {
            recp.setAmtChocolate("5");
        } catch (Exception e) {
        }
        assertEquals(5, recp.getAmtChocolate());
    }
    /*
    Coffee
     */

    @Test
    public void testSetAmtCoffeeCatchInt() {
        assertThrows(RecipeException.class, () -> {
            recp.setAmtCoffee("5a");
        });
    }

    @Test
    public void testSetAmtCoffeeCatchNegativeInt() {
        assertThrows(RecipeException.class, () -> {
            recp.setAmtCoffee("-5");
        });
    }

    @Test
    public void testSetAmtCoffeeValid() {
        try {
            recp.setAmtCoffee("5");
        } catch (Exception e) {
        }
        assertEquals(5, recp.getAmtCoffee());
    }

    @Test
    public void testGetAmtCoffeeValid() {
        try {
            recp.setAmtCoffee("5");
        } catch (Exception e) {
        }
        assertEquals(5, recp.getAmtCoffee());
    }

    /*
    Milk
     */
    @Test
    public void testSetAmtMilkCatchInt() {
        assertThrows(RecipeException.class, () -> {
            recp.setAmtMilk("5a");
        });
    }

    @Test
    public void testSetAmtMilkCatchNegativeInt() {
        assertThrows(RecipeException.class, () -> {
            recp.setAmtMilk("-5");
        });
    }

    @Test
    public void testSetAmtMilkValid() {
        try {
            recp.setAmtMilk("5");
        } catch (Exception e) {
        }
        assertEquals(5, recp.getAmtMilk());
    }

    @Test
    public void testGetAmtMilkValid() {
        try {
            recp.setAmtMilk("5");
        } catch (Exception e) {
        }
        assertEquals(5, recp.getAmtMilk());
    }

    /*
    Sugar
     */
    @Test
    public void testSetAmtSugarCatchInt() {
        assertThrows(RecipeException.class, () -> {
            recp.setAmtSugar("5a");
        });
    }

    @Test
    public void testSetAmtSugarCatchNegativeInt() {
        assertThrows(RecipeException.class, () -> {
            recp.setAmtSugar("-5");
        });
    }

    @Test
    public void testSetAmtSugarValid() {
        try {
            recp.setAmtSugar("5");
        } catch (Exception e) {
        }
        assertEquals(5, recp.getAmtSugar());
    }

    @Test
    public void testGetAmtSugarValid() {
        try {
            recp.setAmtSugar("5");
        } catch (Exception e) {
        }
        assertEquals(5, recp.getAmtSugar());
    }

    /*
        name
     */
    @Test
    public void testSetName() {
        recp.setName("testName");
        assertEquals("testName", recp.getName());
    }

    @Test
    public void testGetName() {
        recp.setName("testName");
        assertEquals("testName", recp.getName());
    }

    /*
    price
     */
    @Test
    public void testSetPriceCatchInt() {
        assertThrows(RecipeException.class, () -> {
            recp.setPrice("5a");
        });
    }

    @Test
    public void testSetPriceCatchNegativeInt() {
        assertThrows(RecipeException.class, () -> {
            recp.setPrice("-5");
        });
    }

    @Test
    public void testSetPriceValid() {
        try {
            recp.setPrice("5");
        } catch (Exception e) {
        }
        assertEquals(5, recp.getPrice());
    }

    @Test
    public void testGetPriceValid() {
        try {
            recp.setPrice("5");
        } catch (Exception e) {
        }
        assertEquals(5, recp.getPrice());
    }

    @Test
    public void testHashWithName() {
        int actual = 31;
        assertEquals(actual, recp.hashCode());
    }

    @Test
    public void testHashWithNoName() {
        String name = "hello there";
        recp.setName(name);
        int actual = name.hashCode() + 31;
        assertEquals(actual, recp.hashCode());
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
    public void equalsWhenNamesAreSimilar() {
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
    public void equalsWhenNamesAreDiffernet() {
        Recipe r = new Recipe();
        Recipe r2 = new Recipe();
        r.setName("Ham");
        r2.setName("Ham2");

        assertFalse(r.equals(r2));
    }

    @Test
    public void equalsReturnsFalseWhenRecipeNull() {
        Recipe r = new Recipe();
        r.setName("Ham");

        assertFalse(r.equals(null));
    }

    @Test
    public void equalsReturnsFalseWhenRecipeIsDifferentObject() {
        Recipe r = new Recipe();
        r.setName("Ham");

        assertFalse(r.equals(new Integer(2)));
    }

    @Test
    public void ingredientsEqualsReturnsFalseWhenRecipeNull() {
        Recipe r = new Recipe();
        r.setName("Ham");

        assertFalse(r.ingredientsEquals(null));
    }

    @Test
    public void ingredientsEqualsReturnsFalseWhenRecipeIsDifferentObject() {
        Recipe r = new Recipe();
        r.setName("Ham");

        assertFalse(r.ingredientsEquals(new Integer(2)));
    }


    @AfterEach
    public void tearDown() {

    }

    @AfterAll
    public static void tearDownClass() {

    }
}
