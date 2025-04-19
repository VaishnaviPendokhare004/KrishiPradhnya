import java.sql.*;
import java.util.*;
public class LoginPage {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/krishidata";
        String USER = "root";
        String PASS = "Vvp555&...";
        String queryInsert = "Insert into soil(sid,stype,sph,snitrogen,sphosphorus,spotassium) values(?,?,?,?,?,?);";
        String queryUpdate = "Update soil set stype=?,sph=?,snitrogen=?,sphosphorus=?,spotassium=? where sid=?;";
        String queryDelete = "delete from soil where sid=?;";
        String queryRetrieve = "select * from crop;"; // crop schema is empty
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Retrieve 2. Insert 3. Update 4. Delete 5. Exit");
        int n = sc.nextInt();;
        while(n!=5){
            switch (n){
                case 1: { //Retrieve data...
                    try{
                        Connection con = DriverManager.getConnection(URL,USER,PASS);
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(queryRetrieve);
                        System.out.println("id\tname\tseason\tduration\tsoil id\n--\t----\t------\t--------\t-------");
                        while (rs.next()){
                            System.out.println(rs.getInt("cid")+"\t"+rs.getString("cname")+"\t"+rs.getString("cseason")+"\t"+rs.getString("cduration")+"\t\t\t"+rs.getInt("sid"));
                        }
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                break;
                case 2: { //Insert...
                    System.out.println("Enter soil id, type, ph, nitrogen, phosphorus, potassium : ");
                    int soilId = sc.nextInt();
                    String soilType = sc.next();
                    String soilPh = sc.next();
                    String soilNitrogen = sc.next();
                    String soilPhosphorus = sc.next();
                    String soilPotassium = sc.next();
                    try{
                        Connection con = DriverManager.getConnection(URL,USER,PASS);
                        PreparedStatement ps = con.prepareStatement(queryInsert);
                        ps.setInt(1,soilId);
                        ps.setString(2,soilType);
                        ps.setString(3,soilPh);
                        ps.setString(4,soilNitrogen);
                        ps.setString(5,soilPhosphorus);
                        ps.setString(6,soilPotassium);
                        ps.executeUpdate();
                        System.out.println("Data inserted");
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                break;
                case 3: { //Update...
                    System.out.println("Enter soil type, ph, nitrogen, phosphorus, potassium & id: ");
                    String soilType = sc.next();
                    String soilPh = sc.next();
                    String soilNitrogen = sc.next();
                    String soilPhosphorus = sc.next();
                    String soilPotassium = sc.next();
                    int soilId = sc.nextInt();
                    try{
                        Connection con = DriverManager.getConnection(URL,USER,PASS);
                        PreparedStatement ps = con.prepareStatement(queryUpdate);
                        ps.setString(1,soilType);
                        ps.setString(2,soilPh);
                        ps.setString(3,soilNitrogen);
                        ps.setString(4,soilPhosphorus);
                        ps.setString(5,soilPotassium);
                        ps.setInt(6,soilId);
                        ps.executeUpdate();
                        System.out.println("Data updated");
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                break;
                case 4: { //Delete...
                    System.out.println("Enter soil type, ph, nitrogen, phosphorus, potassium & id: ");
                    int soilId = sc.nextInt();
                    try{
                        Connection con = DriverManager.getConnection(URL,USER,PASS);
                        PreparedStatement ps = con.prepareStatement(queryDelete);
                        ps.setInt(1,soilId);
                        ps.executeUpdate();
                        System.out.println("Data deleted");
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                break;
                case 5: break;
                default:
                    System.out.println("Enter correct option!");;
            }
            System.out.println("Choose option again...");
            n = sc.nextInt();
        }
    }
}
