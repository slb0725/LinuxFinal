import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/Text1")
public class Text1 extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://123.60.102.144/linux_final?useUnicode=true&characterEncoding=utf8";
    static final String USER = "root";
    static final String PASS = "Liujiang0913@";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
          response.setContentType("text/html");
          // set response encoding
          response.setCharacterEncoding("UTF-8");
          PrintWriter out = response.getWriter();

          Student stu = getStudent();

          out.println("<h1>hello world, " + stu.getName() + "</h1>"
                      +"<h1>id: " + stu.getId() + "</h1>"
                      +"<h1>name: " + stu.getName() + "</h1>"
                      +"<h1>age: " + stu.getAge() + "</h1>");
    }
    
    public Student getStudent() {
        Student stu = new Student();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM t_student WHERE id=4";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                stu.setName(rs.getString("name"));
                stu.setId(rs.getInt("id"));
                stu.setAge(rs.getString("age"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    return stu;
   
    }
    class Student {
      private String name;
      private int id;
      private String age;
      
      public String getName(){
        return name;
      }
      
      public String getAge(){
        return age;
      }
      
      public int getId(){
        return id;
      }
      
      
      public void setId(int id){
        this.id = id;
      }
      
      public void setName(String name){
        this.name = name;
      }
      public void setAge(String age){
        this.age = age;
      }
  }
}



