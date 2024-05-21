package com.exalt.producerconsumer;

import com.exalt.producerconsumer.model.Pass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.*;
import java.util.Date;

@SpringBootApplication
public class ProducerConsumerApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(ProducerConsumerApplication.class, args);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		long now = new Date().getTime();
		Pass pass = new Pass(1,"Rama","Pannir",true,dateFormat.parse("29/10/1982 00:00:00"), dateFormat.format(now), dateFormat.format(now));
		System.out.println(pass);
	}

}
