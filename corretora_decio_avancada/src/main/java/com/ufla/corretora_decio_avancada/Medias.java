/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufla.corretora_decio_avancada;

/**
 *
 * @author decio.mendonca
 */

import java.io.File;

 
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Medias extends MetaTrader {
    

    public void Salvar(){
        
        String caminho = "";
        
        System.out.println("Iniciando armazenamento de dados");
        if (this.Ativo != null) {
            caminho = "C:\\Users\\decio.mendonca\\Desktop\\Lixo\\Avançada\\Salvos\\Ativo_".concat(this.Ativo).concat("_DadosSalvos.xls");
        }

		try {
			WritableWorkbook planilha = Workbook.createWorkbook(new File(caminho));
			// Adicionando o nome da aba
			WritableSheet aba = planilha.createSheet("DadosMediasMetaTrader", 0);
 
			// Cabeçalhos
			String cabecalho[] = new String[7];
			cabecalho[0] = "Fechamento";
			cabecalho[1] = "Média Simples";
			cabecalho[2] = "Média Exponencial";
			cabecalho[3] = "Desvio padrão";
			cabecalho[4] = "Média Curta";
                        cabecalho[5] = "Média Intermediária";
			cabecalho[6] = "Média Longa";
			 
			// Cor de fundo das celulas
			Colour bckcolor = Colour.WHITE;
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setBackground(bckcolor);
 
			// Cor e tipo de fonte
			WritableFont fonte = new WritableFont(WritableFont.ARIAL);
			fonte.setColour(Colour.BLACK);
			cellFormat.setFont(fonte);
 
			// Write the Header to the excel file
			for (int i = 0; i < cabecalho.length; i++) {
				Label label = new Label(i, 0, cabecalho[i]);
				aba.addCell(label);
				WritableCell cell = aba.getWritableCell(i, 0);
				cell.setCellFormat(cellFormat);
                                
			}
 
			for (int linha = 1; linha < this.ML.size(); linha++) { // Número da linha
 
				Number number = new Number(0, linha, this.close.get(linha));
                               	aba.addCell(number);
                                                                 
				Number number1 = new Number(1, linha, this.MS.get(linha));
				aba.addCell(number1);
 
				Number number2 = new Number(2, linha, this.ME.get(linha));
				aba.addCell(number2);
                                
                                Number number3 = new Number(3, linha, this.desvio.get(linha));
				aba.addCell(number3);
                                
                                Number number4 = new Number(4, linha, this.MC.get(linha));
				aba.addCell(number4);
                                
                                Number number5 = new Number(5, linha, this.MI.get(linha));
				aba.addCell(number5);
                                
                                Number number6 = new Number(6, linha, this.ML.get(linha));
				aba.addCell(number6);
			}
 
			planilha.write();
			// Fecha o arquivo
			planilha.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Fim do armazenamento de dados");
                
	}
       
}
    
    
    

