package com.perfios.bankaccount;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Bank Account Transaction" );
        
        StatefulKnowledgeSession session = null;

		try {
			KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			builder.add(ResourceFactory.newClassPathResource("rules.drl"), ResourceType.DRL);
			if (builder.hasErrors()) {
				throw new RuntimeException(builder.getErrors().toString());
			}

			KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
			knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());

			session = knowledgeBase.newStatefulKnowledgeSession();
			
			Account account = new Account(101,(float)1000.00);	
			session.insert(account);
			
			Transaction transaction = new Transaction(101, TransactionType.DEPOSIT,(float) 500.00);
			session.insert(transaction);
		    session.fireAllRules();
		    
		    Transaction transaction1 = new Transaction(101, TransactionType.WITHDRAWL,(float) 20000.00);
		    session.insert(transaction1);
		    session.fireAllRules();
		    
		    Transaction transaction2 = new Transaction(101, TransactionType.DEPOSIT,(float) 5000.00);
		    session.insert(transaction2);
		    session.fireAllRules();
		    
		    Transaction transaction3 = new Transaction(101, TransactionType.WITHDRAWL,(float) 2000.00);
		    session.insert(transaction3);
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
