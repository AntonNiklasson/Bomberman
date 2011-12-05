import javax.swing.JFrame;



public class Window extends JFrame {
	
	protected JFrame frame;
	
	public Window() {
		// Init frame
		frame = new JFrame("WINDOW TITLE");
		
		// Stop program when closing
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setting frame size
		frame.setSize(300, 300); // TODO Change these values
		
		// Centering the frame
		frame.setLocationRelativeTo(null);
		
		// Show the frame
		frame.setVisible(true);
		
		// No resizing
		frame.setResizable(false);
	}

}
