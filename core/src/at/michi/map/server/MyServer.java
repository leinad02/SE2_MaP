package at.michi.map.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import at.michi.map.Main;
import at.michi.map.networkClasses.ClientName;
import at.michi.map.networkClasses.ClientRegister;
import at.michi.map.networkClasses.ForClient;
import at.michi.map.networkClasses.ForServer;
import at.michi.map.networkClasses.LoginRequest;
import at.michi.map.networkClasses.LoginResponse;
import at.michi.map.networkClasses.ServerName;

import java.io.IOException;

/**
 * Created by Michi on 08.04.2017.
 */

public class MyServer {
    private int TCP_PORT, UDP_PORT;
    private Server server;
    private Kryo kryo;
    ClientRegister clientRegister;
    ForServer forServer;

    public MyServer(int tcp, int udp){
        this.TCP_PORT = tcp;
        this.UDP_PORT = udp;

        server = new Server();
        clientRegister = new ClientRegister();
        forServer = new ForServer();
        kryo = server.getKryo();
        registerKryoClasses();
    }

    public void startServer(MyServer myServer, final Main game, String name){
        server.start();
        try {
            server.bind(TCP_PORT, UDP_PORT);
            MyServerListener listener = new MyServerListener(clientRegister, name, forServer);
            server.addListener(listener);
            while(!clientRegister.isLogin()){
                System.out.println(clientRegister.isLogin());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServerName serverName = new ServerName();
        serverName.setNameFromServer(name);
        server.sendToAllTCP(serverName);
        game.gotoGameScreen(myServer, name, forServer.getName());
    }


    public void stopServer(){
        server.stop();
    }

    private void registerKryoClasses(){
        kryo.register(LoginRequest.class);
        kryo.register(LoginResponse.class);
        kryo.register(ClientRegister.class);
        kryo.register(ClientName.class);
        kryo.register(ServerName.class);
        kryo.register(ForServer.class);
        kryo.register(ForClient.class);
    }
}
