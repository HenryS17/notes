package com.henry.notes;

import java.util.List;

import com.henry.notes.controller.NotesController;
import com.henry.notes.model.Note;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	NotesController notesController = new NotesController();
//    	Long l = new Long(1);
//    	Note note = notesController.get(l);
//        assertTrue( note.getId() == 1 && note.getBody().equals("some thing1") );
//        
//        List<Note> notes = notesController.get("thing1");
//        assertTrue(notes.size() == 1);
        
//        Note newNote = notesController.create("{\"body\":\"put milk\"}");
//        
//        assertTrue(newNote.getId() == 1 && newNote.getBody().equals("put milk"));
    }
}
