package dto;

import java.util.List;

public class CreateOrderRequest {
    private List<String> ingredients;

    public List<String> getIngredients() {
        return ingredients;
    }

    public CreateOrderRequest setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
