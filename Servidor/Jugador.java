package Servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import Servidor.*;

public class Jugador implements Runnable {

	
	InetAddress Ip;
	Boolean turno;
	DatagramSocket server;
	int puerto;
	Integer[][] tablero;
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
	
	

	private String[] contar(int x, int y) {
		// TODO Auto-generated method stub
		int contador = 0;
		String revelad0 ="";
		for (int i = -1; i < 1; i++) {
			for (int j = -1; j < 1; j++) {
				
			if(tablero[x+i][y+j] == -1 && i != 0 && j!=0) {
				contador++;
				
			}
			}}
			if(contador==0) {
				
				for (int i = -1; i < 1; i++) {
					for (int j = -1; j < 1; j++) {
						
						revelad0 =revelar(x+i,y+j);
					}
			}}
		
		String[] a = {contador+"",revelad0};
		return a;
	}
	private String revelar(int i,int o) {
		// TODO Auto-generated method stub
		String celdas="";
		String[] contado = contar(i,o);
		String cont = contado[0];
		if (cont.equals("0")) {
			celdas = contado[1]+i+","+o+"-";
			tablero[i][o] = 0;
		}
		return cont;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Socket socket;
		byte[] mensaje="empieza".getBytes();
	      
	      //ENVIO DATAGRAMA AL CLIENTE
	      DatagramPacket paqEnviado=new DatagramPacket(mensaje,mensaje.length,Ip,puerto);
	      byte[] recibidos=new byte[1024];
		    byte[] enviados=new byte[1024];
		    
	      while(true){
	    	  if (turno) {
	    		  try {
	  				socket = new Socket(server.getInetAddress(),server.getPort());
	  			
	  				ObjectOutputStream objeto = new ObjectOutputStream(socket.getOutputStream());
	  				objeto.writeObject(tablero);
	  			} catch (IOException e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			
				}
	    	  mensaje = "mueve".getBytes();
	    	  paqEnviado=new DatagramPacket(mensaje,mensaje.length,Ip,puerto);
	    	  
		      System.out.println(" Esperando Datagrama...");
		      // RECIBO DATAGRAMA
		      recibidos=new byte[1024]; // prueba a comentarlo y ve el fallo
		      DatagramPacket paqRecibido=new DatagramPacket(recibidos,recibidos.length);  
		      try {
				server.receive(paqRecibido);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
		      String cadena=new String(paqRecibido.getData());
		      if(cadena.contains("movimiento") && turno && paqRecibido.getAddress().equals(Ip)) {
		    	  int x = Integer.parseInt(cadena.substring(cadena.indexOf(':'), cadena.indexOf('-')));
		       int y = Integer.parseInt(cadena.substring(cadena.indexOf('-')));
		       
		       movimiento(x, y);
		      
		    
			
		    	 
		       
		      
		      }}}
		      
	      
		
	}
	private void movimiento(int x, int y) {
		// TODO Auto-generated method stub
		 byte[] mensaje;
		 DatagramPacket paqEnviado;
		 
		switch (tablero[x][y]) {
			case -1: {
				
				mensaje = ("movimiento:"+x+","+y+"-bomba").getBytes();
				paqEnviado=new DatagramPacket(mensaje,mensaje.length,Ip,puerto);
				try {
					server.send(paqEnviado);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				turno = false;
			}
			case -2:
				String[] a = contar(x,y);
				mensaje = ("movimiento:"+x+y+"-numeroBombas."+a[0]).getBytes();
				paqEnviado=new DatagramPacket(mensaje,mensaje.length,Ip,puerto);
				try {
					server.send(paqEnviado);
					tablero[x][y] = Integer.parseInt(a[0]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(a[1].length()!=0) {
				mensaje = ("revelado:"+a[1]).getBytes();
				paqEnviado=new DatagramPacket(mensaje,mensaje.length,Ip,puerto);
				try {
					server.send(paqEnviado);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			     turno = false;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + tablero[x][y]);
		}
		
		       
		
		
		      }
			      
		       
	
	}
