import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExhibitionDBManager {
    // 1. Database path
    private static final String DB_PATH = "jdbc:ucanaccess://C:/Users/YourName/Desktop/VUExhibitionSystem/VUE_Exhibition.accdb";

    // 2. Connection method
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_PATH);
    }

    // 3. Register
    public static void registerParticipant(String regID, String name, String faculty, String title,
                                           String contact, String email, String imagePath) {
        String sql = "INSERT INTO Participants (RegID, Name, Faculty, ProjectTitle, Contact, Email, ImagePath) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = connect(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, regID);
            pst.setString(2, name);
            pst.setString(3, faculty);
            pst.setString(4, title);
            pst.setString(5, contact);
            pst.setString(6, email);
            pst.setString(7, imagePath);

            pst.executeUpdate();
            System.out.println("✅ Participant registered successfully.");
        } catch (SQLException e) {
            System.err.println("❌ Registration error: " + e.getMessage());
        }
    }

    // 4. Search
    public static ResultSet searchParticipant(String regID) {
        String sql = "SELECT * FROM Participants WHERE RegID = ?";
        try {
            Connection con = connect();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, regID);
            return pst.executeQuery();
        } catch (SQLException e) {
            System.err.println("❌ Search error: " + e.getMessage());
            return null;
        }
    }

    // 5. Update
    public static void updateParticipant(String regID, String name, String faculty, String title,
                                         String contact, String email, String imagePath) {
        String sql = "UPDATE Participants SET Name=?, Faculty=?, ProjectTitle=?, Contact=?, Email=?, ImagePath=? WHERE RegID=?";

        try (Connection con = connect(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, faculty);
            pst.setString(3, title);
            pst.setString(4, contact);
            pst.setString(5, email);
            pst.setString(6, imagePath);
            pst.setString(7, regID);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Participant updated.");
            } else {
                System.out.println("⚠️ No record found.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Update error: " + e.getMessage());
        }
    }

    // 6. Delete
    public static void deleteParticipant(String regID) {
        String sql = "DELETE FROM Participants WHERE RegID=?";

        try (Connection con = connect(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, regID);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("🗑️ Participant deleted.");
            } else {
                System.out.println("⚠️ No record to delete.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Deletion error: " + e.getMessage());
        }
    }

    // 7. Testing Main
    public static void main(String[] args) {
        // Test Register
        registerParticipant("REG005", "Ivan Jimmy", "Engineering", "Smart Irrigation", "0759123456", "ivan@vu.ac.ug", "C:/images/project.jpg");

        // Test Search
        try (ResultSet rs = searchParticipant("REG005")) {
            if (rs != null && rs.next()) {
                System.out.println("Name: " + rs.getString("Name"));
            } else {
                System.out.println("No such participant.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test Update
        updateParticipant("REG005", "Ivan J.", "Engineering", "Smart Irrigation V2", "0759123456", "ivan@vu.ac.ug", "C:/images/project2.jpg");

        // Test Delete
        deleteParticipant("REG005");
    }
}
