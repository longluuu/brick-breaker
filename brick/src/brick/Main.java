package brick;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Gameplay panel = new Gameplay();
		frame.getContentPane().add(panel);
		frame.setBounds(10,10,700,600);
		frame.setTitle("Break Ball");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
