package Cliente;

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
	boolean pulos = false;
	Integer[][] arr;
	Font f;
	int pulsadox;
	int pulsadoy;

	public Tablero(Integer[][] array,boolean turno) {
		super("Buscaminas");
		setSize(500, 500);
		arr = array;
		this.activo = false;
		setResizable(false);
		f = new Font(Font.MONOSPACED, Font.PLAIN, 25);
		accion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Presionaste el botón: " + e.getActionCommand());
				
			}
		
		};
		boton = new JButton[n][n];
		setLayout(new GridLayout(n, n));
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if(array[i][j] == -2) {
				boton[i][j] = new JButton();
				
				boton[i][j].setActionCommand(Poner(i,j));
				boton[i][j].addActionListener(accion);
				
					boton[i][j].setEnabled(turno);
				
				boton[i][j].setFont(f);
				add(boton[i][j]);
				}else if (array[i][j] == -1) {
					boton[i][j] = new JButton();
					boton[i][j].setActionCommand(i + "-" + j);
					boton[i][j].setEnabled(false);
					boton[i][j].setFont(f);
					boton[i][j].setText("*");
					add(boton[i][j]);
				}else {
					boton[i][j] = new JButton();
					boton[i][j].setEnabled(false);
					boton[i][j].setFont(f);
					boton[i][j].setText(array[i][j]+"");
					add(boton[i][j]);
				}
			}
		repaint();
		setVisible(true);
	}

	public String Poner(int x,int y) {
		
		pulsadox = x;
		pulsadoy = y;
		pulos = true;
		return "";
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
