import java.util.Scanner;

public class Nothing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Scientific Calculator");
        System.out.println("---------------------");
        
        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();
        
        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();
        
        System.out.println("Select an operation:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Exponentiation");
        System.out.println("6. Square root");
        
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        
        double result = 0;
        
        switch (choice) {
            case 1:
                result = num1 + num2;
                break;
            case 2:
                result = num1 - num2;
                break;
            case 3:
                result = num1 * num2;
                break;
            case 4:
                result = num1 / num2;
                break;
            case 5:
                result = Math.pow(num1, num2);
                break;
            case 6:
                result = Math.sqrt(num1);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        
        System.out.println("Result: " + result);
        
        scanner.close();
    }
}
