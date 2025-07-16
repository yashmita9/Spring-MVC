package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.CustomerDTO;

@Repository
public class customerDAO {

	@PersistenceContext
	public EntityManager entityManager;
	
	public long add(CustomerDTO dto) {
		
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(CustomerDTO dto) {
		
		entityManager.merge(dto);
	}
	
	public void delete(CustomerDTO dto) {
		
		entityManager.remove(dto);
	}
	
	public CustomerDTO findByPk(long pk) {
		CustomerDTO dto = entityManager.find(CustomerDTO.class, pk);
		return dto;
	}
	
	public CustomerDTO findByUniqueKey(String attribute, String value) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<CustomerDTO> cq = builder.createQuery(CustomerDTO.class);

		Root<CustomerDTO> qRoot = cq.from(CustomerDTO.class);

		Predicate condition = builder.equal(qRoot.get(attribute), value);

		cq.where(condition);

		TypedQuery<CustomerDTO> tq = entityManager.createQuery(cq);

		List<CustomerDTO> list = tq.getResultList();

		CustomerDTO dto = null;

		if (list.size() > 0) {

			dto = list.get(0);

		}

		return dto;
	}
	
	public List search(CustomerDTO dto, int pageNo, int pageSize) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<CustomerDTO> cq = builder.createQuery(CustomerDTO.class);

		Root<CustomerDTO> qRoot = cq.from(CustomerDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (dto != null) {

			if (dto.getClientName() != null && dto.getClientName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("clientName"), dto.getClientName() + "%"));
			}
			if (dto.getLocation() != null && dto.getLocation().length() > 0) {
				predicateList.add(builder.like(qRoot.get("location"), dto.getLocation() + "%"));
			}
			
		}

		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<CustomerDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

		List<CustomerDTO> list = tq.getResultList();

		return list;
	}

}
