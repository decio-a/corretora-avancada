/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufla.corretora_decio_avancada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author decio.mendonca
 */
public class MetaTrader {
  
    // Valores
    protected ArrayList<String> data = new ArrayList<>();
    protected ArrayList<String> time = new ArrayList<>();
    protected ArrayList<Double> close = new ArrayList<>();
   
    
    //Médias
    protected ArrayList<Double> MS = new ArrayList<>();
    protected ArrayList<Double> ME = new ArrayList<>();
    protected ArrayList<Double> desvio = new ArrayList<>();
    protected ArrayList<Double> MC = new ArrayList<>();
    protected ArrayList<Double> MI = new ArrayList<>();
    protected ArrayList<Double> ML = new ArrayList<>();
    protected int qtPeriodosMS = 5;
    protected int qtPeriodosME = 5;
    protected int qtPeriodosMC = 5;
    protected int qtPeriodosMI = 40;
    protected int qtPeriodosML = 100;
    protected String Ativo = "";
    protected String caminho_ativo = "";

 
    public ArrayList<Double> getMS() {
        return MS;
    }

    public void setMS(ArrayList<Double> MS) {
        this.MS = MS;
    }

    public ArrayList<Double> getME() {
        return ME;
    }

    public void setME(ArrayList<Double> ME) {
        this.ME = ME;
    }

    public ArrayList<Double> getDesvio() {
        return desvio;
    }

    public void setDesvio(ArrayList<Double> desvio) {
        this.desvio = desvio;
    }

    public ArrayList<Double> getMC() {
        return MC;
    }

    public void setMC(ArrayList<Double> MC) {
        this.MC = MC;
    }

    public ArrayList<Double> getMI() {
        return MI;
    }

    public void setMI(ArrayList<Double> MI) {
        this.MI = MI;
    }

    public ArrayList<Double> getML() {
        return ML;
    }

    public void setML(ArrayList<Double> ML) {
        this.ML = ML;
    }

    public int getQtPeriodosMS() {
        return qtPeriodosMS;
    }

    public void setQtPeriodosMS(int qtPeriodosMS) {
        this.qtPeriodosMS = qtPeriodosMS;
    }

    public int getQtPeriodosME() {
        return qtPeriodosME;
    }

    public void setQtPeriodosME(int qtPeriodosME) {
        this.qtPeriodosME = qtPeriodosME;
    }

    public int getQtPeriodosMC() {
        return qtPeriodosMC;
    }

    public void setQtPeriodosMC(int qtPeriodosMC) {
        this.qtPeriodosMC = qtPeriodosMC;
    }

    public int getQtPeriodosMI() {
        return qtPeriodosMI;
    }

    public void setQtPeriodosMI(int qtPeriodosMI) {
        this.qtPeriodosMI = qtPeriodosMI;
    }

    public int getQtPeriodosML() {
        return qtPeriodosML;
    }

    public void setQtPeriodosML(int qtPeriodosML) {
        this.qtPeriodosML = qtPeriodosML;
    }

    public String getAtivo() {
        return Ativo;
    }

    public void setAtivo(String Ativo) {
        this.Ativo = Ativo;
    }

    public String getCaminho_ativo() {
        return caminho_ativo;
    }

    public void setCaminho_ativo(String caminho_ativo) {
        this.caminho_ativo = caminho_ativo;
    }   
    
public void pegadados() throws IOException{
    
    try (BufferedReader ler = new BufferedReader(new FileReader(this.caminho_ativo))){
        String linha = ler.readLine();

        while(linha != null){
            linha = ler.readLine();
            if (linha != null) {
            String[] textoSeparado = linha.split("\\s+");
            this.data.add(textoSeparado[0]);
            this.time.add(textoSeparado[1]);
            this.close.add(Double.parseDouble(textoSeparado[5]));
             
            }
            

        }
    }
    
}

    public String getData(int indice) {
        return data.get(indice);
    }

    public String getTime(int indice) {
        return time.get(indice);
    }


    public ArrayList<Double> getClose() {
        return close;
    }
    
public void MediaSimples(int periodos){
        this.qtPeriodosMS = periodos;
        int size = this.close.size();
        int aux1 = 0;
        int aux2 = periodos; // usar periodos
        double media = 0;
        
        while  (aux2 < size){
            media = 0;
            aux1 = aux2 - periodos;
            for (int i = aux1; i < aux2; i++) {
                media = media + this.close.get(i);
            }
            media = media/periodos;
            this.MS.add(media);
            aux2++;
            
            
        }
    }

public void MediaExponencial(int periodos){
    this.qtPeriodosME = periodos;
    //parametrização de periodos
    if(this.qtPeriodosME != this.qtPeriodosMS){
        this.MS.clear();
        MediaSimples(this.qtPeriodosME);
    }
    
    //calculo media exponencial
    int size = this.MS.size()-1;
    int aux3 = 0;
    double mediaE = 0;
    double Multiplicador = 0;
    double mediaS = 0;
    
    while (aux3 < size){
         mediaE = 0;
         Multiplicador = this.close.get(periodos+1+aux3);
         mediaS = MS.get(aux3);
         mediaE = ((2/(periodos+1))*(Multiplicador-mediaS))+mediaS;
         this.ME.add(mediaE);
         aux3++;
    }
    
}

    public void DesvioPadrao(){
        if(this.qtPeriodosMS > 0){
            int size = this.MS.size();
            int aux0 = 0;
            int aux1 = this.qtPeriodosMS;
            int aux2 = 0;
            double soma = 0;
            double desvio = 0;
            
            while (aux0 < size){
                
                for (int i = aux2; i < aux1; i++) {
                    soma = (double) (soma + Math.pow(this.close.get(i) - this.MS.get(aux0),2));
                }
                
                desvio = (double) Math.sqrt((soma/this.qtPeriodosMS));
                this.desvio.add(desvio);
                aux0++;
            }
            
        }else{
                System.out.println("Aumenta o período");
                    }
    }
    
    public ArrayList<Double> MetodoMediaGeral(int qtdPeriodos){
		ArrayList<Double> mediaGeral = new ArrayList<>();
		mediaGeral.clear();
		int contTam = this.close.size();
		int contAux2 = qtdPeriodos;
		int contAux1 = 0;
		double media = 0;
		while(contAux2<contTam) {
			media = 0;
			contAux1 = contAux2-qtdPeriodos;
			for(int i=contAux1; i<contAux2; i++){
				media = media + this.close.get(i);
			}
			media = media/qtdPeriodos;
			mediaGeral.add(media);
			contAux2++;
		}
		return mediaGeral;
	}
	
	public void MediaCurta(){
		this.MC = MetodoMediaGeral(qtPeriodosMC);
	}
	
	public void MediaIntermediaria(){
		this.MI = MetodoMediaGeral(qtPeriodosMI);
	}
	
	public void MediaLonga(){
		this.ML = MetodoMediaGeral(qtPeriodosML);
	}
    

} // ULTIMO CONCHETE