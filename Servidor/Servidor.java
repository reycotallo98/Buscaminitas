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
	System.out.println(tablero);
	jugador1.tablero = tablero;
	jugador2.tablero = tablero;
	jugador1.turno = true;
	Thread juga1 = new Thread(jugador1);
	Thread juga2 = new Thread(jugador1);
	// TODO Auto-generated method stub
	
	juga1.start();
	juga2.start();
	
	while(true) {
		
		jugador2.tableroOculto = jugador1.tableroOculto;
		while(jugador1.getTurno() ) {
			
			
			try {
				juga2.wait(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		jugador2.setTurno(true);
		
		while(jugador2.getTurno() ) {
			
			
			try {
				juga1.wait(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		jugador1.setTurno(true);
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
