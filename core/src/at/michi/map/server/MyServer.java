package at.michi.map.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import at.michi.map.networkClasses.LoginRequest;
import at.michi.map.networkClasses.LoginResponse;

import java.io.IOException;

/**
 * Created by Michi on 07.04.2017.
 */

public class MyServer {
    private int TCP_PORT, UDP_PORT;
    private Server server;
    private Kryo kryo;

    public MyServer(int tcp, int udp){
        this.TCP_PORT = tcp;
        this.UDP_PORT = udp;

        server = new Server();
        kryo = server.getKryo();
        registerKryoClasses();
    }

    public void startServer(){
        server.start();
        try {
            server.bind(TCP_PORT, UDP_PORT);
            server.addListener(new MyServerListener());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void stopServer(){
        server.stop();
    }

    private void registerKryoClasses(){
        kryo.register(LoginRequest.class);
        kryo.register(LoginResponse.class);
    }
}
