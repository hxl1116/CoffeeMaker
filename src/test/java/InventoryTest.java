import exceptions.InventoryException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryTest {
    private static final String EXPECTED_TO_STRING = "Coffee: 15\r\nMilk: 15\r\nSugar: 15\r\nChocolate: 15\r\n";
    private static final String MILK_EXCEPTION_MSG = "Units of milk must be a positive integer";
    private static final String SUGAR_EXCEPTION_MSG = "Units of sugar must be a positive integer";
    private static final int DEFAULT_RESOURCE_AMT = 15;
    private static final int NO_RESOURCE_AMT = 0;
    private static final int LOW_RESOURCE_AMT = 5;
    private static final int HIGH_RESOURCE_AMT = 20;
    private static final String MILK_AMT_INT = "5";
    private static final String MILK_AMT_FLOAT = "5.5";
    private static final String SUGAR_AMT_INT = "5";
    private static final String SUGAR_AMT_FLOAT = "5.5";

    private int default_amount = 15;

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
        inventory.setSugar(HIGH_RESOURCE_AMT);

        assertEquals(HIGH_RESOURCE_AMT, inventory.getSugar(), "Should be " + HIGH_RESOURCE_AMT);
    }

    @Test
    void testSetSugarWithNegativeInteger() {
        inventory.setSugar(-DEFAULT_RESOURCE_AMT);

        assertEquals(DEFAULT_RESOURCE_AMT, inventory.getSugar(), "Should be " + DEFAULT_RESOURCE_AMT);

//        Exception exception = assertThrows(InventoryException.class, () -> {
//            inventory.setSugar(Integer.parseInt("-" + SUGAR_AMT_INT));
//        });
//
//        assertEquals(SUGAR_EXCEPTION_MSG, exception.getMessage());
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
            inventory.addSugar(String.valueOf(-LOW_RESOURCE_AMT));
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

    @Test
    public void testInventory() {
        assertNotNull(inventory, "Inventory not created.");
    }

    @Test
    public void testGetChocolate() {
        assertEquals(default_amount, inventory.getChocolate(), "The inventory did not start with the default amount of chocolate.");
    }

    @Test
    public void testGetChocolateNo() {
        assertNotSame(default_amount + 1, inventory.getChocolate(), "The inventory start with the default amount of chocolate.");
    }

    @Test
    public void testSetPositiveChocolate() {
        int new_amount = 10;
        inventory.setChocolate(new_amount);
        int testAmount = 0;

        assertEquals(new_amount, testAmount, "The amount of chocolate in the inventory is incorrect.");
    }

    @Test
    public void testSetZeroChocolate() {
        int new_amount = 0;
        inventory.setChocolate(new_amount);
        int testAmount = 0;

        assertEquals(new_amount, testAmount, "The amount of chocolate in the inventory is incorrect.");
    }

    @Test
    public void testSetNegativeChocolate() {
        int testAmount = 0;
        int negative_amount = -1;
        inventory.setChocolate(negative_amount);

        assertEquals(default_amount, testAmount, "Amount of chocolate was set while trying to add a negative amount.");
    }

    @Test
    public void testAddPositiveChocolate() {
        int add = 10;
        inventory.setChocolate(default_amount);
        assertEquals(default_amount + add, inventory.getChocolate(), "Amount of chocolate not increased.");
    }

    @Test
    public void testAddNegativeChocolate() {
        inventory.setChocolate(default_amount);
        boolean NegFlag = false;
        String neg = "-1";
        try {
            inventory.addChocolate(neg);
        } catch (InventoryException e) {
            NegFlag = true;
        }

        assertTrue(NegFlag, "Exception not thrown for adding a negative amount of chocolate");
    }

    @Test
    public void testAddNotANumberChocolate() {
        inventory.setChocolate(default_amount);
        boolean NanFlag = false;
        String neg = "a";
        try {
            inventory.addChocolate(neg);
        } catch (InventoryException e) {
            NanFlag = true;
        }
        assertTrue(NanFlag, "Exception not thrown for adding a letters worth of chocolate.");
    }

    @Test
    public void testGetCoffee() {
        assertEquals(default_amount, inventory.getCoffee(), "Incorrect default amount of coffee.");
    }

    @Test
    public void testGetCoffeeNo() {
        assertNotSame(default_amount + 1, inventory.getCoffee(), "The inventory start with the default amount of Coffee.");
    }

    @Test
    public void testSetPositiveCoffee() {
        int new_amount = 10;
        inventory.setCoffee(new_amount);
        int testAmount = 0;

        assertEquals(new_amount, testAmount, "Amount of coffee not set correctly.");
    }

    @Test
    public void testSetZeroCoffee() {
        int new_amount = 0;
        inventory.setCoffee(new_amount);
        int testAmount = 0;

        assertEquals(new_amount, testAmount, "Amount of coffee not set correctly.");
    }

    @Test
    public void testSetNegativeCoffee() {
        int newAmount = -1;
        inventory.setCoffee(newAmount);
        int testAmount = 0;

        assertEquals(default_amount, testAmount, "Negative amount of coffee set.");
    }

    @Test
    public void testAddPositiveCoffee() {
        int add = 5;
        inventory.setCoffee(default_amount);
        try {
            inventory.addCoffee(String.valueOf(add));
        } catch (InventoryException e) {
            //TODO Auto created Catch
        }
        assertEquals(default_amount + add, inventory.getCoffee(), "Coffee amount not correct");
    }

    @Test
    public void testAddCoffeeNonInt() {
        boolean LetterFlag = false; //Checks if letter tries to be added
        inventory.setCoffee(default_amount);
        try {
            inventory.addCoffee("a");
        } catch (InventoryException e) {
            LetterFlag = true;
        }
        assertTrue(LetterFlag, "Exception not thrown for adding a letter instead of a positive number.");
    }

    @Test
    public void testAddCoffeeNegative() {
        int add = -20;
        boolean NegativeFlag = false; //Checks if negative number tries to be added
        inventory.setCoffee(default_amount);
        try {
            inventory.addCoffee(String.valueOf(add));
        } catch (InventoryException e) {
            NegativeFlag = true;
        }
        assertTrue(NegativeFlag, "Exception not thrown for adding a negative number instead of a positive number.");
    }

    @Test
    public void testGetMilk() {
        assertEquals(default_amount, inventory.getMilk(), "Wrong amount of Milk.");
    }

    @Test
    public void testGetMilkNo() {
        assertNotSame(default_amount + 1, inventory.getMilk(), "The inventory start with the default amount of Milk.");
    }

    @Test
    public void testSetMilkPos() {
        int pos_amount = 10;
        inventory.setMilk(pos_amount);
        int testAmount = 0;

        assertEquals(pos_amount, testAmount, "Amount of coffee not set correctly.");
    }

    @Test
    public void testSetMilkNeg() {
        int neg_amount = -10;
        inventory.setMilk(neg_amount);
        int testAmount = 0;

        assertNotSame(neg_amount, testAmount, "Negative amount of coffee set.");
    }

    @AfterEach
    void tearDown() {
        inventory = null;
    }

    @AfterAll
    static void tearDownClass() {

    }


}
