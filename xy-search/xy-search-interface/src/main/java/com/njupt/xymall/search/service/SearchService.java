package com.njupt.xymall.search.service;


import com.njupt.xymall.common.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String keyword, int page, int rows) throws Exception;
}
