import java.awt.Button;
import java.awt.Container;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SpringLayout.Constraints;


public class EncryptionGUI {

	//GUI
	private JFrame frame;
	private Button privateKeyGenButton;
	private Button encryptButton;
	private Button decryptButton;


	private TextField privateKeyGenField;
	private TextField privateKeyInputField;
	private TextField publicKeyOutputField;
	
	private TextField encryptionKeyInputField;
	private TextField decryptionKeyInputField;

	private TextField messageEncryptionInputField;
	private TextField messageEncryptionOutputField;
	private TextField messageDecryptionInputField;
	private TextField messageDecryptionOutputField;

	public static void main(String[] args) {
		new EncryptionGUI();
	}
	
	public EncryptionGUI() {

		frame = new JFrame("Encryption GUI");
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 600);

		Container pane = frame.getContentPane();
		pane.setLayout(new SpringLayout());

		JPanel generateKeyPane = new JPanel();
		generateKeyPane.add(new Label("Private Key"));
		privateKeyGenField = new TextField(50);
		generateKeyPane.add(privateKeyGenField);
		privateKeyGenButton = new Button("Generate Key");
		privateKeyGenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrivateKey myPrivateKey = new PrivateKey(500);
				privateKeyGenField.setText(myPrivateKey.toString());
			}
		});
		generateKeyPane.add(privateKeyGenButton);
		pane.add(generateKeyPane, new Constraints(Spring.constant(
				0), Spring.constant(0)));

		
		
		JPanel extractPublicKeyPane = new JPanel();
		extractPublicKeyPane.add(new Label("Private Key:"));
		privateKeyInputField = new TextField(50);
		extractPublicKeyPane.add(privateKeyInputField);
		extractPublicKeyPane.add(new Label("Public Key:"));
		publicKeyOutputField = new TextField(50);
		extractPublicKeyPane.add(publicKeyOutputField);
		privateKeyGenButton = new Button("Extract Public Key");
		privateKeyGenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrivateKey myPrivateKey = new PrivateKey(privateKeyInputField.getText());
				publicKeyOutputField.setText(myPrivateKey.getPublicKey().toString());
			}
		});
		extractPublicKeyPane.add(privateKeyGenButton);
		pane.add(extractPublicKeyPane, new Constraints(Spring.constant(0), 
				Spring.constant(frame.getHeight()/5)));
		
		
		
		JPanel encryptPane = new JPanel();
		encryptPane.add(new Label("Public Key:"));
		encryptionKeyInputField = new TextField(50);
		encryptPane.add(encryptionKeyInputField);
		encryptPane.add(new Label("Message:"));
		messageEncryptionInputField = new TextField(50);
		encryptPane.add(messageEncryptionInputField);
		encryptButton = new Button("Encrypt");
		encryptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PublicKey myPublicKey = new PublicKey(encryptionKeyInputField.getText());
				String newMessage = myPublicKey.encrypt(messageEncryptionInputField.getText());
				messageEncryptionOutputField.setText(newMessage);
			}
		});
		encryptPane.add(encryptButton);
		messageEncryptionOutputField = new TextField(50);
		pane.add(messageEncryptionOutputField, new Constraints(Spring.constant(0), 
				Spring.constant(1*frame.getHeight()/2)));
		pane.add(encryptPane, new Constraints(Spring.constant(0), 
				Spring.constant(2*frame.getHeight()/5)));
		
		
		
		JPanel decryptPane = new JPanel();
		decryptPane.add(new Label("Private Key:"));
		decryptionKeyInputField = new TextField(50);
		decryptPane.add(decryptionKeyInputField);
		decryptPane.add(new Label("Message:"));
		messageDecryptionInputField = new TextField(50);
		decryptPane.add(messageDecryptionInputField);
		decryptButton = new Button("Decrypt");
		decryptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrivateKey myPrivateKey = new PrivateKey(decryptionKeyInputField.getText());
				String newMessage = myPrivateKey.decrypt(messageDecryptionInputField.getText());
				messageDecryptionOutputField.setText(newMessage);
			}
		});
		decryptPane.add(decryptButton);
		messageDecryptionOutputField = new TextField(50);
		pane.add(messageDecryptionOutputField, new Constraints(Spring.constant(0), 
				Spring.constant(7*frame.getHeight()/10)));
		pane.add(decryptPane, new Constraints(Spring.constant(0), 
				Spring.constant(3*frame.getHeight()/5)));

		frame.setVisible(true);
	}
	
	
}

