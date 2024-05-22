package com.exalt.producerconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication
public class ProducerConsumerApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(ProducerConsumerApplication.class, args);
/*		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		long now = new Date().getTime();
		Pass pass = new Pass(1L,"Rama","Pannir",true,dateFormat.parse("29/10/1982 00:00:00"), dateFormat.format(now), dateFormat.format(now));
		System.out.println(pass);*/
	}

}
