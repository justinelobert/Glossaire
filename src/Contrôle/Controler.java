package Contrôle;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Modele.ModeleDonnee;

public class Controler {

	public static void actualiseListe(DefaultTableModel modeltab) {
		modeltab.setRowCount(0);
		
		ArrayList<Object[]> ar = ModeleDonnee.afficheLexiques();

        for (Object[] objects : ar) {
            modeltab.addRow(objects);
        }
		
	}
}
