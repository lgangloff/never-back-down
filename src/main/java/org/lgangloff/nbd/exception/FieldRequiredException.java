package org.lgangloff.nbd.exception;

public class FieldRequiredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String field;
	
	public FieldRequiredException(String field) {
		super("Le champs " + field + " est obligatoire");
		this.field = field;
	}

	public String getField() {
		return field;
	}
	
}
