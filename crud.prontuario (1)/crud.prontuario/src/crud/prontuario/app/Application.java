package crud.prontuario.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import crud.prontuario.dao.ExameDAO;
import crud.prontuario.dao.PacienteDAO;
import crud.prontuario.database.DatabaseConnectionMySQL;
import crud.prontuario.exception.PacienteJaCadastradoException;
import crud.prontuario.exception.ValidacaoException;
import crud.prontuario.model.Exame;
import crud.prontuario.model.Paciente;

public class Application {

    public static void main(String[] args) {
        DatabaseConnectionMySQL dbConnection = new DatabaseConnectionMySQL();
        PacienteDAO pacienteDAO = new PacienteDAO(dbConnection);
        Scanner scanner = new Scanner(System.in);
        
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE PRONTUÁRIO MÉDICO ===");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Editar Paciente");
            System.out.println("3. Deletar Paciente");
            System.out.println("4. Cadastrar Exame");
            System.out.println("5. Editar Exame");
            System.out.println("6. Deletar Exame");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            
            switch (opcao) {
                case 1: // Cadastrar Paciente
                    cadastrarPaciente(pacienteDAO, scanner);
                    break;
                    
                case 2: // Editar Paciente
                    editarPaciente(pacienteDAO, scanner);
                    break;
                    
                case 3: // Deletar Paciente
                    deletarPaciente(pacienteDAO, scanner);
                    break;
                    
                case 4: // Cadastrar Exame
                    cadastrarExame(dbConnection, pacienteDAO, scanner);
                    break;
                    
                case 5: // Editar Exame
                    editarExame(dbConnection, pacienteDAO, scanner);
                    break;
                    
                case 6: // Deletar Exame
                    deletarExame(dbConnection, pacienteDAO, scanner);
                    break;
                    
                case 0:
                    System.out.println("\nSaindo do sistema...");
                    break;
                    
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
            
        } while (opcao != 0);
        
        scanner.close();
        dbConnection.closeConnection(dbConnection.getConnection());
    }

    private static void cadastrarPaciente(PacienteDAO pacienteDAO, Scanner scanner) {
        try {
            System.out.println("\n=== CADASTRAR PACIENTE ===");
            
            System.out.print("Informe o ID do paciente: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            
            System.out.print("Informe o nome do paciente: ");
            String nome = scanner.nextLine();
            
            System.out.print("Informe o CPF do paciente: ");
            String cpf = scanner.nextLine();
            
            if (nome == null || nome.trim().isEmpty()) {
                throw new ValidacaoException("O nome do paciente é obrigatório!");
            }
            
            if (cpf == null || cpf.trim().isEmpty()) {
                throw new ValidacaoException("O CPF do paciente é obrigatório!");
            }
            
            Paciente pacienteExistente = pacienteDAO.findById(id);
            if (pacienteExistente != null) {
                throw new PacienteJaCadastradoException("Paciente com ID " + id + " já cadastrado!");
            }
            
            Paciente novoPaciente = new Paciente(id, nome, cpf);
            pacienteDAO.create(novoPaciente);
            
            System.out.println("\nPaciente cadastrado com sucesso!");
            System.out.println("ID: " + id);
            System.out.println("Nome: " + nome);
            System.out.println("CPF: " + cpf);
            
        } catch (ValidacaoException | PacienteJaCadastradoException e) {
            System.err.println("\nErro: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\nErro ao cadastrar paciente: " + e.getMessage());
        }
    }

    private static void editarPaciente(PacienteDAO pacienteDAO, Scanner scanner) {
        try {
            System.out.println("\n=== EDITAR PACIENTE ===");
            
            System.out.println("\nLista de Pacientes Cadastrados:");
            List<Paciente> pacientes = pacienteDAO.findAll();
            if (pacientes.isEmpty()) {
                System.out.println("Nenhum paciente cadastrado.");
                return;
            }
            
            for (Paciente p : pacientes) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | CPF: " + p.getCpf());
            }
            
            System.out.print("\nInforme o ID do paciente que deseja editar: ");
            Long idEditar = scanner.nextLong();
            scanner.nextLine();
            
            Paciente pacienteEditar = pacienteDAO.findById(idEditar);
            if (pacienteEditar == null) {
                System.out.println("\nPaciente não encontrado com o ID: " + idEditar);
                return;
            }
            
            System.out.println("\nDados atuais do paciente:");
            System.out.println("Nome: " + pacienteEditar.getNome());
            System.out.println("CPF: " + pacienteEditar.getCpf());
            
            System.out.println("\nDigite os novos dados (deixe em branco para manter o valor atual):");
            
            System.out.print("Novo nome [" + pacienteEditar.getNome() + "]: ");
            String novoNome = scanner.nextLine();
            
            System.out.print("Novo CPF [" + pacienteEditar.getCpf() + "]: ");
            String novoCpf = scanner.nextLine();
            
            if (!novoNome.isEmpty()) {
                pacienteEditar.setNome(novoNome);
            }
            
            if (!novoCpf.isEmpty()) {
                if (novoCpf.length() != 11) {
                    throw new ValidacaoException("CPF deve conter 11 dígitos!");
                }
                pacienteEditar.setCpf(novoCpf);
            }
            
            pacienteDAO.update(pacienteEditar);
            
            System.out.println("\nPaciente atualizado com sucesso!");
            System.out.println("ID: " + pacienteEditar.getId());
            System.out.println("Nome: " + pacienteEditar.getNome());
            System.out.println("CPF: " + pacienteEditar.getCpf());
            
        } catch (ValidacaoException e) {
            System.err.println("\nErro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\nErro ao editar paciente: " + e.getMessage());
        }
    }

    private static void deletarPaciente(PacienteDAO pacienteDAO, Scanner scanner) {
        try {
            System.out.println("\n=== DELETAR PACIENTE ===");
            
            System.out.println("\nLista de Pacientes Cadastrados:");
            List<Paciente> pacientes = pacienteDAO.findAll();
            if (pacientes.isEmpty()) {
                System.out.println("Nenhum paciente cadastrado.");
                return;
            }
            
            for (Paciente p : pacientes) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | CPF: " + p.getCpf());
            }
            
            System.out.print("\nInforme o ID do paciente que deseja deletar: ");
            Long idDeletar = scanner.nextLong();
            scanner.nextLine();
            
            Paciente pacienteDeletar = pacienteDAO.findById(idDeletar);
            if (pacienteDeletar == null) {
                System.out.println("\nPaciente não encontrado com o ID: " + idDeletar);
                return;
            }
            
            System.out.println("\nDados do paciente a ser deletado:");
            System.out.println("ID: " + pacienteDeletar.getId());
            System.out.println("Nome: " + pacienteDeletar.getNome());
            System.out.println("CPF: " + pacienteDeletar.getCpf());
            
            if (!pacienteDeletar.getExames().isEmpty()) {
                System.out.println("\nATENÇÃO: Este paciente possui " + pacienteDeletar.getExames().size() + 
                                  " exames associados. Todos os exames serão removidos.");
            }
            
            System.out.print("\nTem certeza que deseja deletar este paciente? (S/N): ");
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("S")) {
                pacienteDAO.delete(pacienteDeletar);
                System.out.println("\nPaciente deletado com sucesso!");
            } else {
                System.out.println("\nOperação cancelada. O paciente não foi deletado.");
            }
            
        } catch (Exception e) {
            System.err.println("\nErro ao deletar paciente: " + e.getMessage());
        }
    }

    private static void cadastrarExame(DatabaseConnectionMySQL dbConnection, PacienteDAO pacienteDAO, Scanner scanner) {
        try {
            System.out.println("\n=== CADASTRAR EXAME ===");
            
            System.out.println("\nLista de Pacientes Disponíveis:");
            List<Paciente> pacientes = pacienteDAO.findAll();
            if (pacientes.isEmpty()) {
                System.out.println("Nenhum paciente cadastrado. Cadastre um paciente primeiro.");
                return;
            }
            
            for (Paciente p : pacientes) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome());
            }
            
            System.out.print("\nInforme o ID do paciente para associar o exame: ");
            Long idPaciente = scanner.nextLong();
            scanner.nextLine();
            
            Paciente paciente = pacienteDAO.findById(idPaciente);
            if (paciente == null) {
                System.out.println("\nPaciente não encontrado com o ID: " + idPaciente);
                return;
            }
            
            System.out.println("\n=== DADOS DO EXAME ===");
            System.out.println("Paciente: " + paciente.getNome() + " (ID: " + paciente.getId() + ")");
            
            System.out.print("Informe o ID do exame: ");
            Long idExame = scanner.nextLong();
            scanner.nextLine();
            
            System.out.print("Descrição do exame: ");
            String descricao = scanner.nextLine();
            
            System.out.print("Data do exame (AAAA-MM-DD HH:MM): ");
            String dataStr = scanner.nextLine();
            LocalDateTime data = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new ValidacaoException("A descrição do exame é obrigatória!");
            }
            
            if (data == null || data.isAfter(LocalDateTime.now())) {
                throw new ValidacaoException("Data do exame inválida! Deve ser uma data/hora no passado.");
            }
            
            Exame novoExame = new Exame(idExame, descricao, data);
            
            ExameDAO exameDAO = new ExameDAO(dbConnection);
            exameDAO.create(novoExame);
            
            paciente.getExames().add(novoExame);
            pacienteDAO.update(paciente);
            
            System.out.println("\nExame cadastrado com sucesso!");
            System.out.println("ID: " + idExame);
            System.out.println("Paciente: " + paciente.getNome());
            System.out.println("Descrição: " + descricao);
            System.out.println("Data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            
        } catch (ValidacaoException e) {
            System.err.println("\nErro de validação: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("\nFormato de data inválido! Use o formato AAAA-MM-DD HH:MM");
        } catch (Exception e) {
            System.err.println("\nErro ao cadastrar exame: " + e.getMessage());
        }
    }

    private static void editarExame(DatabaseConnectionMySQL dbConnection, PacienteDAO pacienteDAO, Scanner scanner) {
        try {
            System.out.println("\n=== EDITAR EXAME ===");
            
            System.out.println("\nLista de Pacientes:");
            List<Paciente> pacientes = pacienteDAO.findAll();
            if (pacientes.isEmpty()) {
                System.out.println("Nenhum paciente cadastrado.");
                return;
            }
            
            for (Paciente p : pacientes) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome());
            }
            
            System.out.print("\nInforme o ID do paciente: ");
            Long idPaciente = scanner.nextLong();
            scanner.nextLine();
            
            Paciente paciente = pacienteDAO.findById(idPaciente);
            if (paciente == null) {
                System.out.println("Paciente não encontrado!");
                return;
            }
            
            System.out.println("\nExames do paciente " + paciente.getNome() + ":");
            if (paciente.getExames().isEmpty()) {
                System.out.println("Nenhum exame cadastrado para este paciente.");
                return;
            }
            
            for (Exame e : paciente.getExames()) {
                System.out.println("ID: " + e.getId() + " | Descrição: " + e.getDescricao() + 
                                 " | Data: " + e.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
            
            System.out.print("\nInforme o ID do exame que deseja editar: ");
            Long idExame = scanner.nextLong();
            scanner.nextLine();
            
            Exame exameEditar = null;
            for (Exame e : paciente.getExames()) {
                if (e.getId().equals(idExame)) {
                    exameEditar = e;
                    break;
                }
            }
            
            if (exameEditar == null) {
                System.out.println("Exame não encontrado!");
                return;
            }
            
            System.out.println("\nDados atuais do exame:");
            System.out.println("Descrição: " + exameEditar.getDescricao());
            System.out.println("Data: " + exameEditar.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            
            System.out.println("\nDigite os novos dados (deixe em branco para manter o valor atual):");
            
            System.out.print("Nova descrição [" + exameEditar.getDescricao() + "]: ");
            String novaDescricao = scanner.nextLine();
            
            System.out.print("Nova data (AAAA-MM-DD HH:MM) [" + 
                          exameEditar.getData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "]: ");
            String novaDataStr = scanner.nextLine();
            
            if (!novaDescricao.isEmpty()) {
                exameEditar.setDescricao(novaDescricao);
            }
            
            if (!novaDataStr.isEmpty()) {
                LocalDateTime novaData = LocalDateTime.parse(novaDataStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                exameEditar.setData(novaData);
            }
            
            ExameDAO exameDAO = new ExameDAO(dbConnection);
            exameDAO.update(exameEditar);
            
            System.out.println("\nExame atualizado com sucesso!");
            
        } catch (DateTimeParseException e) {
            System.err.println("Formato de data inválido! Use AAAA-MM-DD HH:MM");
        } catch (Exception e) {
            System.err.println("Erro ao editar exame: " + e.getMessage());
        }
    }

    private static void deletarExame(DatabaseConnectionMySQL dbConnection, PacienteDAO pacienteDAO, Scanner scanner) {
        try {
            System.out.println("\n=== DELETAR EXAME ===");
            
            System.out.println("\nLista de Pacientes:");
            List<Paciente> pacientes = pacienteDAO.findAll();
            if (pacientes.isEmpty()) {
                System.out.println("Nenhum paciente cadastrado.");
                return;
            }
            
            for (Paciente p : pacientes) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome());
            }
            
            System.out.print("\nInforme o ID do paciente: ");
            Long idPaciente = scanner.nextLong();
            scanner.nextLine();
            
            Paciente paciente = pacienteDAO.findById(idPaciente);
            if (paciente == null) {
                System.out.println("Paciente não encontrado!");
                return;
            }
            
            System.out.println("\nExames do paciente " + paciente.getNome() + ":");
            if (paciente.getExames().isEmpty()) {
                System.out.println("Nenhum exame cadastrado para este paciente.");
                return;
            }
            
            for (Exame e : paciente.getExames()) {
                System.out.println("ID: " + e.getId() + " | Descrição: " + e.getDescricao() + 
                                 " | Data: " + e.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
            
            System.out.print("\nInforme o ID do exame que deseja deletar: ");
            Long idExame = scanner.nextLong();
            scanner.nextLine();
            
            Exame exameDeletar = null;
            for (Exame e : paciente.getExames()) {
                if (e.getId().equals(idExame)) {
                    exameDeletar = e;
                    break;
                }
            }
            
            if (exameDeletar == null) {
                System.out.println("Exame não encontrado!");
                return;
            }
            
            System.out.println("\nDados do exame a ser deletado:");
            System.out.println("Descrição: " + exameDeletar.getDescricao());
            System.out.println("Data: " + exameDeletar.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            
            System.out.print("\nTem certeza que deseja deletar este exame? (S/N): ");
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("S")) {
                ExameDAO exameDAO = new ExameDAO(dbConnection);
                exameDAO.delete(exameDeletar);
                
                paciente.getExames().remove(exameDeletar);
                pacienteDAO.update(paciente);
                
                System.out.println("Exame deletado com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao deletar exame: " + e.getMessage());
        }
    }
}