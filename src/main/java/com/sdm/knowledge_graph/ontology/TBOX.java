package com.sdm.knowledge_graph.ontology;

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
        // Ontology for Venue
        //==================================
        
        OntClass venue = model.createClass( constants.BASE_URI.concat("Venue") );
        OntClass conference = model.createClass( constants.BASE_URI.concat("Conference") );
        OntClass journal = model.createClass( constants.BASE_URI.concat("Journal") );
        
        venue.addSubClass( conference );
        venue.addSubClass( journal );

        //==================================
        // Ontology for Decision
        //==================================

        OntClass decision = model.createClass( constants.BASE_URI.concat("Decision") );
        OntClass acceptOrRejected = model.createClass( constants.BASE_URI.concat("Accepted_Or_Rejected") );
        OntClass reveiwtext = model.createClass( constants.BASE_URI.concat("Review_Text") );

        decision.addSubClass( acceptOrRejected );
        decision.addSubClass( reveiwtext );

        //==================================
        // Ontology for Areas
        //==================================

        OntClass areas = model.createClass( constants.BASE_URI.concat("Areas") );

        OntClass artificialIntelligence = model.createClass( constants.BASE_URI.concat("Artificial_Intelligence") );
        OntClass machineLearning = model.createClass( constants.BASE_URI.concat("Machine_Learning") );
        OntClass naturalLanguageProcessing = model.createClass( constants.BASE_URI.concat("Natural_Language_Processing") );
        OntClass database = model.createClass( constants.BASE_URI.concat("Database") );
        
        areas.addSubClass( artificialIntelligence );
        areas.addSubClass( machineLearning );
        areas.addSubClass( naturalLanguageProcessing );
        areas.addSubClass( database );

        //==================================
        // Ontology for Publications
        //==================================

        OntClass publications = model.createClass( constants.BASE_URI.concat("Publications") );
        OntClass conferenceProceedings = model.createClass( constants.BASE_URI.concat("Conference_Proceedings") );
        OntClass journalVolume = model.createClass( constants.BASE_URI.concat("Journal_Volume") );

        publications.addSubClass( conferenceProceedings );
        publications.addSubClass( journalVolume );

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

        OntProperty reviewIsGiven = model.createOntProperty( constants.BASE_URI.concat("review_is_given"));
        reviewIsGiven.addDomain( decision );
        reviewIsGiven.addRange( acceptOrRejected );
        reviewIsGiven.addLabel( "Reviewer is taking a decision","en");

        OntProperty hasReviweComments = model.createOntProperty( constants.BASE_URI.concat("has_review_comments"));
        hasReviweComments.addDomain( decision );
        hasReviweComments.addRange( reveiwtext );
        hasReviweComments.addLabel( "Review comments are added","en");

        OntProperty conferenceHasAreas = model.createOntProperty( constants.BASE_URI.concat("conference_has_areas"));
        conferenceHasAreas.addDomain( conference );
        conferenceHasAreas.addRange( areas );
        conferenceHasAreas.addLabel( "Conference has areas","en");

        OntProperty journalHasAreas = model.createOntProperty( constants.BASE_URI.concat("journal_has_areas"));
        journalHasAreas.addDomain( journal );
        journalHasAreas.addRange( areas );
        journalHasAreas.addLabel( "Journal has areas","en");

        OntProperty paperHasPublication = model.createOntProperty( constants.BASE_URI.concat("paper_has_publication"));
        paperHasPublication.addDomain( paper );
        paperHasPublication.addRange( publications );
        paperHasPublication.addLabel( "Paper has publications","en");

        OntProperty publicationHasConfProceedings = model.createOntProperty( constants.BASE_URI.concat("publication_has_conference_proceeding"));
        publicationHasConfProceedings.addDomain( publications );
        publicationHasConfProceedings.addRange( conferenceProceedings );
        publicationHasConfProceedings.addLabel( "Publication has conference proceedings","en");

        OntProperty publicationHasJourVolume = model.createOntProperty( constants.BASE_URI.concat("publication_has_journal_volume"));
        publicationHasJourVolume.addDomain( publications );
        publicationHasJourVolume.addRange( journalVolume );
        publicationHasJourVolume.addLabel( "Publication has journal volumes","en");
        
        model.write(System.out);
    }
}
