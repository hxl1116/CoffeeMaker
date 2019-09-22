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

        assert(!isAdded);

        // None of the recipes should have the name "new"
        // if they do, then that means the above recipe was added.
        for(Recipe r : recipeBook.getRecipes()){
            assert(r.getName().equals("new") == false);
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
        assert(!isAdded);
    }

    /**
     * Should return element from non-empty list of recipes.
     */
    @Test
    public void getRecipeReturnsElements() {
        Recipe r = new Recipe();
        r.setName("alex");
        recipeBook.addRecipe(r);

        assert(recipeBook.getRecipes()[0].getName().equals("alex"));
    }

    /**
     * Should remove a recipe from the recipe book and returns its name.
     */
    @Test
    public void deleteRecipeReturnsName() {
        Recipe r = new Recipe();
        r.setName("alex");
        recipeBook.addRecipe(r);

        assert(recipeBook.deleteRecipe(0).equals("alex"));
        assert(recipeBook.getRecipes()[0] == null);
    }


    @AfterEach
    public void tearDown() {

    }

    @AfterAll
    public static void tearDownClass() {

    }
}
