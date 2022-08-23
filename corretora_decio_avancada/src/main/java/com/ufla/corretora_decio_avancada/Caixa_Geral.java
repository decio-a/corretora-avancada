/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufla.corretora_decio_avancada;

import java.util.ArrayList;

/**
 *
 * @author decio.mendonca
 */
public class Caixa_Geral  {
    protected ArrayList<String> data = new ArrayList<>();
    protected ArrayList<String> time = new ArrayList<>();
    protected ArrayList<Double> close = new ArrayList<>();
    protected ArrayList<Integer> cliente = new ArrayList<>();
    protected ArrayList<Integer> operacao = new ArrayList<>();
    protected ArrayList<Double> saldo_Cliente = new ArrayList<>();
    protected int cont = 0;

    public Caixa_Geral() {
    }
    
    public void salva_operacao(String data, String time, double close, int cliente, int op, double saldoc){
        this.data.add(data);
        this.time.add(time);
        this.close.add(close);
        this.cliente.add(cliente);
        this.operacao.add(op);
        this.saldo_Cliente.add(saldoc);
        cont++;
    }

    public int getCont() {
        return cont;
    }

    
    @Override
    public String toString() {
        return "Caixa_Geral{" + "data=" + data + ", time=" + time + ", close=" + close + ", cliente=" + cliente + ", operacao=" + operacao + ", saldo_Cliente=" + saldo_Cliente + '}';
    }
    
    
}
