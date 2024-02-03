package com.cry.web_delivery.Repository;

import com.cry.web_delivery.Entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrtRepositoty extends JpaRepository<CartEntity, Integer> {



}
