package test;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class GUI extends JFrame implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static BeyesClassification bayes = new BeyesClassification();
	private static JTextArea textArea;
	private static final int DEFAULT_WIDTH = 564;
	private static final int DEFAULT_HEIGHT = 471;
	private boolean flagTrain = false;
	private boolean flagTest = false;
	private static JPanel panel = null;

	public GUI()
	{
		super();
		getContentPane().setLayout(null);
		setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		final JButton button1 = new JButton();
		button1.addActionListener(new ActionListener()
		{
			public void actionPerformed(final ActionEvent e)
			{
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int n = chooser.showOpenDialog(getContentPane());
				if (n == JFileChooser.APPROVE_OPTION)
				{
					bayes.setTrainPath(chooser.getSelectedFile().getPath());
					flagTrain = true;
				}
			}
		});
		button1.setText("TRAIN");
		button1.setBounds(93, 64, 106, 28);
		getContentPane().add(button1);

		final JButton button2 = new JButton();
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(final ActionEvent e)
			{
				final JFrame f = new JFrame();
				FileDialog fd = new FileDialog(f, "打开文件对话框", FileDialog.LOAD);
				fd.setVisible(true);
				String str = fd.getDirectory() + fd.getFile();
				bayes.setTestPath(str);
				flagTest = true;
			}
		});
		button2.setText("TEST");
		button2.setBounds(325, 64, 106, 28);
		getContentPane().add(button2);

		textArea = new JTextArea();
		textArea.setBounds(68, 116, 412, 246);
		getContentPane().add(textArea);

		setVisible(true);

		Toolkit tk = this.getToolkit();
		Dimension dm = tk.getScreenSize();
		this.setLocation((int) (dm.getWidth() - DEFAULT_WIDTH) / 2,
				(int) (dm.getHeight() - DEFAULT_HEIGHT) / 2);// ��ʾ����Ļ����
	}

	public static void setTextArea(String s)
	{
		// textArea.append(s+"\n");
		textArea.insert(s + "\n", 0);
	}

	public static void main(String[] args)
	{
		// EventQueue.invokeLater(new Runnable() {
		// public void run() {
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
		}
		GUI gui = new GUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		Thread t = new Thread(gui);
		t.start();
		// }
		// });
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while (true)
		{
			if (flagTrain)
			{
				JOptionPane.showMessageDialog(
						panel,
						"Training is start!\nIt costs "
								+ bayes.getTrainingTime() + " ms");
				bayes.train();
				flagTrain = false;
				JOptionPane.showMessageDialog(
						panel,
						"Training is OVER!\nIt costs "
								+ bayes.getTrainingTime() + " ms");
			}
			if (flagTest)
			{
				bayes.test("");
				flagTest = false;
				JOptionPane.showMessageDialog(panel,
						"Test is OVER!\nIt belongs to " + bayes.getLabelName());
			}
		}
	}
}
