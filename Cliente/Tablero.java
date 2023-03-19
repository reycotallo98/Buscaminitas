package Cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Tablero extends JFrame {

	private static final long serialVersionUID = 1L;
	final int n = 8;
	JButton boton[][];
	ActionListener accion;
	boolean activo;
	
	Integer[][] array;
	Font f;
	int pulsadox;
	int pulsadoy;
	public String movimiento;
	boolean pulsado;
	JButton[][] botones = new JButton[8][8];

	public Tablero(Integer[][] arr,boolean turno) {
		super("Buscaminas");
		setSize(500, 500);
		array = arr;
		movimiento ="";
		pulsado = false;
		setResizable(false);
		f = new Font(Font.MONOSPACED, Font.PLAIN, 25);
		accion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!pulsado) {
				movimiento = e.getActionCommand();
				
				System.out.println("Presionaste el botón: " + movimiento);
				pulsado = true;
				}
				
				
			}
		
		};
		activo = turno;
		Activo();
		boton = new JButton[n][n];
		setLayout(new GridLayout(n, n));
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if(array[i][j] == -2) {
				boton[i][j] = new JButton();
				boton[i][j].setActionCommand("mov,"+i+"," + j);
				boton[i][j].addActionListener(accion);
				boton[i][j].setEnabled(turno);
				boton[i][j].setBackground(Color.blue);
				
				
				
				add(boton[i][j]);
				botones[i][j] = boton[i][j];
				}else if (array[i][j] == -1) {
					boton[i][j] = new JButton();
					boton[i][j].setActionCommand("");
					boton[i][j].setEnabled(false);
					
					boton[i][j].setText("*");
					add(boton[i][j]);
					botones[i][j] = boton[i][j];
				}else {
					boton[i][j] = new JButton();
					boton[i][j].setEnabled(false);
					
					boton[i][j].setText(array[i][j]+"");
					add(boton[i][j]);
					botones[i][j] = boton[i][j];
				}
			}
		repaint();
		setVisible(true);
	}

	public  String getmov() {
		return this.movimiento;
	}

	public void pintar(Integer[][] arr,boolean turno) {
		arr = array;
		activo = turno;
		Activo();
		setVisible(false);
		

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if(array[i][j] == -2) {
				
				botones[i][j].setActionCommand("mov,"+i+"," + j);
				botones[i][j].addActionListener(accion);
				botones[i][j].setEnabled(turno);
				if(turno) {
				botones[i][j].setBackground(Color.blue);
				}else{

					botones[i][j].setBackground(Color.red);
				}
				
				
				
				}else if (array[i][j] == -1) {
					
					botones[i][j].setActionCommand("");
					botones[i][j].setEnabled(false);
					
					boton[i][j].setText("*");
					
				}else {
					
					botones[i][j].setEnabled(false);
					
					botones[i][j].setText(array[i][j]+"");
				
				}
			}
		repaint();
		setVisible(true);
	}

	public void Activo() {
		if(activo) {
			this.setTitle("Es tu turno");
			this.activo = false;
		}else {
			this.setTitle("Esperando oponente");
			this.activo = true;
		}
	}

	
}
