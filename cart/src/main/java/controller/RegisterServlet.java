package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import murach.business.User;
import murach.data.UserDB;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {

    // 1. Lấy dữ liệu từ form
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirmPassword");

    // 2. Kiểm tra password
    if (!password.equals(confirmPassword)) {

        request.setAttribute(
                "error",
                "Password và Confirm Password không giống nhau."
        );

        request.getRequestDispatcher("register.jsp")
                .forward(request, response);

        return;
    }

    // 3. Kiểm tra email đã tồn tại chưa
    User existingUser = UserDB.selectUserByEmail(email);

    if (existingUser != null) {

        request.setAttribute(
                "error",
                "Email này đã được đăng ký."
        );

        request.getRequestDispatcher("register.jsp")
                .forward(request, response);

        return;
    }

    // 4. Tạo User mới
    User user = new User();

    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPassword(password);

    // 5. Lưu vào database
    boolean success = UserDB.insert(user);

    if (success) {

    // 6. Đăng nhập ngay sau khi đăng ký
    HttpSession session = request.getSession();
    session.setAttribute("user", user);

    // 7. Quay lại Checkout
    response.sendRedirect("cart?action=checkout");

    } else {

    // Nếu lưu database thất bại
    request.setAttribute(
            "error",
            "Đăng ký thất bại. Không thể lưu tài khoản vào database."
    );

    request.getRequestDispatcher("register.jsp")
            .forward(request, response);

    }

}

}
