package com.prss6.sisgourmet.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.prss6.sisgourmet.model.Desk;
import com.prss6.sisgourmet.repository.DeskRepository;

@Service
public class DeskService {

	@Autowired
	private DeskRepository deskRepository;
	
	public Desk update(Long id, Desk Desk) {
		Desk DeskSaved = findDeskById(id);
		BeanUtils.copyProperties(Desk, DeskSaved, "id");
		return deskRepository.save(DeskSaved);
	}

	public Desk findDeskById(Long id) {
		Desk DeskSaved = deskRepository.findById(id)
				.orElseThrow(
				(() -> new EmptyResultDataAccessException(1)));
		return DeskSaved;
	}
	
}
