package at.michi.map.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import at.michi.map.networkClasses.ClientName;
import at.michi.map.networkClasses.ClientRegister;
import at.michi.map.networkClasses.ForServer;
import at.michi.map.networkClasses.LoginRequest;
import at.michi.map.networkClasses.LoginResponse;

/**
 * Created by Michi on 07.04.2017.
 */

public class MyServerListener extends Listener {
    String connText = "verbunden";
    ClientRegister clientRegister;
    String name;
    ForServer forServer;

    public MyServerListener(ClientRegister clientRegister, String name, ForServer forServer) {
        this.clientRegister = clientRegister;
        this.name = name;
        this.forServer = forServer;
    }

    @Override
    public void received(Connection connection, Object object){
        if(object instanceof ClientName){
            ClientName clientname = (ClientName) object;
            this.forServer.setName(clientname.getNameFromClient());
        } else if(object instanceof LoginRequest){
            LoginRequest request = (LoginRequest) object;
            if(request.getMessageText().matches(connText)){
                System.out.println("Juchuuuu: " + clientRegister.isLogin());
                this.clientRegister.setLogin(true);
                connection.sendTCP(this.clientRegister);
            } else {
                System.out.println(request.getMessageText());
                LoginResponse response = new LoginResponse();
                response.setResponseText("Super!!!");
                connection.sendTCP(response);
            }
        }
    }

}
