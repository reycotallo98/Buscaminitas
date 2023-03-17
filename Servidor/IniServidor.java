package Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import Servidor.*;
public class IniServidor {
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket serverSocket = new DatagramSocket(3000);
		//DatagramSocket serverSocket=new DatagramSocket(3000,InetAddress.getByName("192.168.204.1"));    
	    byte[] recibidos=new byte[1024];
	    byte[] enviados=new byte[1024];
	    String cadena;

	    ArrayList<Servidor> partidas = new ArrayList<>();
	    
	    while(true){
	      System.out.println(" Esperando Datagrama...");
	      // RECIBO DATAGRAMA
	      recibidos=new byte[1024]; // prueba a comentarlo y ve el fallo
	      DatagramPacket paqRecibido=new DatagramPacket(recibidos,recibidos.length);  
	      serverSocket.receive(paqRecibido);
	      cadena=new String(paqRecibido.getData());
	      
	      if (cadena.equals("quierojugar")) {
	      //DIRECCION ORIGEN
	    	  Jugador nuevo = new Jugador(paqRecibido.getAddress(),false,serverSocket,paqRecibido.getPort());
	    	  for(Servidor partida : partidas) {
	    	  if (partida.players<2) {
	    		  if(partida.players<1) {
	    			  partida.jugador1=nuevo;  
	    			  partida.players++;
	    			  ArrayList<Integer[]> a = new ArrayList<>();
	    			  for(int i = 0; i < 8;i++ ) {
	    				  for(int b = 0; b < 8;b++ ) {
	    					  ArrayList<Integer> ll = new ArrayList<>();
		    				  if(b < ((int)(Math.random()*7))) {
		    					  ll.add(-1);
		    				  }else {
		    					  ll.add(-2);
		    				  }
		    				 Collections.shuffle(ll);
		    				  a.add((Integer[]) ll.toArray());
	    				  }}
	    			  partida.tablero = (Integer[][]) a.toArray();
	    		  }else if(partida.players<2){
	    			  partida.jugador2=nuevo;
	    			  partida.players++;
	    			  partida.run();
	    		  }
	    		
	    		  
	    		  }
	    	  }
	      
	 
	      
	      
	    }
		
}
	}

}
