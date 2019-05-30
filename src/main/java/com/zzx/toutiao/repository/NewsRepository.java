package com.zzx.toutiao.repository;

import com.zzx.toutiao.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  NewsRepository extends JpaRepository<News,Integer> {

}
