package servlets;

import database.DataSource;
import musicLibrary.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

@WebServlet("/result")
public class resultServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String path = "/result.jsp";
        ServletContext servletContext = getServletContext();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if(user == null){
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }
        try {
            DataSource dataSource = new DataSource();
            user = dataSource.getUser(user.getLogin(), user.getPassword());
            System.out.println(user);
            session.setAttribute("user", user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            try {
                requestDispatcher.forward(request, response);

            }catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
