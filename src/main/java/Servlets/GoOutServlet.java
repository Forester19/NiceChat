package Servlets;

import Accaunts.AccauntService;
import Tamplate.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Владислав on 09.02.2017.
 */
public class GoOutServlet extends HttpServlet {
    private AccauntService accauntService;
    private SignInServlet signInServlet;
//    private String s = signInServlet.getLogin();



    public GoOutServlet(AccauntService accauntService) {
        this.accauntService = accauntService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Object> data = accauntService.getMapLoginToProfile();
       // s = signInServlet.getLogin();
        //accauntService.deleteByLogin(s);
        resp.getWriter().println(PageGenerator.instance().getPage("html/index.html", data));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
