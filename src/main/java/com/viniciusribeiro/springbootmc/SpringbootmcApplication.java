package com.viniciusribeiro.springbootmc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.viniciusribeiro.springbootmc.domain.Categoria;
import com.viniciusribeiro.springbootmc.domain.Cidade;
import com.viniciusribeiro.springbootmc.domain.Cliente;
import com.viniciusribeiro.springbootmc.domain.Endereco;
import com.viniciusribeiro.springbootmc.domain.Estado;
import com.viniciusribeiro.springbootmc.domain.ItemPedido;
import com.viniciusribeiro.springbootmc.domain.Pagamento;
import com.viniciusribeiro.springbootmc.domain.PagamentoComCartao;
import com.viniciusribeiro.springbootmc.domain.Pedido;
import com.viniciusribeiro.springbootmc.domain.Produto;
import com.viniciusribeiro.springbootmc.domain.enums.EstadoPagamento;
import com.viniciusribeiro.springbootmc.domain.enums.TipoCliente;
import com.viniciusribeiro.springbootmc.repositories.CategoriaRepository;
import com.viniciusribeiro.springbootmc.repositories.CidadeRepository;
import com.viniciusribeiro.springbootmc.repositories.ClienteRepository;
import com.viniciusribeiro.springbootmc.repositories.EnderecoRepository;
import com.viniciusribeiro.springbootmc.repositories.EstadoRepository;
import com.viniciusribeiro.springbootmc.repositories.ItemPedidoRepository;
import com.viniciusribeiro.springbootmc.repositories.PagamentoRepository;
import com.viniciusribeiro.springbootmc.repositories.PedidoRepository;
import com.viniciusribeiro.springbootmc.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringbootmcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "monitor", 550.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
			
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null,"S??o Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"S??o Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "mariasilva@mai.com", "10245687255",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("255458889", "254466987"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "251","casa", "bela vista", "34155255", cli1, c1);
	    cli1.getEnderecos().addAll(Arrays.asList(e1));	
	    
	    clienteRepository.saveAll(Arrays.asList(cli1));
	    enderecoRepository.saveAll(Arrays.asList(e1));
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	    
	    Pedido ped1 = new Pedido(null, sdf.parse("05/09/2022 14:0"), cli1, e1);
	    
	    Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
	    ped1.setPagamento(pagto1);
	    
	    cli1.getPedidos().addAll(Arrays.asList(ped1));
	    
	    pedidoRepository.saveAll(Arrays.asList(ped1));
	    pagamentoRepository.saveAll(Arrays.asList(pagto1));
	    
	    ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
	    
	    ped1.getItens().addAll(Arrays.asList(ip1));
	    p1.getItens().addAll(Arrays.asList(ip1));
	    
	    itemPedidoRepository.saveAll(Arrays.asList(ip1));
	}

}
