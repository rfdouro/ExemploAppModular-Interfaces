package br.rfdouro;

import javax.swing.*;


import java.awt.*;
import java.io.File;

public class GUI extends JFrame {
 private MainApp app;
 private JTextArea outputArea;

 public GUI() {
  app = new MainApp();
  initializeUI();
 }

 private void initializeUI() {
  setTitle("Aplicação Modular");
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setSize(600, 400);
  setLocationRelativeTo(null);

  // Painel principal
  JPanel panel = new JPanel(new BorderLayout());

  // Botão para carregar JAR
  JButton loadButton = new JButton("Carregar Implementação");
  loadButton.addActionListener(e -> carregarJar());

  try {
   // Tenta carregar a implementação padrão
   // Se falhar, o usuário pode carregar um JAR externo
   // para fornecer a implementação
   // Se não houver implementação padrão, o botão de carregar JAR permanece ativo
   // para que o usuário possa carregar uma implementação
   // Se houver implementação padrão, desabilita o botão de carregar JAR
   app.carregarImplementacao();
   loadButton.setEnabled(false);
   outputArea = new JTextArea("Implementação padrão carregada.\n");
  } catch (Exception ex) {
   System.out.println("Erro ao carregar serviço: " + ex.getMessage());
  }

  // Área de texto para output
  outputArea = new JTextArea();
  outputArea.setEditable(false);

  // Botão para executar ação
  JButton executeButton = new JButton("Executar Ação");
  executeButton.addActionListener(e -> executarAcao());

  // Layout
  JPanel buttonPanel = new JPanel();
  buttonPanel.add(loadButton);
  buttonPanel.add(executeButton);

  panel.add(buttonPanel, BorderLayout.NORTH);
  panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

  add(panel);
 }

 private void carregarJar() {
  JFileChooser fileChooser = new JFileChooser();
  fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JAR files", "jar"));

  int result = fileChooser.showOpenDialog(this);
  if (result == JFileChooser.APPROVE_OPTION) {
   File selectedFile = fileChooser.getSelectedFile();
   try {
    app.carregarImplementacao(selectedFile);
    outputArea.append("JAR carregado com sucesso: " + selectedFile.getName() + "\n");
   } catch (Exception ex) {
    JOptionPane.showMessageDialog(this, "Erro ao carregar JAR: " + ex.getMessage());
   }
  }
 }

 private void executarAcao() {
  try {
   String resultado = app.getServico().executarAcao("Teste");
   outputArea.append("Resultado: " + resultado + "\n");
  } catch (Exception ex) {
   JOptionPane.showMessageDialog(this, "Erro ao executar: " + ex.getMessage());
  }
 }
}
