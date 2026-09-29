package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    public static class Product {
        public String id;
        public String description;
        public double price;
        public Product(String id, String description, double price) {
            this.id = id;
            this.description = description;
            this.price = price;
        }
    }
    public static class CartItem {

        public Product product;
        public int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public double getAmount() {
            return product.price * quantity;
        }
    }
    private Product getProduct(String id) {

        if (id.equals("P1")) {
            return new Product("P1","86 (the band) - True Life Songs and Pictures",14.95);
        }

        if (id.equals("P2")) {
            return new Product("P2","Paddlefoot - The first CD",12.95);
        }

        if (id.equals("P3")) {
            return new Product(
                    "P3",
                    "Paddlefoot - The second CD",
                    14.95
            );
        }

        if (id.equals("P4")) {
            return new Product("P4","Joe Rut - Genuine Wood Grained Finish",14.95);
        }

        return null;
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            showCart(request, response);
        }

        else if (action.equals("add")) {
            addToCart(request, response);
        }
        else if (action.equals("update")) {
            updateCart(request, response);
        }
        else if (action.equals("remove")) {
            removeFromCart(request, response);
        }
        else if (action.equals("checkout")) {
            checkout(request, response);
        }
        else {
            showCart(request, response);
        }
    }

    private void addToCart(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String id = request.getParameter("id");
        Cookie[] cookies = request.getCookies();
        int quantity = 0;
        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("cart_" + id)) {

                    quantity = Integer.parseInt(cookie.getValue());
                }
            }
        }

        quantity++;
        Cookie cookie =new Cookie("cart_" + id, String.valueOf(quantity));
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("cart");
    }

    private void showCart(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<CartItem> cartItems = getCartItems(request);
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }


    private List<CartItem> getCartItems(
            HttpServletRequest request) {

        List<CartItem> cartItems = new ArrayList<>();

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return cartItems;
        }

        for (Cookie cookie : cookies) {

            String name = cookie.getName();

            if (name.startsWith("cart_")) {

                String id = name.substring(5);

                int quantity =Integer.parseInt(cookie.getValue());

                Product product = getProduct(id);

                if (product != null && quantity > 0) {
                    cartItems.add(new CartItem(product, quantity)
                    );
                }
            }
        }

        return cartItems;
    }
    private void updateCart(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        String quantityString =
                request.getParameter("quantity");

        int quantity =
                Integer.parseInt(quantityString);

        Cookie cookie =
                new Cookie(
                        "cart_" + id,
                        String.valueOf(quantity)
                );

        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("cart");
    }

    private void removeFromCart(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String id = request.getParameter("id");

        Cookie cookie =
                new Cookie("cart_" + id, "");

        cookie.setMaxAge(0);

        cookie.setPath("/");

        response.addCookie(cookie);

        response.sendRedirect("cart");
    }

    private void checkout(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<CartItem> cartItems = getCartItems(request);
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getAmount();
        }

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("total", total);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }
}