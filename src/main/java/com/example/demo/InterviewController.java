package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


@Configuration
class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for(HttpMessageConverter<?> converter: converters) {
            if(converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper mapper = ((MappingJackson2HttpMessageConverter)converter).getObjectMapper();
                mapper.setFilterProvider(new SimpleFilterProvider().addFilter("InterviewModelFilter", SimpleBeanPropertyFilter.serializeAll()).addFilter("InterviewFilter", SimpleBeanPropertyFilter.serializeAll()));
            }
        }
    }
}


@RestController
public class InterviewController {
	
	static List<Interview> ilist= new ArrayList<Interview>();
	
	@RequestMapping(value = "/interview", produces = "application/json")
	public ResponseEntity<String> first() throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("title", "status");
		FilterProvider filters = new SimpleFilterProvider().addFilter("InterviewModelFilter", filter);
		mapper.setFilterProvider(filters);
		
		Interview i = ilist.get(0);
		return ResponseEntity.ok(mapper.writer().writeValueAsString(i));
	} 
	
	@RequestMapping("/interviews")
	public ResponseEntity<List<Interview>> all(){
		return ResponseEntity.ok(ilist);
	}
	
	@GetMapping("/interview/{id}")
	public ResponseEntity<Interview> read(@PathVariable("id") Integer id) throws JsonProcessingException {
	
		Interview foundInterview = null;
		if(id >= 0 && id < ilist.size()) {
			foundInterview = ilist.get(id);
			
			return ResponseEntity.ok(foundInterview);
		}else{
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(value = "/interview", produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<?> createInterview(@Valid @RequestBody Interview interview) throws JsonProcessingException {
		ilist.add(interview);
		return ResponseEntity.ok().build();
	}	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    
	    return errors;
	}
}


