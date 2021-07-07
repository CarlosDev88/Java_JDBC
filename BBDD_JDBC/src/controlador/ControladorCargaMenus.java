package controlador;

import java.awt.event.*;

import modelo.CargaMenus;
import vista.MarcoAplicacion;

public class ControladorCargaMenus extends WindowAdapter {
	CargaMenus obj = new CargaMenus();
	MarcoAplicacion elmarco;

	public ControladorCargaMenus(MarcoAplicacion elmarco) {
		this.elmarco = elmarco;

	}

	@Override
	public void windowOpened(WindowEvent event) {
		obj.ejecutaConsultas();

		try {
			while(obj.rs.next()) {
				elmarco.secciones.addItem(obj.rs.getString(1));
			}
			
			while(obj.rs2.next()) {
				elmarco.paises.addItem(obj.rs2.getString(1));
			}

		} catch (Exception e) {
			System.out.println("aca hay problemas");
			e.printStackTrace();
		}
	}

}
