package com.hardik.flash.storage.bean;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.hardik.flash.storage.configuration.AmazonS3Configuration;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@EnableConfigurationProperties(AmazonS3Configuration.class)
public class AmazonS3Bean {

	private final AmazonS3Configuration amazonS3Configuration;

	@Bean
	public AmazonS3 amazonS3() {
		var credentials = amazonS3Configuration.getStorage();
		AWSCredentials awsCredentials = new BasicAWSCredentials(credentials.getAccessKey(), credentials.getSecretKey());
		return AmazonS3ClientBuilder.standard().withRegion(credentials.getRegion())
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
	}

}