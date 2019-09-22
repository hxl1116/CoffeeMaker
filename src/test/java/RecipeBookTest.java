import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class RecipeBookTest {

    RecipeBook recipeBook;

    @BeforeAll
    public static void setupClass() {

    }

    @BeforeEach
    public void setup() {
        recipeBook = new RecipeBook();
    }

    /**
     * By exposing the number of recipes per recipe book,
     * the recipe book can be checked for a maximum amount of
     * recipes.
     */
    @Test
    public void recipeBookLengthIsFour() {
        assertEquals(recipeBook.getRecipes().length,RecipeBook.NUM_RECIPES,
                "The recipe book doesn't have the correct length of recipes.");
    }

    /**
     * The recipe book should allows for adding/editing/etc entries,
     * in the event that the recipe book is not null.
     */
    @Test
    public void checkRecipeBookNotNull() {
        assertNotNull(recipeBook.getRecipes(),"The recipe book is null.");
    }

    /**
     * Should not be able to add a null recipe to a recipe book.
     */
    @Test
    public void addNullRecipe() {
        boolean isAdded = recipeBook.addRecipe(null);
        assertFalse(isAdded,"Cannot add null recipe to recipe book.");
    }

    /**
     * Empty recipe books should allow any non-null recipe
     * to be added.
     */
    @Test
    public void addRecipeToEmptyBook() {
        Recipe r = mock(Recipe.class);
        boolean isAdded = recipeBook.addRecipe(r);
        assertTrue(isAdded,"Recipe " + r + " was not added.");
        assertEquals(recipeBook.getRecipes()[0],r,"The recipe was not added to the list.");
    }

    /**
     * Full recipe book should not allow for new recipes to
     * be added.
     */
    @Test
    public void addRecipeToFullBook() {
        for(int i = 0; i < RecipeBook.NUM_RECIPES; i++){
            Recipe r = new Recipe();
            r.setName(""+i);
            recipeBook.addRecipe(r);
        }

        Recipe newRecipe = new Recipe();
        newRecipe.setName("new");
        boolean isAdded = recipeBook.addRecipe(newRecipe);

        assertFalse(isAdded,"Recipe was added to full recipe book when it shouldn't have.");

        // None of the recipes should have the name "new"
        // if they do, then that means the above recipe was added.
        for(Recipe r : recipeBook.getRecipes()){
            assertFalse(r.getName().equals("new"),"Unwanted recipe was in recipe book:" + r);
        }
    }

    /**
     * Should not be able to add a duplicate recipe
     */
    @Test
    public void addDuplicateRecipeToBook() {
        Recipe r = new Recipe();
        recipeBook.addRecipe(r);
        boolean isAdded = recipeBook.addRecipe(r);
        assertFalse(isAdded,"Duplicated recipe added to recipe book.");
    }

    /**
     * Should return element from non-empty list of recipes.
     */
    @Test
    public void getRecipeReturnsElements() {
        Recipe r = new Recipe();
        r.setName("alex");
        recipeBook.addRecipe(r);

        assertEquals(recipeBook.getRecipes()[0].getName(),"alex",
                "Recipe book didn't contain proper recipe.");
    }

    /**
     * Should remove a recipe from the recipe book and returns its name.
     */
    @Test
    public void deleteRecipeReturnsName() {
        Recipe r = new Recipe();
        r.setName("alex");
        recipeBook.addRecipe(r);

        assertEquals(recipeBook.deleteRecipe(0),"alex",
                "Incorrect recipe was removed");
        assertNull(recipeBook.getRecipes()[0],"Recipe still exists after removal.");
    }

    /**
     * Should not be able to delete recipes when there are no recipes.
     */
    @Test
    public void deleteRecipeCantDeleteNothing() {
        assertNull(recipeBook.deleteRecipe(0),"Deleted recipe when" +
                "no recipes were present.");
    }


    /**
     * Should not be able to delete recipes that have an index outside the bounds of the
     * recipe book.
     */
    @Test
    public void deleteOutsideBookBounds() {
        recipeBook.addRecipe(new Recipe());
        assertNull(recipeBook.deleteRecipe(-1),
                "Recipe was deleted when [recipeToDelete] was below zero");
        assertNull(recipeBook.deleteRecipe(RecipeBook.NUM_RECIPES),
                "Recipe was delete when [recipeToDelete] was above recipe book bounds.");
    }

    /**
     * Should update an already existing recipe.
     */
    @Test
    public void editRecipeUpdatesExistingRecipe() {


        Recipe oldR = mock(Recipe.class);
        Recipe newR = mock(Recipe.class);

        oldR.setName("Ham");
        newR.setName("Meat");

        recipeBook.addRecipe(oldR);
        recipeBook.editRecipe(0,newR);
        assertEquals(recipeBook.getRecipes()[0].getName(),newR.getName());
    }

    /**
     * Should not allow for recipes to be edited if the bounds
     * are not within the arrays bounds.
     */
    @Test
    public void editRecipeShouldHandleBadArgs() {

        String name = recipeBook.editRecipe(-1,new Recipe());
        assertNull(name, "Cannot edit a recipe at the location: -1");

        String name2 = recipeBook.editRecipe(RecipeBook.NUM_RECIPES,new Recipe());
        assertNull(name, "Cannot edit a recipe at the location : " + RecipeBook.NUM_RECIPES);
    }

    /**
     * Should allow someone to edit a recipe even if it is null.
     */
    @Test
    public void editRecipeShouldAllowEditOfNull() {
        String name = recipeBook.editRecipe(0,new Recipe());
        assertNull(name, "Cannot edit a recipe at the location: -1");
    }

    /**
     * Should not be able to add a null recipe or invalid recipe.
     */
    @Test
    public void editRecipeShouldNotBeNull() {
        String name = recipeBook.editRecipe(0,null);
        assertNull(name, "Cannot edit a recipe at the location: -1");
    }

    @AfterEach
    public void tearDown() {

    }

    @AfterAll
    public static void tearDownClass() {

    }
}
