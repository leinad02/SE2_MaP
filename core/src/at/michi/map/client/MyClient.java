package at.michi.map.client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import java.io.IOException;

import at.michi.map.networkClasses.ClientRegister;
import at.michi.map.networkClasses.LoginRequest;
import at.michi.map.networkClasses.LoginResponse;

/**
 * Created by Michi on 07.04.2017.
 */

public class MyClient {
    private int TCP_PORT, UDP_PORT, TIMEOUT;
    private Client client;
    private Kryo kryo;

    public MyClient(int tcp, int udp, int timeout){
        this.TCP_PORT = tcp;
        this.UDP_PORT = udp;
        this.TIMEOUT = timeout;

        client = new Client();
        kryo = client.getKryo();
        registerKryoClasses();
    }

    public void connect(String serverIp){
        try {
            client.start();
            client.connect(TIMEOUT, serverIp, TCP_PORT, UDP_PORT);
            client.addListener(new MyClientListener());
            LoginRequest req = new LoginRequest();
            req.setMessageText("verbunden");
            client.sendTCP(req);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void disconnect(){
        client.stop();
    }

    private void registerKryoClasses(){
        kryo.register(LoginRequest.class);
        kryo.register(LoginResponse.class);
        kryo.register(ClientRegister.class);
    }
}
