package com.zosh.service;

import com.zosh.domain.storeStatus;
import com.zosh.exceptions.UserException;
import com.zosh.model.Store;
import com.zosh.model.User;
import com.zosh.payload.dto.StoreDto;

import java.util.List;


public interface StoreService {

    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(long id) throws Exception;
    List<StoreDto> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDto updateStore(Long id, StoreDto storeDto) throws UserException;
    void  deleteStore(Long id) throws UserException;
    StoreDto getStoreByEmployee() throws UserException;

    StoreDto moderateStore(Long id, storeStatus status) throws Exception;

}
