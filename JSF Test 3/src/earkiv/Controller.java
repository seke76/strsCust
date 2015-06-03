package earkiv;

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
	private String message="default";
	
	public Controller() {
		System.out.println("Controller constructur");
		search = new Search("", "");
		//documents = new ArrayList<Document>();
	}
	
		
	public Search search() {
		System.out.println("Search method");
		documents = dataManager.searchDocuments(dataManager, search);
		System.out.println("list is empty: "+documents.isEmpty());
		System.out.println(String.valueOf(documents.size()));
		if(documents.isEmpty()) {
			message = "Inga dokument känner sig träffade";
		}
		else {
			message="Ett eller flera dokument funna";
		}
		return null;
	}
	

	public Search getSearch() {
		System.out.println("getSearch method");
		return search;
	}

	public void setSearch(Search search) {
		System.out.println("setSearch method");
		this.search = search;
	}
	
	public List<Document> getDocuments() {
		System.out.println("getDocuments method");
		//System.out.println("list is empty: "+documents.isEmpty());
		//System.out.println(String.valueOf(documents.size()));
		
		return documents;
	}

	public void setDocuments(ArrayList<Document> documents) {
		System.out.println("setDocuments method");
		this.documents = documents;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String messge) {
		this.message= message;
	}
}
