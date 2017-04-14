package at.michi.map.client;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import at.michi.map.networkClasses.ClientRegister;
import at.michi.map.networkClasses.LoginRequest;
import at.michi.map.networkClasses.LoginResponse;

/**
 * Created by Michi on 07.04.2017.
 */

public class MyClientListener extends Listener {

    /*@Override
    public void connected(Connection connection){
        LoginRequest req = new LoginRequest();
        req.setMessageText("Ich möchte mich verbinden!");
        connection.sendTCP(req);
    }*/

    @Override
    public void received(Connection connection, Object object) {
        System.out.println("Message from server to client: ");
        if (object instanceof LoginResponse) {
            LoginResponse response = (LoginResponse) object;
            System.out.println(response.getResponseText());
        } else if(object instanceof ClientRegister){
            ClientRegister clientRegister = (ClientRegister) object;
            System.out.println(clientRegister.isLogin());
        }
    }
}
