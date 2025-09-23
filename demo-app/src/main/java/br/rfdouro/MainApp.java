package br.rfdouro;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

import javax.swing.SwingUtilities;

import br.rfdouro.spec.Impressao;

public class MainApp {
    private Impressao servico;

    // Usa o classloader da aplicação ou indica um JAR externo para carregar a implementação
    // Se nenhum JAR for passado, tenta carregar a implementação do classpath da aplicação
    // Se um JAR for passado, tenta carregar a implementação do JAR externo
    // Lança exceção se não encontrar a implementação
    // A implementação deve estar declarada no arquivo META-INF/services/br.rfdouro.spec.Impressao
    // Exemplo de uso:
    // java -cp demo-app.jar;impl-externa.jar br.rfdouro.MainApp
    // ou
    // java -cp demo-app.jar br.rfdouro.MainApp impl-externa.jar
    public void carregarImplementacao() throws Exception {
        // Usando ServiceLoader para carregar a implementação
        ServiceLoader<Impressao> serviceLoader = ServiceLoader.load(Impressao.class);

        servico = serviceLoader.findFirst()
                .orElseThrow(() -> new RuntimeException("Implementação não encontrada"));
    }

    // Carrega a implementação de um JAR externo
    // Usa a pesquisa por um JAR externo
    // Efetuada na janela da aplicação
    public void carregarImplementacao(File jarFile) throws Exception {
        URL[] urls = { jarFile.toURI().toURL() };
        URLClassLoader classLoader = new URLClassLoader(urls, getClass().getClassLoader());

        // Usando ServiceLoader para carregar a implementação
        ServiceLoader<Impressao> serviceLoader = ServiceLoader.load(Impressao.class, classLoader);

        servico = serviceLoader.findFirst()
                .orElseThrow(() -> new RuntimeException("Implementação não encontrada"));
    }

    public Impressao getServico() {
        return servico;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

}