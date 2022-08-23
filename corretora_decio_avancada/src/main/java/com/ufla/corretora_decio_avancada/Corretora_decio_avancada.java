/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.ufla.corretora_decio_avancada;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author decio.mendonca
 */
public class Corretora_decio_avancada {

    public static void main(String[] args) throws IOException {
        
//        Corretora investimentos = null;
//        Caixa caixa1 = new Caixa(investimentos);
//        Caixa caixa2 = new Caixa(investimentos);
//        Caixa_Geral caixaG = new Caixa_Geral();
//        investimentos = new Corretora(caixaG, caixa1, caixa2);
//        
//        Cliente[] cliente = investimentos.getCliente();
//        
//        
//        System.out.println(cliente[9].getSaldo());
//        System.out.println(investimentos.getCaixaGeral().toString());
        
//--------------------------------------------------------------------------------------------------------------------------------

      String file = "C:\\Users\\decio.mendonca\\Desktop\\Lixo\\Avançada\\Salvos\\Ativo_A_DadosSalvos.xls";   
      Grafico grafico = new Grafico();
      grafico.Graficos(file);
       
        System.out.println("Terminei bem");
    }
}
