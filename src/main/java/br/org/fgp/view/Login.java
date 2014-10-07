package br.org.fgp.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;

import br.org.fgp.core.SecurityUtils;
import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;

public class Login extends JFrame {

	protected JPanel contentPane;
	
	protected JTextField txtUsuario;
	
	static Login frame = new Login();
	
	JLabel lblMsg = new JLabel("");

	@Autowired
	private UsuarioDao usuarioDao;
	
	private JPasswordField txtSenha;
	private JButton btnLogar;
	private static Future<?> threadLogin;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JLabel lblBemVindo = new JLabel("Bem Vindo!");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 30));

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtSenha = new JPasswordField();
		txtSenha.setEchoChar('#');

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");

		JLabel lblSenha = new JLabel("Senha:");

		btnLogar = new JButton("Logar");
		//Definindo o enter dispara o botão para Logar
		rootPane.setDefaultButton(btnLogar);

		lblMsg.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMsg.setForeground(Color.RED);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap(141, Short.MAX_VALUE)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								lblUsurio)
																						.addComponent(
																								lblSenha)
																						.addComponent(
																								lblBemVindo,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								txtSenha)
																						.addComponent(
																								txtUsuario))
																		.addGap(130))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addGap(10)
																										.addComponent(
																												lblMsg))
																						.addComponent(
																								btnLogar,
																								GroupLayout.PREFERRED_SIZE,
																								90,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(161)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addGap(38)
						.addComponent(lblBemVindo)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblUsurio)
						.addGap(7)
						.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblSenha)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnLogar)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblMsg)
						.addContainerGap(39, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
		ComponenteControlado<Login> controleAcesso = new ComponenteControlado<Login>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);

		btnLogar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logar();
			}

			private void logar() {
				Usuario usuario = usuarioDao.buscarPorLogin(txtUsuario.getText());
				String senhaCriptografada = SecurityUtils.encrypt( String.valueOf( txtSenha.getPassword() ) ) ;
				if (usuario.getSenha().equals(senhaCriptografada ) ) {
					JOptionPane.showMessageDialog(null, "Seja bem-vindo "
							+ usuario.getLogin());
					TelaPrincipal.main(usuario);
					frame.dispose();
					threadLogin.cancel(true);
				} else {
					lblMsg.setText("Usuário ou senha inválido.");
				}
			}
		});
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
