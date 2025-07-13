package crud.prontuario.app;

import crud.prontuario.dao.PacienteDAO;
import crud.prontuario.database.DatabaseConnectionMySQL;
import crud.prontuario.exception.*;
import crud.prontuario.model.Paciente;
import java.util.Scanner;

public class Application {
    private static final PacienteDAO pacienteDAO = new PacienteDAO(new DatabaseConnectionMySQL());
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Opção: ");
            
            switch (opcao) {
                case 1:
                    cadastrarPaciente();
                    break;
                // outros casos do menu...
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
            if (opcao != 0) {
                pausarExecucao();
            }
        } while (opcao != 0);
        
        scanner.close();
    }

    private static void cadastrarPaciente() {
        System.out.println("\n=== CADASTRO DE NOVO PACIENTE ===");
        
        try {
            // Passo 2: Exibir formulário
            Paciente paciente = exibirFormularioCadastro();
            
            // Passo 4: Confirmação
            if (confirmarDados(paciente)) {
                // Passo 5: Armazenar no banco
                pacienteDAO.create(paciente);
                // Passo 6: Mensagem de sucesso
                System.out.println("\n✅ Paciente cadastrado com sucesso!");
            } else {
                System.out.println("\n❌ Cadastro cancelado.");
            }
        } catch (ValidacaoException e) {
            System.err.println("\n⛔ Dados inválidos: " + e.getMessage());
        }
    }

    private static Paciente exibirFormularioCadastro() throws ValidacaoException {
        // Passo 3: Inserir dados
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine().trim();
        
        System.out.print("CPF (apenas números): ");
        String cpf = scanner.nextLine().trim();
        
        // Validações
        if (nome.isEmpty()) {
            throw new ValidacaoException("O nome é obrigatório");
        }
        
        if (cpf.length() != 11 || !cpf.matches("\\d+")) {
            throw new ValidacaoException("CPF deve ter 11 dígitos numéricos");
        }
        
        return new Paciente(null, nome, cpf);
    }

    private static boolean confirmarDados(Paciente paciente) {
        System.out.println("\n--- CONFIRME OS DADOS ---");
        System.out.println("Nome: " + paciente.getNome());
        System.out.println("CPF: " + formatarCPF(paciente.getCpf()));
        
        System.out.print("\nConfirmar cadastro? (S/N): ");
        String resposta = scanner.nextLine().trim();
        return resposta.equalsIgnoreCase("S");
    }

    private static String formatarCPF(String cpf) {
        return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1 - Cadastrar Paciente");
        System.out.println("2 - Buscar Paciente");
        System.out.println("3 - Listar Pacientes");
        System.out.println("0 - Sair");
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    private static void pausarExecucao() {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
}