/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufla.corretora_decio_avancada;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author decio.mendonca
 */
public class Caixa extends Caixa_Geral {

    private Corretora corretora;
    private ArrayList<String> dados = new ArrayList<>();
    private int indice = 0;
    private Semaphore s = new Semaphore(1);
    private boolean ocupado = false;

    public Caixa(Corretora corretora) {
      
        this.corretora = corretora;
    }
    
    public void operacao(int tipo_op, Cliente cliente, Corretora corretora, Ativo ativo) throws InterruptedException{
        //realizar operacao;
        //salvar o tempo
        
        s.acquire();
        ocupado = true;
        if ( tipo_op == 1 ) {
            //vendendo
            cliente.setPriority(10);
            for (int i = 0; i < cliente.acoes.size(); i++) {
                if (cliente.acoes.get(i).getAtivo().equals(ativo.getAtivo())) {
                    cliente.acoes.remove(i);
                    cliente.setSaldo(cliente.getSaldo()+ativo.getClose(indice));
                    super.salva_operacao(ativo.getData(indice), ativo.getTime(indice), ativo.getClose(indice), cliente.getCliente(), tipo_op, cliente.getSaldo());
                    indice++;
                    
                }else{
                    System.out.println("Você não tem esse ativo");
                    indice++;
            } 
        }
                Thread.sleep(500);
        }
        if (tipo_op == 0) {
            //comprando --- falta salvar no caixa geral
            cliente.setPriority(5);
            if (cliente.getSaldo() > ativo.getClose(this.indice)) {
                cliente.acoes.add(ativo);
                cliente.setSaldo(cliente.getSaldo()-ativo.getClose(this.indice));
                super.salva_operacao(ativo.getData(indice), ativo.getTime(indice), ativo.getClose(indice), cliente.getCliente(), tipo_op, cliente.getSaldo());
                indice++;            
            }else{
                System.out.println("Você não tem dinheiros");
                indice++;
            }
            Thread.sleep(500);
        }
        
        s.release();
        ocupado = false;
        
        
    }

    public int getCont() {
        return cont;
    }

    public boolean isOcupado() {
        return ocupado;
    }

 
    
    
}
