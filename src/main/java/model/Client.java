package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

public class Client {

    private int port = 6789;
    private String ipAddress;
    private DatagramSocket socket;
    private InetAddress host;
    private Jogador jogador;

    public Client(String ipAddress, Jogador jogador) {
        try {
            this.ipAddress = ipAddress;
            socket = new DatagramSocket();
            host = InetAddress.getByName(ipAddress);
            this.jogador = jogador;
        } catch (SocketException ex) {
            System.out.println("Erro Socket: " + ex.getLocalizedMessage());
        } catch (UnknownHostException ex) {
            System.out.println("Erro Host: " + ex.getLocalizedMessage());
        }
    }

    public void enviarChute(String msg) {
        try {
            DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.getBytes().length, host, port);
            socket.send(request);
        } catch (IOException ex) {
            System.out.println("Erro Envio: " + ex.getLocalizedMessage());
        }
    }
    
    public void enviarChavePrivada()
    {
    	try
    	{    		
    		DatagramPacket request = new DatagramPacket(jogador.getPrivateKey(), jogador.getPrivateKey().length, host, port);
            socket.send(request);
    	}catch(Exception e)
    	{
    		
    	}
    }
}