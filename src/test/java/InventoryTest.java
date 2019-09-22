import exceptions.InventoryException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InventoryTest {
    private static final int DEFAULT_RESOURCE_AMT = 15;
    private static final int LOW_RESOURCE_AMT = 5;
    private static final int HIGH_RESOURCE_AMT = 20;
    private static final String MILK_EXCEPTION_MSG = "Units of milk must be a positive integer";
    private static final String SUGAR_EXCEPTION_MSG = "Units of sugar must be a positive integer";
    private static final String COFFEE_AMT_INT = "5";
    private static final String MILK_AMT_INT = "5";
    private static final String MILK_AMT_FLOAT = "5.5";
    private static final String SUGAR_AMT_INT = "5";
    private static final String SUGAR_AMT_FLOAT = "5.5";
    private static final String CHOCOLATE_AMT_INT = "5";

    private static Inventory inventory;

    @Mock
    private static Recipe recipe;

    @BeforeAll
    static void setupClass() {

    }

    @BeforeEach
    void setup() {
        inventory = new Inventory();
        recipe = mock(Recipe.class);
    }

    @Test
    void testAddMilkWithPositiveInteger() {
        try {
            inventory.addMilk(MILK_AMT_INT);
        } catch (InventoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddMilkWithNegativeInteger() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addMilk("-" + MILK_AMT_INT);
        });

        assertEquals(MILK_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testAddMilkWithPositiveFloat() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addMilk(MILK_AMT_FLOAT);
        });

        assertEquals(MILK_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testAddMilkWithNegativeFloat() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addMilk("-" + MILK_AMT_FLOAT);
        });

        assertEquals(MILK_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testAddMilkWithNull() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addMilk(null);
        });

        assertEquals(MILK_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testAddMilkOnMultipleThreads() {

    }

    @Test
    void testGetSugar() {
        assertEquals(DEFAULT_RESOURCE_AMT, inventory.getSugar(), "Should be " + DEFAULT_RESOURCE_AMT);
    }

    @Test
    void testSetSugarWithPositiveInteger() {
        inventory.setSugar(Integer.parseInt(SUGAR_AMT_INT));
    }

    @Test
    void testSetSugarWithNegativeInteger() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.setSugar(Integer.parseInt("-" + SUGAR_AMT_INT));
        });

        assertEquals(SUGAR_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testSetSugarOnMultipleThreads() {

    }

    @Test
    void testAddSugarWithPositiveInteger() {
        try {
            inventory.addSugar(SUGAR_AMT_INT);
        } catch (InventoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddSugarWithNegativeInteger() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addSugar("-" + SUGAR_AMT_INT);
        });

        assertEquals(SUGAR_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testAddSugarWithPositiveFloat() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addSugar(SUGAR_AMT_FLOAT);
        });

        assertEquals(SUGAR_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testAddSugarWithNegativeFloat() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addSugar("-" + SUGAR_AMT_FLOAT);
        });

        assertEquals(SUGAR_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testAddSugarWithNull() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addSugar(null);
        });

        assertEquals(SUGAR_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testAddSugarOnMultipleThreads() {

    }

    @Test
    void testEnoughIngredientsSufficientAll() {
        when(recipe.getAmtCoffee()).thenReturn(DEFAULT_RESOURCE_AMT);
        when(recipe.getAmtMilk()).thenReturn(DEFAULT_RESOURCE_AMT);
        when(recipe.getAmtSugar()).thenReturn(DEFAULT_RESOURCE_AMT);
        when(recipe.getAmtChocolate()).thenReturn(DEFAULT_RESOURCE_AMT);

        assertTrue(inventory.enoughIngredients(recipe));
    }

    @Test
    void testEnoughIngredientsNotEnoughCoffee() {
        when(recipe.getAmtCoffee()).thenReturn(HIGH_RESOURCE_AMT);
        when(recipe.getAmtMilk()).thenReturn(LOW_RESOURCE_AMT);
        when(recipe.getAmtSugar()).thenReturn(LOW_RESOURCE_AMT);
        when(recipe.getAmtChocolate()).thenReturn(LOW_RESOURCE_AMT);

        assertFalse(inventory.enoughIngredients(recipe));
    }

    @Test
    void testEnoughIngredientsNotEnoughMilk() {
        when(recipe.getAmtCoffee()).thenReturn(LOW_RESOURCE_AMT);
        when(recipe.getAmtMilk()).thenReturn(HIGH_RESOURCE_AMT);
        when(recipe.getAmtSugar()).thenReturn(LOW_RESOURCE_AMT);
        when(recipe.getAmtChocolate()).thenReturn(LOW_RESOURCE_AMT);

        assertFalse(inventory.enoughIngredients(recipe));
    }

    @Test
    void testEnoughIngredientsNotEnoughSugar() {
        when(recipe.getAmtCoffee()).thenReturn(LOW_RESOURCE_AMT);
        when(recipe.getAmtMilk()).thenReturn(LOW_RESOURCE_AMT);
        when(recipe.getAmtSugar()).thenReturn(HIGH_RESOURCE_AMT);
        when(recipe.getAmtChocolate()).thenReturn(LOW_RESOURCE_AMT);

        assertFalse(inventory.enoughIngredients(recipe));
    }

    @Test
    void testEnoughIngredientsNotEnoughChocolate() {
        when(recipe.getAmtCoffee()).thenReturn(LOW_RESOURCE_AMT);
        when(recipe.getAmtMilk()).thenReturn(LOW_RESOURCE_AMT);
        when(recipe.getAmtSugar()).thenReturn(LOW_RESOURCE_AMT);
        when(recipe.getAmtChocolate()).thenReturn(HIGH_RESOURCE_AMT);

        assertFalse(inventory.enoughIngredients(recipe));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void testEnoughIngredientsWithNull() {
        when(recipe.getAmtCoffee()).thenReturn(LOW_RESOURCE_AMT);
        when(recipe.getAmtMilk()).thenReturn(HIGH_RESOURCE_AMT);
        when(recipe.getAmtSugar()).thenReturn(LOW_RESOURCE_AMT);
        when(recipe.getAmtChocolate()).thenReturn(LOW_RESOURCE_AMT);

        assertFalse(inventory.enoughIngredients(null));
    }

    @AfterEach
    void tearDown() {
        inventory = null;
    }

    @AfterAll
    static void tearDownClass() {

    }
}
