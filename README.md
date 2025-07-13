<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gerenciamento de Prontuário Médico</title>
    <style>
        :root {
            --primary: #2c3e50;
            --secondary: #3498db;
            --accent: #2ecc71;
            --danger: #e74c3c;
            --light: #ecf0f1;
            --dark: #34495e;
        }
        
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        body {
            background-color: #f5f7fa;
            color: #333;
            line-height: 1.6;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        
        header {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: white;
            padding: 2rem 0;
            text-align: center;
            border-radius: 0 0 20px 20px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            margin-bottom: 2rem;
        }
        
        header h1 {
            font-size: 2.5rem;
            margin-bottom: 0.5rem;
        }
        
        .tagline {
            font-size: 1.2rem;
            opacity: 0.9;
            max-width: 800px;
            margin: 0 auto;
        }
        
        .badge {
            display: inline-block;
            background-color: var(--accent);
            color: white;
            padding: 0.3rem 0.8rem;
            border-radius: 20px;
            font-size: 0.9rem;
            margin: 0.3rem;
            vertical-align: middle;
        }
        
        .section {
            background: white;
            border-radius: 15px;
            padding: 2rem;
            margin-bottom: 2rem;
            box-shadow: 0 4px 15px rgba(0,0,0,0.05);
            transition: transform 0.3s ease;
        }
        
        .section:hover {
            transform: translateY(-5px);
        }
        
        .section-title {
            color: var(--primary);
            border-bottom: 2px solid var(--secondary);
            padding-bottom: 0.8rem;
            margin-bottom: 1.5rem;
            display: flex;
            align-items: center;
        }
        
        .section-title i {
            margin-right: 10px;
            font-size: 1.5rem;
        }
        
        .features {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 1.5rem;
        }
        
        .feature-card {
            background: #f8f9fa;
            border-left: 4px solid var(--secondary);
            padding: 1.5rem;
            border-radius: 8px;
            transition: all 0.3s ease;
        }
        
        .feature-card:hover {
            background: #e3f2fd;
            transform: translateX(5px);
        }
        
        .feature-card h3 {
            color: var(--dark);
            margin-bottom: 0.8rem;
            display: flex;
            align-items: center;
        }
        
        .feature-card h3 i {
            margin-right: 10px;
            color: var(--secondary);
        }
        
        .tech-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 1rem;
        }
        
        .tech-item {
            display: flex;
            align-items: center;
            background: #f0f7ff;
            padding: 1rem;
            border-radius: 8px;
            transition: all 0.3s ease;
        }
        
        .tech-item:hover {
            background: #d4e6ff;
            transform: scale(1.02);
        }
        
        .tech-icon {
            width: 50px;
            height: 50px;
            background-color: var(--secondary);
            color: white;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 15px;
            font-size: 1.5rem;
        }
        
        .structure-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 1.5rem;
        }
        
        .structure-card {
            background: #f8f9fa;
            padding: 1.5rem;
            border-radius: 10px;
            border: 1px solid #e0e0e0;
        }
        
        .structure-card h3 {
            color: var(--primary);
            margin-bottom: 1rem;
            display: flex;
            align-items: center;
        }
        
        .tree {
            background: #2c3e50;
            color: #ecf0f1;
            padding: 1.5rem;
            border-radius: 10px;
            font-family: monospace;
            line-height: 1.8;
            overflow-x: auto;
            margin-top: 1rem;
        }
        
        .file {
            color: #3498db;
        }
        
        .dir {
            color: #2ecc71;
            font-weight: bold;
        }
        
        .code-block {
            background: #2d2d2d;
            color: #f8f8f2;
            padding: 1.5rem;
            border-radius: 8px;
            font-family: monospace;
            overflow-x: auto;
            margin: 1.5rem 0;
            position: relative;
        }
        
        .code-header {
            background: #3d3d3d;
            padding: 0.5rem 1rem;
            border-radius: 8px 8px 0 0;
            margin: -1.5rem -1.5rem 1rem -1.5rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .btn {
            display: inline-block;
            background: var(--secondary);
            color: white;
            padding: 0.8rem 1.5rem;
            border-radius: 8px;
            text-decoration: none;
            font-weight: 600;
            transition: all 0.3s ease;
            border: none;
            cursor: pointer;
            margin-top: 0.5rem;
        }
        
        .btn:hover {
            background: #2980b9;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        
        .btn-danger {
            background: var(--danger);
        }
        
        .btn-danger:hover {
            background: #c0392b;
        }
        
        .btn-success {
            background: var(--accent);
        }
        
        .btn-success:hover {
            background: #27ae60;
        }
        
        .flex-buttons {
            display: flex;
            gap: 1rem;
            margin-top: 1rem;
        }
        
        .exception-list {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 1rem;
        }
        
        .exception-item {
            background: #fef2f2;
            padding: 1rem;
            border-radius: 8px;
            border-left: 4px solid var(--danger);
        }
        
        .exception-item h4 {
            color: var(--danger);
            margin-bottom: 0.5rem;
        }
        
        .diagram {
            background: white;
            padding: 2rem;
            border-radius: 15px;
            text-align: center;
            margin: 2rem 0;
            box-shadow: 0 4px 15px rgba(0,0,0,0.05);
        }
        
        .diagram img {
            max-width: 100%;
            border-radius: 10px;
        }
        
        footer {
            text-align: center;
            padding: 2rem 0;
            color: var(--dark);
            border-top: 1px solid #eee;
            margin-top: 2rem;
        }
        
        @media (max-width: 768px) {
            .features, .tech-grid, .structure-grid {
                grid-template-columns: 1fr;
            }
            
            .flex-buttons {
                flex-direction: column;
            }
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <header>
        <div class="container">
            <h1><i class="fas fa-heartbeat"></i> Sistema de Gerenciamento de Prontuário Médico</h1>
            <p class="tagline">Solução completa para gestão de pacientes e exames médicos com operações CRUD e persistência em banco de dados</p>
            <div style="margin-top: 1.5rem;">
                <span class="badge">Java</span>
                <span class="badge">MySQL</span>
                <span class="badge">JDBC</span>
                <span class="badge">CRUD</span>
                <span class="badge">DAO Pattern</span>
            </div>
        </div>
    </header>
    
    <div class="container">
        <section class="section">
            <h2 class="section-title"><i class="fas fa-info-circle"></i> Descrição do Projeto</h2>
            <p>Este sistema oferece uma solução completa para o gerenciamento de prontuários médicos, permitindo operações CRUD (Create, Read, Update, Delete) para pacientes e exames médicos com persistência em banco de dados MySQL.</p>
            
            <div class="features">
                <div class="feature-card">
                    <h3><i class="fas fa-user-plus"></i> Cadastro de Pacientes</h3>
                    <p>Cadastro completo de pacientes com validação de CPF e confirmação de dados antes do armazenamento.</p>
                </div>
                <div class="feature-card">
                    <h3><i class="fas fa-file-medical"></i> Gestão de Exames</h3>
                    <p>Registro e gerenciamento de exames médicos com descrição e data/hora de realização.</p>
                </div>
                <div class="feature-card">
                    <h3><i class="fas fa-database"></i> Persistência em Banco de Dados</h3>
                    <p>Armazenamento seguro em banco MySQL com conexão configurável via arquivo properties.</p>
                </div>
                <div class="feature-card">
                    <h3><i class="fas fa-shield-alt"></i> Tratamento de Exceções</h3>
                    <p>Sistema robusto de tratamento de erros com exceções personalizadas para diversos cenários.</p>
                </div>
            </div>
        </section>
        
        <section class="section">
            <h2 class="section-title"><i class="fas fa-laptop-code"></i> Tecnologias Utilizadas</h2>
            <div class="tech-grid">
                <div class="tech-item">
                    <div class="tech-icon"><i class="fab fa-java"></i></div>
                    <div>
                        <h3>Java 11+</h3>
                        <p>Linguagem principal</p>
                    </div>
                </div>
                <div class="tech-item">
                    <div class="tech-icon"><i class="fas fa-database"></i></div>
                    <div>
                        <h3>MySQL</h3>
                        <p>Banco de dados relacional</p>
                    </div>
                </div>
                <div class="tech-item">
                    <div class="tech-icon"><i class="fas fa-plug"></i></div>
                    <div>
                        <h3>JDBC</h3>
                        <p>Conexão com banco de dados</p>
                    </div>
                </div>
                <div class="tech-item">
                    <div class="tech-icon"><i class="fas fa-cube"></i></div>
                    <div>
                        <h3>Padrão DAO</h3>
                        <p>Data Access Object</p>
                    </div>
                </div>
            </div>
        </section>
        
        <section class="section">
            <h2 class="section-title"><i class="fas fa-cogs"></i> Configuração do Ambiente</h2>
            
            <h3><i class="fas fa-database"></i> Configuração do Banco de Dados</h3>
            <p>Crie o banco de dados e as tabelas necessárias:</p>
            
            <div class="code-block">
                <div class="code-header">
                    <span>SQL</span>
                </div>
                <pre><code>CREATE DATABASE prontuario_db;

CREATE TABLE pacientes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE
);

CREATE TABLE exames (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    descricao TEXT NOT NULL,
    data DATETIME NOT NULL
);</code></pre>
            </div>
            
            <h3><i class="fas fa-file-alt"></i> Arquivo config.properties</h3>
            <p>Crie o arquivo de configuração com as informações do seu banco:</p>
            
            <div class="code-block">
                <div class="code-header">
                    <span>config.properties</span>
                </div>
                <pre><code>DB_ADDRESS=localhost
DB_PORT=3306
DB_SCHEMA=prontuario_db
DB_USER=seu_usuario
DB_PASSWORD=sua_senha</code></pre>
            </div>
        </section>
        
        <section class="section">
            <h2 class="section-title"><i class="fas fa-play-circle"></i> Executando o Projeto</h2>
            
            <h3><i class="fas fa-terminal"></i> Compilação</h3>
            <div class="code-block">
                <div class="code-header">
                    <span>Terminal</span>
                </div>
                <pre><code>javac -d bin src/crud/prontuario/app/*.java 
src/crud/prontuario/dao/*.java 
src/crud/prontuario/database/*.java 
src/crud/prontuario/exception/*.java 
src/crud/prontuario/model/*.java 
src/crud/prontuario/util/*.java</code></pre>
            </div>
            
            <h3><i class="fas fa-rocket"></i> Execução</h3>
            <div class="code-block">
                <div class="code-header">
                    <span>Terminal</span>
                </div>
                <pre><code>java -cp bin crud.prontuario.app.Application</code></pre>
            </div>
            
            <div class="flex-buttons">
                <a href="#" class="btn"><i class="fas fa-download"></i> Baixar Projeto</a>
                <a href="#" class="btn btn-success"><i class="fab fa-github"></i> Ver no GitHub</a>
            </div>
        </section>
        
        <section class="section">
            <h2 class="section-title"><i class="fas fa-folder-open"></i> Estrutura do Projeto</h2>
            
            <div class="structure-grid">
                <div class="structure-card">
                    <h3><i class="fas fa-sitemap"></i> Árvore de Diretórios</h3>
                    <div class="tree">
                        <span class="dir">src/</span><br>
                        <span class="dir">├── crud/</span><br>
                        <span class="dir">│   ├── prontuario/</span><br>
                        <span class="dir">│   │   ├── app/</span><br>
                        <span class="file">│   │   │   └── Application.java</span><br>
                        <span class="dir">│   │   ├── dao/</span><br>
                        <span class="file">│   │   │   ├── ExameDAO.java</span><br>
                        <span class="file">│   │   │   ├── IEntityDAO.java</span><br>
                        <span class="file">│   │   │   └── PacienteDAO.java</span><br>
                        <span class="dir">│   │   ├── database/</span><br>
                        <span class="file">│   │   │   ├── DatabaseConnectionMySQL.java</span><br>
                        <span class="file">│   │   │   └── IConnection.java</span><br>
                        <span class="dir">│   │   ├── exception/</span><br>
                        <span class="file">│   │   │   ├── AtualizarExameException.java</span><br>
                        <span class="file">│   │   │   ├── AtualizarPacienteException.java</span><br>
                        <span class="file">│   │   │   ├── BaseException.java</span><br>
                        <span class="file">│   │   │   └── ...</span><br>
                        <span class="dir">│   │   ├── model/</span><br>
                        <span class="file">│   │   │   ├── Exame.java</span><br>
                        <span class="file">│   │   │   └── Paciente.java</span><br>
                        <span class="dir">│   │   └── util/</span><br>
                        <span class="file">│   │       └── ConfigLoader.java</span>
                    </div>
                </div>
                
                <div class="structure-card">
                    <h3><i class="fas fa-project-diagram"></i> Diagrama de Classes</h3>
                    <div class="diagram">
                        <img src="https://i.imgur.com/sample-diagram.png" alt="Diagrama de Classes" style="max-width:100%">
                        <p style="margin-top: 1rem; color: var(--dark);">Diagrama de classes do sistema mostrando as relações entre DAOs, Models e Database</p>
                    </div>
                </div>
            </div>
        </section>
        
        <section class="section">
            <h2 class="section-title"><i class="fas fa-bug"></i> Tratamento de Exceções</h2>
            
            <p>O sistema implementa um tratamento robusto de exceções com classes personalizadas:</p>
            
            <div class="exception-list">
                <div class="exception-item">
                    <h4>ValidacaoException</h4>
                    <p>Erros de validação de dados (ex: CPF inválido)</p>
                </div>
                <div class="exception-item">
                    <h4>PacienteJaCadastradoException</h4>
                    <p>Tentativa de cadastrar paciente com CPF existente</p>
                </div>
                <div class="exception-item">
                    <h4>PacienteNaoEncontradoException</h4>
                    <p>Paciente não encontrado no banco de dados</p>
                </div>
                <div class="exception-item">
                    <h4>CriarExameException</h4>
                    <p>Falha ao criar um novo exame</p>
                </div>
                <div class="exception-item">
                    <h4>AtualizarExameException</h4>
                    <p>Falha ao atualizar um exame</p>
                </div>
                <div class="exception-item">
                    <h4>DeletarExameException</h4>
                    <p>Falha ao excluir um exame</p>
                </div>
            </div>
        </section>
        
        <section class="section">
            <h2 class="section-title"><i class="fas fa-flask"></i> Testando o Sistema</h2>
            
            <h3><i class="fas fa-check-circle"></i> Fluxo de Cadastro de Paciente</h3>
            <div class="code-block">
                <div class="code-header">
                    <span>Terminal</span>
                </div>
                <pre><code>=== MENU PRINCIPAL ===
1 - Cadastrar Paciente
2 - Buscar Paciente
3 - Listar Pacientes
0 - Sair
Opção: 1

=== CADASTRO DE NOVO PACIENTE ===
Nome completo: Maria Silva
CPF (apenas números): 12345678901

--- CONFIRME OS DADOS ---
Nome: Maria Silva
CPF: 123.456.789-01

Confirmar cadastro? (S/N): S

✅ Paciente cadastrado com sucesso!</code></pre>
            </div>
            
            <div class="flex-buttons">
                <a href="#" class="btn"><i class="fas fa-book"></i> Documentação Completa</a>
                <a href="#" class="btn btn-danger"><i class="fas fa-file-pdf"></i> Baixar PDF</a>
            </div>
        </section>
    </div>
    
    <footer>
        <div class="container">
            <p>Desenvolvido com <i class="fas fa-heart" style="color:#e74c3c"></i> usando Java e MySQL</p>
            <p style="margin-top: 1rem;">Licença MIT - 2023</p>
        </div>
    </footer>
    
    <script>
        // Simples efeito de realce nos blocos de código
        document.querySelectorAll('.code-block').forEach(block => {
            block.addEventListener('click', function() {
                const range = document.createRange();
                range.selectNodeContents(this.querySelector('code'));
                window.getSelection().removeAllRanges();
                window.getSelection().addRange(range);
                
                try {
                    document.execCommand('copy');
                    const originalText = this.querySelector('.code-header').innerText;
                    this.querySelector('.code-header').innerHTML = '<span><i class="fas fa-check"></i> Copiado!</span>';
                    
                    setTimeout(() => {
                        this.querySelector('.code-header').innerText = originalText;
                    }, 2000);
                } catch (err) {
                    console.error('Falha ao copiar: ', err);
                }
                
                window.getSelection().removeAllRanges();
            });
        });
    </script>
</body>
</html>
