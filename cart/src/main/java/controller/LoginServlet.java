package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import murach.business.User;
import murach.data.UserDB;

public class LoginServlet extends HttpServlet {


@Override
protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    User user = UserDB.selectUser(email, password);

    if (user != null) {

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        response.sendRedirect("cart?action=checkout");

    } else {

        request.setAttribute("error", "Email chưa được đăng ký.");

        request.getRequestDispatcher("login.jsp")
               .forward(request, response);
    }
}


}
