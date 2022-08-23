/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufla.corretora_decio_avancada;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author decio.mendonca
 */
public class Ativo extends Medias {
    
    private String caminho = "";
    
    public Ativo (int ativo){
        
        if (ativo == 1) {
            caminho = "C:\\Users\\decio.mendonca\\Desktop\\Lixo\\Avançada\\EURUSD_M5.csv";
            this.Ativo = "A";
            this.caminho_ativo = caminho;
 
            try {
                pegadados();
                MediaSimples(qtPeriodosMS);
                MediaExponencial(qtPeriodosMS);
                DesvioPadrao();
                MediaCurta();
                MediaIntermediaria();
                MediaLonga();
           
            } catch (IOException ex) {
                Logger.getLogger(Ativo.class.getName()).log(Level.SEVERE, null, ex);
            }  
          
            Salvar();
        }
         if (ativo == 2) {
            caminho = "C:\\Users\\decio.mendonca\\Desktop\\Lixo\\Avançada\\SP500m_M2.csv";
            this.Ativo = "B";
            this.caminho_ativo = caminho;
            try {
                pegadados();
                 MediaSimples(qtPeriodosMS);
                MediaExponencial(qtPeriodosMS);
                DesvioPadrao();
                MediaCurta();
                MediaIntermediaria();
                MediaLonga();
           
            } catch (IOException ex) {
                Logger.getLogger(Ativo.class.getName()).log(Level.SEVERE, null, ex);
            }  
            super.Salvar();
        }
          if (ativo == 3) {
            caminho = "C:\\Users\\decio.mendonca\\Desktop\\Lixo\\Avançada\\STOX50_M2.csv";
            this.Ativo = "C";
            this.caminho_ativo = caminho;
            try {
                pegadados();
                MediaSimples(qtPeriodosMS);
                MediaExponencial(qtPeriodosMS);
                DesvioPadrao();
                MediaCurta();
                MediaIntermediaria();
                MediaLonga();
           
            } catch (IOException ex) {
                Logger.getLogger(Ativo.class.getName()).log(Level.SEVERE, null, ex);
            }  
            super.Salvar();
        }
           if (ativo == 4) {
            caminho = "C:\\Users\\decio.mendonca\\Desktop\\Lixo\\Avançada\\XPDUSD_M2.csv";
            this.Ativo = "D";
            this.caminho_ativo = caminho;
            try {
                pegadados();
                MediaSimples(qtPeriodosMS);
                MediaExponencial(qtPeriodosMS);
                DesvioPadrao();
                MediaCurta();
                MediaIntermediaria();
                MediaLonga();
           
            } catch (IOException ex) {
                Logger.getLogger(Ativo.class.getName()).log(Level.SEVERE, null, ex);
            }  
            super.Salvar();
        }
        
    }

    public String getCaminho() {
        return caminho;
    }

    @Override
    public String getAtivo() {
        return Ativo;
    }
    

    public double getClose(int indice) {
        return close.get(indice);
    }
}
