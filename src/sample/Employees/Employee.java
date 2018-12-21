package sample.Employees;

import java.io.Serializable;

public abstract class Employee implements Serializable {

    protected EmployeeType type;
    protected String family = "Unknown";
    protected int age = 0;
    protected double experience = 0.0;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public Employee(String family, int age, double experience) {

        this.family = family;
        this.age = age;
        this.experience = experience;
    }

    public Employee() {
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getFamily() + " " + getAge() + " " + getExperience() + " " + getType();
    }
}