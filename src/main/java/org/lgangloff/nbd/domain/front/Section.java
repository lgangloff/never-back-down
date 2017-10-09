package org.lgangloff.nbd.domain.front;

import java.util.ArrayList;
import java.util.List;

public class Section {

	public Long id;
	public String name;
	public String title;
	
	public List<Row> rows = new ArrayList<>();

	public Section( String name, String title) {
		super();
		this.name = name;
		this.title = title;
	}

	public void addRow(Row e) {
		this.rows.add(e);
	}

}
