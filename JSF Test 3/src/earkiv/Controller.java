package earkiv;

import java.sql.SQLException;
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
		search = new Search("", "", null);
		//documents = new ArrayList<Document>();
	}
	
		
	public Search search() {
		System.out.println("Search method");
		message = "";
		
		try {
			documents = dataManager.searchDocuments(dataManager, search);
			System.out.println("list is empty: "+documents.isEmpty());
			System.out.println(String.valueOf(documents.size()));
			
			if(documents.isEmpty()) {
				message = "Inga dokument känner sig träffade";
			}
			else {
				message = documents.size()+" dokument funna";
			}
			
		} catch (Exception e) {
			message = "Ingen databas koppling";
			//e.printStackTrace();
		}

		return null;
	}
	
	public String reset () {
		System.out.println("Reset");
		return "reset";
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
}
