package com.example.demo.util.report.poiUtil;


public class ExcelMappingBean{
	
	private String excelTitle;
	private String property;
	private String type;
	private boolean required;
	private int minLength;
	private int maxLength;
	private String mapName;
	private String mapDefaultValue;
	private String annoName;
	private String className;
	private String methodName;
	
	public ExcelMappingBean() {
		super();
	}
	
	public String getExcelTitle() {
		return excelTitle;
	}
	public void setExcelTitle(String excelTitle) {
		this.excelTitle = excelTitle;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public int getMinLength() {
		return minLength;
	}
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}
	public int getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public String getMapDefaultValue() {
		return mapDefaultValue;
	}
	public void setMapDefaultValue(String mapDefaultValue) {
		this.mapDefaultValue = mapDefaultValue;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAnnoName() {
		return annoName;
	}
	public void setAnnoName(String annoName) {
		this.annoName = annoName;
	}
	
}
