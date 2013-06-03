package com.learning.jsr.bean.validation;

public interface Animal {
	@NotEmpty
	String getName();

	@NotEmpty
	String getOwnerName();
}
