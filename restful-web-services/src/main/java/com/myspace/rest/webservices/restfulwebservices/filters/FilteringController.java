package com.myspace.rest.webservices.restfulwebservices.filters;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	 //This is to filter the values dynamically using filterOutAllExcept
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1","value2","value3");
		MappingJacksonValue mapping = filtering(someBean);
		return  mapping;
	}
	//This is used to filtering the list of  values dynamically using filterOutAllExcept
	@GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBeans(){
		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"), 
				new SomeBean("value12", "value23", "value34"));
		MappingJacksonValue mapping = filterList(list);
		return mapping;
	}
    //This is to filter the values dynamically using filterOutAllExcept
	private MappingJacksonValue filterList(List<SomeBean> list) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
				 mapping.setFilters(filters);
		return mapping;
	}
	
	private MappingJacksonValue filtering(SomeBean someBean) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
				mapping.setFilters(filters);
		return mapping;
	}
}
