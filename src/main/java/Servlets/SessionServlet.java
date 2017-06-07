package Servlets;

import Accaunts.AccauntService;
import Accaunts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionServlet extends HttpServlet {
    private AccauntService accauntService;

    public SessionServlet(AccauntService accauntService) {
        this.accauntService = accauntService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionId = req.getSession().getId();
        UserProfile userProfile = accauntService.getProfileBySessionId(sessionId);
        if (userProfile == null){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else {
            Gson gson = new Gson();
            String json = gson.toJson(userProfile);
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String login = req.getParameter("login");
       String pass = req.getParameter("pass");

        if (login == null || pass == null){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("NULL");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile userProfile = accauntService.getUsersByLogin(login);

        if (userProfile == null || !userProfile.getPassvord().equals(pass)){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        accauntService.addSessionId(req.getSession().getId(), userProfile);
        Gson gson = new Gson();
        String json = gson.toJson(userProfile);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(json);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String session = req.getSession().getId();
        UserProfile userProfile = accauntService.getProfileBySessionId(session);

        if (userProfile == null){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("NO exist users");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else{
            accauntService.deleteSession(session);
            resp.setContentType("text/html;charset=utf-8");
            Gson json = new Gson();
            String j = json.toJson("Goodbye");
            resp.getWriter().println(j);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
