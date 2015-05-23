package earkiv;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import earkiv.Search;

@ManagedBean
@SessionScoped
public class Controller {
	
	//private List<Document> documents;
	private Search search;
	
	public Controller() {
		System.out.println("Controller constructur ");
		search = new Search("", "");
		//documents = new ArrayList<Document>();
	}
	
		
	public Search search() {
		
		System.out.println("Search");
		System.out.println(search);
		
		return null;
	}
	

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}
}
