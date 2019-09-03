package com.tacobell.DiscountProgram;

import java.math.BigDecimal;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
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
			builder.add(ResourceFactory.newClassPathResource("discountRules.drl"), ResourceType.DRL);
			if (builder.hasErrors()) {
				throw new RuntimeException(builder.getErrors().toString());
			}

			KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
			knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());

			session = knowledgeBase.newStatefulKnowledgeSession();
			session.fireAllRules();
			// purchase > $15 and <= $25
			Purchase firstPurchase = new Purchase(new BigDecimal("16"), 1, false);
			FactHandle purchaseFact = session.insert(firstPurchase);
			session.fireAllRules();

			System.out.println("----------------");

			// purchase > $25
			firstPurchase = new Purchase(new BigDecimal("26"), 1, false);
			session.update(purchaseFact, firstPurchase);
			session.fireAllRules();
			System.out.println("----------------");

			// combo purchase containing 3 tacos and a drink
			firstPurchase = new Purchase(new BigDecimal("26"), 3, true);
			session.update(purchaseFact, firstPurchase);
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
