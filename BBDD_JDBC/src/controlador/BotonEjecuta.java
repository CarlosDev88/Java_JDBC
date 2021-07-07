package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import modelo.EjecutaConsultas;
import vista.MarcoAplicacion;

public class BotonEjecuta implements ActionListener {
	private MarcoAplicacion elmarco;
	EjecutaConsultas consulta = new EjecutaConsultas();
	private ResultSet resultadoConsulta;

	public BotonEjecuta(MarcoAplicacion elmarco) {
		this.elmarco = elmarco;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String seleccionSeccion = (String) elmarco.secciones.getSelectedItem();
		String seleccionPais = (String) elmarco.paises.getSelectedItem();
		
		
	resultadoConsulta = consulta.filtraBBDD(seleccionSeccion, seleccionPais);
		
		try {
			elmarco.resultado.setText("");
			while(resultadoConsulta.next()) {
				elmarco.resultado.append(resultadoConsulta.getString(1));
				elmarco.resultado.append(" | ");
				elmarco.resultado.append(resultadoConsulta.getString(2));
				elmarco.resultado.append(" | ");
				elmarco.resultado.append(resultadoConsulta.getString(3));
				elmarco.resultado.append(" | ");
				elmarco.resultado.append(resultadoConsulta.getString(4));
				elmarco.resultado.append("\n");
				
			}
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		
	}

}
