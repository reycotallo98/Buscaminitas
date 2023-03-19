package Servidor;
import java.util.ArrayList;

import Servidor.*;

public class Servidor implements Runnable {
	// Si no quiero limitar la escucha a una interfaz
Jugador jugador1;
Jugador jugador2;
ArrayList<ArrayList<Integer>> tablero;
int players = 0;
public Servidor( ) {
	super();
	
	
}
@Override
public void run() {
	jugador1.tablero = tablero;
	jugador2.tablero = tablero;
	jugador1.turno = true;
	Thread juga1 = new Thread(jugador1);
	Thread juga2 = new Thread(jugador1);
	// TODO Auto-generated method stub
	
	juga1.run();
	juga2.run();
	
	while(true) {
		
		jugador2.tableroOculto = jugador1.tableroOculto;
		while(jugador1.turno ) {
			
			
			try {
				juga2.wait();
				juga1.notify();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		jugador2.turno = true;
		
		while(jugador2.turno ) {
			
			try {
				juga2.notify();
				juga1.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jugador1.tableroOculto = jugador2.tableroOculto;
		}
		jugador1.turno = true;
		if(jugador1.ganador == false) {
			jugador2.ganar();
			jugador1.perder();
		}
		if(jugador2.ganador == false) {
			jugador2.perder();
			jugador1.ganar();
			
		}
	}
	
}


	     }
