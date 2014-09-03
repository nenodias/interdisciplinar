package br.org.fgp.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.FrameControlado;

public class Login extends FrameControlado {

	protected JPanel contentPane;
	protected JTextField txtUsuario;
	static Login frame = new Login();
	JLabel lblMsg = new JLabel("");
	
	@Autowired
	private UsuarioDao usuarioDao;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					frame.setVisible(true);		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblBemVindo = new JLabel("Bem Vindo!");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtSenha = new JPasswordField();
		txtSenha.setEchoChar('#');
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		
		JLabel lblSenha = new JLabel("Senha:");
		
		JButton btnLogar = new JButton("Logar");
		
		
		lblMsg.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMsg.setForeground(Color.RED);
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(141, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblUsurio)
								.addComponent(lblSenha)
								.addComponent(lblBemVindo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtSenha)
								.addComponent(txtUsuario))
							.addGap(130))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(lblMsg))
								.addComponent(btnLogar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
							.addGap(161))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addComponent(lblBemVindo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUsurio)
					.addGap(7)
					.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSenha)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMsg)
					.addContainerGap(39, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		pronto(TipoUsuario.ADMINISTRADOR);
		
		btnLogar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			   Usuario usuario = usuarioDao.buscarPorId(1);
			   
			    if(txtUsuario.getText().equals(usuario.getLogin()) && String.valueOf(txtSenha.getPassword()).equals(usuario.getSenha()))
			    {
					JOptionPane.showMessageDialog(null, "Seja bem-vindo "+usuario.getLogin());
					TelaPrincipal.main(null);
					frame.dispose();
			    }			
			    else
			    {
			    	lblMsg.setText("falha");			    	
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
