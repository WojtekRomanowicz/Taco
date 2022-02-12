package com.example.taco.repository;

import com.example.taco.domain.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TacoRepository  extends PagingAndSortingRepository<Taco, Long> {


}
