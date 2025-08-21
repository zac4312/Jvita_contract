package com.jvita.truck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jvita.truck.model.CMSModel;

@Repository
public interface CMSRepository extends JpaRepository<CMSModel, Long> {

}