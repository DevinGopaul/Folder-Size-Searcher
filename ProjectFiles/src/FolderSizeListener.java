import java.io.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

public class FolderSizeListener implements ActionListener {
	private DirectorySearcher searcher;
	private JLabel progressLabel;
	private JLabel baseHeader;
	private JLabel baseLabel;
	private JLabel biggestSubfoldersLabels[];
	private JLabel biggestFilesLabels[];
	private JFrame frame;
	public FolderSizeListener(JLabel progressLabel, JLabel baseHeader, JLabel baseLabel, JLabel biggestSubfoldersLabels[], JLabel biggestFilesLabels[], JFrame frame) {
		this.progressLabel = progressLabel;
		this.baseHeader = baseHeader;
		this.baseLabel = baseLabel;
		this.biggestSubfoldersLabels = biggestSubfoldersLabels;
		this.biggestFilesLabels = biggestFilesLabels;
		this.searcher = new DirectorySearcher();
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		System.out.println(e.getActionCommand());
		if (e.getActionCommand() == "Choose folder") {
			this.progressLabel.setText("Working...");
            int returnVal = fc.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
               File file = fc.getSelectedFile();
			   this.baseHeader.setText("Total size of "+file.getPath());
			   this.frame.pack();
			   ArrayList<String> strings = this.searcher.search(file.getPath());
			   this.baseLabel.setText(strings.get(0));
			   this.progressLabel.setText("Done");
			   for (int i = 0; i < 5; i++){
				   this.biggestSubfoldersLabels[i].setText(strings.get(i+1));
			   }
			   for (int i = 0; i < 5; i++){
				this.biggestFilesLabels[i].setText(strings.get(i+6));
			}
			   this.frame.pack();
            } else {
				this.progressLabel.setText("Ready to perform");
            }
        }
	}
}
