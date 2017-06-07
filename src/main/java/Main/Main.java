package Main;

//import Servlets.SessionServlet;


import Accaunts.AccauntService;
import Accaunts.UserProfile;
import Chat.WebSocketChatServlet;

import Servlets.SignInServlet;
import Servlets.SignUpServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by Владислав on 21.01.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        AccauntService accauntService = new AccauntService();

        accauntService.addNewUser(new UserProfile("admin"));
        accauntService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new SignUpServlet(accauntService)), "/api/v1/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accauntService)), "/api/v1/signin");


        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");
        ResourceHandler resourceHandler = new ResourceHandler();

        resourceHandler.setResourceBase("public_html");
        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, context});


        Server server = new Server(7070);
        server.setHandler(handlerList);
        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();


    }
}
