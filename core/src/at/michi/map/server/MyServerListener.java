package at.michi.map.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import at.michi.map.networkClasses.LoginRequest;
import at.michi.map.networkClasses.LoginResponse;

/**
 * Created by Michi on 07.04.2017.
 */

public class MyServerListener extends Listener {

    @Override
    public void received(Connection connection, Object object){
        LoginResponse response = new LoginResponse();
        response.setResponseText("Hallo wer bist du?");
        connection.sendTCP(response);
        if(object instanceof LoginRequest){
            LoginRequest request = (LoginRequest) object;
            System.out.println(request.getMessageText());
        }
    }
}
