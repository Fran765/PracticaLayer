package ejercicio1.infraestructura.ui;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ejercicio1.dominio.portsin.DominioException;
import ejercicio1.dominio.portsin.ParticipanteRecord;
import ejercicio1.dominio.portsin.RegistroDeParticipante;

public class AgregarParticianteView extends JFrame {

	private RegistroDeParticipante registro;
	private JTextField nombre;
	private JTextField telefono;
	private JTextField region;

	public AgregarParticianteView(RegistroDeParticipante registro) {

		this.registro = registro;
		setupUIComponents();
	}

	private void setupUIComponents() {

		setTitle("Add Participant");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		nombre = new JTextField(10);
		telefono = new JTextField(10);
		region = new JTextField(10);
		nombre.setText("");
		telefono.setText("");
		region.setText("China");

		JPanel contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		contentPane.add(new JLabel("Nombre: "));
		contentPane.add(nombre);
		contentPane.add(new JLabel("Telefono: "));
		contentPane.add(telefono);
		contentPane.add(new JLabel("Region: "));
		contentPane.add(region);

		JButton botonCargar = new JButton("Cargar");
		botonCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					registro.registrar(new ParticipanteRecord(nombre.getText(), telefono.getText(), region.getText()));
					dispose();

				} catch (DominioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());

				}
			}
		});

		contentPane.add(botonCargar);
		setContentPane(contentPane);
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pack();
		setVisible(true);
	}
}
