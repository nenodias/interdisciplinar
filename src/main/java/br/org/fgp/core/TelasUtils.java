package br.org.fgp.core;

import java.util.HashMap;
import java.util.Map;

import br.org.fgp.model.Categoria;
import br.org.fgp.model.EntradaProduto;
import br.org.fgp.model.Fornecedor;
import br.org.fgp.model.Marca;
import br.org.fgp.model.Produto;
import br.org.fgp.model.Setor;
import br.org.fgp.model.Usuario;
import br.org.fgp.view.CadastroCategoria;
import br.org.fgp.view.CadastroEntradaProduto;
import br.org.fgp.view.CadastroFornecedor;
import br.org.fgp.view.CadastroMarca;
import br.org.fgp.view.CadastroProduto;
import br.org.fgp.view.CadastroSetor;
import br.org.fgp.view.CadastroUsuario;

public class TelasUtils {

	private static Usuario usuarioLogado;
	
	public static Class getView(Class clazz){
		Map<Class, Class> mapa = new HashMap<Class, Class>();
		
		mapa.put(Categoria.class, CadastroCategoria.class);
		mapa.put(EntradaProduto.class, CadastroEntradaProduto.class);
		mapa.put(Fornecedor.class, CadastroFornecedor.class);
		mapa.put(Marca.class, CadastroMarca.class);
		mapa.put(Produto.class, CadastroProduto.class);
		mapa.put(Setor.class, CadastroSetor.class);
		mapa.put(Usuario.class, CadastroUsuario.class);
		
		return mapa.get(clazz);
	}

	public static Usuario getUsuarioLogado(){
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Usuario usuarioLogado) {
		TelasUtils.usuarioLogado = usuarioLogado;
	}

}
