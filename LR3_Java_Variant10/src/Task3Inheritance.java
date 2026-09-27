import java.util.Scanner;

class Person implements AutoCloseable {
    protected String name;
    protected int age;
    protected String gender;
    protected double weight;
    protected double height;

    public Person() {
        this("Невідомо", 0, "невідомо", 0.0, 0.0);
    }

    public Person(String name, int age, String gender, double weight, double height) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public Person(String name, int age, String gender) {
        this(name, age, gender, 0.0, 0.0);
    }

    public double bodyMassIndex() {
        if (height <= 0) return 0;
        return weight / (height * height);
    }

    public String adulthood() {
        return age >= 18 ? "Повнолітній" : "Не повнолітній";
    }

    public String information() {
        return String.format(
            "Ім'я: %s%nВік: %d%nСтать: %s%nВага: %.2f кг%nРіст: %.2f м%n" +
            "Індекс маси тіла: %.2f%nПовнолітність: %s",
            name, age, gender, weight, height, bodyMassIndex(), adulthood()
        );
    }

    public void updateAge(int newAge) {
        this.age = newAge;
    }

    public void updateWeight(double newWeight) {
        this.weight = newWeight;
    }

    // У Java немає класичних деструкторів як у C++.
    // Метод close() виконує роль явного звільнення ресурсу/фінального повідомлення.
    @Override
    public void close() {
        System.out.println("Лабораторна робота виконана студентом 2 курсу ПІБ студента");
    }
}

class Child extends Person {
    private String parentsFullName;

    public Child() {
        super();
        this.parentsFullName = "Невідомо";
    }

    public Child(String name, int age, String gender, double weight,
                 double height, String parentsFullName) {
        super(name, age, gender, weight, height);
        this.parentsFullName = parentsFullName;
    }

    public Child(String name, int age, String parentsFullName) {
        super(name, age, "не вказано");
        this.parentsFullName = parentsFullName;
    }

    @Override
    public double bodyMassIndex() {
        if (height <= 0) return 0;
        return (weight / (height * height)) * 0.2;
    }

    public String educationalInstitution() {
        return age < 6
                ? "Дошкільний навчальний заклад"
                : "Школа";
    }

    public void updateParents(String newParentsFullName) {
        this.parentsFullName = newParentsFullName;
    }

    @Override
    public String information() {
        return super.information() +
                String.format("%nПІБ батьків: %s%nЗаклад навчання: %s",
                        parentsFullName, educationalInstitution());
    }
}

class Pensioner extends Person {
    private String residenceAddress;

    public Pensioner() {
        super();
        this.residenceAddress = "Невідома адреса";
    }

    public Pensioner(String name, int age, String gender, double weight,
                      double height, String residenceAddress) {
        super(name, age, gender, weight, height);
        this.residenceAddress = residenceAddress;
    }

    public Pensioner(String name, int age, String residenceAddress) {
        super(name, age, "не вказано");
        this.residenceAddress = residenceAddress;
    }

    @Override
    public String adulthood() {
        return "Повнолітній";
    }

    public double pension() {
        if (age >= 90) return age * 75;
        if (age >= 80) return age * 65;
        if (age >= 70) return age * 55;
        return 0;
    }

    public void updateAddress(String newAddress) {
        this.residenceAddress = newAddress;
    }

    @Override
    public String information() {
        return super.information() +
                String.format("%nАдреса проживання: %s%nПенсія: %.2f грн",
                        residenceAddress, pension());
    }
}

public class Task3Inheritance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Завдання 3. Наслідування та поліморфізм ===");

        System.out.println("\n--- Введення Людини ---");
        System.out.print("Ім'я: ");
        String personName = scanner.nextLine();
        System.out.print("Вік: ");
        int personAge = Integer.parseInt(scanner.nextLine());
        System.out.print("Стать: ");
        String personGender = scanner.nextLine();
        System.out.print("Вага (кг): ");
        double personWeight = Double.parseDouble(scanner.nextLine());
        System.out.print("Ріст (м): ");
        double personHeight = Double.parseDouble(scanner.nextLine());

        Person person = new Person(
            personName, personAge, personGender, personWeight, personHeight
        );

        System.out.println("\n--- Введення Дитини ---");
        System.out.print("Ім'я: ");
        String childName = scanner.nextLine();
        System.out.print("Вік: ");
        int childAge = Integer.parseInt(scanner.nextLine());
        System.out.print("Стать: ");
        String childGender = scanner.nextLine();
        System.out.print("Вага (кг): ");
        double childWeight = Double.parseDouble(scanner.nextLine());
        System.out.print("Ріст (м): ");
        double childHeight = Double.parseDouble(scanner.nextLine());
        System.out.print("ПІБ батьків: ");
        String parents = scanner.nextLine();

        Child child = new Child(
            childName, childAge, childGender, childWeight, childHeight, parents
        );

        System.out.println("\n--- Введення Пенсіонера ---");
        System.out.print("Ім'я: ");
        String pensionerName = scanner.nextLine();
        System.out.print("Вік: ");
        int pensionerAge = Integer.parseInt(scanner.nextLine());
        System.out.print("Стать: ");
        String pensionerGender = scanner.nextLine();
        System.out.print("Вага (кг): ");
        double pensionerWeight = Double.parseDouble(scanner.nextLine());
        System.out.print("Ріст (м): ");
        double pensionerHeight = Double.parseDouble(scanner.nextLine());
        System.out.print("Адреса: ");
        String address = scanner.nextLine();

        Pensioner pensioner = new Pensioner(
            pensionerName, pensionerAge, pensionerGender,
            pensionerWeight, pensionerHeight, address
        );

        // Демонстрація динамічного поліморфізму:
        Person[] people = {person, child, pensioner};

        System.out.println("\n=== Інформація про об'єкти ===");
        for (Person p : people) {
            System.out.println("\n" + p.information());
            System.out.printf("Поліморфний виклик ІМТ: %.2f%n", p.bodyMassIndex());
            System.out.println("Поліморфний виклик повноліття: " + p.adulthood());
        }

        // Оновлення інформації
        System.out.println("\n=== Оновлення ===");
        System.out.print("Нова вага людини: ");
        person.updateWeight(Double.parseDouble(scanner.nextLine()));

        System.out.print("Новий вік дитини: ");
        child.updateAge(Integer.parseInt(scanner.nextLine()));

        System.out.print("Нова адреса пенсіонера: ");
        pensioner.updateAddress(scanner.nextLine());

        System.out.println("\n=== Після оновлення ===");
        for (Person p : people) {
            System.out.println("\n" + p.information());
        }

        // Явне виконання фінального повідомлення.
        person.close();
        child.close();
        pensioner.close();

        scanner.close();
    }
}
