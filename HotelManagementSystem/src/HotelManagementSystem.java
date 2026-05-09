import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HotelManagementSystem {


    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("***Welcome to Hotel Abbot***");

        Admin admin = new Admin();
        HR hr = new HR();

        while (true) {

            System.out.println("\n1.Administrator Login");
            System.out.println("2.Manager Login");
            System.out.println("3.HR Login");
            System.out.println("4.Exit");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 4) break;

                switch (choice) {

                    case 1:

                        RPDAO rpdao = new RPDAO();

                        System.out.println("**Administrator account**\n");

                        System.out.print("Enter admin name: ");
                        String adminName = scanner.nextLine();

                        System.out.print("Enter admin id: ");
                        int adminId = scanner.nextInt();
                        scanner.nextLine();

                        if (hr.loginEmployee(adminName, adminId)) {

                            while (true) {

                                System.out.println("\n1.Add Customer");
                                System.out.println("2.Delete Customer");
                                System.out.println("3.Show Waiting List");
                                System.out.println("4.Back");

                                int adminChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (adminChoice == 4) break;

                                switch (adminChoice) {

                                    case 1:

                                        System.out.print("Enter name: ");
                                        String name = scanner.nextLine();

                                        System.out.print("Enter last name: ");
                                        String lastName = scanner.nextLine();

                                        System.out.print("Enter id: ");
                                        int id = scanner.nextInt();
                                        scanner.nextLine();


                                        List<Plan> plans = rpdao.getAllPlans();
                                        rpdao.printPlans(plans);

                                        System.out.print("Choose a plan ID: ");
                                        int chosenPlanId = scanner.nextInt();
                                        scanner.nextLine();


                                        Plan chosenPlan = null;
                                        for (Plan p : plans) {
                                            if (p.getPlanId() == chosenPlanId) {
                                                chosenPlan = p;
                                                break;
                                            }
                                        }


                                        if (chosenPlan == null) {
                                            System.out.println("Invalid plan ID. Booking cancelled.");
                                            break;
                                        }


                                        List<Room> availableRooms = rpdao.getRoomsByPlanId(chosenPlanId);
                                        availableRooms.removeIf(r -> !r.isAvailable());
                                        rpdao.printAvailableRooms(availableRooms);


                                        if (availableRooms.isEmpty()) {
                                            System.out.println("No available rooms for this plan. Booking cancelled.");
                                            break;
                                        }

                                        System.out.print("Choose a Room ID: ");
                                        int chosenRoomId = scanner.nextInt();
                                        scanner.nextLine();

                                        Room chosenRoom = null;
                                        for (Room r : availableRooms) {
                                            if (r.getRoomId() == chosenRoomId) {
                                                chosenRoom = r;
                                                break;
                                            }
                                        }


                                        if (chosenRoom == null) {
                                            System.out.println("Invalid room ID. Booking cancelled.");
                                            break;
                                        }

                                        System.out.print("Enter number of nights: ");
                                        int nights = scanner.nextInt();
                                        scanner.nextLine();


                                        double totalPrice = chosenPlan.getPricePerNight() * nights;
                                        System.out.println("Total price for " + nights + " night(s): $" + totalPrice);


                                        admin.AddCostumer(name, lastName, id, totalPrice);


                                        rpdao.updateAvailability(chosenRoomId, false);
                                        System.out.println("Room " + chosenRoom.getRoomNumber() + " has been booked successfully.");
                                        break;

                                    case 2:

                                        System.out.print("Enter id to delete: ");
                                        int deleteId = scanner.nextInt();
                                        scanner.nextLine();

                                        admin.CheckOutCostumer(deleteId);
                                        break;

                                    case 3:
                                        admin.showWaitingList();
                                        break;

                                    default:
                                        System.out.println("Enter valid choice");
                                }
                            }
                        } else {
                            System.out.println("Wrong credentials");
                        }
                        break;

                    case 2:

                        Manager manager = new Manager(admin.customers, hr.employees);

                        System.out.println("**Manager account**\n");

                        System.out.print("Enter your name: ");
                        String managerName = scanner.nextLine();

                        System.out.print("Enter your id: ");
                        int managerId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter your secret word: ");
                        String secret = scanner.nextLine();

                        if (managerName.equals("Yahya") && managerId == 321 && secret.equals("apple")) {

                            while (true) {

                                System.out.println("\n1.Get Total Revenue");
                                System.out.println("2.Get Total Expenses");
                                System.out.println("3.Show Month Summary");
                                System.out.println("4.Add Employee");
                                System.out.println("5.Show Revenue History");
                                System.out.println("6.Exit");

                                int choice2 = scanner.nextInt();
                                scanner.nextLine();

                                if (choice2 == 6) break;

                                switch (choice2) {

                                    case 1:
                                        manager.showTotalRevenue();
                                        break;

                                    case 2:
                                        manager.showTotalExpenses();
                                        break;

                                    case 3:
                                        manager.getSummary();
                                        break;

                                    case 4:

                                        System.out.print("Enter Employee name: ");
                                        String eName = scanner.nextLine();

                                        System.out.print("Enter phone number: ");
                                        int ePhone = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Enter Age: ");
                                        int eAge = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Enter ID: ");
                                        int eId = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Enter Salary: ");
                                        double eSalary = scanner.nextDouble();
                                        scanner.nextLine();

                                        hr.AddEmployee(eName, ePhone, eAge, eId, eSalary);
                                        break;

                                    case 5:
                                        manager.showRevenueHistory();
                                        break;

                                    default:
                                        System.out.println("Enter valid choice");
                                }
                            }
                        } else {
                            System.out.println("Wrong credentials");
                        }
                        break;

                    case 3:

                        System.out.print("Enter your name: ");
                        String hrName = scanner.nextLine();

                        System.out.print("Enter your ID: ");
                        int hrId = scanner.nextInt();
                        scanner.nextLine();

                        if (hrName.equals("ABD") && hrId == 456) {

                            while (true) {

                                System.out.println("\n1.Add new employees");
                                System.out.println("2.Edit employee details");
                                System.out.println("3.Remove employees");
                                System.out.println("4.View total salaries");
                                System.out.println("5.Track number of employees");
                                System.out.println("6.Show All Actions Made by HR");
                                System.out.println("7.Show Last actions");
                                System.out.println("8.Exit");

                                int choice3 = scanner.nextInt();
                                scanner.nextLine();

                                if (choice3 == 8) break;

                                switch (choice3) {

                                    case 1:

                                        System.out.print("Enter Employee name: ");
                                        String EName = scanner.nextLine();

                                        System.out.print("Enter Employee phone: ");
                                        int EPhone = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Enter Employee Age: ");
                                        int EAge = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Enter Employee ID: ");
                                        int EId = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Enter Employee Salary: ");
                                        double ESalary = scanner.nextDouble();
                                        scanner.nextLine();

                                        hr.AddEmployee(EName, EPhone, EAge, EId, ESalary);
                                        break;

                                    case 2:

                                        System.out.print("Enter employee ID to edit: ");
                                        int editId = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Enter new name: ");
                                        String newName = scanner.nextLine();

                                        System.out.print("Enter new salary: ");
                                        double newSalary = scanner.nextDouble();
                                        scanner.nextLine();

                                        hr.editEmployee(editId, newName, newSalary);
                                        break;

                                    case 3:

                                        System.out.print("Enter employee ID to delete: ");
                                        int deleteId2 = scanner.nextInt();
                                        scanner.nextLine();

                                        hr.DeleteEmployee(deleteId2);
                                        break;

                                    case 4:
                                        hr.CalculateSalaries();
                                        break;

                                    case 5:
                                        hr.countEmployees();
                                        break;

                                    case 6:
                                        hr.showAllActions();
                                        break;

                                    case 7:
                                        hr.showLastAction();
                                        break;

                                    default:
                                        System.out.println("Enter valid choice");
                                }
                            }
                        } else {
                            System.out.println("Wrong credentials");
                        }
                        break;

                    default:
                        System.out.println("Enter a valid choice");
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a number");
                scanner.nextLine();
            }

        }

        scanner.close();
    }
}