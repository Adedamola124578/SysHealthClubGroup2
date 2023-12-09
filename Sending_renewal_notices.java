import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


//public class Project {
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Member {
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    Date expirationDate;

    // Constructor
    public Member(String firstName, String lastName, String phoneNumber, String email, Date expirationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.expirationDate = expirationDate;
    }
}

public class Project {

    public static void main(String[] args) {
        // Create sample data and populate the members ArrayList
        List<Member> members = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Sample member 1
            Date expirationDate1 = dateFormat.parse("2023-12-15");
            members.add(new Member("John", "Doe", "1234567890", "john.doe@email.com", expirationDate1));

            // Sample member 2
            Date expirationDate2 = dateFormat.parse("2023-12-28");
            members.add(new Member("Jane", "Smith", "9876543210", "jane.smith@email.com", expirationDate2));

            // Sample member 3
            Date expirationDate3 = dateFormat.parse("2023-11-30");
            members.add(new Member("Bob", "Johnson", "5555555555", "bob.johnson@email.com", expirationDate3));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Rest of the code remains unchanged
        List<Member> expiringMembers = findExpiringMembers(members);
        writeToTextFile(expiringMembers, "ExpiringMembers.txt");
        displayMembers(expiringMembers);
    }

    // Remaining methods remain unchanged



    private static List<Member> findExpiringMembers(List<Member> members) {
            List<Member> expiringMembers = new ArrayList<>();

            // Get the current date
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();

            // Set the calendar to the beginning of the next month
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            // Set the time to the end of the last day of the next month
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            Date endOfMonth = calendar.getTime();

            // Check each member's expiration date
            for (Member member : members) {
                if (member.expirationDate.after(currentDate) && member.expirationDate.before(endOfMonth)) {
                    expiringMembers.add(member);
                }
            }

            return expiringMembers;
        }

        private static void writeToTextFile(List<Member> members, String fileName) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (Member member : members) {
                    writer.write(String.format("First Name: %s%n", member.firstName));
                    writer.write(String.format("Last Name: %s%n", member.lastName));
                    writer.write(String.format("Phone Number: %s%n", member.phoneNumber));
                    writer.write(String.format("Email: %s%n", member.email));
                    writer.write(String.format("Expiration Date: %s%n", member.expirationDate));
                    writer.write("------------------------------\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void displayMembers(List<Member> members) {
            for (Member member : members) {
                System.out.println("First Name: " + member.firstName);
                System.out.println("Last Name: " + member.lastName);
                System.out.println("Phone Number: " + member.phoneNumber);
                System.out.println("Email: " + member.email);
                System.out.println("Expiration Date: " + member.expirationDate);
                System.out.println("------------------------------");
            }
        }

        // You need to implement this method to retrieve members from your data source
        private static List<Member> getMembers() {
            // This is a placeholder; replace it with actual implementation
            return new ArrayList<>();
        }
    }

