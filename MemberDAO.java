package members;


import java.sql.*;

public class MemberDAO extends DBConfig {

 public void addMember(int id, String name, int age, String plan) {
     try (Connection conn = getConnection()) {
         String sql = "INSERT INTO Members (Member_Id, Member_Name, Member_Age, Member_Plan) VALUES (?, ?, ?, ?)";
         PreparedStatement stmt = conn.prepareStatement(sql);
         stmt.setInt(1, id);
         stmt.setString(2, name);
         stmt.setInt(3, age);
         stmt.setString(4, plan);

         stmt.executeUpdate();
         System.out.println("✅ Member Joined!");
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }

 public void viewMembers() {
     try (Connection conn = getConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT * FROM members")) {
         
         System.out.println("\nID\tName\tAge\tPlan");
         System.out.println("----------------------------");
         while (rs.next()) {
             System.out.println(rs.getInt("Member_Id") + "\t" + 
                                rs.getString("Member_Name") + "\t" + 
                                rs.getInt("Member_Age") + "\t" + 
                                rs.getString("Member_Plan"));
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }

 public void updatePlan(int id, String newPlan) {
     try (Connection conn = getConnection()) {
         String sql = "UPDATE members SET plan = ? WHERE id = ?";
         PreparedStatement stmt = conn.prepareStatement(sql);
         stmt.setString(1, newPlan);
         stmt.setInt(2, id);

         int rowsAffected = stmt.executeUpdate();
         if (rowsAffected > 0) {
              System.out.println("✅ Plan Updated!");
         } else {
              System.out.println("⚠️ Member ID not found.");
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }

 public void deleteMember(int id) {
     try (Connection conn = getConnection()) {
         String sql = "DELETE FROM members WHERE id = ?";
         PreparedStatement stmt = conn.prepareStatement(sql);
         stmt.setInt(1, id);

         int rowsAffected = stmt.executeUpdate();
          if (rowsAffected > 0) {
              System.out.println("✅ Member Removed.");
         } else {
              System.out.println("⚠️ Member ID not found.");
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
}
