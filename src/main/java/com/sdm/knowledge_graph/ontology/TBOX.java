package com.sdm.knowledge_graph.ontology;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;

import com.sdm.knowledge_graph.common.constants;
import com.sdm.knowledge_graph.common.utils;

public class TBOX {

    public static void createAndSaveTBOX() {

        // https://jena.apache.org/documentation/javadoc/jena/org/apache/jena/ontology/OntModel.html
        // https://jena.apache.org/documentation/javadoc/jena/org/apache/jena/ontology/OntModelSpec.html
        // https://jena.apache.org/documentation/javadoc/jena/org/apache/jena/rdf/model/ModelFactory.html#createOntologyModel(org.apache.jena.ontology.OntModelSpec)
        OntModel model = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM_RDFS_INF );

        //==================================
        // Ontology for Persons
        //==================================
        
        OntClass person = model.createClass( constants.BASE_URI.concat("Person") );
        OntClass author = model.createClass( constants.BASE_URI.concat("Author") );
        OntClass chair = model.createClass( constants.BASE_URI.concat("Chair") );
        OntClass editor = model.createClass( constants.BASE_URI.concat("Editor") );
        OntClass reviewer = model.createClass( constants.BASE_URI.concat("Reviewer") );
        
        person.addSubClass( author );
        person.addSubClass( chair );
        person.addSubClass( editor );
        person.addSubClass( reviewer );
        
        //==================================
        // Ontology for Papers
        //==================================

        OntClass paper = model.createClass( constants.BASE_URI.concat("Paper") );
        OntClass fullPaper = model.createClass( constants.BASE_URI.concat("Full_Paper") );
        OntClass shortPaper = model.createClass( constants.BASE_URI.concat("Short_Paper") );
        OntClass demoPaper = model.createClass( constants.BASE_URI.concat("Demo_Paper") );
        OntClass posterPaper = model.createClass( constants.BASE_URI.concat("Poster_Paper") );
        
        paper.addSubClass( fullPaper );
        paper.addSubClass( shortPaper );
        paper.addSubClass( demoPaper );
        paper.addSubClass( posterPaper ); // Only for conference papers
        
        //==================================
        // Ontology for Paper's Year
        //==================================
        
        OntClass year = model.createClass( constants.BASE_URI.concat("Year") );

        //==================================
        // Ontology for Venue
        //==================================
        
        OntClass venue = model.createClass( constants.BASE_URI.concat("Venue") );
        OntClass conference = model.createClass( constants.BASE_URI.concat("Conference") );
        OntClass journal = model.createClass( constants.BASE_URI.concat("Journal") );
        
        venue.addSubClass( conference );
        venue.addSubClass( journal );
        
        //==================================
        // Ontology for Conference Types
        //==================================

        OntClass workshop = model.createClass( constants.BASE_URI.concat("Workshop") );
        OntClass symposium = model.createClass( constants.BASE_URI.concat("Symposium") );
        OntClass expertGroup = model.createClass( constants.BASE_URI.concat("Expert_Group") );
        OntClass regularConference = model.createClass( constants.BASE_URI.concat("Regular_Conference") );
        
        conference.addSubClass( workshop );
        conference.addSubClass( symposium );
        conference.addSubClass( expertGroup );
        conference.addSubClass( regularConference );
        
        //==================================
        // Ontology for Decision
        //==================================

        OntClass decision = model.createClass( constants.BASE_URI.concat("Decision") );
        OntClass acceptOrRejected = model.createClass( constants.BASE_URI.concat("Accepted_Or_Rejected") );
        OntClass reviewText = model.createClass( constants.BASE_URI.concat("Review_Text") );

        decision.addSubClass( acceptOrRejected );
        decision.addSubClass( reviewText );

        //==================================
        // Ontology for Areas
        //==================================

        // We will programmically create the sub-classes for areas during the creation of ABOX
        // So, we can have all the areas that are mentioned in our publication's data

        OntClass areas = model.createClass( constants.BASE_URI.concat("Areas") );

        //==================================
        // Ontology for Publications
        //==================================

        OntClass publications = model.createClass( constants.BASE_URI.concat("Publications") );

        //==================================
        // Ontology Properties
        //==================================

        OntProperty submit = model.createOntProperty( constants.BASE_URI.concat("submit") );
        submit.addDomain( author );
        submit.addRange( paper );
        submit.addLabel("Author submits paper", "en");

        OntProperty submittedToVenue = model.createOntProperty( constants.BASE_URI.concat("submitted_to") );
        submittedToVenue.addDomain( paper );
        submittedToVenue.addRange( venue );
        submittedToVenue.addLabel("Author submits paper to a venue", "en");
        
        OntProperty handlesConferences = model.createOntProperty( constants.BASE_URI.concat("handles_conference") );
        handlesConferences.addDomain( chair );
        handlesConferences.addRange( conference );
        handlesConferences.addLabel("Chair(s) handles a conference", "en");

        OntProperty handlesJournals = model.createOntProperty( constants.BASE_URI.concat("handles_journal") );
        handlesJournals.addDomain( editor );
        handlesJournals.addRange( journal );
        handlesJournals.addLabel("Editor(s) handles a journal", "en");

        OntProperty assignedByChairs = model.createOntProperty( constants.BASE_URI.concat("assigned_by_chairs") );
        assignedByChairs.addDomain( chair );
        assignedByChairs.addRange( reviewer );
        assignedByChairs.addLabel("Chairs assign reviewers", "en");

        OntProperty assignedByEditors = model.createOntProperty( constants.BASE_URI.concat("assigned_by_editors") );
        assignedByEditors.addDomain( editor );
        assignedByEditors.addRange( reviewer );
        assignedByEditors.addLabel("Editors assign reviewers", "en");

        OntProperty assignedTo = model.createOntProperty( constants.BASE_URI.concat("assigned_to") );
        assignedTo.addDomain( reviewer );
        assignedTo.addRange( paper );
        assignedTo.addLabel("Reviewers are assigned to a paper", "en");

        OntProperty takesDecision = model.createOntProperty( constants.BASE_URI.concat("takes_decision"));
        takesDecision.addDomain( reviewer );
        takesDecision.addRange( decision );
        takesDecision.addLabel("Reviewer takes a decision","en");

        OntProperty reviewIsGiven = model.createOntProperty( constants.BASE_URI.concat("is_paper_accepted"));
        reviewIsGiven.addDomain( decision );
        reviewIsGiven.addRange( acceptOrRejected );
        reviewIsGiven.addLabel( "Paper is accepted or rejected ?","en");

        OntProperty hasReviewComments = model.createOntProperty( constants.BASE_URI.concat("has_review_comments"));
        hasReviewComments.addDomain( decision );
        hasReviewComments.addRange( reviewText );
        hasReviewComments.addLabel( "Comments added by the Reviewer","en");

        OntProperty hasArea = model.createOntProperty( constants.BASE_URI.concat("has_area"));
        hasArea.addDomain( venue );
        hasArea.addRange( areas );
        hasArea.addLabel( "Venue has area","en");

        OntProperty paperHasPublication = model.createOntProperty( constants.BASE_URI.concat("has_publication"));
        paperHasPublication.addDomain( paper );
        paperHasPublication.addRange( publications );
        paperHasPublication.addLabel( "Paper is published","en");

        OntProperty paperYear = model.createOntProperty( constants.BASE_URI.concat("published_year"));
        paperYear.addDomain( paper );
        paperYear.addRange( year );
        paperYear.addLabel( "Paper published in year ","en");

        try {
            
            utils.line_separator();
            utils.log("Saving ontology model to '" + constants.TBOX_MODEL_PATH + "'");
            
            FileOutputStream writerStream = new FileOutputStream( constants.TBOX_MODEL_PATH );
            model.write(writerStream, "RDF/XML");
            writerStream.close();
            
            utils.log("Ontology model saved!");
            utils.line_separator();

        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
}
