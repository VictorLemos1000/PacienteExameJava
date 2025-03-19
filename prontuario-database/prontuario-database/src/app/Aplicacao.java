package app;

import dao.ExameDAO;
import dao.PacienteDAO;
import db.DatabaseConnection;
import db.MySQLDatabaseConnection;
import exception.DatabaseConectionException;
import gui.TelaPrincipal;
import model.Paciente;

import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import service.ExameService;
import service.PacienteService;

public class Aplicacao {

    public static void main(String[] args) {
        DatabaseConnection dbConnection = null;
//        try {
            dbConnection = new MySQLDatabaseConnection();
            
            ExameDAO exameDAO = new ExameDAO(dbConnection);
            PacienteDAO pacienteDAO = new PacienteDAO(dbConnection);
            
            ExameService exameServ = new ExameService(exameDAO);
            PacienteService pacServ = new PacienteService(pacienteDAO);
            
            try {
				pacServ.adicionarPaciente(new Paciente(0L, "000", "Gustavo"));
			} catch (InputMismatchException | IllegalArgumentException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
//            SwingUtilities.invokeLater(() -> {
//                try {
//                    new TelaPrincipal(pacServ, exameServ).setVisible(true);
//                } catch (Exception e) {
//                    System.err.println("Erro ao inicializar a interface gráfica: " + e.getMessage());
//                    JOptionPane.showMessageDialog(null, "Não foi possível iniciar a interface gráfica: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//                }
//            });
//        } catch (DatabaseConectionException e) {
//            System.err.println("Erro de conexão com o banco de dados: " + e.getMessage());
//            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//            return;
//        } catch (Exception e) {
//            System.err.println("Erro inesperado: " + e.getMessage());
//            JOptionPane.showMessageDialog(null, "Erro inesperado ao iniciar a aplicação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//            return;
//        } finally {
//            if (dbConnection != null) {
//                try {
//                    dbConnection.disconnect();
//                } catch (DatabaseConectionException e) {
//                    System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
//                    JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//       }
    }
}