/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufla.corretora_decio_avancada;

/**
 *
 * @author decio.mendonca
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Grafico {

    
    public void Graficos(String caminho){
        
        var MS = new XYSeries("Media Simples");
        var ME = new XYSeries("Media exponencial");
        var DP = new XYSeries("Desvio padrao");
        var MC = new XYSeries("Media curta");
        var MI = new XYSeries("Media intermediaria");
        var ML = new XYSeries("Media longa");
      

        try {
            BufferedReader bf = new BufferedReader(new FileReader(caminho));
            int i =0;
            String linha = bf.readLine();

            while (linha != null) {
                            // problema aqui
                String[] ler_linhas = linha.split("\\s+");
                
                MS.add(i, Float.parseFloat(ler_linhas[1]));
                ML.add(i, Float.parseFloat(ler_linhas[2]));
                MC.add(i, Float.parseFloat(ler_linhas[3]));
                MI.add(i, Float.parseFloat(ler_linhas[4]));
                ME.add(i, Float.parseFloat(ler_linhas[5]));
                DP.add(i, Float.parseFloat(ler_linhas[6]));

                i++;
            }
            bf.close();
            
        }
        catch (IOException e) {
            System.out.println( "erro não tratado - somente indicação ");
            
        }

        var dataset = new XYSeriesCollection();
        dataset.addSeries(ML);
        dataset.addSeries(MI);
        dataset.addSeries(MC);
        dataset.addSeries(ME);
        dataset.addSeries(DP);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Evolução dos Preços",
                "Evolução",
                "Preço",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        XYPlot plot = chart.getXYPlot();
        var render = new XYLineAndShapeRenderer();
        render.setSeriesPaint(0, Color.red);
        render.setSeriesStroke(0, new BasicStroke(2.0f));
        render.setSeriesPaint(0, Color.blue);
        render.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(render);
        plot.setBackgroundPaint(Color.white);
        plot.setForegroundAlpha(0.5f);
        plot.setRangeGridlinePaint(Color.red);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.black);
        plot.setDomainGridlinesVisible(true);

        chart.getLegend().setFrame(BlockBorder.NONE);
        ChartFrame frame1 = new ChartFrame("Grafico de linhas", chart);
        frame1.setVisible(true);
        frame1.setSize(1200,800);


    }

}
