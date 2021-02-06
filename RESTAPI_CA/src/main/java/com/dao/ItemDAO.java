package com.dao;

import java.util.List;

import com.db.HibernateTemplate;
import com.dto.ItemDetails;

public class ItemDAO{
	public List<ItemDetails> getAllItemDetails(){
		List<ItemDetails> itemDetails = (List)HibernateTemplate.getObjectListByQuery("From ItemDetails");
		return itemDetails;
	}
	public int additem(ItemDetails item){
		System.out.println(item);
		return HibernateTemplate.addObject(item);
	}
}