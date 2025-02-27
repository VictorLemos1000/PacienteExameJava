package org.prontuario.app;

import java.util.Scanner;

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
		Scanner input = new Scanner(System.in);
		
		try {
			p = pdao.findById(1L);
			System.out.println(p);
		}catch(PacienteNaoEncontradoException excecao) {
			JOptionPane.showMessageDialog(null, excecao.getMessage());
		}
		
		System.out.println(" Insira um das opções para realizar as tarefas do CRUD:");
		System.out.println(" Digite 1: caso queira se cadastrar no sistema.");
		System.out.println(" Digite 2: caso o paciente cadastrado queira cadastrar um exame.");
		System.out.println(" Digite 3: caso caso queira listar os paciente cadastrados.");
		int opcao = input.nextInt();
		
		switch (opcao) {
		case 1:
			
			break;

		case 2:
			
			break;
			
		case 3:
			
			break;
			
		case 4:
			
			break;
		default:
			break;
		}
	}
}
