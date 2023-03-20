package Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import Servidor.*;

public class Jugador  {

	
	static InetAddress Ip;
	Boolean turno;
	public static DatagramSocket server;
	int puerto;
	ArrayList<ArrayList<Integer>> tablero;
	ArrayList<ArrayList<Integer>> tableroOculto;

	boolean ganador = true;
	public Jugador(InetAddress ip, Boolean turno, DatagramSocket server, int i) {
		super();
		Ip = ip;
		this.turno = turno;
		this.server = server;
		this.puerto = i;

		
	}
	public DatagramSocket getServer() {
		return server;
	}
	public void setServer(DatagramSocket server) {
		this.server = server;
	}
	
	public InetAddress getIp() {
		return Ip;
	}
	public void setIp(InetAddress ip) {
		Ip = ip;
	}
	public Boolean getTurno() {
		return turno;
	}
	public void setTurno(Boolean turno) {
		this.turno = turno;
	}
	
	


	
}
		
	
	
	
	
			      
		       
	
	

	
	
	
