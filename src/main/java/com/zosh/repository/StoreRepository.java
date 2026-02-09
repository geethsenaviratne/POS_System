package com.zosh.repository;

import com.zosh.model.Store;
import com.zosh.payload.dto.StoreDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreAdminId(long adminId);
}
