import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class AirTransport {
    protected String model;
    protected int year;
    protected double altitude;
    protected double speed;

    public AirTransport() {
        this("Невідома модель", 2000, 0, 0);
    }

    public AirTransport(String model, int year, double altitude, double speed) {
        this.model = model;
        this.year = year;
        this.altitude = altitude;
        this.speed = speed;
    }

    // 4 методи, з них 2 абстрактні
    public abstract double calculateFlightTime(double distance);
    public abstract String getTransportType();

    public String information() {
        return String.format(
            "Тип: %s%nМодель: %s%nРік: %d%nВисота: %.2f м%nШвидкість: %.2f км/год",
            getTransportType(), model, year, altitude, speed
        );
    }

    public void updateAltitude(double newAltitude) {
        this.altitude = newAltitude;
    }
}

class Helicopter extends AirTransport {
    private int rotorCount;

    public Helicopter() {
        super();
        this.rotorCount = 1;
    }

    public Helicopter(String model, int year, double altitude,
                      double speed, int rotorCount) {
        super(model, year, altitude, speed);
        this.rotorCount = rotorCount;
    }

    @Override
    public double calculateFlightTime(double distance) {
        return speed > 0 ? distance / speed : 0;
    }

    @Override
    public String getTransportType() {
        return "Гелікоптер";
    }

    public void land() {
        altitude = 0;
    }

    public void changeRotorCount(int count) {
        rotorCount = count;
    }

    @Override
    public String information() {
        return super.information() + "\nКількість роторів: " + rotorCount;
    }
}

class Airplane extends AirTransport {
    private int engines;

    public Airplane() {
        super();
        this.engines = 2;
    }

    public Airplane(String model, int year, double altitude,
                    double speed, int engines) {
        super(model, year, altitude, speed);
        this.engines = engines;
    }

    @Override
    public double calculateFlightTime(double distance) {
        return speed > 0 ? distance / speed : 0;
    }

    @Override
    public String getTransportType() {
        return "Літак";
    }

    public void takeOff(double newAltitude) {
        altitude = newAltitude;
    }

    public void changeEngines(int count) {
        engines = count;
    }

    @Override
    public String information() {
        return super.information() + "\nКількість двигунів: " + engines;
    }
}

// Інтерфейс винесений за межі ієрархії доменних класів.
interface DispatchControl {
    void registerFlight(AirTransport flight);
    List<AirTransport> searchByAltitude(double minAltitude);
}

// Клас-сервіс реалізує інтерфейс та інкапсулює колекцію.
class DispatchControlService implements DispatchControl {
    private final List<AirTransport> flights = new ArrayList<>();

    @Override
    public void registerFlight(AirTransport flight) {
        flights.add(flight);
    }

    @Override
    public List<AirTransport> searchByAltitude(double minAltitude) {
        List<AirTransport> result = new ArrayList<>();

        for (AirTransport flight : flights) {
            if (flight.altitude >= minAltitude) {
                result.add(flight);
            }
        }

        return result;
    }

    public void showAll() {
        for (AirTransport flight : flights) {
            System.out.println("\n" + flight.information());
        }
    }
}

public class Task4AbstractInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Завдання 4. Абстрактний клас та інтерфейс ===");

        System.out.println("\n--- Гелікоптер ---");
        System.out.print("Модель: ");
        String hModel = scanner.nextLine();
        System.out.print("Рік: ");
        int hYear = Integer.parseInt(scanner.nextLine());
        System.out.print("Висота (м): ");
        double hAltitude = Double.parseDouble(scanner.nextLine());
        System.out.print("Швидкість (км/год): ");
        double hSpeed = Double.parseDouble(scanner.nextLine());
        System.out.print("Кількість роторів: ");
        int rotors = Integer.parseInt(scanner.nextLine());

        Helicopter helicopter = new Helicopter(
            hModel, hYear, hAltitude, hSpeed, rotors
        );

        System.out.println("\n--- Літак ---");
        System.out.print("Модель: ");
        String aModel = scanner.nextLine();
        System.out.print("Рік: ");
        int aYear = Integer.parseInt(scanner.nextLine());
        System.out.print("Висота (м): ");
        double aAltitude = Double.parseDouble(scanner.nextLine());
        System.out.print("Швидкість (км/год): ");
        double aSpeed = Double.parseDouble(scanner.nextLine());
        System.out.print("Кількість двигунів: ");
        int engines = Integer.parseInt(scanner.nextLine());

        Airplane airplane = new Airplane(
            aModel, aYear, aAltitude, aSpeed, engines
        );

        DispatchControlService service = new DispatchControlService();
        service.registerFlight(helicopter);
        service.registerFlight(airplane);

        System.out.println("\n=== Усі зареєстровані повітряні судна ===");
        service.showAll();

        System.out.print("\nВведіть мінімальну висоту для пошуку: ");
        double minAltitude = Double.parseDouble(scanner.nextLine());

        List<AirTransport> found = service.searchByAltitude(minAltitude);

        System.out.println("\n=== Результат пошуку ===");
        if (found.isEmpty()) {
            System.out.println("Повітряних суден із такою висотою немає.");
        } else {
            for (AirTransport flight : found) {
                System.out.println("\n" + flight.information());
            }
        }

        System.out.print("\nВведіть відстань польоту (км) для розрахунку часу: ");
        double distance = Double.parseDouble(scanner.nextLine());

        System.out.printf("Час польоту гелікоптера: %.2f год.%n",
                helicopter.calculateFlightTime(distance));
        System.out.printf("Час польоту літака: %.2f год.%n",
                airplane.calculateFlightTime(distance));

        scanner.close();
    }
}
