package com.cry.web_delivery.Repository;

import com.cry.web_delivery.Entity.OrderItem;
import com.cry.web_delivery.Entity.keys.KeyOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemReppository extends JpaRepository<OrderItem, KeyOrderItem> {
}
