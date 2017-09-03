package com.henry.notes.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.el.parser.ParseException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.TextField;
//import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.lucene.index.*;
import org.apache.lucene.index.memory.*;
import org.apache.lucene.queryparser.classic.QueryParser;


public class Notes {
	List<Note> notes = new ArrayList<Note>();
	private long nextId = 1;
	private Directory index = new RAMDirectory();
	IndexWriter indexWriter;
	
	public Notes() {
	}
	
	public List<Note> getNotes() {
		return notes;
	}
	public Note create(String body) {
		long id = nextId++;
		
		Note note = new Note(id, body); //jsonNode.get("body").toString());
		notes.add(note);
		addDoc(id, body);
		return note;
	}
	
	public Note get(long id) {
		for (Note note : notes) {
			if (note.getId() == id) {
				return note;
			}
		}
		return new Note();
	}

	public List<Note> query(String s) {
		List<Note> notes = new ArrayList<>();
		System.out.println(s);
		
		try {
			Query q = new QueryParser("body", new StandardAnalyzer()).parse(s);
			
			 int hitsPerPage = 20;
		        IndexReader reader = DirectoryReader.open(index);
		        IndexSearcher searcher = new IndexSearcher(reader);
		        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage); //, hits);
		        searcher.search(q, collector);
		        ScoreDoc[] hits = collector.topDocs().scoreDocs;	
		        
		        for (int i = 0; i < hits.length; ++i) {
		            int docId = hits[i].doc;
		            Document d = searcher.doc(docId);
		            notes.add(new Note(Long.parseLong(d.get("id"), 10), d.get("body")));
		        }
		}
		catch (org.apache.lucene.queryparser.classic.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
        
        return notes;
	}
	
	private void addDoc(long id, String body ) {
		try {
	        Document doc = new Document();
	        doc.add(new TextField("id", String.valueOf(id), Field.Store.YES));
	        doc.add(new TextField("body", body, Field.Store.YES));
	        IndexWriterConfig config = new IndexWriterConfig();
			indexWriter = new IndexWriter(index, config);
	        indexWriter.addDocument(doc);		
	        indexWriter.close();
		}
		catch (IOException e) {
			
		}
    }
}
