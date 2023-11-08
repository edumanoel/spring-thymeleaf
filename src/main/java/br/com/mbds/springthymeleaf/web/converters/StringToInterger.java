package br.com.mbds.springthymeleaf.web.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToInterger implements Converter<String, Integer> {

	@Override
	public Integer convert(String text) {
		text = text.trim();
		if (text.matches("[0-9]+")) {
			return Integer.valueOf(text);
		}
		return null;
	}

}
