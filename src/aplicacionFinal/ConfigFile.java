package aplicacionFinal;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ConfigFile {

	public static void main(String[] args) {
		Marco3 miMarco  = new Marco3();
		miMarco.setDefaultCloseOperation(3);
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("config file", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(miMarco);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +chooser.getSelectedFile().getAbsolutePath());
		}
	}

}

class Marco3 extends JFrame {
	public Marco3() {
		setBounds(300, 300, 300, 300);
		setVisible(true);
	}

}
