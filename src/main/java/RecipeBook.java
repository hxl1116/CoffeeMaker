public class RecipeBook {

    /**
     * Array of recipes in coffee maker
     */
    private Recipe[] recipeArray;
    /**
     * Number of recipes in coffee maker
     */
    public static final int NUM_RECIPES = 4;

    /**
     * Default constructor for a RecipeBook.
     */
    public RecipeBook() {
        recipeArray = new Recipe[NUM_RECIPES];
    }

    /**
     * Returns the recipe array.
     *
     * @return Recipe[]
     */
    public synchronized Recipe[] getRecipes() {
        return recipeArray;
    }

    public synchronized boolean addRecipe(Recipe r) {

        //Assume that the recipe is not null.
        if (r == null) {
            return false;
        }

        //Assume recipe doesn't exist in the array until
        //find out otherwise
        boolean exists = false;
        //Check that recipe doesn't already exist in array
        for (int i = 0; i < recipeArray.length; i++) {
            if (r.equals(recipeArray[i])) {
                exists = true;
            }
        }
        //Assume recipe cannot be added until find an empty
        //spot
        boolean added = false;
        //Check for first empty spot in array
        if (!exists) {
            for (int i = 0; i < recipeArray.length && !added; i++) {
                if (recipeArray[i] == null) {
                    recipeArray[i] = r;
                    added = true;
                }
            }
        }
        return added;
    }

    /**
     * Returns the name of the recipe deleted at the position specified
     * and null if the recipe does not exist.
     *
     * @param recipeToDelete
     * @return String
     */
    public synchronized String deleteRecipe(int recipeToDelete) {

        // Assumes that the recipeToDelete isn't outside the bounds of the
        // recipe book array.
        if (recipeToDelete >= NUM_RECIPES || recipeToDelete < 0) {
            return null;
        }

        if (recipeArray[recipeToDelete] != null) {
            String recipeName = recipeArray[recipeToDelete].getName();
            recipeArray[recipeToDelete] = null;
            return recipeName;
        } else {
            return null;
        }
    }

    /**
     * Returns the name of the recipe edited at the position specified
     * and null if the recipe does not exist.
     *
     * @param recipeToEdit
     * @param newRecipe
     * @return String
     */
    public synchronized String editRecipe(int recipeToEdit, Recipe newRecipe) {

        // Assumes that the [recipeToEdit] is within the [recipeBook].
        if (recipeToEdit < 0 || recipeToEdit >= NUM_RECIPES || newRecipe == null){
            return null;
        }

        if (recipeArray[recipeToEdit] != null) {
            String recipeName = recipeArray[recipeToEdit].getName();
            recipeArray[recipeToEdit] = newRecipe;
            return recipeName;
        } else {
            return null;
        }
    }

}
