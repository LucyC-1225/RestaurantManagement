public class Staff {
    private String name;
    private String job;
    private boolean[] schedule; //--> (M, T, W, T, F, S, S) indicates the days that the staff works
    private int hoursPerDay;
    private double payPerHour;
    private int daysWorkedUnpaid; //0 when staff is just hired

    public Staff(String name, String job, boolean[] schedule, int hoursPerDay, double payPerHour, int daysWorkedUnpaid) {
        this.name = name;
        this.job = job;
        this.schedule = schedule;
        this.hoursPerDay = hoursPerDay;
        this.payPerHour = payPerHour;
        this.daysWorkedUnpaid = daysWorkedUnpaid;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public boolean[] getSchedule() {
        return schedule;
    }

    public int getHoursPerDay() {
        return hoursPerDay;
    }

    public double getPayPerHour() {
        return payPerHour;
    }

    public int getDaysWorkedUnpaid() {
        return daysWorkedUnpaid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSchedule(boolean[] schedule) {
        this.schedule = schedule;
    }

    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public void setPayPerHour(double payPerHour) {
        this.payPerHour = payPerHour;
    }

    public void setDaysWorkedUnpaid(int daysWorkedUnpaid) {
        this.daysWorkedUnpaid = daysWorkedUnpaid;
    }

    public String getStaffDetail() {
        String str = name + " | " + job + " | " + hoursPerDay + " | " + payPerHour + " | " + daysWorkedUnpaid + " | [";
        for (int i = 0; i < schedule.length - 1; i++) {
            str += schedule[i] + ", ";
        }
        str += schedule[schedule.length - 1] + "]";
        return str;

    }
}
