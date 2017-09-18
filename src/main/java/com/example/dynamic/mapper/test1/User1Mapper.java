package com.example.dynamic.mapper.test1;

import java.util.List;

import com.example.dynamic.entity.UserEntity;

public interface User1Mapper {
	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);
}
