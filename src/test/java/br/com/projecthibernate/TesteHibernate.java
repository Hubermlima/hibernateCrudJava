package br.com.projecthibernate;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import dao.GenericDao;
import model.Telefone;
import model.UsuarioPessoa;

public class TesteHibernate {
      
	@Test
	public void addTest() {

		GenericDao<UsuarioPessoa> genericDao = new GenericDao<UsuarioPessoa>();
		
		UsuarioPessoa usuario = new UsuarioPessoa();
		usuario.setEmail("huber.produtos@gmail.com");
		usuario.setLogin("huberaba");
		usuario.setNome("Ana");
		usuario.setSenha("1atl389guh");
		usuario.setIdade(45);
		usuario.setSobrenome("Cristina");
				
		usuario = genericDao.update(usuario);
		
		

	}

	@Test
	public void addFone() {
		GenericDao<Object> genericDao = new GenericDao<>();

		UsuarioPessoa usuario = new UsuarioPessoa();
		usuario = (UsuarioPessoa) genericDao.finder(new UsuarioPessoa(), 7L); // Pesquiso
		
		Telefone telefone = new Telefone("Trabalho", "092", "98402-5926", "120", usuario);
        telefone.setUsuarioPessoa(usuario);
        telefone = (Telefone) genericDao.update(telefone);
        
	}
	@Test
	public void updateTest() {

		GenericDao<UsuarioPessoa> genericDao = new GenericDao<UsuarioPessoa>();
		UsuarioPessoa usuario = new UsuarioPessoa();
		usuario = genericDao.finder(new UsuarioPessoa(), 5L); // Pesquiso
		usuario.setEmail("antonio_gostoso@hmail"); // Altero
		usuario = genericDao.update(usuario); // Atualizo
		
	}
	
	@Test
	public void deleteTest() {

		GenericDao<UsuarioPessoa> genericDao = new GenericDao<UsuarioPessoa>();
		UsuarioPessoa usuario = new UsuarioPessoa();
		usuario = genericDao.finder(new UsuarioPessoa(), 4L); // Pesquiso
		genericDao.delete(usuario, usuario.getId());
		
	}
	 
	@Test
	public void listAllTest() { 

		GenericDao<UsuarioPessoa> genericDao = new GenericDao<UsuarioPessoa>();
		List<UsuarioPessoa> list = genericDao.listAll(new UsuarioPessoa());

		list.forEach(user-> user.getTelefones().forEach(telefone -> System.out.println(telefone.toString())));
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void listMax() {
	    EntityManager entityManager = HibernateUtil.getEntityManager();
		List<UsuarioPessoa> list = entityManager.createQuery(" from usuarios").setMaxResults(2).getResultList();
	    list.forEach(user -> System.out.println(user));
	    
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void listByParameter() {
	    EntityManager entityManager = HibernateUtil.getEntityManager();
		List<UsuarioPessoa> list = entityManager.createQuery(" from usuarios where nome = :nome or sobrenome = :sobrenome")
				.setParameter("nome", "huber")
				.setParameter("sobrenome", "martins")
				.getResultList();
		
	    list.forEach(user -> System.out.println(user));
	    
	}
	
	@Test
	public void totalAge() {
	    EntityManager entityManager = HibernateUtil.getEntityManager();
	    Long sumAge = (Long) entityManager.createQuery("select sum(idade) from usuarios").getSingleResult();
	    System.out.println("Sum of all ages: " + sumAge);
		
	    
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void namedQueriesTest() {
	    List<UsuarioPessoa> list = HibernateUtil.getEntityManager().createNamedQuery("UsuarioPessoa.listAll").getResultList();
        list.forEach(user -> System.out.println(user));
        
	    
	}
}
