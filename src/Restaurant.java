import java.util.ArrayList;

public class Restaurant {
    private String name;
    private double revenue;
    private double expenses;
    private ArrayList<MenuItem> menu;
    private ArrayList<String> revenueTracker;
    private ArrayList<String> expensesTracker;

    public Restaurant(String name) {
        this.name = name;
        menu = new ArrayList<MenuItem>();
        revenueTracker = new ArrayList<String>();
        expensesTracker = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getExpenses() {
        return expenses;
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    public ArrayList<String> getRevenueTracker() {
        return revenueTracker;
    }

    public ArrayList<String> getExpensesTracker() {
        return expensesTracker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    public void setRevenueTracker(ArrayList<String> revenueTracker) {
        this.revenueTracker = revenueTracker;
    }

    public void setExpensesTracker(ArrayList<String> expensesTracker) {
        this.expensesTracker = expensesTracker;
    }

    public void addRevenueDetails(double revenue, String details) {
        revenueTracker.add("$" + revenue + " | " + details);
    }

    public void addExpenseDetails(double expense, String details) {
        expensesTracker.add("$" + expense + " | " + details);
    }

    public void addRevenue(double revenue) {
        this.revenue += revenue;
    }

    public void addExpenses(double expenses) {
        this.expenses = expenses;
    }
    public String getRevenueDetails() {
        String str = "";
        str += "Total Revenue: " + revenue + "\n";
        for (int i = 0; i < revenueTracker.size(); i++) {
            str += i + 1 + ". " + revenueTracker.get(i) + "\n";
        }
        return str;
    }
    public String getExpensesDetails() {
        String str = "";
        str += "Total Expenses: " + expenses + "\n";
        for (int i = 0; i < expensesTracker.size(); i++) {
            str += i + 1 + ". " + expensesTracker.get(i) + "\n";
        }
        return str;
    }
    public String getRestaurantDetails() {
        String str = "";
        str += "Restaurant Name: " + getName() + "\n";
        str += "Revenue: $" + getRevenue() + "\n";
        str += "Expenses: $" + getExpenses() + "\n";
        return str;
    }
    public void sortMenuItemByCategory() {
        ArrayList<MenuItem> sortedMenu = new ArrayList<MenuItem>();
        while (menu.size() != 0) {
            MenuItem removed = menu.remove(0);
            sortedMenu.add(removed);

            for (int j = 0; j < menu.size(); j++) {
                if (removed.getCategory().equals(menu.get(j).getCategory())) {
                    sortedMenu.add(menu.remove(j));
                    j--;
                }
            }
        }
        for (int i = 0; i < sortedMenu.size(); i++) {
            menu.add(sortedMenu.get(i));
        }
    }
}
