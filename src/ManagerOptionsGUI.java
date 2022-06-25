import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ManagerOptionsGUI {
    private Manager m;
    private JFrame frame;
    private JButton back;
    //home panel
    private JPanel homePanel;
    private JTextArea restaurantDetails;
    private JButton revenueAndExpensesButton;
    private JButton staffButton;
    private JButton menuButton;
    private JButton closeRestaurantButton;
    private JButton simulate;

    //Revenue and expenses panel
    private JPanel revenueAndExpensesPanel;
    private JTextArea revenueDetails;
    private JTextArea expensesDetails;
    private JButton addRevenue;
    private JButton addExpense;

    //Staff panel
    private JPanel staffPanel;
    private JTextArea staffDetails;
    private JButton hireButton;
    private JButton fireButton;
    private JButton payButton;
    private DefaultListModel staffData;
    private JList listOfStaff;

    //Menu panel
    private JPanel menuPanel;
    private DefaultListModel menuData;
    private JList listOfMenu;
    private JButton addMenuItemButton;
    private JButton editMenuItemButton;
    private JButton deleteMenuItemButton;

    //close restaurant panel
    private JPanel closeRestaurantPanel;
    private JButton addAmountSold;

    public ManagerOptionsGUI(Manager m) {
        this.m = m;

        frame = new JFrame("Manager Window");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        homeWindow();
    }
    public void homeWindow() {
        homePanel = new JPanel();
        homePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        restaurantDetails = new JTextArea();
        restaurantDetails.setText(m.getRestaurant().getRestaurantDetails());
        restaurantDetails.setEditable(false);
        homePanel.add(restaurantDetails, c);

        c.gridy = 1;
        revenueAndExpensesButton = new JButton("Revenue and Expenses");
        revenueAndExpensesButton.addActionListener(this::actionPerformed);
        homePanel.add(revenueAndExpensesButton, c);

        c.gridy = 2;
        staffButton = new JButton("Staff");
        staffButton.addActionListener(this::actionPerformed);
        homePanel.add(staffButton, c);

        c.gridy = 3;
        menuButton = new JButton("Menu");
        menuButton.addActionListener(this::actionPerformed);
        homePanel.add(menuButton, c);

        c.gridy = 4;
        closeRestaurantButton = new JButton("Close Restaurant");
        closeRestaurantButton.addActionListener(this::actionPerformed);
        homePanel.add(closeRestaurantButton, c);

        c.gridy = 5;
        simulate = new JButton("Simulate");
        simulate.addActionListener(this::actionPerformed);
        homePanel.add(simulate, c);

        frame.add(homePanel);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == revenueAndExpensesButton) {
            initiateRevenueAndExpensesPanel();
        } else if (source == back) {
            if (revenueAndExpensesPanel != null) {
                frame.remove(revenueAndExpensesPanel);
            }
            if (staffPanel != null) {
                frame.remove(staffPanel);
            }
            if (menuPanel != null) {
                frame.remove(menuPanel);
            }
            if (closeRestaurantPanel != null) {
                frame.remove(closeRestaurantPanel);
            }
            frame.validate();
            frame.repaint();
            homeWindow();
            frame.revalidate();
        } else if (source == addRevenue) {
            double revenue = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter a revenue:"));
            String revenueDetails = JOptionPane.showInputDialog(frame, "Give revenue details. \"Date: details\"");
            m.getRestaurant().addRevenueDetails(revenue, revenueDetails);
            m.getRestaurant().addRevenue(revenue);
            //updates the textArea
            this.revenueDetails.setText(m.getRestaurant().getRevenueDetails());
        } else if (source == addExpense) {
            double expense = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter an expense:"));
            String expenseDetails = JOptionPane.showInputDialog(frame, "Give expense details. \"Date: details\"");
            m.getRestaurant().addExpenseDetails(expense, expenseDetails);
            m.getRestaurant().addExpenses(expense);
            //updates the textArea
            this.expensesDetails.setText(m.getRestaurant().getExpensesDetails());
        } else if (source == staffButton) {
            initiateStaffPanel();
        } else if (source == hireButton) {
            String name = JOptionPane.showInputDialog(frame, "Name of new staff:");
            String job = JOptionPane.showInputDialog(frame, "Job of new staff:");
            int hoursPerDay = Integer.parseInt(JOptionPane.showInputDialog(frame, "Hours worked per day:"));
            double payPerHour = Double.parseDouble(JOptionPane.showInputDialog(frame, "Pay Per Hour:"));
            int daysWorkedUnpaid = Integer.parseInt(JOptionPane.showInputDialog(frame, "Days worked unpaid:"));
            boolean monday = Boolean.parseBoolean(JOptionPane.showInputDialog(frame, "Does the staff work on monday? true or false"));
            boolean tuesday = Boolean.parseBoolean(JOptionPane.showInputDialog(frame, "Does the staff work on tuesday? true or false"));
            boolean wednesday = Boolean.parseBoolean(JOptionPane.showInputDialog(frame, "Does the staff work on wednesday? true or false"));
            boolean thursday = Boolean.parseBoolean(JOptionPane.showInputDialog(frame, "Does the staff work on thursday? true or false"));
            boolean friday = Boolean.parseBoolean(JOptionPane.showInputDialog(frame, "Does the staff work on friday? true or false"));
            boolean saturday = Boolean.parseBoolean(JOptionPane.showInputDialog(frame, "Does the staff work on saturday? true or false"));
            boolean sunday = Boolean.parseBoolean(JOptionPane.showInputDialog(frame, "Does the staff work on sunday? true or false"));
            boolean[] schedule = {monday, tuesday, wednesday, thursday, friday, saturday, sunday};
            Staff newStaff = new Staff(name, job, schedule, hoursPerDay, payPerHour, daysWorkedUnpaid);
            m.addStaff(newStaff);
            staffData.addElement((staffData.size()) + " | " + m.getStaff().get(m.getStaff().size() - 1).getStaffDetail());
        } else if (source == fireButton) {
            int index = listOfStaff.getSelectedIndex();
            staffData.removeElementAt(index);
            m.removeStaff(index);
        } else if (source == payButton) {
            int index = listOfStaff.getSelectedIndex();
            Staff paidStaff = m.getStaff().get(index);
            double paidAmount = paidStaff.getDaysWorkedUnpaid() * paidStaff.getHoursPerDay() * paidStaff.getPayPerHour();
            paidStaff.setDaysWorkedUnpaid(0);
            m.getRestaurant().addExpenses(paidAmount);
            m.getRestaurant().addExpenseDetails(paidAmount, "Paid " + paidStaff.getName() + " $" + paidAmount);
            staffData.setElementAt((index + 1) + " | " + m.getStaff().get(index).getStaffDetail(), index);
        } else if (source == menuButton) {
            initiateMenuPanel();
        } else if (source == addMenuItemButton) {
            String name = JOptionPane.showInputDialog(frame, "Name of menu item:");
            String category = JOptionPane.showInputDialog(frame, "Category Name:");
            double ingredientCost = Double.parseDouble(JOptionPane.showInputDialog(frame, "Total Cost of Making Menu Item:"));
            double sellPrice = Double.parseDouble(JOptionPane.showInputDialog(frame, "Sell Price:"));
            MenuItem newMenuItem = new MenuItem(name, ingredientCost, sellPrice, category);
            m.getRestaurant().getMenu().add(newMenuItem);
            menuData.addElement(m.getRestaurant().getMenu().size() + " | " + m.getRestaurant().getMenu().get(m.getRestaurant().getMenu().size() - 1).getDetails());
        } else if (source == editMenuItemButton) {
            int index = listOfMenu.getSelectedIndex();
            MenuItem editMenuItem = m.getRestaurant().getMenu().get(index);
            String name = JOptionPane.showInputDialog(frame, "Name of menu item:");
            String category = JOptionPane.showInputDialog(frame, "Category Name:");
            double ingredientCost = Double.parseDouble(JOptionPane.showInputDialog(frame, "Total Cost of Making Menu Item:"));
            double sellPrice = Double.parseDouble(JOptionPane.showInputDialog(frame, "Sell Price:"));
            editMenuItem.setName(name);
            editMenuItem.setTotalIngredientCost(ingredientCost);
            editMenuItem.setSellPrice(sellPrice);
            editMenuItem.setCategory(category);
            menuData.setElementAt(index + 1 + " | " + m.getRestaurant().getMenu().get(index).getDetails(), index);
        } else if (source == deleteMenuItemButton) {
            int index = listOfMenu.getSelectedIndex();
            m.getRestaurant().getMenu().remove(index);
            menuData.removeElementAt(index);
        } else if (source == closeRestaurantButton) {
            int dayOfWeek = Integer.parseInt(JOptionPane.showInputDialog(frame, "What day of the week is it?\n1. Monday\n2. Tuesday\n2. Wednesday\n4. Thursday\n5. Friday\n6. Saturday\n7. Sunday"));
            m.increaseDaysWorkedUnpaid(dayOfWeek);
            initiateCloseRestaurantPanel();
        } else if (source == addAmountSold) {
            String date = JOptionPane.showInputDialog(frame, "Date:");
            int[] indexes = listOfMenu.getSelectedIndices();
            double totalExpenses = 0;
            double totalRevenue = 0;
            for (int i = 0; i < indexes.length; i++) {
                totalExpenses += m.getRestaurant().getMenu().get(indexes[i]).getTotalIngredientCost();
                totalRevenue += m.getRestaurant().getMenu().get(indexes[i]).getSellPrice();
            }
            String expensesDetails = date + ": Menu item expenses +$" + totalExpenses;
            m.getRestaurant().addExpenseDetails(totalExpenses, expensesDetails);
            m.getRestaurant().addExpenses(totalExpenses);
            String revenuDetails = date + ": Menu item earnings +$" + totalRevenue;
            m.getRestaurant().addRevenueDetails(totalRevenue, revenuDetails);
            m.getRestaurant().addRevenue(totalRevenue);
        }
    }
    public void initiateCloseRestaurantPanel() {
        frame.remove(homePanel);
        frame.validate();
        frame.repaint();

        closeRestaurantPanel = new JPanel();
        closeRestaurantPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        menuData = new DefaultListModel();
        m.getRestaurant().sortMenuItemByCategory(); //sort it by category
        for (int i = 0; i < m.getRestaurant().getMenu().size(); i++) {
            menuData.addElement((i + 1) + " | " + m.getRestaurant().getMenu().get(i).getDetails());
        }
        listOfMenu = new JList(menuData);
        listOfMenu.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listOfMenu.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScroller = new JScrollPane(listOfMenu);
        listScroller.setPreferredSize(new Dimension(250, 80));
        closeRestaurantPanel.add(listScroller, c);

        c.gridy = 1;
        addAmountSold = new JButton("Add Amount Sold");
        addAmountSold.addActionListener(this::actionPerformed);
        closeRestaurantPanel.add(addAmountSold, c);

        c.gridy = 2;
        back = new JButton("Back");
        back.addActionListener(this::actionPerformed);
        closeRestaurantPanel.add(back, c);

        frame.add(closeRestaurantPanel);
        frame.revalidate();
    }
    public void initiateMenuPanel() {
        frame.remove(homePanel);
        frame.validate();
        frame.repaint();

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        menuData = new DefaultListModel();
        m.getRestaurant().sortMenuItemByCategory(); //sort it by category
        for (int i = 0; i < m.getRestaurant().getMenu().size(); i++) {
            menuData.addElement((i + 1) + " | " + m.getRestaurant().getMenu().get(i).getDetails());
        }
        listOfMenu = new JList(menuData);
        listOfMenu.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listOfMenu.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScroller = new JScrollPane(listOfMenu);
        listScroller.setPreferredSize(new Dimension(250, 80));
        menuPanel.add(listScroller, c);

        c.gridy = 1;
        addMenuItemButton = new JButton("Add Menu Item");
        addMenuItemButton.addActionListener(this::actionPerformed);
        menuPanel.add(addMenuItemButton, c);

        c.gridy = 2;
        editMenuItemButton = new JButton("Edit Menu Item");
        editMenuItemButton.addActionListener(this::actionPerformed);
        menuPanel.add(editMenuItemButton, c);

        c.gridy = 3;
        deleteMenuItemButton = new JButton("Delete Menu Item");
        deleteMenuItemButton.addActionListener(this::actionPerformed);
        menuPanel.add(deleteMenuItemButton, c);

        c.gridy = 4;
        back = new JButton("Back");
        back.addActionListener(this::actionPerformed);
        menuPanel.add(back, c);

        frame.add(menuPanel);
        frame.revalidate();
    }
    public void initiateStaffPanel() {
        frame.remove(homePanel);
        frame.validate();
        frame.repaint();

        staffPanel = new JPanel();
        staffPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        staffDetails = new JTextArea("Name | Job | Hours Per Day | Pay Per Hour | Days worked Unpaid | Schedule");
        staffDetails.setEditable(false);
        staffPanel.add(staffDetails, c);

        c.gridy = 1;
        staffData = new DefaultListModel();
        for (int i = 0; i < m.getStaff().size(); i++) {
            staffData.addElement((i + 1) + " | " + m.getStaff().get(i).getStaffDetail());
        }
        listOfStaff = new JList(staffData);
        listOfStaff.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listOfStaff.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScroller = new JScrollPane(listOfStaff);
        listScroller.setPreferredSize(new Dimension(250, 80));
        staffPanel.add(listScroller, c);

        c.gridy = 2;
        hireButton = new JButton("Hire");
        hireButton.addActionListener(this::actionPerformed);
        staffPanel.add(hireButton, c);

        c.gridy = 3;
        fireButton = new JButton("Fire");
        fireButton.addActionListener(this::actionPerformed);
        staffPanel.add(fireButton, c);

        c.gridy = 4;
        payButton = new JButton("Pay");
        payButton.addActionListener(this::actionPerformed);
        staffPanel.add(payButton, c);

        c.gridy = 5;
        back = new JButton("Back");
        back.addActionListener(this::actionPerformed);
        staffPanel.add(back, c);

        frame.add(staffPanel);
        frame.revalidate();
    }
    public void initiateRevenueAndExpensesPanel() {
        frame.remove(homePanel);
        frame.validate();
        frame.repaint();

        revenueAndExpensesPanel = new JPanel();
        revenueAndExpensesPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        revenueDetails = new JTextArea(m.getRestaurant().getRevenueDetails());
        revenueDetails.setEditable(false);
        revenueAndExpensesPanel.add(revenueDetails, c);

        c.gridy = 1;
        expensesDetails = new JTextArea(m.getRestaurant().getExpensesDetails());
        expensesDetails.setEditable(false);
        revenueAndExpensesPanel.add(expensesDetails, c);

        c.gridy = 2;
        addRevenue = new JButton("Add Revenue");
        addRevenue.addActionListener(this::actionPerformed);
        revenueAndExpensesPanel.add(addRevenue, c);

        c.gridy = 3;
        addExpense = new JButton("Add Expense");
        addExpense.addActionListener(this::actionPerformed);
        revenueAndExpensesPanel.add(addExpense, c);

        c.gridy = 4;
        back = new JButton("Back");
        back.addActionListener(this::actionPerformed);
        revenueAndExpensesPanel.add(back, c);

        frame.add(revenueAndExpensesPanel);
        frame.revalidate();
    }
}
