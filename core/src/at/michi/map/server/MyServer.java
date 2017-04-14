package at.michi.map.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import at.michi.map.Main;
import at.michi.map.networkClasses.ClientRegister;
import at.michi.map.networkClasses.LoginRequest;
import at.michi.map.networkClasses.LoginResponse;

import java.io.IOException;

/**
 * Created by Michi on 08.04.2017.
 */

public class MyServer {
    private int TCP_PORT, UDP_PORT;
    private Server server;
    private Kryo kryo;
    ClientRegister clientRegister;

    public MyServer(int tcp, int udp){
        this.TCP_PORT = tcp;
        this.UDP_PORT = udp;

        server = new Server();
        clientRegister = new ClientRegister();
        kryo = server.getKryo();
        registerKryoClasses();
    }

    public void startServer(final Main game){
        server.start();
        try {
            server.bind(TCP_PORT, UDP_PORT);
            MyServerListener listener = new MyServerListener(clientRegister);
            server.addListener(listener);
            while(!clientRegister.isLogin()){
                game.gotoServerScreen();
                System.out.println(clientRegister.isLogin());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        game.gotoGameScreen();
    }


    public void stopServer(){
        server.stop();
    }

    private void registerKryoClasses(){
        kryo.register(LoginRequest.class);
        kryo.register(LoginResponse.class);
        kryo.register(ClientRegister.class);
    }
}
