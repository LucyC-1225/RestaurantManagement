import java.util.ArrayList;

public class Manager {
    private String name;
    private Restaurant restaurant;
    private ArrayList<Staff> staff;

    public Manager(String name, Restaurant restaurant) {
        this.name = name;
        this.restaurant = restaurant;
        staff = new ArrayList<Staff>();
    }
    public void start() {
        new AccountManagerGUI();
    }
    public String getName() {
        return name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }

    public String getAllStaffDetails() {
        String str = "";
        for (int i = 0; i < staff.size(); i++) {
            str += staff.get(i).getStaffDetail() + "\n";
        }
        return str;
    }
    public void addStaff(Staff newStaff) {
        staff.add(newStaff);
    }
    public void removeStaff(int index) {
        staff.remove(index);
    }
    public void increaseDaysWorkedUnpaid(int dayOfWeek) {
        for (int i = 0; i < staff.size(); i++) {
            boolean[] schedule = staff.get(i).getSchedule();
            if (schedule[dayOfWeek - 1]) {
                staff.get(i).setDaysWorkedUnpaid(staff.get(i).getDaysWorkedUnpaid() + 1);
            }
        }
    }
}
