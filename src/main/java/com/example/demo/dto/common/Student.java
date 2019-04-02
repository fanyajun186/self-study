package com.example.demo.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student extends People{

	private String grade;
	
	private String school;
	
	private Long deptCode;
	
}
