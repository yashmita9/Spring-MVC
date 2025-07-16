package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.rays.dao.customerDAO;
import com.rays.dto.CustomerDTO;

@Service
@Transactional
public class CustomerService {

	@Autowired
	public customerDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(CustomerDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(CustomerDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		try {
			CustomerDTO dto = findById(id);
			dao.delete(dto);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Transactional(readOnly = true)
	public CustomerDTO findById(long pk) {
		CustomerDTO dto = dao.findByPk(pk);
		return dto;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public long save(CustomerDTO dto) {
		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto);
		} else {
			id = add(dto);
		}
		return id;
	}

	@Transactional(readOnly = true)
	public List search(CustomerDTO dto, int pageNo, int pageSize) {
		List list = dao.search(dto, pageNo, pageSize);
		return list;
	}


}
