public class MenuItem {
    private String name;
    private double totalIngredientCost;
    private double sellPrice;
    private String category;

    public MenuItem(String name, double totalIngredientCost, double sellPrice, String category) {
        this.name = name;
        this.totalIngredientCost = totalIngredientCost;
        this.sellPrice = sellPrice;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getTotalIngredientCost() {
        return totalIngredientCost;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalIngredientCost(double totalIngredientCost) {
        this.totalIngredientCost = totalIngredientCost;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getDetails() {
        String str = "";
        str += name + " | expense: $" + totalIngredientCost + " | sell price: $" + sellPrice + " | " + category;
        return str;
    }
}
