/**
 * 
 */
package view;

import controller.ThreadCalculo;

/**
 * @author Lucas Batista 24 de mar. de 2024
 */
public class Main {
	public static void main(String[] args) {

		for (int i = 1; i <= 21; i++) {
			ThreadCalculo thread = new ThreadCalculo(i);
			thread.start();
		}
	}

}