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
public class SignInServlet extends HttpServlet {

    private AccauntService accauntService;
private UserProfile userProfile;

//    String email = userProfile.getEmail();

    private String login;
    private String pass;


    public SignInServlet(AccauntService accauntService) {
        this.accauntService = accauntService;
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        login = req.getParameter("login");
        pass = req.getParameter("pass");


        Map<String,Object> data = accauntService.getMapLoginToProfile();

        if (login.isEmpty() || pass.isEmpty()){
            resp.getWriter().println(PageGenerator.instance().getPage("NullFields.html", data));
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("NULL");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }
        else{
             userProfile = accauntService.getUsersByLogin(login);

            if (login.equals(userProfile.getLogin())&&pass.equals(userProfile.getPassvord())){

                resp.getWriter().println(PageGenerator.instance().getPage("html2/SignIn.html", data));
                resp.getWriter().println("\n client \n" + userProfile.toString());
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_OK);

            }
            else{
              //  resp.getWriter().println(PageGenerator.instance().getPage("SignIn.html", data));
                resp.getWriter().println("who are you??" + "<p><h1> musterlover</h1></p>");
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }





        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
}
