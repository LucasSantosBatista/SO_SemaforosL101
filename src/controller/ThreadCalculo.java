/**
 * 
 */
package controller;

import java.util.concurrent.Semaphore;

/**
 * @author Lucas Batista 24 de mar. de 2024
 */
public class ThreadCalculo extends Thread {
	private int id;

	private static Semaphore semaforo = new Semaphore(1);

	public ThreadCalculo(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		int resto = id % 3;

		switch (resto) {
		case 1:
			calcular(0.2, 1.0);
			fazerTransacaoBancoDeDados(1);
			calcular(0.2, 1.0);
			fazerTransacaoBancoDeDados(1);
			break;
		case 2:
			calcular(0.5, 1.5);
			fazerTransacaoBancoDeDados(1.5);
			calcular(0.5, 1.5);
			fazerTransacaoBancoDeDados(1.5);
			calcular(0.5, 1.5);
			fazerTransacaoBancoDeDados(1.5);
			break;
		case 0:
			calcular(1, 2);
			fazerTransacaoBancoDeDados(1.5);
			calcular(1, 2);
			fazerTransacaoBancoDeDados(1.5);
			calcular(1, 2);
			fazerTransacaoBancoDeDados(1.5);
			break;
		default:
			break;
		}
	}

	private void calcular(double min, double max) {
		double tempo = min + Math.random() * (max - min);
		try {
			Thread.sleep((long) (tempo * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void fazerTransacaoBancoDeDados(double segundos) {
		try {
			semaforo.acquire(); // Adquire o semáforo
			System.out.println("Thread " + id + " iniciando transação com o banco de dados");
			Thread.sleep((long) (segundos * 1000)); // Simula a transação com o banco de dados
			System.out.println("Thread " + id + " concluindo transação com o banco de dados");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release(); // Libera o semáforo
		}
	}
}
