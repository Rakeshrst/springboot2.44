package com.learnspring.SpringBoot24.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilterController {

/**
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("Home","Address1","Chennai");
	}
	**/
	
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean=new SomeBean("Home","Address1","Chennai");
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
				.filterOutAllExcept("homename","address1");
		FilterProvider filters=new SimpleFilterProvider()
							.addFilter("someBeanFilter", filter);
		MappingJacksonValue mapping=new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
	return mapping;
	}
	
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		List<SomeBean> somebeanList=Arrays.asList(new SomeBean("Home","Address1","Chennai"),
				new SomeBean("MyHome","MyAddress","MyCity"));
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
				.filterOutAllExcept("address1","city");
		FilterProvider filters=new SimpleFilterProvider()
							.addFilter("someBeanFilter", filter);
		MappingJacksonValue mapping=new MappingJacksonValue(somebeanList);
		mapping.setFilters(filters);
		return mapping;

	}
}
