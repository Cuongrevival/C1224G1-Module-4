package com.codegym.repository;

import com.codegym.model.Province;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IProvinceRepo extends CrudRepository<Province, Long> {

}
