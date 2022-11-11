package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.SetorDao;
import br.org.fgp.model.Contato;
import br.org.fgp.model.ContatoTelefone;
import br.org.fgp.model.Fornecedor;
import br.org.fgp.model.Setor;
import br.org.fgp.model.Telefone;
import br.org.fgp.view.core.ButtonColumnEditar;
import br.org.fgp.view.core.ButtonColumnExcluir;
import br.org.fgp.view.core.JBusca;
import br.org.fgp.view.core.JButtonAdicionar;
import br.org.fgp.view.core.JButtonCancelar;
import br.org.fgp.view.core.JButtonOk;
import br.org.fgp.view.core.TableModelGenerico;

public class CadastroContato extends JDialog {

    private static final long serialVersionUID = 908511701739184294L;

    private static final Logger LOGGER = Logger.getLogger(CadastroContato.class);

    private Contato contato;
    private JDialog painel;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JScrollPane painelTabela;
    private JTable tabela;
    private List<ContatoTelefone> listaTelefones;
    private TableModelGenerico<ContatoTelefone> modelGenerico;
    private JBusca<Setor, Integer> txtSetor;
    private SetorDao setorDao;

    public CadastroContato(Contato contato) {
        this.painel = this;
        this.contato = contato;
        this.setModal(true);
        setAlwaysOnTop(true);
        setBounds(0, 0, 640, 300);
        setSize(640, 300);
        setLocationRelativeTo(null);
        setTitle("Contato");
        JPanel buttonPane = new JPanel();
        getContentPane().setLayout(null);
        getContentPane().add(buttonPane);

        adicionarComponente(new JLabel("Nome:"), 2);
        txtNome = new JTextField();
        adicionarComponente(txtNome, 2);

        adicionarComponente(new JLabel("Email:"), 4);
        txtEmail = new JTextField();
        adicionarComponente(txtEmail, 4);

        adicionarComponente(new JLabel("Setor:"), 6);
        txtSetor = new JBusca<Setor, Integer>();
        setorDao = ApplicationContextConfig.getContext().getBean(SetorDao.class);
        txtSetor.setDaoGenerico(setorDao);
        adicionarComponente(txtSetor, 6);

        painelTabela = new JScrollPane();
        tabela = new JTable();
        if (this.contato != null && this.contato.getListaTelefone() != null) {
            listaTelefones = this.contato.getListaTelefone();
        } else {
            listaTelefones = new ArrayList<ContatoTelefone>();
        }
        modelGenerico = new TableModelGenerico<ContatoTelefone>(listaTelefones, ContatoTelefone.class);
        JButton btnAdicionarTelefone = new JButtonAdicionar();
        btnAdicionarTelefone.setBounds(TelasUtils.DEFAULT_X + TelasUtils.DEFAULT_ESPACO, TelasUtils.DEFAULT_Y + (6 * TelasUtils.DEFAULT_ESPACO), TelasUtils.DEFAULT_LARGURA_COMPONENTE / 2, TelasUtils.DEFAULT_ALTURA_COMPONENTE);
        add(btnAdicionarTelefone);
        tabela.setModel(modelGenerico);
        tabela.setEnabled(true);
        painelTabela.setViewportView(tabela);
        painelTabela.setEnabled(true);
        painelTabela.setBounds(TelasUtils.DEFAULT_X + TelasUtils.DEFAULT_ESPACO, TelasUtils.DEFAULT_Y + (8 * TelasUtils.DEFAULT_ESPACO), TelasUtils.DEFAULT_LARGURA_COMPONENTE * 2, TelasUtils.DEFAULT_ALTURA_COMPONENTE + TelasUtils.DEFAULT_ALTURA_COMPONENTE * 2);
        add(painelTabela);
        painelTabela.setVisible(true);

        buttonPane.setBounds(0, 220, 300, TelasUtils.DEFAULT_ALTURA_COMPONENTE * 2);
        JButton okButton = new JButtonOk();
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        JButton cancelButton = new JButtonCancelar();
        buttonPane.add(cancelButton);

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eventoCliqueTabela(e);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fecharDialogo();
            }
        });
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                novoContato();
            }

        });
        btnAdicionarTelefone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarTelefone();
            }
        });
        if (StringUtils.isNotBlank(contato.getNome())) {
            txtNome.setText(contato.getNome());
        }
        if (StringUtils.isNotBlank(contato.getEmail())) {
            txtEmail.setText(contato.getEmail());
        }
        if (contato.getSetor() != null) {
            txtSetor.setText(contato.getSetor().getId().toString());
        }
        atualizaDesenhoTabela();
    }

    protected void novoContato() {
        contato.setListaTelefone(listaTelefones);
        contato.setNome(txtNome.getText());
        contato.setEmail(txtEmail.getText());
        if (StringUtils.isNotBlank(txtSetor.getText())) {
            Integer idSetor = Integer.parseInt(txtSetor.getText());
            Setor setor = setorDao.buscarPorId(idSetor);
            contato.setSetor(setor);
        }
        txtNome.setText(StringUtils.EMPTY);
        txtEmail.setText(StringUtils.EMPTY);
        txtSetor.setText(StringUtils.EMPTY);
        fecharDialogo();
    }

    private void fecharDialogo() {
        painel.dispose();
    }

    private void adicionarComponente(JComponent componente, int valor) {
        Map<String, Integer> parametros = new HashMap<String, Integer>();
        TelasUtils.adicionarComponente(componente, valor, this, parametros);
    }

    private void adicionarTelefone() {
        final Telefone telefone = new Telefone();
        abrirModalTelefone(telefone);
        if (StringUtils.isNotBlank(telefone.getTelefone())) {
            ContatoTelefone contatoTelefone = new ContatoTelefone();
            contatoTelefone.setTelefone(telefone);
            contatoTelefone.setContato(contato);
            listaTelefones.add(contatoTelefone);
            atualizaDesenhoTabela();
        }
    }

    private void atualizaDesenhoTabela() {
        if (listaTelefones.size() > 0) {
            ButtonColumnEditar buttonColumnEditar = new ButtonColumnEditar(tabela, null, modelGenerico.getCountadorColunas());
            ButtonColumnExcluir buttonColumnExcluir = new ButtonColumnExcluir(tabela, null, modelGenerico.getCountadorColunas() + 1);

            if (TelasUtils.getUsuarioLogado() != null && TelasUtils.isPermision(Fornecedor.class, TelasUtils.getUsuarioLogado().getTipo())) {
                buttonColumnExcluir.setEnabled(false);
            }

        }
        if (!modelGenerico.getLista().equals(listaTelefones)) {
            modelGenerico.getLista().clear();
            for (ContatoTelefone contatoTelefone : listaTelefones) {
                modelGenerico.getLista().add(contatoTelefone);
            }
            listaTelefones = (List<ContatoTelefone>) modelGenerico.getLista();

        }
        modelGenerico.fireTableDataChanged();
        tabela.updateUI();
    }

    private void abrirModalTelefone(final Telefone telefone) {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    CadastroTelefone dialog = new CadastroTelefone(telefone);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setLocationRelativeTo(painel);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        };
        runnable.run();
    }

    @SuppressWarnings("unchecked")
    private void eventoCliqueTabela(MouseEvent e) {
        if (e.getClickCount() == 2) {
            TableModelGenerico<ContatoTelefone> model = (TableModelGenerico<ContatoTelefone>) tabela.getModel();
            boolean atualizaTabela = false;
            int linha = tabela.getSelectedRow();
            int coluna = tabela.getSelectedColumn();
            ContatoTelefone contatoTelefone = listaTelefones.get(linha);

            if (coluna == model.getCountadorColunas()) {
                abrirModalTelefone(contatoTelefone.getTelefone());
                atualizaTabela = true;
            } else if (coluna == model.getCountadorColunas() + 1 && TelasUtils.getUsuarioLogado() != null && TelasUtils.isPermision(Fornecedor.class, TelasUtils.getUsuarioLogado().getTipo())) {
                int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro: " + contatoTelefone.getTelefone() + " ?", "Excluir?", JOptionPane.YES_NO_OPTION);
                if (excluir == JOptionPane.YES_OPTION) {
                    int contador = 0;
                    while (true) {
                        if (listaTelefones.get(contador).equals(contatoTelefone)) {
                            ContatoTelefone contatTelefone = listaTelefones.get(contador);
                            listaTelefones.remove(contatTelefone);
                            break;
                        }
                        contador++;
                    }
                }
                atualizaTabela = true;
            }
            if (atualizaTabela) {
                atualizaDesenhoTabela();
            }
        }
    }
}
