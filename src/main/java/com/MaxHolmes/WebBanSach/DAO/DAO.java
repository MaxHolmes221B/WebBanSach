package com.MaxHolmes.WebBanSach.DAO;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
	
    List<T> findAll();
    
    T findById(int id);
     
    int save(T t, int id);
    
    int update(T t, int id);
    
    int deleteById(int id);
}