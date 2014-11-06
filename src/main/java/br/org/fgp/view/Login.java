package br.org.fgp.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.SecurityUtils;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.JButtonOk;
import br.org.fgp.view.core.UILookAndFellManager;

public class Login extends JFrame {

	private static final String LOGIN = "Login";

	private static final long serialVersionUID = -6161972191900709890L;

	protected JPanel contentPane;

	protected JTextField txtUsuario;

	static Login frame = new Login();

	@Autowired
	private UsuarioDao usuarioDao;

	private JPasswordField txtSenha;
	private JButton btnLogar;
	private static Future<?> threadLogin;

	JLabel lblMsg = new JLabel("");

	public static void main(String[] args) {
		
		Runnable run = new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		ExecutorService executadorServico = Executors.newSingleThreadExecutor();
		threadLogin = executadorServico.submit(run);
	}

	public Login() {
		UILookAndFellManager.init();
		setTitle("Bem vindo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 303);
		setSize(300, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, LOGIN, TitledBorder.LEFT, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{50, 100, 0};
		gbl_contentPane.rowHeights = new int[]{14, 30, 30, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		GridBagConstraints gbc_lblUsurio = new GridBagConstraints();
		gbc_lblUsurio.anchor = GridBagConstraints.WEST;
		gbc_lblUsurio.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsurio.gridx = 0;
		gbc_lblUsurio.gridy = 1;
		contentPane.add(lblUsurio, gbc_lblUsurio);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
		gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsuario.gridx = 1;
		gbc_txtUsuario.gridy = 1;
		contentPane.add(txtUsuario, gbc_txtUsuario);

		JLabel lblSenha = new JLabel("Senha:");
		GridBagConstraints gbc_lblSenha = new GridBagConstraints();
		gbc_lblSenha.anchor = GridBagConstraints.WEST;
		gbc_lblSenha.insets = new Insets(0, 0, 5, 5);
		gbc_lblSenha.gridx = 0;
		gbc_lblSenha.gridy = 2;
		contentPane.add(lblSenha, gbc_lblSenha);
		txtSenha = new JPasswordField();
		txtSenha.setEchoChar('#');
		GridBagConstraints gbc_txtSenha = new GridBagConstraints();
		gbc_txtSenha.insets = new Insets(0, 0, 5, 0);
		gbc_txtSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSenha.gridx = 1;
		gbc_txtSenha.gridy = 2;
		contentPane.add(txtSenha, gbc_txtSenha);

		lblMsg = new JLabel("");		
		lblMsg.setForeground(Color.RED);
		GridBagConstraints gbc_lblMsg = new GridBagConstraints();
		gbc_lblMsg.gridx = 1;
		gbc_lblMsg.gridy = 4;
		contentPane.add(lblMsg, gbc_lblMsg);

		btnLogar = new JButtonOk();	
		btnLogar.setText(LOGIN);
		//Definindo o enter dispara o BOTÃO para Logar
		rootPane.setDefaultButton(btnLogar);
		GridBagConstraints gbc_btnLogar = new GridBagConstraints();
		gbc_btnLogar.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogar.gridx = 1;
		gbc_btnLogar.gridy = 3;
		contentPane.add(btnLogar, gbc_btnLogar);

		ComponenteControlado<Login> controleAcesso = new ComponenteControlado<Login>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);
		usuarioDao = ApplicationContextConfig.getContext().getBean(UsuarioDao.class);

		btnLogar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logar();
			}


		});
	}

	private void logar() {
		Usuario usuario = usuarioDao.buscarPorLogin(txtUsuario.getText());
		String senhaCriptografada = SecurityUtils.encrypt( String.valueOf( txtSenha.getPassword() ) ) ;
		if (usuario != null && usuario.getSenha().equals(senhaCriptografada) ) {
			TelasUtils.setUsuarioLogado(usuario);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TelaPrincipal telaPrincipal = ApplicationContextConfig.getContext().getBean(TelaPrincipal.class);
						telaPrincipal.load(null);
						telaPrincipal.show();
						telaPrincipal.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			frame.dispose();
			threadLogin.cancel(true);
		} else {
			lblMsg.setText("Usuário ou senha inválidos.");
		}
	}
	
	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JTextField getTxtSenha() {
		return txtSenha;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
}
