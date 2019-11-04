package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Table;

import br.com.projecthibernate.HibernateUtil;

public class GenericDao<E> {


	private EntityManager entityManager = HibernateUtil.getEntityManager();

	public E update(E entity) {
		EntityTransaction transation = entityManager.getTransaction();
		transation.begin();
		entity = entityManager.merge(entity);
		transation.commit();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public E finder(E entity, Long id) {

		return entity = (E) entityManager.find(entity.getClass(), id);

	}
 
	public void delete(E entity, Long id) { 

		EntityTransaction transation = entityManager.getTransaction();
		transation.begin();
		String sql = "delete from " + entity.getClass().getAnnotation(Table.class).name() + " where id =" + id;
		entityManager.createNativeQuery(sql).executeUpdate();
		transation.commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<E> listAll(E entity) {
		
		String tableName = entity.getClass().getAnnotation(Table.class).name(); // @Table (name="usuarios")
		EntityTransaction transation = entityManager.getTransaction();
		transation.begin();
		List<E> list = entityManager.createQuery("from " + tableName).getResultList();
		transation.commit();
		
		return list;

	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
