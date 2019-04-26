package com.springclouddataflow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

@EnableBinding(Processor.class)
@SpringBootApplication
public class ProcessorApplication {

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public Object transform(String path) throws IOException {

    	File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		StringBuffer sb = new StringBuffer();
		while ((st = br.readLine()) != null) {
			sb.append(st);
		}
        return sb.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProcessorApplication.class, args);
    }
}
