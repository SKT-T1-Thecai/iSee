package com.cv.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.entity.News;
import com.cv.service.NewsService;
import com.cv.dao.NewsRepository;
@Service
public class NewsServiceimpl implements NewsService{

	@Autowired
    private NewsRepository newsRepository;
	@Override
	public ArrayList<News> getNewsBytype(String Type) {
		
		return newsRepository.findBytype(Type);
	}

	@Override
	public ArrayList<News> getNewsByarea(String Area) {
		
		return newsRepository.findByarea(Area);
	}

	@Override
	public ArrayList<News> sortbytime(ArrayList<News> newlist) {
		Collections.sort(newlist,comparator);
		return newlist;
	}

	Comparator<News> comparator = new Comparator<News>(){
        public int compare(News s1, News s2) {
            return s2.getTime().compareTo(s1.getTime());
        }
    };
	
}
