import exceptions.InventoryException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryTest {
    private static final String EXPECTED_TO_STRING = "Coffee: 15\r\nMilk: 15\r\nSugar: 15\r\nChocolate: 15\r\n";
    private static final String COFFEE_EXCEPTION_MSG = "Units of coffee must be a positive integer";
    private static final String MILK_EXCEPTION_MSG = "Units of milk must be a positive integer";
    private static final String SUGAR_EXCEPTION_MSG = "Units of sugar must be a positive integer";
    private static final String CHOCOLATE_EXCEPTION_MSG = "Units of chocolate must be a positive integer";
    private static final int DEFAULT_RESOURCE_AMT = 15;
    private static final int NO_RESOURCE_AMT = 0;
    private static final int LOW_RESOURCE_AMT = 5;
    private static final int HIGH_RESOURCE_AMT = 20;
    private static final String ADD_AMT_INT = "5";
    private static final String ADD_AMT_FLOAT = "5.5";

    private static Inventory inventory;

    @Spy
    private static Inventory spyInventory;

    @Mock
    private static Recipe mockRecipe;

    @BeforeAll
    static void setupClass() {
        mockRecipe = mock(Recipe.class);
    }

    @BeforeEach
    void setup() {
        inventory = new Inventory();
        spyInventory = spy(inventory);
    }

    @Test
    @Disabled
    void testInventory() {
        assertNotNull(inventory, "Inventory not created.");
    }

    @Test
    void testGetCoffee() {
        assertEquals(DEFAULT_RESOURCE_AMT, inventory.getCoffee(), "Should be " + DEFAULT_RESOURCE_AMT);
    }

    @Test
    @Disabled
    void testGetCoffeeNo() {
        assertNotSame(DEFAULT_RESOURCE_AMT + 1, inventory.getCoffee(), "The inventory start with the default amount of Coffee.");
    }

    @Test
    void testSetCoffeeToZero() {
        inventory.setCoffee(NO_RESOURCE_AMT);

        assertEquals(NO_RESOURCE_AMT, inventory.getCoffee(), "Should be " + NO_RESOURCE_AMT);
    }

    @Test
    void testSetCoffeeWithPositiveInteger() {
        inventory.setCoffee(HIGH_RESOURCE_AMT);

        assertEquals(HIGH_RESOURCE_AMT, inventory.getCoffee(), "Should be " + HIGH_RESOURCE_AMT);
    }

    @Test
    void testSetCoffeeWithNegativeInteger() {
        inventory.setCoffee(-DEFAULT_RESOURCE_AMT);

        assertEquals(DEFAULT_RESOURCE_AMT, inventory.getCoffee());
    }

    @Test
    void testAddCoffeeWithPositiveInteger() {
        try {
            inventory.addCoffee(ADD_AMT_INT);
        } catch (InventoryException e) {
            // TODO: 9/22/2019 Modify default catch
            e.printStackTrace();
        }

        assertEquals(HIGH_RESOURCE_AMT, inventory.getCoffee(), "Should be " + HIGH_RESOURCE_AMT);
    }

    @Test
    void testAddCoffeeWithNegativeInteger() {
        Exception exception = assertThrows(InventoryException.class, () -> inventory.addCoffee("-" + ADD_AMT_INT));

        assertEquals(COFFEE_EXCEPTION_MSG, exception.getMessage(), "Should be " + COFFEE_EXCEPTION_MSG);
    }

    @Test
    void testAddCoffeeWithPositiveFloat() {
        Exception exception = assertThrows(InventoryException.class, () -> inventory.addCoffee(ADD_AMT_FLOAT));

        assertEquals(COFFEE_EXCEPTION_MSG, exception.getMessage(), "Should be " + COFFEE_EXCEPTION_MSG);
    }

    @Test
    void testAddCoffeeWithNegativeFloat() {
        Exception exception = assertThrows(InventoryException.class, () -> inventory.addCoffee("-" + ADD_AMT_FLOAT));

        assertEquals(COFFEE_EXCEPTION_MSG, exception.getMessage(), "Should be " + COFFEE_EXCEPTION_MSG);
    }

    @Test
    void testAddCoffeeWithNull() {
        Exception exception = assertThrows(InventoryException.class, () -> inventory.addCoffee(null));

        assertEquals(COFFEE_EXCEPTION_MSG, exception.getMessage(), "Should be " + COFFEE_EXCEPTION_MSG);
    }

    @Test
    void testGetMilk() {
        assertEquals(DEFAULT_RESOURCE_AMT, inventory.getMilk(), "Wrong amount of Milk.");
    }

    @Test
    @Disabled
    void testGetMilkNo() {
        assertNotSame(DEFAULT_RESOURCE_AMT + 1, inventory.getMilk(), "The inventory start with the default amount of Milk.");
    }

    @Test
    void testSetMilkToZero() {
        inventory.setMilk(NO_RESOURCE_AMT);

        assertEquals(NO_RESOURCE_AMT, inventory.getMilk(), "Should be " + NO_RESOURCE_AMT);
    }

    @Test
    void testSetMilkWithPositiveInteger() {
        inventory.setMilk(HIGH_RESOURCE_AMT);

        assertEquals(HIGH_RESOURCE_AMT, inventory.getMilk(), "Should be " + HIGH_RESOURCE_AMT);
    }

    @Test
    void testSetMilkWithNegativeInteger() {
        inventory.setMilk(-DEFAULT_RESOURCE_AMT);

        assertEquals(DEFAULT_RESOURCE_AMT, inventory.getMilk(), "Should be " + DEFAULT_RESOURCE_AMT);
    }

    @Test
    void testAddMilkWithPositiveInteger() {
        try {
            inventory.addMilk(ADD_AMT_INT);
        } catch (InventoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddMilkWithNegativeInteger() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addMilk("-" + ADD_AMT_INT);
        });

        assertEquals(MILK_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testAddMilkWithPositiveFloat() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addMilk(ADD_AMT_FLOAT);
        });

        assertEquals(MILK_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testAddMilkWithNegativeFloat() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addMilk("-" + ADD_AMT_FLOAT);
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
    @Disabled
    void testAddMilkOnMultipleThreads() {

    }

    @Test
    void testGetSugar() {
        assertEquals(DEFAULT_RESOURCE_AMT, inventory.getSugar(), "Should be " + DEFAULT_RESOURCE_AMT);
    }

    @Test
    void testSetSugarToZero() {
        inventory.setSugar(NO_RESOURCE_AMT);

        assertEquals(NO_RESOURCE_AMT, inventory.getSugar(), "Should be " + NO_RESOURCE_AMT);
    }

    @Test
    void testSetSugarWithPositiveInteger() {
        inventory.setSugar(HIGH_RESOURCE_AMT);

        assertEquals(HIGH_RESOURCE_AMT, inventory.getSugar(), "Should be " + HIGH_RESOURCE_AMT);
    }

    @Test
    void testSetSugarWithNegativeInteger() {
        inventory.setSugar(-DEFAULT_RESOURCE_AMT);

        assertEquals(DEFAULT_RESOURCE_AMT, inventory.getSugar(), "Should be " + DEFAULT_RESOURCE_AMT);
    }

    @Test
    @Disabled
    void testSetSugarOnMultipleThreads() {

    }

    @Test
    void testAddSugarWithPositiveInteger() {
        try {
            inventory.addSugar(ADD_AMT_INT);
        } catch (InventoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddSugarWithNegativeInteger() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addSugar(String.valueOf(-LOW_RESOURCE_AMT));
        });

        assertEquals(SUGAR_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testAddSugarWithPositiveFloat() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addSugar(ADD_AMT_FLOAT);
        });

        assertEquals(SUGAR_EXCEPTION_MSG, exception.getMessage());
    }

    @Test
    void testAddSugarWithNegativeFloat() {
        Exception exception = assertThrows(InventoryException.class, () -> {
            inventory.addSugar("-" + ADD_AMT_FLOAT);
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
    @Disabled
    void testAddSugarOnMultipleThreads() {

    }

    @Test
    void testGetChocolate() {
        assertEquals(DEFAULT_RESOURCE_AMT, inventory.getChocolate(), "Should be " + DEFAULT_RESOURCE_AMT);
    }

    @Test
    @Disabled
    void testGetChocolateNo() {
        assertNotSame(DEFAULT_RESOURCE_AMT + 1, inventory.getChocolate(), "The inventory start with the default amount of chocolate.");
    }

    @Test
    void testSetChocolateToZero() {
        inventory.setChocolate(NO_RESOURCE_AMT);

        assertEquals(NO_RESOURCE_AMT, inventory.getChocolate(), "Should be " + NO_RESOURCE_AMT);
    }

    @Test
    void testSetChocolateWithPositiveInteger() {
        inventory.setChocolate(HIGH_RESOURCE_AMT);

        assertEquals(HIGH_RESOURCE_AMT, inventory.getChocolate(), "Should be " + HIGH_RESOURCE_AMT);
    }

    @Test
    void testSetChocolateWithNegativeInteger() {
        inventory.setChocolate(-DEFAULT_RESOURCE_AMT);

        assertEquals(DEFAULT_RESOURCE_AMT, inventory.getChocolate(), "Should be " + DEFAULT_RESOURCE_AMT);
    }

    @Test
    void testAddChocolateWithPositiveInteger() {
        try {
            inventory.addChocolate(ADD_AMT_INT);
        } catch (InventoryException e) {
            // TODO: 9/22/2019 Modify default catch
            e.printStackTrace();
        }

        assertEquals(HIGH_RESOURCE_AMT, inventory.getChocolate(), "Should be " + HIGH_RESOURCE_AMT);
    }

    @Test
    void testAddChocolateWithNegativeInteger() {
        Exception exception = assertThrows(InventoryException.class, () -> inventory.addChocolate("-" + ADD_AMT_INT));

        assertEquals(CHOCOLATE_EXCEPTION_MSG, exception.getMessage(), "Should be " + CHOCOLATE_EXCEPTION_MSG);
    }

    @Test
    void testAddChocolateWithPositiveFloat() {
        Exception exception = assertThrows(InventoryException.class, () -> inventory.addChocolate(ADD_AMT_FLOAT));

        assertEquals(CHOCOLATE_EXCEPTION_MSG, exception.getMessage(), "Should be " + CHOCOLATE_EXCEPTION_MSG);
    }

    @Test
    void testAddChocolateWithNegativeFloat() {
        Exception exception = assertThrows(InventoryException.class, () -> inventory.addChocolate("-" + ADD_AMT_FLOAT));

        assertEquals(CHOCOLATE_EXCEPTION_MSG, exception.getMessage(), "Should be " + CHOCOLATE_EXCEPTION_MSG);
    }

    @Test
    void testAddChocolateWithNull() {
        Exception exception = assertThrows(InventoryException.class, () -> inventory.addChocolate(null));

        assertEquals(CHOCOLATE_EXCEPTION_MSG, exception.getMessage(), "Should be " + CHOCOLATE_EXCEPTION_MSG);
    }

    @Test
    void testEnoughIngredientsSufficientResources() {
        when(mockRecipe.getAmtCoffee()).thenReturn(DEFAULT_RESOURCE_AMT);
        when(mockRecipe.getAmtMilk()).thenReturn(DEFAULT_RESOURCE_AMT);
        when(mockRecipe.getAmtSugar()).thenReturn(DEFAULT_RESOURCE_AMT);
        when(mockRecipe.getAmtChocolate()).thenReturn(DEFAULT_RESOURCE_AMT);

        assertTrue(inventory.enoughIngredients(mockRecipe));
    }

    @Test
    void testEnoughIngredientsPlentyResources() {
        when(mockRecipe.getAmtCoffee()).thenReturn(LOW_RESOURCE_AMT);
        when(mockRecipe.getAmtMilk()).thenReturn(LOW_RESOURCE_AMT);
        when(mockRecipe.getAmtSugar()).thenReturn(LOW_RESOURCE_AMT);
        when(mockRecipe.getAmtChocolate()).thenReturn(LOW_RESOURCE_AMT);

        assertTrue(inventory.enoughIngredients(mockRecipe));
    }

    @Test
    void testEnoughIngredientsNotEnoughCoffee() {
        when(mockRecipe.getAmtCoffee()).thenReturn(HIGH_RESOURCE_AMT);
        when(mockRecipe.getAmtMilk()).thenReturn(LOW_RESOURCE_AMT);
        when(mockRecipe.getAmtSugar()).thenReturn(LOW_RESOURCE_AMT);
        when(mockRecipe.getAmtChocolate()).thenReturn(LOW_RESOURCE_AMT);

        assertFalse(inventory.enoughIngredients(mockRecipe));
    }

    @Test
    void testEnoughIngredientsNotEnoughMilk() {
        when(mockRecipe.getAmtCoffee()).thenReturn(LOW_RESOURCE_AMT);
        when(mockRecipe.getAmtMilk()).thenReturn(HIGH_RESOURCE_AMT);
        when(mockRecipe.getAmtSugar()).thenReturn(LOW_RESOURCE_AMT);
        when(mockRecipe.getAmtChocolate()).thenReturn(LOW_RESOURCE_AMT);

        assertFalse(inventory.enoughIngredients(mockRecipe));
    }

    @Test
    void testEnoughIngredientsNotEnoughSugar() {
        when(mockRecipe.getAmtCoffee()).thenReturn(LOW_RESOURCE_AMT);
        when(mockRecipe.getAmtMilk()).thenReturn(LOW_RESOURCE_AMT);
        when(mockRecipe.getAmtSugar()).thenReturn(HIGH_RESOURCE_AMT);
        when(mockRecipe.getAmtChocolate()).thenReturn(LOW_RESOURCE_AMT);

        assertFalse(inventory.enoughIngredients(mockRecipe));
    }

    @Test
    void testEnoughIngredientsNotEnoughChocolate() {
        when(mockRecipe.getAmtCoffee()).thenReturn(LOW_RESOURCE_AMT);
        when(mockRecipe.getAmtMilk()).thenReturn(LOW_RESOURCE_AMT);
        when(mockRecipe.getAmtSugar()).thenReturn(LOW_RESOURCE_AMT);
        when(mockRecipe.getAmtChocolate()).thenReturn(HIGH_RESOURCE_AMT);

        assertFalse(inventory.enoughIngredients(mockRecipe));
    }

    @Test
    void testEnoughIngredientsWithNull() {
        assertThrows(NullPointerException.class, () -> inventory.enoughIngredients(null));
    }

    @Test
    @Disabled
    void testEnoughIngredientsOnMultipleThreads() {

    }

    @Test
    void testUseIngredientsSufficientResources() {
        doReturn(true).when(spyInventory).enoughIngredients(mockRecipe);
        when(mockRecipe.getAmtCoffee()).thenReturn(DEFAULT_RESOURCE_AMT);
        when(mockRecipe.getAmtMilk()).thenReturn(DEFAULT_RESOURCE_AMT);
        when(mockRecipe.getAmtSugar()).thenReturn(DEFAULT_RESOURCE_AMT);
        when(mockRecipe.getAmtChocolate()).thenReturn(DEFAULT_RESOURCE_AMT);

        assertTrue(spyInventory.useIngredients(mockRecipe));
        assertAll(
                "resource decrements",
                () -> assertEquals(NO_RESOURCE_AMT, inventory.getCoffee()),
                () -> assertEquals(NO_RESOURCE_AMT, inventory.getMilk()),
                () -> assertEquals(NO_RESOURCE_AMT, inventory.getSugar()),
                () -> assertEquals(NO_RESOURCE_AMT, inventory.getChocolate())
        );

        verify(spyInventory).enoughIngredients(mockRecipe);
    }

    @Test
    void testUseIngredientsNotEnoughResources() {
        doReturn(false).when(spyInventory).enoughIngredients(mockRecipe);

        assertFalse(spyInventory.useIngredients(mockRecipe));

        verify(spyInventory).enoughIngredients(mockRecipe);
        verifyZeroInteractions(mockRecipe);
    }

    @Test
    void testUseIngredientsWithNull() {
        assertThrows(NullPointerException.class, () -> inventory.useIngredients(null));
    }

    @Test
    void testToString() {
        assertEquals(EXPECTED_TO_STRING, inventory.toString(), String.format("Should be: %s", EXPECTED_TO_STRING));
    }

    @AfterEach
    void tearDown() {
        inventory = null;
        reset(spyInventory, mockRecipe);
    }

    @AfterAll
    static void tearDownClass() {
        mockRecipe = null;
    }
}
