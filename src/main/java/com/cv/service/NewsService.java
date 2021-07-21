package com.cv.service;

import com.cv.entity.News;
import java.util.ArrayList;
public interface NewsService {
	
	ArrayList<News> getNewsBytype(String Type);
	ArrayList<News> getNewsByarea(String Area);
	ArrayList<News> sortbytime(ArrayList<News> newlist);
}
