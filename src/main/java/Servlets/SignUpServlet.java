package Servlets;

import Accaunts.AccauntService;
import Accaunts.UserProfile;
import Tamplate.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Владислав on 23.01.2017.
 */
//@WebServlet(name = "signUpServlet", urlPatterns = {"/api/v1/signup"})
public class SignUpServlet extends HttpServlet {
    private AccauntService accauntService;
    String session;

    public SignUpServlet(AccauntService accauntService) {

        this.accauntService = accauntService;
    }

    public SignUpServlet() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String email = req.getParameter("email");
        String name = req.getParameter("user");




        if (login.isEmpty() || pass.isEmpty()) {
            Map<String, Object> data = accauntService.getMapLoginToProfile();
            resp.getWriter().println(PageGenerator.instance().getPage("NullFields.html", data));
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("NULL");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else {
            name = login;
            UserProfile userProfile = new UserProfile(login, pass, email);
            accauntService.addNewUser(userProfile);
            session = req.getSession().getId();
            accauntService.addSessionId(session, userProfile);
            Map<String, Object> data =accauntService.getMapLoginToProfile();
            resp.getWriter().println(PageGenerator.instance().getPage("SignUpResult.html", data));
            resp.getWriter().println("\n dear \n" + accauntService.getUsersByLogin(login).toString());

            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);

        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    public String getSession() {
        return session;
    }
}
