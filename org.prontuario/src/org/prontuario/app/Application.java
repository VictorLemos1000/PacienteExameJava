package org.prontuario.app;

import javax.swing.JOptionPane;

import org.prontuario.dao.EntityDAO;
import org.prontuario.dao.PacienteDAO;
import org.prontuario.db.ConexaoMySQL;
import org.prontuario.exception.PacienteNaoEncontradoException;
import org.prontuario.model.IPaciente;
import org.prontuario.model.PacienteMasculino;
import org.prontuario.util.ConfigLoader;

public class Application {

	public static void main(String[] args) {
		
		EntityDAO<IPaciente> pdao = new PacienteDAO(new ConexaoMySQL());
		IPaciente p;
		
		try {
			p = pdao.findById(1L);
			System.out.println(p);
		}catch(PacienteNaoEncontradoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
		
		
	}
	
	
}
