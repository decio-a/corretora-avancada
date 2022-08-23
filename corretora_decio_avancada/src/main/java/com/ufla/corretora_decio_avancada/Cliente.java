/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufla.corretora_decio_avancada;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author decio.mendonca
 */
public class Cliente extends Thread {
    
    private int cliente;
    private double saldo;
    ArrayList<Ativo> ativos = new ArrayList<>();
    private Corretora corretora;
    ArrayList<Ativo> acoes = new ArrayList<>();
    private int cont_op = 0;
    private int risco[] = null;

    public Cliente(int cliente, float saldo, Corretora corretora) {
        start();
        this.cliente = cliente;
        this.saldo = saldo;
        this.corretora = corretora;

    }

    public double getSaldo() {
        return saldo;
    }

    public  void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    

    public int getCliente() {
        return cliente;
    }
    
   
   public void operacao() throws InterruptedException{
       
      this.risco = corretora.risco();
       
       if (corretora.getCaixa1().isOcupado() == false & corretora.isStatus() == true) {
           corretora.getCaixa1().operacao(0, this, corretora, this.ativos.get(risco[0]));
           corretora.getCaixa1().operacao(1, this, corretora, this.acoes.get(risco[1]));

           
       }else if (corretora.getCaixa2().isOcupado() == false & corretora.isStatus() == true){
           corretora.getCaixa2().operacao(0, this, corretora, this.ativos.get(risco[0]));
           corretora.getCaixa1().operacao(1, this, corretora, this.acoes.get(risco[1]));
          
       }else{
           Thread.sleep(500);
       }

   }

    @Override
   public void run(){

       while(corretora.getCaixa1().getCont() <1000 & corretora.isStatus() == true){
           try {
               operacao();
           } catch (InterruptedException ex) {
               Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
           }
           cont_op++;
           if (corretora.getCaixa1().getCont() >= 1000) {
               corretora.setStatus(false);
           }
           
       }
   }
    
}
