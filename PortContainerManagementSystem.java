package cce10545;
import java.util.*;

public class PortContainerManagementSystem {
    private static ArrayDeque<Container> containerStack = new ArrayDeque<>();
    private static ArrayDeque<Ship> shipQueue = new ArrayDeque<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Port Container Management System ===");
            System.out.println("[1] Store container");
            System.out.println("[2] View port containers");
            System.out.println("[3] Register arriving ship");
            System.out.println("[4] View waiting ships");
            System.out.println("[5] Load next ship (pop container + poll ship)");
            System.out.println("[0] Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
            case 1:
                storeContainer();
                break;
            case 2:
                viewContainers();
                break;
            case 3:
                registerShip();
                break;
            case 4:
                viewShips();
                break;
            case 5:
                loadNextShip();
                break;
            case 0:
                System.out.println("Exiting system...");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
            }
        } while (choice != 0);
    }

    private static void storeContainer() {
        System.out.print("Enter container ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter weight (kg): ");
        int weight = scanner.nextInt();
        scanner.nextLine(); 

        Container c = new Container(id,desc,weight);
        containerStack.push(c);
        System.out.println("Stored: " + c);
    }

    private static void viewContainers() {
        System.out.println("Top");
        for (Container c : containerStack) {
            System.out.println(c);
        }
        System.out.println("Bottoms");
    }

    private static void registerShip() {
        System.out.print("Enter ship name: ");
        String name = scanner.nextLine();
        System.out.print("Enter captain name: ");
        String captain = scanner.nextLine();

        Ship s = new Ship(name,captain);
        shipQueue.offer(s);
        System.out.println("Registered: " + s);
    }

    private static void viewShips() {
        System.out.println("\nFRONT →");
        for (Ship s : shipQueue) {
            System.out.println(s);
        }
        System.out.println("← REAR");
    }

    private static void loadNextShip() {
        if (containerStack.isEmpty() || shipQueue.isEmpty()) {
            System.out.println("Cant load.its empty.");
            return;
        }

        Container c = containerStack.pop();
        Ship s = shipQueue.poll();
        System.out.println("Loaded: " + c + "to " + s);
        System.out.println("Remaining containers: " + containerStack.size());
        System.out.println("Remaining ships waiting: " + shipQueue.size());
    }
}
