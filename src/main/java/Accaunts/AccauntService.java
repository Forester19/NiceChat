package Accaunts;

import java.util.HashMap;
import java.util.Map;


public class AccauntService {
    private  Map<String,UserProfile> loginToProfile;
    private Map<String,UserProfile> sessionToProfile;

    public AccauntService() {
        loginToProfile = new HashMap<String,UserProfile>();
        sessionToProfile = new HashMap<String,UserProfile>();
    }
    public void addNewUser(UserProfile userProfile){
    loginToProfile.put(userProfile.getLogin(),userProfile);
    }
    public UserProfile getUsersByLogin(String login){
        UserProfile userProfile = loginToProfile.get(login);
       // Sting sb = userProfile.getLogin() + " " + userProfile.getPassvord() + " " + userProfile.getEmail();
        return userProfile;
    }
    public void addSessionId(String sessionid, UserProfile userProfile){
        sessionToProfile.put(sessionid,userProfile);
    }
    public UserProfile getProfileBySessionId(String sessionId){
        return sessionToProfile.get(sessionId);
    }
    public void deleteSession(String sessionId){
        loginToProfile.remove(sessionId);
    }
    public  Map getMapLoginToProfile(){
        return loginToProfile;
    }
    public int showSize(){

          return loginToProfile.size();

}
    public void deleteByLogin(String log){
         loginToProfile.remove(log);
    }
}
