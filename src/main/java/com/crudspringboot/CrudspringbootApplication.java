package com.crudspringboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crudspringboot.domain.Categoria;
import com.crudspringboot.domain.Cidade;
import com.crudspringboot.domain.Cliente;
import com.crudspringboot.domain.Endereco;
import com.crudspringboot.domain.Estado;
import com.crudspringboot.domain.Produto;
import com.crudspringboot.domain.enums.TipoCliente;
import com.crudspringboot.repositories.CategoriaRepository;
import com.crudspringboot.repositories.CidadeRepository;
import com.crudspringboot.repositories.ClienteRepository;
import com.crudspringboot.repositories.EnderecoRepository;
import com.crudspringboot.repositories.EstadoRepository;
import com.crudspringboot.repositories.ProdutoRepository;

@SpringBootApplication
public class CrudspringbootApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudspringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "pendrive_1T", 200.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "MG");
		Estado est2 = new Estado(null, "SP");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12453-153", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("40028922", "456585258"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "apto 303", "jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua maria", "301", "apto 20002", "Dalle", "49922112", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}
}