package br.com.rocha.almoxarifado.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class QueryUtil {

  private static final String PERSISTENCE = "persistence";
  private static final EntityManagerFactory entityManagerFactory;
  private static EntityManager entityManager;

  static {
    entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE);
  }

  private static EntityManager getEntityManager() {
    if (entityManager == null) {
      entityManager = entityManagerFactory.createEntityManager();
    }
    return entityManager;
  }
  
  public static <T> List<T> selectListByNamedQuery(String queryName){
    Query query = getEntityManager().createNamedQuery(queryName);
    return (List<T>)query.getResultList();
  }
  
  public static <T> List<T> selectListByNamedQuery(String queryName, Map<String, Object> map) {
    Query query = getEntityManager().createNamedQuery(queryName);
    Set<Entry<String, Object>> set = map.entrySet();
    Iterator<Entry<String, Object>> ite = set.iterator();
    while (ite.hasNext()) {
      Entry<String, Object> entry = ite.next();
      query.setParameter(entry.getKey(), entry.getValue());
    }
    return (List<T>)query.getResultList();
  }
  
  public static <T> T selectSingleByNamedQuery(String queryName){
    Query query = getEntityManager().createNamedQuery(queryName);
    return (T)query.getSingleResult();
  }

  public static <T> T selectSingleByNamedQuery(String queryName, Map<String, Object> map) {
    Query query = getEntityManager().createNamedQuery(queryName);
    Set<Entry<String, Object>> set = map.entrySet();
    Iterator<Entry<String, Object>> ite = set.iterator();
    while (ite.hasNext()) {
      Entry<String, Object> entry = ite.next();
      query.setParameter(entry.getKey(), entry.getValue());
    }
    return (T)query.getSingleResult();
  }
  
  public static void removeEntity(Object entity){
    EntityTransaction et = entityManager.getTransaction();
    et.begin();
    entityManager.remove(entity);
    et.commit();
  }
  
  public static void saveEntity(Object entity){
    EntityTransaction et = entityManager.getTransaction();
    et.begin();
    entityManager.persist(entity);
    et.commit();
  }

  public static void updateEntity(Object entity){
    EntityTransaction et = entityManager.getTransaction();
    et.begin();
    entityManager.merge(entity);
    et.commit();
  }
}
