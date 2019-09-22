import org.junit.jupiter.api.*;

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
        assert(recipeBook.getRecipes().length == RecipeBook.NUM_RECIPES);
    }

    /**
     * The recipe book should allows for adding/editing/etc entries,
     * in the event that the recipe book is not null.
     */
    @Test
    public void checkRecipeBookNotNull() {
        assert(recipeBook.getRecipes() != null);
    }

    /**
     * Should not be able to add a null recipe to a recipe book.
     */
    @Test
    public void addNullRecipe() {
        boolean isAdded = recipeBook.addRecipe(null);
        assert(!isAdded);
    }

    /**
     * Empty recipe books should allow any non-null recipe
     * to be added.
     */
    @Test
    public void addRecipeToEmptyBook() {
        Recipe r = new Recipe();
        boolean isAdded = recipeBook.addRecipe(r);
        assert(isAdded);
        assert(recipeBook.getRecipes()[0].equals(r));
    }



    @AfterEach
    public void tearDown() {

    }

    @AfterAll
    public static void tearDownClass() {

    }
}
