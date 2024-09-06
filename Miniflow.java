import java.util.Arrays;
import java.util.Scanner;

class Person {
    int id;
    int balance;

    Person(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }
}

public class Miniflow {

    public static void minCashFlow(int N, Person[] persons) {
        // Sort persons by balance
        Arrays.sort(persons, (a, b) -> a.balance - b.balance);

        int left = 0;
        int right = N - 1;

        // Settle debts
        while (left < right) {
            int minSettlement = Math.min(-persons[left].balance, persons[right].balance);
            persons[left].balance += minSettlement;
            persons[right].balance -= minSettlement;

            System.out.println("Person " + persons[left].id + " pays " + minSettlement + " to Person " + persons[right].id);

            if (persons[left].balance == 0) {
                left++;
            }
            if (persons[right].balance == 0) {
                right--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of persons: ");
        int N = sc.nextInt();

        Person[] persons = new Person[N];
        for (int i = 0; i < N; i++) {
            persons[i] = new Person(i, 0); // Initialize persons
        }

        System.out.println("Enter the debts (person i owes person j):");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int debt = sc.nextInt();
                persons[i].balance -= debt;
                persons[j].balance += debt;
            }
        }

        minCashFlow(N, persons);

        sc.close();
    }
}
