package com.perfios.firealarm;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
    @Test
	public void testRules() {

		StatefulKnowledgeSession session = null;

		try {
			KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			builder.add(ResourceFactory.newClassPathResource("killfire.drl"), ResourceType.DRL);
			if (builder.hasErrors()) {
				throw new RuntimeException(builder.getErrors().toString());
			}

			KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
			knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());

			session = knowledgeBase.newStatefulKnowledgeSession();
//			session.fireAllRules();
			
//		    Alarm alarm = new Alarm();
//		    session.insert(alarm);
//		    session.fireAllRules();
//		    
		    Room bedroom = new Room("bedroom");
		    session.insert(bedroom);
		    session.fireAllRules();
		    Fire fireInBedRoom = new Fire(bedroom);
		    session.insert(fireInBedRoom);
		    session.fireAllRules();
		    
		    Sprinkler sprinklerBedRoom = new Sprinkler(bedroom, true);
		    session.insert(sprinklerBedRoom);
		    session.fireAllRules();
		    
		    Room kitchen = new Room("kitchen");
		    session.insert(kitchen);
		    Sprinkler sprinklerKitchen = new Sprinkler(kitchen, true);
		    session.insert(sprinklerKitchen);
		    session.fireAllRules();


		} catch(Throwable t) {
			t.printStackTrace();
		} finally {
			if (session != null) {
				session.dispose();
			}
		}
	}
}
