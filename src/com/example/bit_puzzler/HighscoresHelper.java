package com.example.bit_puzzler;

import java.util.List;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.regions.Region;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;

public class HighscoresHelper {
	protected String nextToken;
	protected AmazonSimpleDBClient sdbClient;
	protected int count;
	
	
	public HighscoresHelper( ) {
        // Initial the SimpleDB Client.
        AWSCredentials credentials = new BasicAWSCredentials( Constants.ACCESS_KEY_ID, Constants.SECRET_KEY );
        this.sdbClient = new AmazonSimpleDBClient( credentials); 
        this.sdbClient.setRegion(Region.getRegion(Regions.US_WEST_2));
        this.nextToken = null;
		this.count = -1;
	}
	public synchronized String[][] getHighScores(int level) {
		String query =  "select player, score from HighScores"+level+" where score >= '0' order by score asc";
		SelectRequest selectRequest = new SelectRequest( query ).withConsistentRead( true );
		selectRequest.setNextToken( this.nextToken );
		
		SelectResult response = this.sdbClient.select( selectRequest );
		this.nextToken = response.getNextToken();
		
		return this.convertItemListToHighScoreList( response.getItems() );	
	}
	
	public String[][] convertItemListToHighScoreList(List<Item> items) {

		String[][] scores = new String[2][10];
		for(int i=0; i<10; i++){
			Item item=null;
			if(items!=null)
				item = items.get(i);
			if(item!=null){
			HighScore hs = this.convertItemToHighScore( item );
			scores[0][i]=hs.getPlayer();
			scores[1][i]=""+hs.getScore();
			}else{
				scores[0][i]="abc";
				scores[1][i]="100";
			}
		}
		return scores;
	}
	protected HighScore convertItemToHighScore( Item item ) {
		return new HighScore( this.getPlayerForItem( item ), this.getScoreForItem( item ) );
	}	
	protected String getPlayerForItem( Item item ) {
		return this.getStringValueForAttributeFromList( "player", item.getAttributes() );
	}
	protected int getScoreForItem( Item item ) {
		return this.getIntValueForAttributeFromList( "score", item.getAttributes() );
	}
	protected String getStringValueForAttributeFromList( String attributeName, List<Attribute> attributes ) {
		for ( Attribute attribute : attributes ) {
			if ( attribute.getName().equals( attributeName ) ) {
				return attribute.getValue();
			}
		}
		
		return "";		
	}	
	protected int getIntValueForAttributeFromList( String attributeName, List<Attribute> attributes ) {
		for ( Attribute attribute : attributes ) {
			if ( attribute.getName().equals( attributeName ) ) {
				return Integer.parseInt( attribute.getValue() );
			}
		}
		
		return 0;		
	}	
}