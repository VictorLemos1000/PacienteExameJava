package crud.prontuario.app;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import crud.prontuario.dao.ExameDAO;
import crud.prontuario.dao.IEntityDAO;
import crud.prontuario.dao.PacienteDAO;
import crud.prontuario.database.DatabaseConnectionMySQL;
import crud.prontuario.model.Exame;
import crud.prontuario.model.Paciente;

public class Application {

	private static final IEntityDAO<Paciente> pacienteDAO = new PacienteDAO(new DatabaseConnectionMySQL());
    private static final IEntityDAO<Exame> exameDAO = new ExameDAO(new DatabaseConnectionMySQL());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n====== SISTEMA DE GESTÃO HOSPITALAR ======");
            System.out.println("1. Início");
            System.out.println("2. Pacientes");
            System.out.println("3. Exames");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> System.out.println("\n// --- INÍCIO SISTEMA DE GESTÃO HOSPITALAR --- //");
                case 2 -> menuPacientes(scanner);
                case 3 -> menuExames(scanner);
                case 0 -> {
                    System.out.println("Encerrando...");
                    executando = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    private static void menuPacientes(Scanner scanner) {
        boolean voltar = false;

        while (!voltar) {
            System.out.println("\n--- Menu Pacientes ---");
            System.out.println("1. Listar pacientes");
            System.out.println("2. Cadastrar paciente");
            System.out.println("3. Editar paciente");
            System.out.println("4. Deletar paciente");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> listarPacientes();
                case 2 -> cadastrarPaciente(scanner);
                case 3 -> editarPaciente(scanner);
                case 4 -> deletarPaciente(scanner);
                case 0 -> voltar = true;
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void listarPacientes() {
        List<Paciente> pacientes = pacienteDAO.findAll();
        System.out.println("\n=== Lista de Pacientes ===");
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            for (Paciente p : pacientes) {
                System.out.printf("ID: %d | Nome: %s | CPF: %s%n", p.getId(), p.getNome(), p.getCpf());
            }
        }
    }

    private static void cadastrarPaciente(Scanner scanner) {
        System.out.println("\n--- Cadastro de Paciente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Paciente p = new Paciente();
        p.setNome(nome);
        p.setCpf(cpf);

        try {
            pacienteDAO.create(p);
            System.out.println("Paciente cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }

    private static void editarPaciente(Scanner scanner) {
        System.out.print("\nInforme o ID do paciente para editar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Paciente p = pacienteDAO.findById(id);
        if (p == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.printf("Nome atual: %s\nNovo nome: ", p.getNome());
        String nome = scanner.nextLine();
        if (!nome.isBlank()) p.setNome(nome);

        System.out.printf("CPF atual: %s\nNovo CPF: ", p.getCpf());
        String cpf = scanner.nextLine();
        if (!cpf.isBlank()) p.setCpf(cpf);

        pacienteDAO.update(p);
        System.out.println("Paciente atualizado com sucesso!");
    }

    private static void deletarPaciente(Scanner scanner) {
        System.out.print("\nInforme o ID do paciente para deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Paciente p = pacienteDAO.findById(id);
        if (p == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.print("Confirma exclusão de " + p.getNome() + "? (S/N): ");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            pacienteDAO.delete(p);
            System.out.println("Paciente deletado com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }


    private static void menuExames(Scanner scanner) {
        boolean voltar = false;

        while (!voltar) {
            System.out.println("\n--- Menu Exames ---");
            System.out.println("1. Listar exames");
            System.out.println("2. Cadastrar exame");
            System.out.println("3. Editar exame");
            System.out.println("4. Deletar exame");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> listarExames();
                case 2 -> cadastrarExame(scanner);
                case 3 -> editarExame(scanner);
                case 4 -> deletarExame(scanner);
                case 0 -> voltar = true;
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void listarExames() {
        List<Exame> exames = exameDAO.findAll();
        System.out.println("\n=== Lista de Exames ===");
        if (exames.isEmpty()) {
            System.out.println("Nenhum exame cadastrado.");
        } else {
            for (Exame e : exames) {
                System.out.printf("ID: %d | Descrição: %s | Data: %s | Paciente ID: %d%n",
                        e.getId(), e.getDescricao(), e.getData(), e.getPaciente_id());
            }
        }
    }

    private static void cadastrarExame(Scanner scanner) {
        System.out.println("\n--- Cadastro de Exame ---");
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("ID do paciente: ");
        Long pacienteId = scanner.nextLong();
        scanner.nextLine();

        Paciente paciente = pacienteDAO.findById(pacienteId);
        if (paciente == null) {
            System.out.println("Paciente não encontrado. Exame não cadastrado.");
            return;
        }

        Exame e = new Exame();
        e.setDescricao(descricao);
        e.setData(LocalDateTime.now());
        e.setPaciente_id(pacienteId);

        try {
            exameDAO.create(e);
            System.out.println("Exame cadastrado com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar exame: " + ex.getMessage());
        }
    }


    private static void editarExame(Scanner scanner) {
        System.out.print("Informe o ID do exame: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Exame e = exameDAO.findById(id);
        if (e == null) {
            System.out.println("Exame não encontrado.");
            return;
        }

        System.out.printf("Descrição atual: %s\nNova descrição (Enter p/ manter): ", e.getDescricao());
        String desc = scanner.nextLine();
        if (!desc.isBlank()) e.setDescricao(desc);

        System.out.printf("Data atual: %s\n", e.getData());
        System.out.print("Atualizar data para agora? (S/N): ");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            e.setData(LocalDateTime.now());
        }

        System.out.printf("Paciente atual: %d\nNovo ID do paciente (Enter p/ manter): ", e.getPaciente_id());
        String pacienteIdStr = scanner.nextLine();
        if (!pacienteIdStr.isBlank()) {
            try {
                Long novoId = Long.parseLong(pacienteIdStr);
                e.setPaciente_id(novoId);
            } catch (NumberFormatException ex) {
                System.out.println("ID inválido, mantendo paciente anterior.");
            }
        }

        exameDAO.update(e);
        System.out.println("Exame atualizado com sucesso!");
    }


    private static void deletarExame(Scanner scanner) {
        System.out.print("Informe o ID do exame: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Exame e = exameDAO.findById(id);
        if (e == null) {
            System.out.println("Exame não encontrado.");
            return;
        }

        System.out.print("Confirma exclusão do exame? (S/N): ");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            exameDAO.delete(e);
            System.out.println("Exame deletado com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }
}
