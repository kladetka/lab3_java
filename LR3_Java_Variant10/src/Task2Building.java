import java.util.Scanner;

class Building {
    private String address;
    private int floors;
    private double area;
    private int yearBuilt;
    private String material;
    private int apartments;
    private boolean hasElevator;

    // Конструктор без параметрів
    public Building() {
        this("Невідома адреса", 1, 50.0, 2000, "цегла", 1, false);
    }

    // Конструктор з усіма параметрами
    public Building(String address, int floors, double area, int yearBuilt,
                    String material, int apartments, boolean hasElevator) {
        this.address = address;
        this.floors = floors;
        this.area = area;
        this.yearBuilt = yearBuilt;
        this.material = material;
        this.apartments = apartments;
        this.hasElevator = hasElevator;
    }

    // Getters / Setters
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getFloors() { return floors; }
    public void setFloors(int floors) { this.floors = floors; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public int getYearBuilt() { return yearBuilt; }
    public void setYearBuilt(int yearBuilt) { this.yearBuilt = yearBuilt; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public int getApartments() { return apartments; }
    public void setApartments(int apartments) { this.apartments = apartments; }

    public boolean isHasElevator() { return hasElevator; }
    public void setHasElevator(boolean hasElevator) { this.hasElevator = hasElevator; }

    // Метод 1: оцінка вартості будівлі
    public double calculateValue(double area, double pricePerSquareMeter) {
        return area * pricePerSquareMeter;
    }

    // Метод 2: визначення віку будівлі
    public int calculateAge(int currentYear) {
        return currentYear - yearBuilt;
    }

    // Метод 3: зміна кількості поверхів
    public void changeFloors(int floors) {
        this.floors = floors;
    }

    public String information() {
        return String.format(
            "Адреса: %s%nПоверхів: %d%nПлоща: %.2f м²%nРік побудови: %d%n" +
            "Матеріал: %s%nКількість квартир: %d%nЛіфт: %s",
            address, floors, area, yearBuilt, material, apartments,
            hasElevator ? "так" : "ні"
        );
    }
}

public class Task2Building {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Завдання 2. Інкапсуляція — Будівля ===");

        System.out.print("Введіть адресу: ");
        String address = scanner.nextLine();

        System.out.print("Введіть кількість поверхів: ");
        int floors = Integer.parseInt(scanner.nextLine());

        System.out.print("Введіть площу (м²): ");
        double area = Double.parseDouble(scanner.nextLine());

        System.out.print("Введіть рік побудови: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("Введіть матеріал: ");
        String material = scanner.nextLine();

        System.out.print("Введіть кількість квартир: ");
        int apartments = Integer.parseInt(scanner.nextLine());

        System.out.print("Чи є ліфт? (так/ні): ");
        boolean elevator = scanner.nextLine().equalsIgnoreCase("так");

        Building building = new Building(
            address, floors, area, year, material, apartments, elevator
        );

        System.out.println("\nІнформація про будівлю:");
        System.out.println(building.information());

        System.out.print("\nВведіть поточний рік для визначення віку: ");
        int currentYear = Integer.parseInt(scanner.nextLine());
        System.out.println("Вік будівлі: " + building.calculateAge(currentYear) + " років");

        System.out.print("Введіть ціну 1 м²: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.printf("Розрахункова вартість: %.2f%n",
                building.calculateValue(building.getArea(), price));

        System.out.print("Введіть нову кількість поверхів: ");
        int newFloors = Integer.parseInt(scanner.nextLine());
        building.changeFloors(newFloors);

        System.out.println("\nПісля оновлення:");
        System.out.println(building.information());

        scanner.close();
    }
}
