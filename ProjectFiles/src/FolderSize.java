import javax.swing.*;
import java.awt.*;

public class FolderSize {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Folder Size Searcher");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

		JPanel baseInfoPanel = new JPanel();
		baseInfoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		baseInfoPanel.setLayout(new BoxLayout(baseInfoPanel, BoxLayout.Y_AXIS));
		JPanel fileInfoPanel = new JPanel();
		fileInfoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		fileInfoPanel.setLayout(new BoxLayout(fileInfoPanel, BoxLayout.Y_AXIS));
		JPanel folderInfoPanel = new JPanel();
		folderInfoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		folderInfoPanel.setLayout(new BoxLayout(folderInfoPanel, BoxLayout.Y_AXIS));

		JButton button;

		button = new JButton("Choose folder");
		topPanel.setMaximumSize(new Dimension(Short.MAX_VALUE,35));

		JLabel progressLabel = new JLabel("Ready to perform");
		

		JLabel baseHeader = new JLabel("Total size of ---");
		baseInfoPanel.add(baseHeader);

		JLabel baseLabel = new JLabel("---");
		baseInfoPanel.add(baseLabel);

		JLabel subfolderHeader = new JLabel("Biggest subfolders (not counting the size of their own subfolders)");
		folderInfoPanel.add(subfolderHeader);


		JLabel biggestSubfoldersLabels[] = new JLabel[5];

		for (int i = 0; i<5; i++){
			int k = i+1;
			biggestSubfoldersLabels[i] = new JLabel(k+") ---");
			folderInfoPanel.add(biggestSubfoldersLabels[i]);
		}

		JLabel filesHeader = new JLabel("Biggest files");
		fileInfoPanel.add(filesHeader);

		JLabel biggestFilesLabels[] = new JLabel[5];

		for (int i = 0; i<5; i++){
			int k = i+1;
			biggestFilesLabels[i] = new JLabel(k+") ---");
			fileInfoPanel.add(biggestFilesLabels[i]);
		}

		topPanel.add(button);
		topPanel.add(progressLabel);

		bottomPanel.add(baseInfoPanel);
		bottomPanel.add(folderInfoPanel);
		bottomPanel.add(fileInfoPanel);

		mainPanel.add(topPanel);
		mainPanel.add(bottomPanel);
		
		frame.getContentPane().add(mainPanel);

		FolderSizeListener listener = new FolderSizeListener(progressLabel, baseHeader, baseLabel, biggestSubfoldersLabels, biggestFilesLabels, frame);

		button.addActionListener(listener);

		frame.pack();

		frame.setVisible(true);


	}
}
