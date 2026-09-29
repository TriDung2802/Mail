package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import murach.business.User;
import murach.util.MailUtil;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

@Override
protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session = request.getSession();

    // 1. Lấy người dùng đã đăng nhập
    User user = (User) session.getAttribute("user");

    // Nếu chưa đăng nhập thì quay về login
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // 2. Lấy tổng tiền
    Object total = request.getAttribute("total");

    if (total == null) {
        total = session.getAttribute("total");
    }

    // 3. Lấy email của tài khoản đang đăng nhập
    String to = user.getEmail();

    // 4. Nội dung email
    String subject = "Thank you for your purchase";

    String body =
            "Dear " + user.getFirstName() + ",\n\n"
            + "Thank you for your purchase!\n\n"
            + "Your payment was successful.\n"
            + "Total payment: $" + total + "\n\n"
            + "Thank you for shopping with us!\n\n"
            + "Murach's Store";

    // 5. Gửi email
    try {
    MailUtil.sendMail(to, subject, body);
    System.out.println("Email sent successfully to: " + to);
} catch (Exception e) {
    System.out.println("Failed to send email: " + e.getMessage());
    e.printStackTrace();
}

    // 6. Đưa total sang thanks.jsp
    request.setAttribute("total", total);

    // 7. Chuyển sang trang cảm ơn
    request.getRequestDispatcher("thanks.jsp")
            .forward(request, response);
}

}
