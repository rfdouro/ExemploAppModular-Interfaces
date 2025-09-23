package com.example.impl;

import javax.swing.JOptionPane;

import br.rfdouro.spec.Impressao;

public class ImpressaoGrafica implements Impressao {
 @Override
 public String executarAcao(String parametro) {
  // Exibe uma mensagem gráfica
  // Usa JOptionPane para mostrar a mensagem
  JOptionPane.showMessageDialog(null, "Executando ação gráfica: " + parametro);
  return "Resultado da ação: " + parametro;
 }

 public void configurar(String config) {
  JOptionPane.showMessageDialog(null, "Configurando impressão gráfica: " + config);
 }

}
