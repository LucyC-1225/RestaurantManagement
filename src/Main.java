public class Main {
    public static void main(String[] args) {
//        Manager m = new Manager("Lucy", new Restaurant("Best Restaurant Ever"));
//        m.start();
        new ManagerOptionsGUI(new Manager("Lucy", new Restaurant("Best Restaurant Ever")));
    }
}
