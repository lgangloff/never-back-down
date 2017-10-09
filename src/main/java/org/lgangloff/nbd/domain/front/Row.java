package org.lgangloff.nbd.domain.front;

import java.util.ArrayList;
import java.util.List;

public class Row {
	public String css;
	public List<Col> cols =new ArrayList<>();

	public Row(String css) {
		this.css = css;
	}
	
	public void addCol(Col e) {
		this.cols.add(e);
	}
}