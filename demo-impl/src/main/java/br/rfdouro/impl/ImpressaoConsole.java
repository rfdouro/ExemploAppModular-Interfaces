package br.rfdouro.impl;

import br.rfdouro.spec.Impressao;

public class ImpressaoConsole implements Impressao {
 @Override
 public String executarAcao(String parametro) {
  System.out.println("Executando ação no console: " + parametro);
  return "Resultado da ação: " + parametro;
 }

 public void configurar(String config) {
  System.out.println("Configurando no console: " + config);
 }

}
