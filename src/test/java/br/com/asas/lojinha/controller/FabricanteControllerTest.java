package br.com.asas.lojinha.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.asas.lojinha.entity.Fabricante;
import br.com.asas.lojinha.service.FabricanteService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FabricanteControllerTest {

	@Mock
	FabricanteService service;

	@Mock
	BindingResult mockBindingResult;

	@Mock
	RedirectAttributes mockRedirectAttributes;

	@Autowired
	protected MockMvc mockMvc;
	
	@Resource
    private Validator validator;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deveApresentarTodosFabricantes() throws Exception {
		List<Fabricante> retorno = geraFabricantes(geraFabricante(1L, "nome"));

		when(service.buscarTodos()).thenReturn(retorno);

		mockMvc.perform(get("/fabricante/lista")).andExpect(status().isOk())
				.andExpect(view().name("fabricante/fabricantes"))
				.andExpect(forwardedUrl("/WEB-INF/jsp/fabricante/fabricantes.jsp"));

		List<Fabricante> resposta = service.buscarTodos();
		verify(service, atMost(1)).buscarTodos();
		assertEquals(retorno, resposta);
	}

	@Test
	public void deveAcessarPaginaSalvarNovoFabricante() throws Exception {
		mockMvc.perform(get("/fabricante/adicionar")).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("fabricante/adicionar"));
	}

	@Test
	public void deveSalvarNovoFabricante() throws Exception {
		Fabricante salvo = new Fabricante();
		salvo.setCodigo(null);
		salvo.setNome("Fábrica");
		
		when(service.salvar(isA(Fabricante.class))).thenReturn(salvo);

		mockMvc.perform(post("/fabricante/adicionar")
				.param("nome", "Fábrica")
				.sessionAttr("fabricante", new Fabricante()))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/fabricante/lista"));
		
		Fabricante servico = service.salvar(salvo);
		
		verify(service, times(1)).salvar(servico);
		verifyNoMoreInteractions(service);
		
		assertThat(servico.getNome(), is("Fábrica"));
		assertNull(servico.getCodigo());
	}
	
	@Test
	public void naoDeveriaSalvarFabricanteFaltaNome() throws Exception {
		Fabricante salvo = geraFabricante(null, "");
		
		when(service.salvar(isA(Fabricante.class))).thenReturn(salvo);

		mockMvc.perform(post("/fabricante/adicionar")
				.param("nome", "")
				.sessionAttr("fabricante", new Fabricante()))
				.andExpect(status().isOk());
		
		Fabricante servico = service.salvar(salvo);
		
		verify(service, times(1)).salvar(servico);
		verifyNoMoreInteractions(service);
		
		assertThat(servico.getNome(), is(""));
		assertNull(servico.getCodigo());
	}
	
	@Test
	public void deveAcessarPaginaAtualizarFabricante() throws Exception {
		mockMvc.perform(get("/fabricante/atualizar/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("fabricante/atualizar"));
	}

	@Test
	public void deveAtualizarFabricante() throws Exception {
		Fabricante salvo = geraFabricante(1L, "Fábrica");
		
		when(service.atualizar(isA(Fabricante.class))).thenReturn(salvo);

		mockMvc.perform(post("/fabricante/atualizar/1")
				.param("codigo", "2")
				.param("nome", "Fábrica")
				.sessionAttr("fabricante", new Fabricante()))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/fabricante/lista"));
		
		Fabricante servico = service.atualizar(salvo);
		
		verify(service, times(1)).atualizar(servico);
		verifyNoMoreInteractions(service);
		
		assertThat(servico.getNome(), is("Fábrica"));
		assertThat(servico.getCodigo(), is(1L));
	}
	
	@Test
	public void deveriaAcessarPaginaExclusaoFabricante() throws Exception {
		mockMvc.perform(get("/fabricante/excluir/1"))
		.andDo(print())
		.andExpect(status()
				.isOk())
		.andExpect(view()
				.name("/fabricante/excluir"));
	}
	
	@Test
	public void deveriaExcluirFabricante() throws Exception {
		Fabricante salvo = geraFabricante(1L, "Fábrica");
		
		when(service.deletar(isA(Fabricante.class))).thenReturn(salvo);

		mockMvc.perform(delete("/fabricante/excluir/1")
				.param("codigo", "1")
				.param("nome", "Fábrica")
				.sessionAttr("fabricante", new Fabricante()))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/fabricante/lista"));
		
		Fabricante servico = service.deletar(salvo);
		
		verify(service, times(1)).deletar(servico);
		verifyNoMoreInteractions(service);
		
		assertThat(servico.getNome(), is("Fábrica"));
		assertThat(servico.getCodigo(), is(1L));
	}

	private Fabricante geraFabricante(Long codigo, String nome) {
		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(codigo);
		fabricante.setNome(nome);
		return fabricante;
	}

	private List<Fabricante> geraFabricantes(Fabricante fabricante) {
		List<Fabricante> fabricantes = new ArrayList<>();
		fabricantes.add(fabricante);

		return fabricantes;
	}	
}
