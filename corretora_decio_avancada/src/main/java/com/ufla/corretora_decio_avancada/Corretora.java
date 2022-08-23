/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufla.corretora_decio_avancada;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author decio.mendonca
 */
public class Corretora extends Thread{
    private Ativo ativoA = new Ativo(1);
    private Ativo ativoB = new Ativo(2);
    private Ativo ativoC = new Ativo(3);
    private Ativo ativoD = new Ativo(4);
    private  int numOp = 1000;
    private Caixa_Geral caixaGeral;
    private Caixa caixa1;
    private Caixa caixa2;
    private Cliente[] cliente = new Cliente[10];
    private double drawdown[] = null;
    private double tendencia[] = null;
    private boolean status = true;

    public Corretora(Caixa_Geral caixaGeral, Caixa caixa1, Caixa caixa2) {
        start();
        this.caixaGeral = caixaGeral;
        this.caixa1 = caixa1;
        this.caixa2 = caixa2;
        this.status = true;
        
        for (int i = 0; i < 10; i++) {
            
            Random aleatorio = new Random();
            cliente[i] = new Cliente(i, aleatorio.nextFloat(500)+200, this);
        }
    }
        
    
    public ArrayList getAtivos(){
        ArrayList<Ativo> total = new ArrayList<>();
        total.add(this.ativoA);
        total.add(this.ativoB);
        total.add(this.ativoC);
        total.add(this.ativoD);
        
        
        return total;
    }

    public Cliente[] getCliente() {
        return cliente;
    }
    
    
    public double[] drawdown(){
        
        double dd_max = 0;
        double dd_min = this.ativoA.close.get(0);
        
        
        for (int i = 0; i < this.ativoA.close.size(); i++) {
            if (this.ativoA.close.get(i) >= dd_max) {
                dd_max = this.ativoA.close.get(i);
            }if (this.ativoA.close.get(i) <= dd_min) {
                dd_min = this.ativoA.close.get(i);
            }
    }
        drawdown[0]= (dd_max-dd_min)/dd_max;
        
            dd_max = 0;
            dd_min = this.ativoB.close.get(0);
            for (int i = 0; i < this.ativoB.close.size(); i++) {
            if (this.ativoB.close.get(i) >= dd_max) {
                dd_max = this.ativoB.close.get(i);
            }if (this.ativoB.close.get(i) <= dd_min) {
                dd_min = this.ativoB.close.get(i);
            } 
    }
        drawdown[1]= (dd_max-dd_min)/dd_max;
        
            dd_max = 0;
            dd_min = this.ativoC.close.get(0);
                for (int i = 0; i < this.ativoC.close.size(); i++) {
            if (this.ativoA.close.get(i) >= dd_max) {
                dd_max = this.ativoC.close.get(i);
            }if (this.ativoC.close.get(i) <= dd_min) {
                dd_min = this.ativoC.close.get(i);
            }
    }
        drawdown[2]= (dd_max-dd_min)/dd_max;
            dd_max = 0;
            dd_min = this.ativoD.close.get(0);
                    for (int i = 0; i < this.ativoD.close.size(); i++) {
            if (this.ativoD.close.get(i) >= dd_max) {
                dd_max = this.ativoD.close.get(i);
            }if (this.ativoD.close.get(i) <= dd_min) {
                dd_min = this.ativoD.close.get(i);
            } 
    }
        drawdown[3]= (dd_max-dd_min)/dd_max;
        
        return drawdown;
    }
    
    public double[] tendencia(){
        
        
        for (int i = 0; i < this.ativoA.MI.size(); i++) {
            tendencia[0]  = this.ativoA.MC.get(i)%this.ativoA.MI.get(i);
        }
        for (int i = 0; i < this.ativoB.MI.size(); i++) {
            tendencia[1]  = this.ativoB.MC.get(i)%this.ativoB.MI.get(i);
        }
        for (int i = 0; i < this.ativoC.MI.size(); i++) {
            tendencia[2]  = this.ativoC.MC.get(i)%this.ativoC.MI.get(i);
        }
        for (int i = 0; i < this.ativoD.MI.size(); i++) {
            tendencia[3]  = this.ativoD.MC.get(i)%this.ativoD.MI.get(i);
        }
        return tendencia;
    }

    public Caixa_Geral getCaixaGeral() {
        return caixaGeral;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Caixa getCaixa1() {
        return caixa1;
    }

    public Caixa getCaixa2() {
        return caixa2;
    }
    
    public int[] risco(){

      int risco[] = null;
      double perigo[] = drawdown();
      double futuro[] = tendencia();
      

       for (int i = 0; i < perigo.length; i++) {
           futuro[i]= perigo[i]*futuro[i];
           
       }
       
       for (int i = 0; i < futuro.length; i++) {
           double aux1 = 0;
           double aux2 = futuro[0];
           risco[0]=0;
           if (aux1 < futuro[i]) {
               risco[1] = i;               
           }
           if (aux2 > futuro[i]) {
               risco[0] = i;
           }
       }

      return risco;
   }
    
    @Override
    public void run(){
            while (numOp >0){
                for (int i = 0; i < 9; i++) {
                    this.cliente[i].start();
                }
            }
    }
    
}
