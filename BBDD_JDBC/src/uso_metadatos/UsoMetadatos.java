package uso_metadatos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UsoMetadatos {

	public static void main(String[] args) {
		new MarcoBBDD();
	}

}

class MarcoBBDD extends JFrame {

	public MarcoBBDD() {

		setBounds(300, 300, 700, 700);

		LaminaBBDD milamina = new LaminaBBDD();
		add(milamina);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

}

class LaminaBBDD extends JPanel {
	private JComboBox comboTablas;
	private JTextArea areaInformacion;
	private Connection miConexion2;
	private FileReader entrada;

	public LaminaBBDD() {

		setLayout(new BorderLayout());

		comboTablas = new JComboBox();

		areaInformacion = new JTextArea();

		add(areaInformacion, BorderLayout.CENTER);

		comboTablas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreTabla = (String) comboTablas.getSelectedItem();

				mostrarInfoTabla(nombreTabla);

			}

		});

		add(comboTablas, BorderLayout.NORTH);
		this.conectarBBDD();
		this.obtenerTablas();

	}

	public void conectarBBDD() {
		miConexion2 = null;
		String datosConfiguracionBBDD[] = new String[3];

		try {
			entrada = new FileReader("C:\\Users\\Usuario\\Documents\\PROGRAMACION\\cursos\\java\\BBDD_JDBC\\src\\uso_metadatos/datos_config.txt");
			BufferedReader buffer = new BufferedReader(entrada);

			for (int i = 0; i <= 2; i++) {
				datosConfiguracionBBDD[i] = buffer.readLine();
			}

			miConexion2 = DriverManager.getConnection(datosConfiguracionBBDD[0], datosConfiguracionBBDD[1],
					datosConfiguracionBBDD[2]);
			entrada.close();

		} catch (Exception e) {
			System.out.println("no conecta");
		}
	}

	public void mostrarInfoTabla(String nombreTabla) {
		ArrayList<String> campos = new ArrayList<String>();

		String consulta = "SELECT * FROM " + nombreTabla;

		try {
			Statement miStatement = miConexion2.createStatement();
			ResultSet rs = miStatement.executeQuery(consulta);

			ResultSetMetaData rsBBDD = rs.getMetaData();

			for (int i = 0; i < rsBBDD.getColumnCount(); i++) {
				campos.add(rsBBDD.getColumnLabel(i));
			}

			while (rs.next()) {
				for (String campo : campos) {
					areaInformacion.append(rs.getString(campo) + " ");
				}

				areaInformacion.append("\n");
			}
		} catch (Exception e) {
			System.out.println("problema al leer");
		}

	}

	public void obtenerTablas() {
		ResultSet miResulset = null;

		try {
			DatabaseMetaData datosBBDD = miConexion2.getMetaData();
			miResulset = datosBBDD.getTables(null, null, null, null);

			while (miResulset.next()) {
				comboTablas.addItem(miResulset.getString("TABLE_NAME"));

			}

		} catch (Exception e) {

		}
	}

}
