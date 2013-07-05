package com.banclogix;
enum State {
	A(Constants.NOUSER), B(Constants.CONNECTERR), C(Constants.CONNECTERR) ;
	
	private String message ;
	 State(String message){
		this.message = message;
	}
	String getMessage(){
		return message;
	}
}