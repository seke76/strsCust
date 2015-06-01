package earkiv;

import hello.Todo;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import earkiv.Search;
import earkiv.DataManager;

@ManagedBean
@SessionScoped
public class Controller {
	
	//Db
	DataManager dataManager = new DataManager();
	
	//private List<Document> documents;
	private ArrayList<Document> documents;
	private Search search;
	
	public Controller() {
		System.out.println("Controller constructur");
		search = new Search("", "");
		//documents = new ArrayList<Document>();
	}
	
		
	public Search search() {
		
		documents = dataManager.searchDocuments(dataManager, search);
		return null;
	}
	

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}
	
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}
}
